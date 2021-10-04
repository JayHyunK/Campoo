package ServletParagraph;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ListParagraph")
public class List extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String category = null;
		String page = null;
		String show = null;
		String search = null;
		String range = null;
		
		try {
			category = request.getParameter("category");
			page = request.getParameter("page");
			show = request.getParameter("show");
			search = request.getParameter("search");
			range = request.getParameter("range");
		}
		catch(Exception e) {
			
		}
		
		if(page==null) {
			page="1";
		}
		if(show==null) {
			show="10";
		}
		if(search==null) {
			search="";
		}
		if(range==null) {
			range="content+title";
		}
		
		request.setAttribute("page", page);
		request.setAttribute("show", show);
		request.setAttribute("search", search);
		request.setAttribute("range", range);
		request.setAttribute("category", category);
		
		RequestDispatcher dis=request.getRequestDispatcher("/Web-Pages/Paragraph/ParagraphList.jsp");
		dis.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}
}
