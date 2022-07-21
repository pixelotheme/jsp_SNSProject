<%@ page language="java" contentType="text/html; charset=UTF-8" isErrorPage="true"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>웹짱 : 로그인 오류</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>


</head>
<body>
<div class="container" style="text-align: center;">
	<h2>로그인 정보 오류 페이지</h2>
<!-- 글정렬 -->
	<div style="text-align: center; padding: 10px">
		<i class="glyphicon glyphicon-exclamation-sign" style="font-size: 10em; color: red;"></i>
	</div>
	<div style="text-align: center;padding: 10px;">
		아이디나 비밀번호를 확인해 주세요.
		<p> 로그인을 다시 시도하시려면 아래의 버튼을 통해 로그인을 해주세요
		
	</div>
	<div style="text-align: center;">
		<a href="/member/loginForm.do" class="btn btn-default">로그인</a>
	</div>
</div>
</body>
</html>