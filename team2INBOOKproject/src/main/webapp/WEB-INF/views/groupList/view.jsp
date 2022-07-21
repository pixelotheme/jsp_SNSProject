<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="pageNav" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="../css/groupView.css">
<meta charset="UTF-8">
<title>그룹 게시 글보기</title>
</head>
<body>
<div class="container">
	<h2>그룹 게시 글보기</h2>
	<div id="viewWrap">
	<form action="list.do" method="get" class="form-inline" >
		<input name="perPageNum" value="${pageObject.perPageNum }" type="hidden">
		<div class="form-group">
  </div>
  </form>
</div>
<div class="dataRow col-md-12" id="groupViewWrap"><br>
<!-- thumbnail은 썸네일이라 이미지파일을 div 안에 넣기 / 이미지를 가운데 정렬하려면 스타일에 text-align:center해주면 가운데 정렬이 됨 -->
 <div class="userPhoto">
		<p class="img"><img src="/upload/member/nyang8.jpg" alt="${vo.id} 이미지"></p>
		<p class="name">${vo.id}</p>
	</div><!-- //userPhoto -->
  <div class="thumbnail" style="text-align:center;">
  	<!-- width:가로 height:세로 -->
	<p><img alt="" src="${vo.fileName }" style="width:650px; height:400px;"></p>
</div>
	<!-- <p class="no">${vo.no }</p> 여기서 <p>로 닫아버리면 view가 보이지 않음. -->
	<p class="no">${vo.no }</p>
	<p style="font-size: 1.5em;">${vo.title } <span style="font-size: 0.6em;">조회수 : ${vo.hit }</span></p>
	<p style="font-size: 1.2em;">${vo.content }</p>
	<p>${vo.hashtag }</p>
	<p style="color: #a9a9a9; font-size: 0.6em;">${vo.writeDate }</p>
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
							<a href="like.do?&no=${vo.no }&inc=0&page=${pageObject.page }&perPageNum=${pageObject.perPageNum}&key=${pageObject.key}&word=${pageObject.word}">
								<img alt="좋아요" src="/upload/image/like_new.png" data-toggle="tooltip" title="클릭하면 좋아요 처리가 됩니다.">
								 
							</a>
						</c:if>
						<c:if test="${!empty vo.myLiked }">
							<!-- 만약 로그인을 한 경우 myLiked가 비어있지 않다면 좋아요를 취소할 수 있다.-->
							<a href="likeCancel.do?&no=${vo.no }&inc=0&page=${pageObject.page }&perPageNum=${pageObject.perPageNum}&key=${pageObject.key}&word=${pageObject.word}">
							<img alt="좋아요" src="/upload/image/likecancel_new.png" data-toggle="tooltip"
						title="클릭하면 좋아요 취소가 됩니다.">
							</a>
						</c:if>
					</c:if>
					</div>
</div>
<br>
		<div>
	<div class="viewContentBtn">
		<a href="updateForm.do?no=${vo.no }&page=${pageObject.page }&perPageNum=${pageObject.perPageNum}&key=${pageObject.key}&word=${pageObject.word}" class="btn btn-default updateBtn">수정</a>
		<a href="delete.do?no=${vo.no }&perPageNum=${pageObject.perPageNum}" class="btn btn-default" onclick="return confirm('정말, 삭제하시겠습니까?')">삭제</a>
		<a href="list.do?page=${pageObject.page }&perPageNum=${pageObject.perPageNum}&key=${pageObject.key}&word=${pageObject.word}" class="btn btn-default">리스트</a>
	
		<a href="/friend/list.do"><img src="/upload/image/friend.png" width = "30" alt="그룹 회원 목록">
		<span class="badge" id="newFriendRequestCount" style="background: red"></span>
		</a>
       
	</div>

		<div>
			<%@include file="../groupComment/list2.jsp" %>
		</div>
	
	</div>
	</div><!-- //container -->
</body>
</html>
