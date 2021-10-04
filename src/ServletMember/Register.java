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
	//GET���� ������ ȸ�� ���� ������. POST�� ���� ����
	
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
			// Request �� 
			request.setCharacterEncoding("utf-8");
			
			user.setId(request.getParameter("id"));
			user.setPw(request.getParameter("pw"));
			user.setName(request.getParameter("name"));
			user.setBirth(request.getParameter("birth"));
			user.setEmail(request.getParameter("email"));
			user.setGender(request.getParameter("gender"));
			
			// DB ����
			conn = db.getConnection();
			stmt = conn.createStatement();
			String sql;
			
			// SQL ����, ���� ������ ������ String Ÿ������ ����
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
			sql += "'"+user.getGrade()+"');";// Grade�� �⺻ Memeber. 
			
			System.out.println("register: "+sql);
			stmt.execute(sql);
			
			// ���� �Ϸ� ��, <�Ϸ� �޽��� ���̰� �̵�>
			PrintWriter out = response.getWriter();
			out.println("<script>alert('������ �Ϸ�Ǿ����ϴ�.');</script>");
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
				System.out.println("Conn.Close ����: "+e);
			}
		}
		RequestDispatcher dis=request.getRequestDispatcher("/");
		dis.forward(request, response);
	}

}
