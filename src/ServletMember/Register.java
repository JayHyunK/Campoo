package ServletMember;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import DTO.Member;
import Database.database;


@WebServlet("/Register")
public class Register extends HttpServlet {
	//GET으로 들어오면 회원 가입 페이지. POST는 가입 진행
	
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dis=request.getRequestDispatcher("/Web-Pages/Member/MemberRegister.jsp");
		dis.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		Statement stmt = null;
		database db = database.getInstance();
		
		Member user = new Member();
		
		try {
			// Request 값 
			request.setCharacterEncoding("utf-8");
			
			user.setId(request.getParameter("id"));
			user.setPw(request.getParameter("pw"));
			user.setName(request.getParameter("name"));
			user.setBirth(request.getParameter("birth"));
			user.setEmail(request.getParameter("email"));
			user.setGender(request.getParameter("gender"));
			
			// DB 연결
			conn = db.getConnection();
			stmt = conn.createStatement();
			String sql;
			
			// SQL 진행, 기입 정보중 생일을 String 타입으로 변경
			SimpleDateFormat format = new SimpleDateFormat ( "yyyy-MM-dd");
			String userDate = format.format (new Date());// == System.currentTimeMillis()
			
			sql = "INSERT INTO MEMBER (ID, PASSWORD, NAME, BIRTHDAY, EMAIL, GENDER, DATE, GRADE) VALUES (";
			sql += "'"+user.getId()+"', ";
			sql += "'"+user.getPw()+"', ";
			sql += "'"+user.getName()+"', ";
			sql += "'"+user.getBirth()+"', ";
			sql += "'"+user.getEmail()+"', ";
			sql += "'"+user.getGender()+"', ";
			sql += "'"+userDate+"', ";
			sql += "'"+user.getGrade()+"');";// Grade는 기본 Memeber. 
			
			System.out.println("register: "+sql);
			stmt.execute(sql);
			
			// 가입 완료 후, <완료 메시지 보이고 이동>
			PrintWriter out = response.getWriter();
			out.println("<script>alert('가입이 완료되었습니다.');</script>");
		}
		catch(Exception e) {
			System.out.print("Servler Registe Error: "+e);
		}
		finally {
			try{
				if(stmt!=null){
					stmt.close();
				}
				if(conn!=null){
					conn.close();
				}
			}
			catch(Exception e){
				System.out.println("Conn.Close 오류: "+e);
			}
		}
		RequestDispatcher dis=request.getRequestDispatcher("/");
		dis.forward(request, response);
	}

}
