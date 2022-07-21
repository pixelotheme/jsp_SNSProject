<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="pageNav" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글보기</title>
<link rel="stylesheet" type="text/css" href="../css/boardView.css">

</head>
<body>
<div class="container">
	<h2>게시판 글보기</h2>
	<div id="viewWrap">
		<input type="hidden" value="${vo.no }">
		<div class="viewImg">
			<p><img src="${vo.fileName}" alt="이미지"></p>
		</div><!-- //viewImg -->
		
		<div class="viewInfo">
			<div class="profile">
				<ul>
					<li class="img"><img src="${vo.photo}" alt="${vo.id} 사진"></li>
					<li class="name">${vo.id}</li>
				</ul>
			</div><!-- //profile -->
			<div class="viewContent">
				<p class="viewText"><b>${vo.title}</b><br/>${vo.content}</p>
				<p class="hashtag">${vo.hashtag}</p>
				<div class="viewLike">
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
							<a href="like.do?<%= request.getQueryString() %>&inc=0&page=${pageObject.page }&perPageNum=${pageObject.perPageNum}&key=${pageObject.key}&word=${pageObject.word}">
								<img alt="좋아요" src="/upload/image/like_new.png" data-toggle="tooltip"
						title="클릭하면 좋아요 처리가 됩니다.">
							</a>
						</c:if>
						<c:if test="${!empty vo.myLiked }">
							<!-- 만약 로그인을 한 경우 myLiked가 비어있지 않다면 좋아요를 취소할 수 있다.-->
							<a href="likeCancel.do?<%= request.getQueryString() %>&inc=0&page=${pageObject.page }&perPageNum=${pageObject.perPageNum}&key=${pageObject.key}&word=${pageObject.word}">
							<img alt="좋아요" src="/upload/image/likecancel_new.png" data-toggle="tooltip"
						title="클릭하면 좋아요 취소가 됩니다.">
							</a>
						</c:if>
					</c:if>
					</div>
					<div class="likeText">
						<p>좋아요 ${vo.likeCnt}개</p>
					</div>
					<div class="viewHit"><p>${vo.hit}</p></div>
					<div class="viewWriteDate"><p>${vo.writeDate}</p></div>
				</div><!-- //viewContent -->
		</div><!-- //viewInfo -->
		
	</div><!-- //viewWrap -->

	<div class="viewContentBtn">
		<c:if test="${!empty login && login.id == vo.id }">
		<a href="updateForm.do?no=${vo.no }&page=${pageObject.page }&perPageNum=${pageObject.perPageNum}&key=${pageObject.key}&word=${pageObject.word}" class="btn btn-default updateBtn">수정</a>
		<a href="delete.do?no=${vo.no }&perPageNum=${pageObject.perPageNum}" class="btn btn-default" onclick="return confirm('정말, 삭제하시겠습니까?')">삭제</a>
		</c:if>
		<a href="list.do?page=${pageObject.page }&perPageNum=${pageObject.perPageNum}&key=${pageObject.key}&word=${pageObject.word}" class="btn btn-default">리스트</a>
	</div>
			
	<%@include file="../comment/list.jsp" %>

</div><!-- //container -->
</body>
</html>