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
			<input type="button" value="����ϱ�" id="btn">
			<input type="submit" value="Ż���ϱ�" onclick="return checkLeave()">
		</form>
		<script>
			function checkLeave(){
				
				let suggest = confirm("Ż���Ͻðڽ��ϱ�?");
				
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