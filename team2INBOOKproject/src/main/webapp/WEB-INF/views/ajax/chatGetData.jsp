<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    
<c:if test="${login.id == dataList.id }">

<div data-no = ${dataList.no} style="text-align: right" class="cnoWriteNum">
(${dataList.id}) ${dataList.content} 
<span  class = "myWriteDate">${dataList.writeDate}</span>
</div>
    
</c:if>    
<c:if test="${login.id != dataList.id }">

<div data-no = ${dataList.no} class="cnoWriteNum">
${dataList.id} ${dataList.content} 
<span class = "yourWriteDate">${dataList.writeDate}</span>
</div>
    
</c:if>    


    </html>