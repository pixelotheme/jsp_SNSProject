<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="pageNav" tagdir="/WEB-INF/tags" %>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" type="text/css" href="../css/comment.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

<script type="text/javascript">
$(document).ready(function() {
	$("#tabs").tabs();
	
	// 댓글 수정과 취소 버튼 보이지 않게 처리 - 자동
	$("#commentUpdateBtn, #cancelCommentUpdateBtn").hide();
	
	// 각각의 댓글에 있는 수정 버튼 이벤트
	$(".commentUpdateBtn").click(function(){
		// 등록 버튼 숨기기, 수정과 취소 버튼 보이기
		$("#commentWriteBtn").hide();
		$("#commentUpdateBtn, #cancelCommentUpdateBtn").show();
		// 클릭한 데이터의 rno 찾아오기 - tag 안에 속성으로 data-rno="10" 형식으로 들어가 있다.
		var rno = $(this).data("rno");
		$("#rno").val(rno);
		
		// 댓글 내용 세팅
		var content = $(this).closest(".commentList").find(".content").text();
		$("#content").val(content);

	});
	
	// 각각의 댓글에 있는 삭제 버튼 이벤트
	$(".commentDeleteBtn").click(function(){
		// 클릭한 데이터의 rno 찾아오기 - tag 안에 속성으로 data-rno="10" 형식으로 들어가 있다.
		var rno = $(this).data("rno");
		$("#rno").val(rno);
		if(confirm("댓글을 삭제하시겠습니까?")) {
			$("#commentForm").attr("action","/comment/commentDelete.do");
			$("#commentForm").submit();
		}
	});
	
	// 댓글 등록 버튼 이벤트
	$("#commentWriteBtn").click(function(){
		// alert("댓글 등록!");
		
		// 데이터 유효성 검사
		// 공백 문자 제거(trim)
		$("#content").val($("#content").val().trim())
		// 공백 문자 없는 데이터 받아 오기
		var content = $("#content").val();
		if(!content){
			// alert("댓글을 입력해 주세요");
			$("#content").focus();
			return false;
		}
		
		// form 데이터 전송 URL 설정
		$("#commentForm").attr("action", "/comment/commentWrite.do");
		$("#commentForm").submit();
	});
	
	// 댓글 수정 버튼 이벤트
	$("#commentUpdateBtn").click(function(){
		// alert("수정 진행");
		
		// 데이터 유효성 검사
		// 공백 문자 제거(trim)
		$("#content").val($("#content").val().trim())
		// 공백 문자 없는 데이터 받아 오기
		var content = $("#content").val();
		if(!content){
			// alert("댓글을 입력해 주세요");
			$("#content").focus();
			return false;
		}
		
		// form 데이터 전송 URL 설정
		$("#commentForm").attr("action", "/comment/commentUpdate.do");
		$("#commentForm").submit();
	});
	
	// 댓글 취소 버튼 이벤트
	$("#cancelCommentUpdateBtn").click(function(){
		// 등록 버튼 보여주기, 수정과 취소 버튼 숨기기
		$("#commentWriteBtn").show();
		$("#commentUpdateBtn, #cancelCommentUpdateBtn").hide();
		
		// 입력하고 있는 데이터 지우기
		$("#content").val("");
	});
	
	$("#tabs-1").hide();
	$(".commentCount > button").click(function(){
		$("#tabs-1").fadeToggle(200);
	});
	
});
</script>

<div class="tabs">
	<div class="commentCount">
		<button type="button">댓글 ${replyPageObject.totalRow}개 모두 보기</button>
	</div>
	<c:if test="${!empty login }">
	<!-- 댓글 등록이나 수정을 위해서 사용되는 폼 -->
	<div>
		<form id="commentForm" method="post">
			<!-- 수정할 때 필요한 정보 입니다. rno -->
			<input name="rno" type="hidden" id="rno">
			<input name="page" type="hidden" value="${pageObject.page}">
			<input name="perPageNum" type="hidden" value="${pageObject.perPageNum}">
			<input name="key" type="hidden" value="${pageObject.key}">
			<input name="word" type="hidden" value="${pageObject.word}">
			<input name="replyPage" type="hidden" value="${replyPageObject.page}">
			<input name="no" value="${vo.no }" type="hidden">
			<div class="input-group commentWrite">
				<div class="writeBox">
					<p class="userPhoto">
						<img src="../upload/image/memberNoImage.jpg" alt="댓글 작성중" class="img-thumbnail">
						
					</p>
					<textarea rows="3" name="content" class="form-control" id="content" placeholder="댓글을 입력해 주세요." maxlength="600"></textarea>
					<div class="btn_group">
						<button class="btn btn-default btn-xs" type="button" id="commentWriteBtn">등록</button>
						<button class="btn btn-default btn-xs" type="button" id="commentUpdateBtn">수정</button>
						<!-- <button class="btn btn-default" type="button" style="font-size: 12pt;"
				      		id="cancelCommentUpdateBtn">취소</button> -->
				    </div>
				</div>
			</div>
		</form>
	</div>
	</c:if>
	<div id="tabs-1">
		<div class="list-group">
		<!-- 데이터가 있는 만큼 반복처리 된다. -->
		<c:if test="${!empty list }">
			<c:forEach items="${list}" var="commentVo">
				<div class="commentList">
					<div class="userInfo">
						<p class="userPhoto"><img src="${commentVo.photo}" alt="${commentVo.id } 사진"></p>
						<div class="infoBox">
							<ul>
								<li class="userId">${commentVo.id}</li>
								<li class="re_writeDate">${commentVo.writeDate}</li>
							</ul>
							<p class="re_content content">${commentVo.content }</p>
						</div>
					</div><!-- //userInfo -->
	 				<c:if test="${!empty login && login.id == commentVo.id }">
	 					<div class="udIcon dropup">
	 						<button class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown">수정/삭제</button>
		 					<ul class="upDel dropdown-menu">
		 						<li><button class="commentUpdateBtn" data-rno="${commentVo.rno }">댓글 수정</button></li>
		 						<li><button class="commentDeleteBtn" data-rno="${commentVo.rno }">댓글 삭제</button></li>
		 					</ul>
	 					</div>
	 				</c:if>
		 		</div><!-- //commentList -->
			</c:forEach>
			<div class="pageNav">
				<pageNav:replyPageNav listURI="view.do" pageObject="${pageObject }"
					replyPageObject="${replyPageObject }"></pageNav:replyPageNav>
			</div>
		</c:if>
		<c:if test="${empty list }">
			<!-- <div class="list-group-item">
				<p>댓글 데이터가 존재하지 않습니다.</p>
			</div> -->
		</c:if>
		</div><!-- //list-group -->
	</div><!-- //tabs-1 -->
</div>