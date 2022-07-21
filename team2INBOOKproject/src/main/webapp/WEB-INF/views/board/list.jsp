<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="pageNav" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="../css/board.css">
<meta charset="UTF-8">
<title>INBOOK 리스트</title>
<script type="text/javascript">
	// body 태그 부분이 처리가 끝나면 실행되는 형식
	// 함수 또는 메소드 function 이름(){~~~} -> 실행 : 이름();
	// function(){~~} -> 익명함수 : 콜백함수 선언
	$(function(){
		// tag -> tag[] 선택
		// .class -> class[] 선택
		// #id -> id 선택
		$(".dataRow").click(function(){
			var no = $(this).find(".no").text();			
// 			alert("dataRow 클릭. no : " + no);
			location = "view.do?no="+no+"&inc=1"
					+ "&page=${pageObject.page}&perPageNum=${pageObject.perPageNum}"
					+ "&key=${pageObject.key}&word=${pageObject.word}"
		});
	});
</script>
</head>
<body>
<div class="container">
<h2>INBOOK</h2>
<div id="boardSearch">
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

<div class="writeBtnWrap">
	<a href="writeForm.do?perPageNum=${pageObject.perPageNum }" id="writeBtn" class="btn btn-default">등록</a>
</div>


<!-- 데이터 있는 만큼 반복문 처리 : 데이터 한개 당 tr 한개 -->
<c:forEach items="${list }" var="vo">
<div class="dataRow" id="boardWrap">
	<p class="no">${vo.no }</p>
	
	<div class="userPhoto">
		<p class="img"><img src="${vo.photo}" alt="${vo.id} 이미지"></p>
		<p class="name">${vo.id}</p>
	</div><!-- //userPhoto -->
	
	<div class="boardImage">
  	<p><img src="${vo.fileName }" alt="이미지"></p>
	</div><!-- //boardImage -->
	
	<p class="ListText"><b>${vo.title}</b><br/>${vo.content}</p>
	<p class="hashtag">${vo.hashtag}</p>
	<div class="listLike">
				<!-- 좋아요 부분 -->
					<c:if test="${empty login }"> 
						<!-- 로그인을 하지 않은 경우 - 좋아요와 상관이 없음 -->
						<img alt="좋아요" src="/upload/image/like_new.png" data-toggle="tooltip"
						title="로그인하셔야 사용할 수 있습니다.">
					</c:if>
					<c:if test="${!empty login }">
						<!--  로그인을 한 경우 -->
						<c:if test="${empty vo.myLiked }">
						<!-- 만약 로그인을 한 경우 myLiked가 비어있다면 좋아요를 누를 수 있다.-->
							<a href="like2.do?&no=${vo.no }&inc=0&page=${pageObject.page }&perPageNum=${pageObject.perPageNum}&key=${pageObject.key}&word=${pageObject.word}">
								<img alt="좋아요" src="/upload/image/like_new.png" data-toggle="tooltip" title="클릭하면 좋아요 처리가 됩니다.">
								 
							</a>
						</c:if>
						<c:if test="${!empty vo.myLiked }">
							<!-- 만약 로그인을 한 경우 myLiked가 비어있지 않다면 좋아요를 취소할 수 있다.-->
							<a href="likeCancel2.do?&no=${vo.no }&inc=0&page=${pageObject.page }&perPageNum=${pageObject.perPageNum}&key=${pageObject.key}&word=${pageObject.word}">
							<img alt="좋아요" src="/upload/image/likecancel_new.png" data-toggle="tooltip"
						title="클릭하면 좋아요 취소가 됩니다.">
							</a>
						</c:if>
					</c:if>
					</div>
					<div class="likeText"><p>좋아요 ${vo.likeCnt}개</p></div>
					<div class="viewHit"><p>${vo.hit}</p></div>
	<p class="boardWriteDate">${vo.writeDate}</p>
</div>
</c:forEach>

<div class="row">
	<div class="col-md-12 text-center">
		<pageNav:pageNav listURI="list.do" pageObject="${pageObject }" />
	</div>
</div>
	
</div>
</body>
</html>