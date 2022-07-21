<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@taglib prefix="pageNav" tagdir="/WEB-INF/tags" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="../css/friendList.css">
<meta charset="UTF-8">
<title> 그룹 회원 목록 </title>

<script type="text/javascript">
	$(function(){

		
		$("#friendRequestListBTN").click(function() {
			location = "friendRequestList.do";

		});
		$("#friendSuggestionListBTN").click(function() {
			location = "suggestions.do";

		});

		$(".friendDeleteBTN").click(function() {
			var requestDeleteID = $(this).closest("ul").find(".friendId").text();
			var result = confirm(requestDeleteID+"(을)를 정말 삭제하시겠 습니까?");
			 if(result) {
		           //yes
			location = "friendDelete.do?requestDeleteID=" + requestDeleteID+"&page=${pageObject.page}&perPageNum=${pageObject.perPageNum}"
			+"&key=${pageObject.key}&word=${pageObject.word}&friendDelete=list";
		
		        } else {
		            //no
		        }
			if("${param.result}"==3){
				alert(requestDeleteID+"가 이미 삭제 되었습니다, 다시 선택해 주세요 ${param.result}");
			}
		
			event.stopPropagation();
		});
				
		$(".dataRow").click(function() {
			var id = $(this).find(".friendId").text();
// 			alert("유저이동"+id);
			
			location = "/groupList/view.do?id="+id;
		});
		
		
	});
</script>

</head>
<body>

<div class="container">
<h2>그룹 회원 목록</h2>
<div id="send_Btn" >
	
		
	<form action="search.do" method="get" class="form-inline" id="search_Bar">

		<input name="perPageNum" value="${pageObject.perPageNum }" type="hidden">
		<div class="form-group">
		<!-- input,select, -->
		<select name="key" class="form-control">
			<option value="i" ${(pageObject.key == "i")? "selected": "" }>아이디</option>
			<option value="n" ${(pageObject.key == "n")? "selected": "" }>이름</option>
			<option value="in" ${(pageObject.key == "in")? "selected": "" }>모두</option>
		</select>
		</div>
	  <div class="input-group">
	    <input type="text" class="form-control" placeholder="Search" name="word" value="${pageObject.word }">
	    <div class="input-group-btn">
	      <button class="btn btn-default" type="submit">
	        <i class="glyphicon glyphicon-search"></i>
	      </button>
	    </div>
	  </div>
	</form>
	<button class="btn btn-default" id="friendRequestListBTN" >받은요청</button>
	
</div>

<!-- 	<tr> -->
<!-- 		<th>사진</th> -->
<!-- 		<th>아이디</th> -->
<!-- 		<th>이름</th> -->
<!-- 		<th>이메일</th> -->
<!-- 		<th>연락처</th> -->
<!-- 	</tr> -->
<div class="friendList">
	<c:forEach items="${list}" var="vo">
	<ul class="dataRow">
		<li>
			<p><img  src="${vo.photo }" class="img-circle image_float"></p>
			<p class="user_Data">
				<span class="friendId">${vo.friendId }</span>
				<span class="user_name">${vo.name } [ ${vo.email } ] [ ${vo.tel } ]</span>
			</p>	
		</li>
		<li class="friend_Btn">
			<p>
				<button class="btn btn-default friendDeleteBTN">그룹회원삭제</button>
			</p>
		</li>
	</ul>
	
	</c:forEach>
	
		<div class="row">
			<div class="col-md-8">
			<pageNav:pageNav listURI="list.do" pageObject="${pageObject }" />
			</div>
		</div>

</div>
</div>
</body>
</html>