<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="pageNav" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">
$(document).ready(function() {
	$(".dataRow").click(function(){
		var no = $(this).find(".no").text();			

		location = "../board/view.do?no=" + no + "&inc=1"
				+ "&page=${pageObject.page}&perPageNum=${pageObject.perPageNum}"
				+ "&key=${pageObject.key}&word=${pageObject.word}"
	});
});
</script>

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