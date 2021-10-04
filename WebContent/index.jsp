<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="Database.database" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>CAMPOO</title>
	</head>
	<body>
		<div id="wrap">
			<jsp:include page="/Web-Pages/Layout/Layout-Header-Index.jsp"></jsp:include>
			<jsp:include page="/Web-Pages/Layout/Layout-CampPopular.jsp"></jsp:include>
			<jsp:include page="/Web-Pages/Layout/Layout-TourPopular.jsp"></jsp:include>
			<jsp:include page="/Web-Pages/Layout/Layout-PicPopular.jsp"></jsp:include>
			<jsp:include page="/Web-Pages/Layout/Layout-Paragraph.jsp"></jsp:include>
			<jsp:include page="/Web-Pages/Layout/Layout-Footer.jsp"></jsp:include>
		</div>
		<a href="Register">임시 회원가입</a>
		<a href="Login">임시 로그인</a>
		<a href="Logout">임시 로그아웃</a>
		<a href="WriteParagraph">임시 글쓰기</a>
		<a href="ListParagraph">임시 글목록</a>
		
	</body>
</html>

<%
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null; 
	database db = database.getInstance();
	
	try{
		conn = db.getConnection();
		stmt = conn.createStatement();
		String sql;
		sql = "SET NAMES UTF8";
		stmt.execute(sql);
		
		sql = "SELECT * FROM CAMPSITE WHERE 소재지지번주소 LIKE '%강원%' LIMIT 10;";// 컬럼명에 '' 가 들어가면 인식 못한다.
		rs = stmt.executeQuery(sql);
		System.out.println(rs.getRow());
		String str = "";
		while(rs.next()){
			str += rs.getString("야영캠핑장명")+" / ";
			System.out.println(str);
		}
		out.print(str);
	}
	catch(Exception e){
		System.out.println("index.jsp DB Connet Error : "+e);
	}
	finally{
		try {
			if(rs!=null){
				rs.close();
			}
			if(stmt!=null){
				stmt.close();
			}
			if(conn!=null){
				conn.close();
			}
		}
		catch(Exception e) {
			System.out.println("Close Error: index.jsp >> ** "+e);
		}
	}
%>