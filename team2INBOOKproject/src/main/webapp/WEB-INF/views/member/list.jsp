<%@page import="com.inbook.friend.service.FriendListService"%>
<%@page import="com.inbook.main.Execute"%>
<%@page import="com.webjjang.util.PageObject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="pageNav" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원리스트</title>
<style type="text/css">
.dataRow:hover{
	background: #eee;
	cursor: pointer;
}
.dataTable img{
	width: 50px;
	height: 60px;
}

/* 등급변경 창 - 독립적인 뜨는 창, 테두리, 처음에는 안보이게 */
#gradeDIV, #statusDIV{
	position: absolute;
	padding: 10px;
	background: #eee;
	border: 1px solid #ccc;
	border-radius: 10px;
	display: none;
}

</style>

<script type="text/javascript">
$(function(){
	$(".dataRow").click(function(){
		var id = $(this).find(".id").text();
	
		location = "view.do?id=" + id + "&page=${pageObject.page}&perPageNum=${pageObject.perPageNum}" 
				+ "&key=${pageObject.key}&word=${pageObject.word}";
	});
	
	
	$(".gradeTD").click(function(event){
	
		var x = event.clientX;
		var y = event.clientY;
		
		
		$("#gradeDIV").css("top", (y + 10) + "px");
		$("#gradeDIV").css("left", (x + 30) + "px");

		
		var id = $(this).parent().find(".id").text();
		$("#gradeId").val(id);
		
		
		$("#gradeDIV, #statusDIV").hide();
		$("#gradeDIV").slideDown();
		
	
		return false;
	});
	
	
	$(".statusTD").click(function(event){
		
		var x = event.clientX;
		var y = event.clientY;
		
	
		$("#statusDIV").css("top", (y + 10) + "px");
		$("#statusDIV").css("left", (x - 130) + "px");

		
		var id = $(this).parent().find(".id").text();
		$("#statusId").val(id);	
		$("#gradeDIV, #statusDIV").hide();
		$("#statusDIV").slideDown();
		
		return false;
	});
	
	
	$("#cancelGradeUpdateBtn").click(function(){
		$("#gradeDIV").slideUp();
	});
	
	
	$("#cancelStatusUpdateBtn").click(function(){
		$("#statusDIV").slideUp();
	});
	

	$("#gradeUpdateForm").submit(function(){
	
		var id = $("#gradeId").val();
		var gradeNo = $(".gradeNo:checked").val();
	
		if(!id) {
			alert("아이디 데이터가 수집안됨");
			return false;
		}
		if(!gradeNo) {
			alert("등급 선택이 되어 있지 않습니다.");
			return false;
		}
		
	
		
	});
	

	$("#statusUpdateForm").submit(function(){
	
		var id = $("#statusId").val();
		var status = $(".status:checked").val();
		
		if(!id) {
			alert("아이디 데이터가 수집안됨");
			return false;
		}
		if(!status) {
			alert("상태 선택이 되어 있지 않습니다.");
			return false;
		}
		
	
		
	});
	
});
</script>

</head>
<body>
<div  class="container">
<h2>회원 리스트</h2>
<table class="table dataTable">
<tr>
	<th>아이디</th>
	<th>사진</th>
	<th>이름</th>
	<th>성별</th>
	<th>생년월일</th>
	<th>연락처</th>
	<th>등급</th>
	<th>상태</th>
</tr>

<!-- 회원이 없는 경우 -->
<c:if test="${empty list }">
	<tr>
		<td colspan="8">데이터가 존재하지 않습니다.</td>
	</tr>
</c:if>

<!-- 데이터 있는 만큼 반복문 처리 : 데이터 한개 당 tr 한개 -->
<c:if test="${!empty list }">
	<c:forEach items="${list }" var="vo">
	<tr class="${(login.id != vo.id)?'dataRow':'' }">
		<td class="id">${vo.id }</td>
		<td><img src="${vo.photo }" class="img-rounded " /></td>
		<td>${vo.name }</td>
		<td>${vo.gender }</td>
		<td>${vo.birth }</td>
		<td>${vo.tel }</td>
		<td class="${(login.id != vo.id)?'gradeTD':'' }" >${vo.gradeName }</td>
		<td class="${(login.id != vo.id)?'statusTD':'' }">${vo.status }</td>
	</tr>
	</c:forEach>
	<tr>
		<td colspan="8"><pageNav:pageNav listURI="list.do" pageObject="${pageObject }"/></td>
	</tr>
</c:if>
</table>
</div>
<div id="gradeDIV">
	<h4>등급 수정</h4>
	<form action="gradeUpdate.do" method="post" id="gradeUpdateForm">
		<input name="page" type="hidden" value="${pageObject.page }"/>
		<input name="perPageNum" type="hidden" value="${pageObject.perPageNum }"/>
		<input name="key" type="hidden" value="${pageObject.key }"/>
		<input name="word" type="hidden" value="${pageObject.word }"/>
		<input name="id" type="hidden" id="gradeId"/>
		<div class="form-group">
			<label><input name="gradeNo" type="radio" value="1" class="gradeNo"> 일반회원</label>
		</div>
		<div class="form-group">
			<label><input name="gradeNo" type="radio" value="9" class="gradeNo"> 관리자</label>
		</div>
		<button>수정</button>
		<button type="button" id="cancelGradeUpdateBtn">취소</button>
	</form>
</div>
<div id="statusDIV">
	<h4>상태 변경</h4>
	<form action="statusUpdate.do" method="post" id="statusUpdateForm">
		<input name="page" type="hidden" value="${pageObject.page }"/>
		<input name="perPageNum" type="hidden" value="${pageObject.perPageNum }"/>
		<input name="key" type="hidden" value="${pageObject.key }"/>
		<input name="word" type="hidden" value="${pageObject.word }"/>
		<input name="id" type="hidden" id="statusId"/>
		<div class="form-group">
			<label><input name="status" type="radio" value="정상" class="status"> 정상</label>
		</div>
		<div class="form-group">
			<label><input name="status" type="radio" value="탈퇴" class="status"> 탈퇴</label>
		</div>
		<div class="form-group">
			<label><input name="status" type="radio" value="강퇴" class="status"> 강퇴</label>
		</div>
		<div class="form-group">
			<label><input name="status" type="radio" value="휴면" class="status"> 휴면</label>
		</div>
		<button>수정</button>
		<button type="button" id="cancelStatusUpdateBtn">취소</button>
	</form>
</div>
</body>
</html>