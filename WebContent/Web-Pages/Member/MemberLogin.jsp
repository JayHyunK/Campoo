<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="Database.database" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>로그인</title>
	</head>
	<body>
		<div id="wrap">
			<jsp:include page="/Web-Pages/Layout/Layout-Header.jsp"></jsp:include>
			<div id="wbody">
				<div id="login">
					<form method="post" action="Login" name="frm" onsubmit="return check()">
						<input type="text" name="userId" placeholder="아이디" class="inputsize">
						<input type="password" name="userPw" placeholder="비밀번호" class="inputsize"> <!-- 눈모양 추가 -->
						<input type="hidden" name="flag" value="login" class="inputsize">
						<input type="submit" value="로그인" class="input-submit">
						<br>
					</form>
					<div id="loginbottom">
						<a href="Member-Register.jsp">회원 가입</a>
						<a href="Member-FindId.jsp">아이디 찾기</a>
						<a href="Member-FindPw.jsp">비밀번호 찾기</a>
					</div>
				</div>
			</div>
			<jsp:include page="/Web-Pages/Layout/Layout-Footer.jsp"></jsp:include>
		</div>
		<script>
			function check(){
				if(document.frm.userId.value==0){
					alert("아이디를 입력하세요.");
					frm.userId.focus();
					return false
				}
				if(document.frm.userPw.value==""){
					alert("비밀번호를 입력하세요.");
					frm.userPw.focus();
					return false
				}
				return true;
			}
		</script>
	</body>
</html>
