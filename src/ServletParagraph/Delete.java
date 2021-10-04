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

@WebServlet("/DeleteParagraph")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		Paragraph para = new Paragraph();
		para.setNum(request.getParameter("num"));
		
		String category = request.getParameter("category");
		String page = request.getParameter("page");
		String show = request.getParameter("show");
		String search = request.getParameter("search");
		String range = request.getParameter("range");
		
		if(page.equals(null)) {
			page="1";
		}
		if(show.equals(null)) {
			show="10";
		}
		if(search.equals(null)) {
			search="";
		}
		if(range.equals(null)) {
			range="content+title";
		}
		
		Connection conn = null;
		Statement stmt = null;
		database db = database.getInstance();

		try {
			String sql = null;
	
			conn = db.getConnection();
			stmt = conn.createStatement();
			
			sql = "UPDATE PARAGRAPH SET VISIBLE='"
				+DTO.Paragraph.Visible.HIDDEN.toString()+"' "
				+"WHERE NUM='"+para.getNum()+"';";
			System.out.println(sql);
			stmt.execute(sql);
		}
		catch(Exception e) {
			System.out.println("DELTE PARAGRAPH SERVLET ERROR: "+e);
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
				System.out.println("Conn.Close ¿À·ù: "+e);
			}
		}
		request.setAttribute("page", page);
		request.setAttribute("show", show);
		request.setAttribute("search", search);
		request.setAttribute("range", range);
		
		RequestDispatcher dis=request.getRequestDispatcher("/List?category="+category+"&page="+page+"&show="+show);
		dis.forward(request, response);
	}
}
