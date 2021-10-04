package ServletComment;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DTO.Comment;
import DTO.Paragraph;
import Database.database;

@WebServlet("/ModifyComment")
public class ModifyComment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		Connection conn = null;
		Statement stmt = null;
		database db = database.getInstance();
		
		Comment com = new Comment();
		Paragraph para = new Paragraph();
		
		try {
			// DB 연결
			conn = db.getConnection();
			stmt = conn.createStatement();
			String sql;
			
			para.setCategory(request.getParameter("category"));
			para.setNum(request.getParameter("paragraphNum"));
			
			com.setParagraphNum(para.getName());
			com.setNum(request.getParameter("commentNum"));
			com.setContent(request.getParameter("commentContent"));
			
			sql = "UPDATE COMMENT SET CONTENT='"
				+ com.getContent() + "' "
				+ "WHERE NUM='"
				+ com.getNum() + "';";
			
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
		RequestDispatcher dis=request.getRequestDispatcher("/Web-Pages/Paragraph/ParagraphList?categoy="+para.getCategory()+"&num="+para.getNum());
		dis.forward(request, response);		
	}
}
