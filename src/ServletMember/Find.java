package ServletMember;

import java.io.*;
import java.sql.*;
import java.util.*;

import javax.mail.internet.*;
import javax.mail.*;
import javax.mail.Authenticator;
import javax.mail.Session;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import DTO.Member;
import Database.database;
import Mail.*;

@WebServlet("/FindPassword")
public class Find extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dis=request.getRequestDispatcher("/Web-Pages/Member/MemberFindPw.jsp");
		dis.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// SSL 처리가 안된 경우 메일 발송이 실패함, 반드시 처리 진행할 것

		request.setCharacterEncoding("utf-8");

	/*	======================================================
		==================== SQL 설정 =====================
		====================================================== */
		 
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null; 
		
		database db = database.getInstance();
		
		Member user = new Member();
		int code = getCodeRandom();
		int flag = 2;
		
		try {
			conn = db.getConnection();
			stmt = conn.createStatement();
			
			String sql;
			
			sql = "SELECT EMAIL, NAME, STATEMENT FROM MEMBER WHERE ID ='"
					+user.getId()+"';";
			rs = stmt.executeQuery(sql);
			
			while(rs.next()){ 
				user.setEmail(rs.getString("EMAIL"));
				user.setName(rs.getString("NAME"));
				user.setStatement(rs.getString("Statement"));
			}
		}
		catch(Exception e) {
			System.out.println("FindServlet Error: "+e);
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
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/xml");
		response.setHeader("Cache-control", "no-cache");
		out.println("<response>");
		
		// 회원 상태 == 탈퇴시에 탈퇴한 아이디 안내. 
		if(user.getStatement().equals(DTO.Member.STATEMENT.BREAK.toString())){
			out.println("<send>break</send>");
		}
		else if(user.getId().equals("null")||user.getId()==null) {
			out.println("<send>notfound</send>");
		}
		else {
			flag = sendEmail(user.getEmail(), user.getName(), code);
			// 1 == FAIL // 0 = SUCCESS
			if(flag==1) {
				out.println("<send>success</send>");
				out.println("<code>"+code+"</code>");
			}
			else {
				out.println("<send>fail</send>");
			}
		}
		out.println("</response>");
		out.close();
		// 코드 일치할 시 > 새로운 페이지 이동후 수정하도록 유도 Session 체크, Session 일치하는 정보 없으면 만료 시킴 
	}	
	
	// Email 발송 
	private int sendEmail(String address, String name, int code) {
		int flag = 0;
		//String host = "smtp.naver.com";
		String host = "smtp.gmail.com";
		Properties prop = new Properties(); 
		
		prop.put("mail.smtp.host", host); 
		prop.put("mail.smtp.port", 587); 
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.auth", "true"); 
		prop.put("mail.smtp.ssl.enable", "false"); //ssl : true,
		
		
		Authenticator auth = new MailAuth();	
		Session session = Session.getDefaultInstance(prop, auth);
		
	
	/*	==================================================
		============== 메일 발송 정보 데이터 ===================
		===================================================*/
		String mailSendId = "programmer.jonghyun";
		
		String subjectEmail = "["+code+"] Campoo 인증코드입니다.";
		String content = "안녕하세요? '"+name+"'님, \n현재 보안 인증을 진행중입니다. '"+name+"'님의 인증코드는 "+code+"입니다.\n30분 안에 인증을 완료해주세요.\n\nCampoo";
		
		try{
			MimeMessage msg = new MimeMessage(session);
			
			msg.setFrom(new InternetAddress(mailSendId, "Campoo Team"));
			//Address from = new InternetAddress(fromEmail); 
			//ms.setFrom(from); // 보내는 사람
			
			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(address));
			//Address to = new InternetAddress(dbemail);
			
			msg.setSubject(subjectEmail); // 제목
			msg.setText(content);// 내용
			
			Transport.send(msg);
			System.out.println("이메일 전송 성공");
		}
		catch(Exception e){
			System.out.println("Mail 보내기 오류"+e);
		    flag = 1;
		}
		return flag;
	}
	
	private int getCodeRandom() {
		int code = (int)Math.random()*100000;
		return code;
	}
}
