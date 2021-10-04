package ServletMember;

import java.io.IOException;
import java.io.PrintWriter;
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

@WebServlet("/Leave")
public class Leave extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	// 삭제
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dis=request.getRequestDispatcher("/Web-Pages/Member/MemberLeave.jsp");
		dis.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		Statement stmt = null;
		database db = database.getInstance();
		
		Member user = new Member();
		
		try {
			request.setCharacterEncoding("utf-8");
			
			user.setId(request.getParameter("userId"));
			user.setPw(request.getParameter("userPw"));
			user.setStatement(DTO.Member.STATEMENT.BREAK.toString());
			
			// DB 연결
			conn = db.getConnection();
			stmt = conn.createStatement();
			String sql;
			
			sql = "UPDATE MEMBER SET STATEMENT=";
			sql += "'"+user.getStatement()+"'";
			sql += " WHERE ID='"+user.getId()+"';";
			
			System.out.println("register: "+sql);
			stmt.execute(sql);
			
			// 탈퇴 완료 후, <완료 메시지 보이고 이동>
			PrintWriter out = response.getWriter();
			out.println("<script>alert('탈퇴가 완료되었습니다.');</script>");
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
