<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="pageNav" tagdir="/WEB-INF/tags" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>채팅방 리스트</title>

<style type="text/css">

.input-group, input-group-btn {
 	
    width: 528%;
    display: flex;
    align-self: center;
    justify-content: space-between;
      padding : 15px;
   margin: auto 0;
   
    
    
}


.container {
/*     background-color: #f9f9f9; */
    display: flex;
    flex-direction: column;
    padding: 32px 0;
    
}

#tabs {
    width: 80%;
    display: flex;
    align-self: center;
    justify-content: space-between;
}

#tabs-1 {
    width: 80%;
    display: flex;
    align-self: left;
    justify-content: space-between;
    right : 150px;
 position: relative;
 right : 100px;
   
}
#col-md-8 {
    align-self: center;


}
.row {
width : 650px;


}

#btn btn-default {
    width: 20%;
    background-color: #1890ff;
    border-radius: 4px;
    border: 1px solid #41a3fe;
    color: white;
}

.list-container {
    display: flex;
    flex-direction: column;
    margin-top: 48px;
    width: 80%;
    align-self: center;
    border-radius: 4px;
    border: 1px solid #eaeaea;
    background-color: white;
}

li {
/*     padding: 16px 24px; */
    margin: 0;
    font-size: 16px;
    border-bottom: 1px solid #eaeaea;
    
}

#list {
    margin: 32px 0;
}

.list-group-item-heading dataRow {
    border-bottom: 1px solid #eaeaea;
    margin-right: 32px;
    padding: 16px 0;
    font-size: 14px;
}


.dataRow:hover{
/* hover 는 마우스가 올라갔을때만 변한다 */
cursor: pointer;
	background: #eee
}

</style>

<script>

	$( function() {
		
		
		$(".dataRow").click(function(){
			var cno = $(this).find("#chatRoomCno").text();			
 			//alert("dataRow 클릭. cno : " + cno);
			location = "/chat/list.do?cno=" + cno 
					+ "&page=${pageObject.page}&perPageNum=${pageObject.perPageNum}"
					+ "&key=${pageObject.key}&word=${pageObject.word}"
		});
		
		// 자동 실행되는 부분 --------------------------------
		// 댓글 제목 디자인 적용
	  $( "#tabs" ).tabs();
	  
		// 등록 버튼을 댓글 내용의 높이와 맞춰보자.
		$("#chatRoomWriteBtn").height($("#title").height());
		
	  // 댓글 수정과 취소 버튼 보이지 않게 처리 - 자동
	  $("#chatRoomUpdateBtn, #cancelChatRoomUpdateBtn").hide();
	  
	  // 각각의 댓글에 있는 수정 버튼 이벤트
	  $(".chatRoomUpdateBtn").click(function(){
		 // 등록버튼 없애기, 수정과 취소 버튼 보이기
		 $("#chatRoomWriteBtn").hide();
		  $("#chatRoomUpdateBtn, #cancelChatRoomUpdateBtn").show();
		  // 클릭한 데이터의 rno 찾아오기 tag 안에 속성으로 data-rno="10" 형식으로 들어가 있다.
		  var cno = $(this).data("cno");
		  $("#cno").val(cno);
		  // 댓글 내용 세팅
		  var title = $(this).closest("div").find(".title").text();
		  $("#title").val(title);
	  });
	  
	  // 각각의 댓글에 있는 삭제 버튼 이벤트
	  $(".chatRoomDeleteBtn").click(function(){
		  // 클릭한 데이터의 rno 찾아오기 tag 안에 속성으로 data-rno="10" 형식으로 들어가 있다.
		  var cno = $(this).data("cno");
		  $("#cno").val(cno);
		  if(confirm(" 채팅방을 삭제하시겠습니까?")){
			  $("#chatRoomForm").attr("action", "chatRoomDelete.do");
			  $("#chatRoomForm").submit();
		  }
	  });
	  
	  
	  
	  // 댓글 등록 버튼 이벤트
	  $("#chatRoomWriteBtn").click(function(){
		  // alert("댓글 등록 클릭");
		  
		  // 데이터 유효성 검사
		  // 공백 문자 제거
		  $("#title").val($("#title").val().trim())
		  // 공백 문자 없는 데이터 받아오기
		  var title = $("#title").val();
		  if(!title){
			  // alert("댓글을 입력해 주세요");
			  $("#title").focus();
			  return false;
		  }
		  
		  
		  // 폼 데이터 전송 URL 설정
		  $("#chatRoomForm").attr("action", "chatRoomWrite.do");
		  $("#chatRoomForm").submit();
	  });
	  
	  // 댓글 수정 버튼 이벤트
	  $("#chatRoomUpdateBtn").click(function(){
		 // alert("수정 진행");
		  // 데이터 유효성 검사
		  // 공백 문자 제거
		  $("#title").val($("#title").val().trim())
		  // 공백 문자 없는 데이터 받아오기
		  var title = $("#title").val();
		  if(!title){
			  // alert("댓글을 입력해 주세요");
			  $("#title").focus();
			  return false;
		  }
		  
		  // 폼 데이터 전송 URL 설정
		  $("#chatRoomForm").attr("action", "chatRoomUpdate.do");
		  $("#chatRoomForm").submit();
	  });
	  
	  // 댓글 취소 버튼 이벤트
	  $("#cancelChatRoomUpdateBtn").click(function(){
		 // 등록버튼 보여주기, 수정과 취소 버튼 없애기
		 $("#chatRoomWriteBtn").show();
		 $("#chatRoomUpdateBtn, #cancelChatRoomUpdateBtn").hide();
		 
		 // 입력하고 있는 데이터 지우기
		 $("#title").val("");
		  
	  });
	    
	  
	} );
	


		
	
	</script>




</head>
<body>
<div class="container">
	
	<div id="tabs">
		 <ul>
		   <li>채팅방 리스트</li>
		
		 </ul>
		<c:if test="${!empty login }">
			 <!-- 댓글 등록이나 수정을 위해서 사용되는 폼. -->
 			 <div >
				<form id="chatRoomForm" method="post">
					<!-- 수정할때 필요한 정보입니다. -->
					<input name="cno" type="hidden" id="cno">
					<input name="page" type="hidden" value="${pageObject.page}">
					<input name="perPageNum" type="hidden" value="${pageObject.perPageNum}">
					<input name="key" type="hidden" value="${pageObject.key}">
					<input name="word" type="hidden" value="${pageObject.word}">
					<input name="chatRoomPage" type="hidden" value="${chatRoomPageObject.page}">
					
<%-- 					<input name="id" value="${vo.id }" type="hidden"> --%>
<%-- 					<input name="title" value="${vo.title }" type="hidden"> --%>
				<div style = "padding : 0px 0px 0px 90px;">
				  <div class="input-group" >
				    <textarea rows="3" name="title" class="form-control" id="title" style="resize: none;"></textarea>
				    <div class="input-group-btn">
				      <button class="btn btn-default" type="button" style="font-size: 12pt;" 
				      	id="chatRoomWriteBtn">생성</button>
				      <button class="btn btn-default" type="button" style="font-size: 12pt;"
				      	id="chatRoomUpdateBtn">수정</button>
				      <br>
				      <button class="btn btn-default" type="button" style="font-size: 12pt;"
				      	id="cancelChatRoomUpdateBtn">취소</button>
				      	
				    </div>
				    
				  </div>
				  </div>
				</form>
				
			 </div>
			 
		 </c:if>
		 <div style = "padding : 100px 500px 0px 0px;">
		 <div id="tabs-1">
		 	<div class="list-group">
		 		<c:if test="${!empty list }">
			 		<!-- 데이터가 있는 만큼 반복처리된다. -->
		 			<c:forEach items="${list }" var="chatRoomVo">
				 		<div class = "list-group-item">
				 			<h5 class="list-group-item-heading dataRow">
				 				<span id="chatRoomCno">${chatRoomVo.cno }</span>.(${chatRoomVo.id }) 
				 				</h5>
				 					
				 				
				 				<c:if test="${!empty login && login.id == chatRoomVo.id  || login.gradeNo == 9 }">
					 				<span class="pull-right">
					 					<button class="chatRoomUpdateBtn btn btn-default btn-xs"			
					 					 data-cno="${chatRoomVo.cno }">수정</button>					 					 
					 					<button class="chatRoomDeleteBtn btn btn-default btn-xs"
					 					 data-cno="${chatRoomVo.cno }">삭제</button>
					 				</span>	
				 				</c:if>
							
				 			<p class="list-group-item-text title ">${chatRoomVo.title }</p>
				 			
				 		</div>
				 		
				 	</c:forEach>
				 	
				 		<div class="row">
			<div class="col-md-12 text-center">
			<pageNav:pageNav listURI="list.do" pageObject="${pageObject }" />
			</div>
		</div>
				 	
				 	
<!-- 				 	<div> -->
<%-- 			 		<pageNav:chatRoomPageNav listURI="list.do" pageObject="${pageObject }"  --%>
<%-- 				 			chatRoomPageObject="${chatRoomPageObject }"/> --%>
<!-- 				 	</div> -->
		 		</c:if>
		 		<c:if test="${empty list }">
		 			<div class="list-group-item">
		 				 데이터가 존재하지 않습니다.
		 			</div>
		 		</c:if>
		 	</div>
		 </div>
	</div>
	</div>
</div>
</body>
</html>