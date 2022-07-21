<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="pageNav" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="../css/groupView.css">
<meta charset="UTF-8">
<title>INBOOK GROUP LIST</title>
<style type="text/css">
/*
th,td{
	border: 1px solid #444;
	padding: 5px;
}
th{
	background: black;
	color: white;
}
*/
.dataRow, button{
}
.dataRow:hover{
	background: #eee;
	cursor: pointer;
}
#ke{
	width:500px;
	margin:auto;
	padding:5px;
}

</style>

<script type="text/javascript">
	// body 태그 부분이 처리가 끝나면 실행되는 형식
	// 함수 또는 메소드 function 이름(){~~~} -> 실행 : 이름();
	// function(){~~} -> 익명함수 : 콜백함수 선언
	$(function(){
		// tag -> tag[] 선택
		// .class -> class[] 선택
		// #id -> id 선택
		$(".dataRow").click(function(){
			var no = $(this).data("no");			
// 			alert("dataRow 클릭. no : " + no);
			location = "view.do?no=" + no + "&inc=1"
					+ "&page=${pageObject.page}&perPageNum=${pageObject.perPageNum}"
					+ "&key=${pageObject.key}&word=${pageObject.word}"
		});
	});
</script>

</head>
<body>
<div class="container">
<h2>INBOOK GROUP LIST</h2>
<div id="ke">
<form action="list.do" method="get" class="form-inline" >
  <input name="perPageNum" value="${pageObject.perPageNum }" type="hidden">
  <div class="form-group">
  	<select name="key" class="form-control">
  		<option value="t" ${(pageObject.key == "t")? "selected": "" }>제목</option>
  		<option value="c" ${(pageObject.key == "c")? "selected": "" }>내용</option>
  		<option value="i" ${(pageObject.key == "i")? "selected": "" }>아이디</option>
  		<option value="tc" ${(pageObject.key == "tc")? "selected": "" }>제목/내용</option>
  		<option value="ti" ${(pageObject.key == "ti")? "selected": "" }>제목/아이디</option>
  		<option value="ci" ${(pageObject.key == "ci")? "selected": "" }>내용/아이디</option>
  		<option value="tci" ${(pageObject.key == "tci")? "selected": "" }>모두</option>
  	</select>
  </div>
  <div class="input-group">
    <input type="text" class="form-control" placeholder="Search" name="word" value="${pageObject.word }">
    <div class="input-group-btn">
      <button class="btn btn-default" type="submit">
        <i class="glyphicon glyphicon-search"></i>
      </button>
    </div>
  </div>
</form>
</div>

<div class="row">
 
 	<!-- 데이터가 있는 만큼 반복 처리한다. -->
 	<c:forEach items="${list }" var="vo" varStatus="vs" >
 	
 	<c:if test="${vs.index != 0 && vs.index % 5 == 0}">
<!-- 				이미지 인덱스가 0보다 크면서 3배의 배수이면 줄바꿈 코드를 추가한다. -->
				${"</div><div class='row'>"}
	</c:if>
		<div class="col-md-4 ">
		  <div class="thumbnail dataRow" data-no = "${vo.no}" id="groupViewWrap" >
		     <div class="userPhoto">
		<p class="img"><img src="/upload/member/nyang8.jpg" alt="${vo.id} 이미지"></p>
		<p class="name">${vo.id}</p>
	</div><!-- //userPhoto -->
		      <img src="${vo.fileName }" alt="${vo.title }" style="width:100% height: 250px;">
		      <div class="caption"><br>
<%-- 		       <div class="no">${vo.no }</div> --%>
		      <div class="title">${vo.title }</div>
		        <div>(${vo.writeDate })</div>
		        <p>${vo.content }</p>
		        <p>${vo.hashtag }</p>
<%-- 		       <div>${vo.writeDate }</div><br>  --%>
<%-- 		        <div>${vo.content }</div> --%>
<%-- 		        <div>${vo.hashtag }</div> --%>
		      </div>
		 
		  </div>
		</div>
		<!-- 데이터가 3의 배수로 찍힌 경우 줄바꿈은 한다. 단 페이지의 총개수와 카운트가 같으면 줄을 만들지 않는다. -->
 	</c:forEach>
</div>

<div><pageNav:pageNav listURI="list.do" pageObject="${pageObject }" /></div>

<c:if test="${!empty login && login.gradeNo == 1  }">
	<!-- 로그인 한 경우만 보이는 메뉴 -->
	<div class="col-md-4 text-right">
	<a href="writeForm.do?perPageNum=${pageObject.perPageNum }" id="writeBtn" class="btn btn-default">그룹 등록</a>
</div>
</c:if>
</div>
</body>
</html>