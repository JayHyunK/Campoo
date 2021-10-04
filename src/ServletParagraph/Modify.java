package ServletParagraph;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DTO.Paragraph;
import Database.database;

@WebServlet("/ModifyParagraph")
public class Modify extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String category=request.getParameter("category");
		request.setAttribute("category", category);
		RequestDispatcher dis=request.getRequestDispatcher("/Web-Pages/Paragraph/ParagraphModify.jsp");
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
			// Request 값 
			request.setCharacterEncoding("utf-8");
			
			para.setCategory(category);
			para.setId(request.getParameter("id"));	
			para.setName(request.getParameter("NAME"));
			para.setTitle(request.getParameter("title"));
			para.setContent(request.getParameter("content"));
			para.setFile(request.getParameter("file"));
			para.setTag(request.getParameter("tag"));
			para.setArea(request.getParameter("area"));
			
			// DB 연결
			conn = db.getConnection();
			stmt = conn.createStatement();
			String sql;

			sql = "UPDATE PARAGRAPH SET TITLE='"+para.getTitle()+"'"
				+ ", NAME='"+para.getName()+"'"
				+ ", CONTENT='"+para.getContent()+"'"
				+ ", FILE='"+para.getFile()+"'"
				+ ", TAG='"+para.getTag()+"'"
				+ ", AREA='"+para.getArea()+"'"
				+ "WHERE NUM='"+para.getNum()+"';";
			System.out.println("register: "+sql);
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
		RequestDispatcher dis=request.getRequestDispatcher("/ParagraphList?categoy="+category);
		dis.forward(request, response);	
	}

}
