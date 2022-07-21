<%@ page language="java" contentType="text/html; charset=UTF-8" isErrorPage="true"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>서버처리 오류</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<body>

<div class="container">
	<h2>에러 페이지</h2>
	<div class="panel panel-default">
	  <div class="panel-heading">서버 실행중 오류 발생</div>
	  <div class="panel-body">
	  <!-- exception을 파라미터로 사용하려면 isErrorPage="true" 로 선언이 되어있어야 예외객체 사용가능 -->
	  	<%=exception.getMessage() %>
		요청하신 페이지 처리 중 서버 오류가 발생되었습니다.
		<p>
		<a href="/main/main.do" class="btn btn-default">메인으로 이동</a>
	  </div>
	</div>
</div>
</body>
</html>