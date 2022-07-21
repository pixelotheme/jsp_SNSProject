<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>   
<%@taglib prefix="pageNav" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="../css/friendRequestList.css">
<meta charset="UTF-8">
<title>받은 요청</title>


<style type="text/css">

</style>
<script type="text/javascript">
	$(function(){
		
		$("#friendListBTN").click(function() {

			location = "list.do";

		});
		$("#friendSuggestionListBTN").click(function() {
			location = "suggestions.do";

		});
		
		$(".friendAcceptBTN").click(function() {
			var requestAcceptID = $(this).closest("ul").find(".requestId").text();
// 			alert("아이디클릭" + requestAcceptID);
			location = "friendRequestAccept.do?requestAcceptID=" + requestAcceptID+"&page=${pageObject.page}&perPageNum=${pageObject.perPageNum}"
			+"&key=${pageObject.key}&word=${pageObject.word}&friendRequestAccept=friendRequestList";
			
			if("${param.result}"==3){
				alert(requestAcceptID+"가 이미 수락 되었습니다, 다시 선택해 주세요 ${param.result}");
			}
			event.stopPropagation();
		});
		$(".friendRefuseBTN").click(function() {
			var requestRefuseID = $(this).closest("ul").find(".requestId").text();
// 			alert("아이디클릭" + requestRefuseID);
			location = "friendRequestRefuse.do?requestRefuseID=" + requestRefuseID+"&page=${pageObject.page}&perPageNum=${pageObject.perPageNum}"
			+"&key=${pageObject.key}&word=${pageObject.word}&friendRequestRefuse=friendRequestList";
			
			if("${param.result}"==3){
				alert(requestRefuseID+"가 이미 거절 되었습니다, 다시 선택해 주세요 ${param.result}");
			}
			event.stopPropagation();
		});
		
		$(".dataRow").click(function() {
			var id = $(this).find(".requestId").text();
// 			alert("유저이동"+id);
			
			location = "/member/mypage2.do?id="+id;
		});
	})
</script>

</head>
<body>

<div class="container">
<h2>받은 요청</h2>
<div id="send_Btn">

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
<button class="btn btn-default" id="friendListBTN" type="button">친구 리스트</button>
<button class="btn btn-default" id="friendSuggestionListBTN" >친구추천</button>
</div>


<div class="friendRequestList">
	<c:forEach items="${friendRequestList}" var="vo">
	<ul class="dataRow">
		<li>
			<p><img  src="${vo.photo }" class="img-circle image_float" width="100" height="100"></p>
			<p class="user_Data">
				<span class="requestId">${vo.hostId }</span>
				<span class="user_name">${vo.name } [ ${vo.email } ]</span>
			</p>
		</li>
		<li class="friend_Btn">
			<p>
				<button class="btn btn-default friendAcceptBTN">친구수락</button>
				<button class="btn btn-default friendRefuseBTN">친구거절</button>
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