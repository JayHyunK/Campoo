<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="Database.database" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>회원 가입</title>
	</head>
	<body>
		<div id="wrap">
			<jsp:include page="/Web-Pages/Layout/Layout-Header.jsp"></jsp:include>

			<div id="wbody">
				<div id="register">
					<div>
						<img src="./contentimage/main-logo-Text2.png" class="nomalimg">
					</div>
					<br>
					<form method="post" action="Register" id="checkform" name="frm" onsubmit="return CheckForm()">
						<div id="regiid">
							<input type="text" name="id" placeholder="아이디" class="inputsize" id="FormId"> 
							<input type="button" id="checkidbtn" value="중복 체크">
						</div>
						<input type="password" name="pw" placeholder="비밀번호" class="inputsize" class="inputsize" id="FormPw"> 
						<input type="password" name="pwCheck" placeholder="비밀번호 확인" class="inputsize" id="FormPwC">
						<hr class="shortLine">
						
						<input type="text" name="name" placeholder="이름을 입력하세요." class="inputsize">
						<addr title="생일을 입력해주세요"><input type="date" name="birth" class="inputsize"></addr>
						<input type="text" name="email" placeholder="이메일을 입력하세요." class="inputsize" id="FormEmail">
						<hr class="shortLine">
						남
						<input type="radio" name="gender" value="male" checked>
						여
						<input type="radio" name="gender" value="female">
						그외
						<input type="radio" name="gender" value="others">
						<br>
						
						<input type="submit" value="가입하기" class="input-submit">
					</form>
					<br>
				</div>
			</div>
			<jsp:include page="/Web-Pages/Layout/Layout-Footer.jsp"></jsp:include>
		</div>
		<script>
	//		
			let checkidbtn = document.getElementById("checkidbtn");
			let checkform = document.getElementById("checkform");
			
			checkidbtn.addEventListener("click", function check(){
				if(document.frm.userId.value==""){//frm 네임 안네 유저 아이디 네임의 밸류 
					alert("아이디를 입력해주세요.");
					document.frm.userId.focus();
					return
				}
				else{
					//AJAX로 중복확인
					
				}
				
			});
			
			window.onkeydown = function() { // F5 막음, 잘못 눌러서 데이터 안날아가게
				var kcode = event.keyCode;
				if(kcode == 116) {
					event.returnValue = false;
				}
			}
			
			if(frm.userName.value="null"){
				frm.userName.value="";
			}
			
		// 적합성 검증 
			
		    function CheckForm(){
			
		    	let CheckFormId = /^[a-z]{1}[a-z0-9]{5,19}$/;
				let CheckFormPassword = /^[A-Za-z0-9]{8,24}$/;
				let CheckFormEmail = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
				let FormId = document.getElementById("FormId");
				let FormPw = document.getElementById("FormPw");
				let FormPwC = document.getElementById("FormPwC");
				let FormEmail = document.getElementById("FormEmail");
				
				
			    if(!check(CheckFormId, FormId, "아이디는 6~20자의 영문 소문자, 숫자로만 입력이 가능합니다.")){
			    	return false;
			    }
			    if(!check(CheckFormPassword, FormPw, "비밀번호는 8~24자의 영문 대소문자와 숫자로 입력해주세요.")){
			    	return false;
			    }
			    if(FormPw.value != FormPwC.value){
			    	alert("입력하신 비밀번호와 비밀번호 확인이 다릅니다. 다시 확인해 주세요.");
			    	frm.userPwCheck.value = "";
			    	frm.userPwCheck.focus();
			        return false;
			    }
			    if(FormEmail.value==""||FormEmail.value.length==0){
			    	alert("이메일을 입력해 주세요");
			    	FormEmail.focus();
			        return false;
			    }
			    if(!check(CheckFormEmail, FormEmail, "적합하지 않은 이메일 형식입니다.")) {
			    	return false;
			   	}
			  	if(frm.userName.value==""){
			  		alert("이름을 입력해 주세요");
			  		frm.userName.focus();
			        return false;
			  	}
			  	if(frm.pwQuestion.value=="비밀번호 찾기 질문"){
			  		alert("비밀번호 찾기 질문을 선택해주세요.");
			        return false;
			  	}
			  	if(frm.pwAnswer.value==""){
			  		alert("비밀번호 찾기 답을 입력해주세요.");
			        return false;
			  	}
		    }
		function check(constraint, element, message){
			if(constraint.test(element.value)) {
				return true;
			}
		    alert(message);
		    element.value = "";
		    element.focus();
		}
		</script>
	</body>
</html>
