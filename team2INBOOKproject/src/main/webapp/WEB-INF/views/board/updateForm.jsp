<%@page import="com.webjjang.util.PageObject"%>
<%@page import="com.inbook.board.vo.BoardVO"%>
<%@page import="com.inbook.board.service.BoardViewService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판글 수정</title>


<style type="text/css">

</style>
</head>
<body>
<div class="container">
<h2>게시판 글수정</h2>
<form action="update.do" method="post">
	<input name="page" value="${pageObject.page }" type="hidden">
	<input name="perPageNum" value="${pageObject.perPageNum }" type="hidden">
	<input name="key" value="${pageObject.key }" type="hidden">
	<input name="word" value="${pageObject.word }" type="hidden">
	
	  <div class="form-group">
	    <label for="no">번호</label>
	    <input type="text" class="form-control" id="no" name="no" maxlength="100" value="${vo.no }" readonly="readonly">
	  </div>
	  <div class="form-group">
	    <label for="title">제목</label>
	    <input type="text" class="form-control" id="title" name="title" maxlength="100" value="${vo.title }">
	  </div>
	  <div class="form-group">
	    <label for="content">내용</label>
	    <textarea rows="7" placeholder="내용 입력" name="content" id="content" class="form-control" >${vo.content }</textarea> 
	  </div>
	  <div class="form-group">
		    <label for="hashtag">해시태그</label>
		    <textarea rows="1" placeholder="태그 입력" name="hashtag" id="hashtag" class="form-control" ></textarea> 
	  	</div>
	 	<div class="form-group">
			<label for="fileName">이미지</label>
			<input name="fileName" id="fileName" class="form-control" type="file">
		</div>
<!-- 	  <div class="form-group"> -->
<!-- 	    <label for="writer">작성자</label> -->
<%-- 	    <input type="text" class="form-control" id="writer" name="writer" maxlength="100" value="${vo.writer }"> --%>
<!-- 	  </div> -->
	  
		<!-- button type - submit:데이터 전달, reset:처음상태, button:동작이 없다. -->
		<button type="submit" class="btn btn-default">수정</button>
		<button type="reset" class="btn btn-default">새로고침</button>
		<button type="button" onclick="history.back()" class="btn btn-default">취소</button>
</form>
</div>
</body>
</html>