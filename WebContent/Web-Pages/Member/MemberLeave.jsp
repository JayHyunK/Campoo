<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="EUC-KR">
		<title>Insert title here</title>
	</head>
	<body>
		<form method="post" action="Leave">
			<input type="button" value="취소하기" id="btn">
			<input type="submit" value="탈퇴하기" onclick="return checkLeave()">
		</form>
		<script>
			function checkLeave(){
				
				let suggest = confirm("탈퇴하시겠습니까?");
				
				let flag = false;
				
				if(suggest==true){
					flag = true;
				}
				
				return flag;
			}
			
			let btn = document.getElementById("btn");
			btn.addEventListener("click", function(){
				location.href="Campoo";
			});
		</script>
	</body>
</html>