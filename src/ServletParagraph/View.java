package ServletParagraph;

import java.io.IOException;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DTO.Paragraph;
import Database.database;
// GET 방식 
@WebServlet("/View")
public class View extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String category = request.getParameter("category");
		String num = request.getParameter("num");
		
		viewUp(num);
		
		request.setAttribute("category", category);
		request.setAttribute("num", num);
		RequestDispatcher dis=request.getRequestDispatcher("ParagraphView.jsp");
		dis.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void viewUp (String num) {
		Paragraph para = new Paragraph();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		database db = database.getInstance();
		
		try {
			conn = db.getConnection();
			stmt = conn.createStatement();
			para.setName(num);
			String sql;
			
			sql = "SELECT VIEW, DAYVIEW, WEEKVIEW, MONTHVIEW FROM PARAGRAPH WHERE NUM='"
				+ para.getNum() +"';";
			
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				para.setView(rs.getString("VIEW"));
				para.setDayView(rs.getString("DAYVIEW"));
				para.setWeekView(rs.getString("WEEKVIEW"));
				para.setMonthView(rs.getString("MONTHVIEW"));
			}
			
			int view = Integer.parseInt(para.getView())+1;
			int dayview = Integer.parseInt(para.getDayView())+1;
			int weekview = Integer.parseInt(para.getWeekView())+1;
			int monthview = Integer.parseInt(para.getMonthView())+1;
			
			para.setView(view+"");
			para.setDayView(dayview+"");
			para.setWeekView(weekview+"");
			para.setMonthView(monthview+"");

			sql = "UPDATE PARAGRAPH SET "
				+ "VIEW='"+para.getView()+"'"
				+ ", DAYVIEW='"+para.getDayView()+"'"
				+ ", WEEKVIEW='"+para.getWeekView()+"'"
				+ ", MONTHVIEW='"+para.getMonthView()+"'"
				+ "WHERE NUM='"+para.getNum()+"';";
			System.out.println("register: "+sql);
			stmt.execute(sql);
		}
		catch(Exception e) {
			System.out.print("Servler VIEWUP METHOD Error: "+e);
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
	}
}
