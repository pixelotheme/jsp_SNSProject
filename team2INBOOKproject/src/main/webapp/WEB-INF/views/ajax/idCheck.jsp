<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${empty id }">
	<div class="alert alert-success">사용 가능한 아이디입니다.</div>
</c:if>
<c:if test="${!empty id }">
	<div class="alert alert-danger">중복된 아이디입니다. 사용할 수 없습니다.</div>
</c:if>