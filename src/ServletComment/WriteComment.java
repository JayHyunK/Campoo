package ServletComment;

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

import DTO.Comment;
import DTO.Paragraph;
import Database.database;


@WebServlet("/WriteComment")
public class WriteComment extends HttpServlet {
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
			
			com.setParentNum(request.getParameter("commentNum"));
			com.setId(request.getParameter("commentId"));
			com.setName(request.getParameter("commentName"));
			com.setContent(request.getParameter("commentCotent"));
			com.setVisible(DTO.Comment.Visible.VISIBLE.toString());
			
			if(com.getParentNum().equals(null)) {
				com.setParentNum("NONE");
			}
			
			// SQL 진행, 기입 정보중 생일을 String 타입으로 변경
			SimpleDateFormat format = new SimpleDateFormat ( "yyyy.MM.dd HH:mm:ss", Locale.KOREA);
			String date = format.format (new Date());// == System.currentTimeMillis()
			com.setDate(date);
			
			sql = "INSERT INTO COMMENT (PARAGRAPHNUM, PARENTNUM, ID, NAME, DATE, CONTENT, VISIBLE) VALUES (";
			sql += "'"+com.getParagraphNum()+"', ";
			sql += "'"+com.getParentNum()+"', ";
			sql += "'"+com.getId()+"', ";
			sql += "'"+com.getName()+"', ";
			sql += "'"+com.getDate()+"', ";
			sql += "'"+com.getContent()+"', ";
			sql += "'"+com.getVisible()+"');";
			
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
		RequestDispatcher dis=request.getRequestDispatcher("/ParagraphList?categoy="+para.getCategory()+"&num="+para.getNum());
		dis.forward(request, response);		
	}
}
