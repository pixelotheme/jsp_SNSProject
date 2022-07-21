<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>INBOOK 회원가입</title>
<style type="text/css">
</style>
<script type="text/javascript">

// keyup 입력시 발생하는 이벤트
$(function(){	$("#id").keyup(function(){
	// 공백을 없애는법
	$("#id").val($("#id").val().trim());
	// 글자수가 4개이상일 경우에만 작동
	if($("#id").val().length >= 4 )
		// 4개이상일 경우 memberajx/idchck.do --> 중복화인 후에 jsp인 ajax/idCheck로 이동하여 설정한 알람 출력
		$("#idCheckDIV").load("/memberajax/idCheck.do?id=" + $("#id").val());
	else
		$("#idCheckDIV").html("<div class=\"alert alert-danger\">아이디는 4자 이상 입력하셔야 합니다.</div>");
});
		
	// 확인버튼을 누를때에만 작동
	$("#writeForm").submit(function(){
		// idmsg에 입력한 아이디값을 담음
		var idMsg = $("#idCheckDIV").text();
		// 담은 값에 가능한이라는 글자가 없다면 중복아이디라고 알려줌
		if(idMsg.indexOf("가능한") < 0){
			alert("중복된 아이디입니다 사용가능한 아이디를 입력해 주세요.");
			// 아이디쪽에 커서있음
			$("#id").focus();
			return false;
		}
		
	});
		// 비밀번호 확인 pw 와 pw2에 비밀번호를 비교하여 같으면 일치 같지않으면 불일치로 알람
		$('.pw').focusout(function () {
			//각각 값을 담는곳
	        var pwd1 = $("#pw1").val();
	        var pwd2 = $("#pw2").val();
	  		// 이부분은 아직 해석불가 어떻게 바꿔도 작동을 함
	        if ( pwd1 != '' && pwd2 == '' ) {
	            null;
	        }
			// 각 값이 비어있다면 확인	        
	        else if (pwd1 != "" || pwd2 != "") {
	            if (pwd1 == pwd2) {
	            	// 일치하면 아래의 설정한 font로 출력
	            	$('#pwcheck').html('비밀번호 일치함<br><br>');
	                $('#pwcheck').attr('color', '#199894b3');
	            } else {
	            	if (pw2 != null){
	            		// 동일함
	            	$('#pwcheck').html('비밀번호 일치하지 않음<br><br>');
	                $('#pwcheck').attr('color', '#f82a2aa3');
	            	$("#pw2").focus();

	            	}
	            };
	        }

	});

})
</script>
</head>
<body>
	<div class="container">
		<h2>회원가입</h2>
		<form action="write.do" method="post" enctype="multipart/form-data"
			id="writeForm">
			<table class="table">
				<tr>
					<th>아이디</th>
					<!-- maxlength : 입력 최대 길이 제한, placeholder:값이 비어 있으면 배경으로 나타나는 글자, required:필수입력 autocomplete:자동완성기능 -->
					<td><input name="id" maxlength="20" placeholder="아이디입력"
						required="required" title="필수입력" autocomplete="off" id="id">
						<font id="idCheckDIV" size="2"></font>
						</td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td><input name="pw" maxlength="20" required="required" type="password" class="pw" id=pw1></td>
				</tr>
				<tr>
					<th>비밀번호 확인</th>
					<td><input name="pw" maxlength="20" required="required" type="password" class="pw" id=pw2> 
						<font id="pwcheck" size="2"></font>
					</td>
				</tr>
				<tr>
					<th>이름</th>
					<td><input name="name" required="required" maxlength="10"></td>
				</tr>
				<tr>
					<th>성별</th>
					<td><input name="gender" type="radio" value="남자"
						checked="checked"> 남자 <input name="gender" type="radio"
						value="여자"> 여자</td>
				</tr>
				<tr>
					<th>생년월일</th>
					<td><input name="births" required="required" maxlength="4"
						style="width: 40px;"> 년 <input name="births"
						required="required" maxlength="2" style="width: 40px;"
						type="number" min="1" max="12" value="1"> 월 <input
						name="births" required="required" maxlength="2"
						style="width: 40px;"> 일</td>
				</tr>
				<tr>
					<th>연락처</th>
					<td><input name="tel" maxlength="13" required="required"></td>
				</tr>
				<tr>
					<th>이메일</th>
					<td><input name="email" required="required"></td>
				</tr>
				<tr>
					<th>사진</th>
					<td><input name="photoFile" type="file"></td>
				</tr>
				<tr>
					<td colspan="2">
						<!-- button type - submit:데이터 전달, reset:처음상태, button:동작이 없다. -->
						<button type="submit">가입</button>
						<button type="reset">새로고침</button>
						<button type="button" onclick="location='/main/main.do'">취소</button>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>