package ServletMember;

import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DTO.Member;
import Database.database;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dis=request.getRequestDispatcher("/Web-Pages/Member/MemberLogin.jsp");
		dis.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		database db = database.getInstance();
		HttpSession session = request.getSession();
		
		
		Member user = new Member();
		
		try {
			
			String sql = null;
			String dbId = null;
			String dbPw = null;
			String dbName = null;
			String dbStatement = null;
			String dbStopDay = null;
			String dbStopTerm = null;
			
			String userKey;
			String userStatement = "NONE";
		
			request.setCharacterEncoding("utf-8");
			
			user.setId(request.getParameter("userId"));
			user.setPw(request.getParameter("userPw"));
			
			conn = db.getConnection();
			stmt = conn.createStatement();
			
			sql = "SELECT * FROM MEMBER WHERE ID ='"
					+user.getId()+"';";
			System.out.println(sql);
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()){ //rs.next()가 없으면 Before start of result set ++ 한줄이라 if 문으로 처리도 가능
				dbId=rs.getString("ID");
				dbPw=rs.getString("PASSWORD");
				dbName=rs.getString("NAME");
				dbStatement=rs.getString("STATEMENT");
				dbStopDay=rs.getString("STOPDAY");
				dbStopTerm=rs.getString("STOPTERM");
			}
			
			// 유저 상태가 BREAK 면 리턴, 이외에 스탑이면 날짜 값 비교함 
			if(dbStatement.equals("BREAK")) {
				return;
			}
			else if(dbStatement.equals("STOP")) {
				SimpleDateFormat Date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
				String today = Date.format(new Date());
				String nowTime = today.replace("-", "").replace(" ", "").replace(":", "");
				
				int term = Integer.parseInt(dbStopTerm);//(day+'000000')
				int nowDay = Integer.parseInt(nowTime);
				int stopDay = Integer.parseInt(dbStopDay)+term;
				
				if(nowDay<stopDay) {
					userStatement = dbStatement;
				}
				else {
					
				}
			}
			
			if(user.getId().equals(dbId)&&user.getPw().equals(dbPw)){
				userKey = user.getId();
				session.setAttribute("LoginStatement", "Success");
				session.setAttribute("UserStatement", userStatement);
				session.setAttribute("Userkey", userKey);
				session.setAttribute("UserName", dbName);
			}
			else if(user.getId().equals(dbId)==false){
				session.setAttribute("LoginStatement", "NotFound");

			}
			else if(user.getId().equals(dbId)==true&&user.getPw().equals(dbPw)==false){
				session.setAttribute("LoginStatement", "Failed");
			}
		}
		catch(Exception e) {
			
		}
		finally {
			try{
				if(rs!=null) {
					rs.close();
				}
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
