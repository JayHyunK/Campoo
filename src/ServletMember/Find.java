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
		// SSL ó���� �ȵ� ��� ���� �߼��� ������, �ݵ�� ó�� ������ ��

		request.setCharacterEncoding("utf-8");

	/*	======================================================
		==================== SQL ���� =====================
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
				System.out.println("Conn.Close ����: "+e);
			}
		}
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/xml");
		response.setHeader("Cache-control", "no-cache");
		out.println("<response>");
		
		// ȸ�� ���� == Ż��ÿ� Ż���� ���̵� �ȳ�. 
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
		// �ڵ� ��ġ�� �� > ���ο� ������ �̵��� �����ϵ��� ���� Session üũ, Session ��ġ�ϴ� ���� ������ ���� ��Ŵ 
	}	
	
	// Email �߼� 
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
		============== ���� �߼� ���� ������ ===================
		===================================================*/
		String mailSendId = "programmer.jonghyun";
		
		String subjectEmail = "["+code+"] Campoo �����ڵ��Դϴ�.";
		String content = "�ȳ��ϼ���? '"+name+"'��, \n���� ���� ������ �������Դϴ�. '"+name+"'���� �����ڵ�� "+code+"�Դϴ�.\n30�� �ȿ� ������ �Ϸ����ּ���.\n\nCampoo";
		
		try{
			MimeMessage msg = new MimeMessage(session);
			
			msg.setFrom(new InternetAddress(mailSendId, "Campoo Team"));
			//Address from = new InternetAddress(fromEmail); 
			//ms.setFrom(from); // ������ ���
			
			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(address));
			//Address to = new InternetAddress(dbemail);
			
			msg.setSubject(subjectEmail); // ����
			msg.setText(content);// ����
			
			Transport.send(msg);
			System.out.println("�̸��� ���� ����");
		}
		catch(Exception e){
			System.out.println("Mail ������ ����"+e);
		    flag = 1;
		}
		return flag;
	}
	
	private int getCodeRandom() {
		int code = (int)Math.random()*100000;
		return code;
	}
}
