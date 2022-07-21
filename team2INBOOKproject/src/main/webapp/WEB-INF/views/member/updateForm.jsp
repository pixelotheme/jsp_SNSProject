<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 정보 수정</title>
<style type="text/css">

</style>
<script type="text/javascript">

$(function(){ $('.pw').focusout(function () {
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
		<h2>내 정보 수정</h2>
		<form action="update.do" method="post" enctype="multipart/form-data" id = "updateForm" >
			<table class="table">
				<tr>
					<th>아이디</th>
<!--  					maxlength : 입력 최대 길이 제한, placeholder:값이 비어 있으면 배경으로 나타나는 글자, required:필수입력 autocomplete:자동완성기능 -->
					<td><input name="id" maxlength="20" placeholder="아이디입력"
					 title="필수입력" autocomplete="off" id="id" readonly="readonly" value = ${vo.id }>
						<font id="idCheckDIV" size="2" ></font></td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td><input name="pw" maxlength="20" required="required"
						type="password" class="pw" id=pw1 value = ${vo.pw }></td>
				</tr>
				<tr> 
					<th>비밀번호 확인</th> 
					<td><input name="pw" maxlength="20" required="required" 
						type="password" class="pw" id=pw2 ><font id="pwcheck" 
 						size="2"></font></td> 
 				</tr> 
				<tr>
					<th>이름</th>
					<td><input name="name"  maxlength="10" value = ${vo.name }></td>
				</tr>
				<tr>
					<th>성별</th>
					<td><input name="gender" type="radio" value="남자"
						checked="checked"> 남자 <input name="gender" type="radio"
						value="여자"> 여자</td>
				</tr>
				<tr>
					<th>생년월일</th>
					<td><input name="births" maxlength="4"style="width: 40px;" > 년 
					<input name="births" required="required" maxlength="2" style="width: 40px;" type="number" min="1" max="12" value="1"> 월 
					<input name="births" maxlength="2" style="width: 40px;" > 일</td>
				</tr>
				<tr>
					<th>연락처</th>
					<td><input name="tel" maxlength="13"  value = ${vo.tel }></td>
				</tr>
				<tr>
					<th>이메일</th>
					<td><input name="email" value = ${vo.email }></td>
				</tr>
				<tr>
					<th>사진</th>
					<td><input name="photoFile" type="file" value = ${vo.photo }></td>
				</tr>
				<tr>
					<td colspan="3">
						<!-- button type - submit:데이터 전달, reset:처음상태, button:동작이 없다. -->
						<button type="submit">수정</button>
						<button type="reset">새로고침</button>
						<button type="button" onclick="location='/member/mypage.do">취소</button>
					</td>
				</tr>
			</table>
		</form>
	</div>
</html>