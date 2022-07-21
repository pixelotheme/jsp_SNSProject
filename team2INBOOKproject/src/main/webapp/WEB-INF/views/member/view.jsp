<%@page import="com.inbook.member.service.MemberViewService"%>
<%@page import="com.inbook.main.Execute"%>
<%@page import="com.inbook.member.vo.LoginVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${(isMyPage)?"내 정보 보기": "회원 정보 보기" }</title>
<style type="text/css">
</style>
</head>
<body>
<div class="container">  
	<h2>${(isMyPage)?"내 정보 보기": "회원 정보 보기" }</h2>
	<table class="table">
		<tr>
			<th>아이디</th>
			<td>${vo.id }</td>
		</tr>
		<tr>
			<th>이름</th>
			<td>${vo.name }</td>
		</tr>
		<tr>
			<th>사진</th>
			<td><img alt="내 사진" src="${vo.photo }"  class="img-thumbnail"></td>
		</tr>
		<tr>
			<th>성별</th>
			<td>${vo.gender }</td>
		</tr>
		<tr>
			<th>생년월일</th>
			<td>${vo.birth }</td>
		</tr>
		<tr>
			<th>연락처</th>
			<td>${vo.tel }</td>
		</tr>
		<tr>
			<th>이메일</th>
			<td>${vo.email }</td>
		</tr>
		<tr>
			<th>가입일</th>
			<td>${vo.regDate }</td>
		</tr>
		<tr>
			<th>접속일</th>
			<td>${vo.conDate }</td>
		</tr>
		<tr>
			<th>상태</th>
			<td>${vo.status }</td>
		</tr>
		<tr>
			<th>등급</th>
			<td>${vo.gradeName }</td>
		</tr>
		<tr>
			<td colspan="3" style="border: none;">
					<c:if test="${login.gradeNo == 1 }">
					<a href="updateForm.do" class="btn btn-success">수정</a>
					<a href="delete.do" class="btn btn-danger">회원탈퇴</a>			
					<button type="button" class="btn btn-default" onclick="location='/member/mypage.do'">취소</button>
					</c:if>
					<c:if test="${login.gradeNo == 9 }">
					<button type="button" class="btn btn-default" onclick="location='/member/mypage.do'">리스트</button>
					</c:if>
			</td>
		</tr>
	</table>
</div>
</body>
</html>