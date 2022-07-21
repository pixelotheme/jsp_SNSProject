<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	System.out.println("/board/delete.jsp - 게시판 글삭제");
	// DB 등록 - BoardWriteService - BoardDAO
	response.sendRedirect("list.jsp");
%>
