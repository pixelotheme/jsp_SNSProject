<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이 페이지</title>
<style type="text/css">
#aa {
 
}
#my {
  width: 300px;
  text-align: center;
}
#name{
	font-size: 33px;
	text-align:center;
}
#friends{
	margin-top : 200px;
	margin-right: 100px;
	padding-right: 100px;
}
#board{
	margin-top : 200px;
	
	margin-right: 100px;
	
	padding-right: 100px;
}
#a1 {
float: right;
}
#b {
float: left;
}
#c {
float: right;
}
#a1,#a2,#b,#c{
font-size: 20px;
font-style: italic;
color: gray;
}


img:hover {
  opacity: 0.5;
}

</style>

</head>
<body>
	<div class="container" id ="my">
		<div class="gallery">
			<a target="_self" href="/member/view.do">
			<img src="${vo.photo }" alt="내정보" width="250" height="250" class="img-circle image_float">
			</a>
			<div class="desc">
			<div id ="name">${vo.name }</div>
			</div>
		</div>
	</div>
<div class="container" id="aa">
		<div class="container" id="friends">
			<a href="/groupList/list.do" id="a1">그룹 채팅</a>
			<a href="/chatRoom/list.do" id="a2">일반 채팅</a>
	</div>
	<div class="container" id = "board">
		<a href="/friend/list.do" id= "b" >친구 목록</a>
		<a href="/board/list.do" id = "c">게시판</a>
	</div>
</div>
</body>
</html>