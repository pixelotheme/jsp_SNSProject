<%@ page language="java" contentType="text/html; charset=UTF-8" isErrorPage="true"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>요청 페이지 없음</title>
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
	<h2>요청 페이지 없음</h2>
	<div class="panel panel-default">
	  <div class="panel-heading">요청한 페이지가 존재하지 않습니다.</div>
	  <div class="panel-body">
	  <!-- exception을 파라미터로 사용하려면 isErrorPage="true" 로 선언이 되어있어야 예외객체 사용가능 -->
<%-- 	  	<%=exception.getMessage() %> --%>
		요청한 페이지가 존재하지 않습니다.<br/><br/>
		다시 한번 시도해 주세요. 계속 오류가 나면 전산담당자 홍길동(hong@naver.com)에게 연락 주세요
		<br/><br/>
		<a href="/main/main.do" class="btn btn-default">메인으로 이동</a>
	  </div>
	</div>
</div>
</body>
</html>