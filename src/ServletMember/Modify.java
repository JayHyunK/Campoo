package ServletMember;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DTO.Member;
import Database.database;

@WebServlet("/ModifyInfo")
public class Modify extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dis=request.getRequestDispatcher("/Web-Pages/Member/MemberModify.jsp");
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
			
			sql = "UPDATE MEMBER SET PASSWORD='"+user.getPw()+"'"
				+ ", NAME='"+user.getName()+"'"
				+ ", BIRTHDAY='"+user.getBirth()+"'"
				+ ", EMAIL='"+user.getEmail()+"'"
				+ ", GENDER='"+user.getGender()+"'"
				+ "WHERE ID='"+user.getId()+"';";
			System.out.println("modify info: "+sql);
			stmt.execute(sql);
			
			// USER 닉네임 변경시 글 및 댓글에 전체 변경 내용 적용
			// 매번 호출시 ID 에 맞는 네임 받아오는 방식으로 구축하려다가 DB 커넥션이 쓸데 없이 자주 일어남을 알게됨
			// 이에 변경시에 전체 적용 방향으로 설정 
			sql = "UPDATE PARAGRAPH SET NAME='"+user.getName()+"'"
				+ "WHERE ID='"+user.getId()+"';";
			System.out.println("modify name 1: "+sql);
			stmt.execute(sql);
			
			sql = "UPDATE COMMENT SET NAME='"+user.getName()+"'"
					+ "WHERE ID='"+user.getId()+"';";
			System.out.println("modify name 2: "+sql);
			stmt.execute(sql);
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
		RequestDispatcher dis=request.getRequestDispatcher("/Campoo");
		dis.forward(request, response);
	}
}
