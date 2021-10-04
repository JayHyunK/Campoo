package ServletParagraph;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DTO.Paragraph;
import Database.database;


@WebServlet("/WriteParagraph")
public class Write extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String category=request.getParameter("category");
		request.setAttribute("category", category);
		RequestDispatcher dis=request.getRequestDispatcher("/Web-Pages/Paragraph/ParagraphWrite.jsp");
		dis.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String category=request.getParameter("category");
		
		Connection conn = null;
		Statement stmt = null;
		database db = database.getInstance();
		
		Paragraph para = new Paragraph();
		
		try {
			para.setCategory(category);
			para.setId(request.getParameter("id"));
			para.setName(request.getParameter("name"));
			para.setTitle(request.getParameter("title"));
			para.setContent(request.getParameter("content"));
			para.setFile(request.getParameter("file"));
			para.setTag(request.getParameter("tag"));
			para.setArea(request.getParameter("area"));
			para.setVisible(DTO.Paragraph.Visible.VISIBLE.toString());
			
			// DB 연결
			conn = db.getConnection();
			stmt = conn.createStatement();
			String sql;
			
			// SQL 진행, 기입 정보중 생일을 String 타입으로 변경
			SimpleDateFormat format = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss", Locale.KOREA);
			String date = format.format (new Date());// == System.currentTimeMillis()
			para.setDate(date);
			
			sql = "INSERT INTO MEMBER (CATEGORY, ID, NAME, DATE, TITLE, CONTENT, FILE, TAG, AREA, VISIBLE) VALUES (";
			sql += "'"+para.getCategory()+"', ";
			sql += "'"+para.getId()+"', ";
			sql += "'"+para.getName()+"', ";
			sql += "'"+para.getDate()+"', ";
			sql += "'"+para.getTitle()+"', ";
			sql += "'"+para.getContent()+"', ";
			sql += "'"+para.getFile()+"', ";
			sql += "'"+para.getTag()+"', ";
			sql += "'"+para.getArea()+"', ";
			sql += "'"+para.getVisible()+"');";
			
			System.out.println("register: "+sql);
			stmt.execute(sql);
		}
		catch(Exception e) {
			System.out.println("Write Servlet Error: "+e);
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
		RequestDispatcher dis=request.getRequestDispatcher("/Web-Pages/Paragraph/ParagraphList?categoy="+category);
		dis.forward(request, response);	
	}
}
