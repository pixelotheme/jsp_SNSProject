<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib prefix="pageNav" tagdir="/WEB-INF/tags"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="../css/friendSearch.css">
<meta charset="UTF-8">
<title>사용자 검색</title>


<style type="text/css">

</style>
<script type="text/javascript">
	$(function() {

		$(".friendSendBTN").click(function() {
			var requestSendID = $(this).closest("ul").find(".requestId").text();
// 			alert("아이디클릭" + requestSendID);
			location = "friendRequestSend.do?requestSendID=" + requestSendID+"&page=${pageObject.page}&perPageNum=${pageObject.perPageNum}"
			+"&key=${pageObject.key}&word=${pageObject.word}&friendRequestSend=search";
			
			if("${param.result}"==3){
				alert(requestSendID+"가 이미 수락 되었습니다, 다시 선택해 주세요 ${param.result}");
			}
			event.stopPropagation();
		});
		$(".friendRequestCancelBTN").click(function() {
			var requestCancelID = $(this).closest("ul").find(".requestId").text();
// 			alert("아이디클릭" + requestCancelID);
			location = "friendRequestCancel.do?requestCancelID=" + requestCancelID+"&page=${pageObject.page}&perPageNum=${pageObject.perPageNum}"
			+"&key=${pageObject.key}&word=${pageObject.word}&friendRequestCancel=search";
			
			if("${param.result}"==3){
				alert(requestCancelID+"가 이미 수락 되었습니다, 다시 선택해 주세요 ${param.result}");
			}
			event.stopPropagation();
		});
		$(".friendAcceptBTN").click(function() {
			var requestAcceptID = $(this).closest("ul").find(".requestId").text();
// 			alert("아이디클릭" + requestAcceptID);
			location = "friendRequestAccept.do?requestAcceptID=" + requestAcceptID+"&page=${pageObject.page}&perPageNum=${pageObject.perPageNum}"
			+"&key=${pageObject.key}&word=${pageObject.word}&friendRequestAccept=search";
			
			if("${param.result}"==3){
				alert(requestAcceptID+"가 이미 수락 되었습니다, 다시 선택해 주세요 ${param.result}");
			}
			event.stopPropagation();
		});
		$(".friendRefuseBTN").click(function() {
			var requestRefuseID = $(this).closest("ul").find(".requestId").text();
// 			alert("아이디클릭" + requestRefuseID);
			location = "friendRequestRefuse.do?requestRefuseID=" + requestRefuseID+"&page=${pageObject.page}&perPageNum=${pageObject.perPageNum}"
			+"&key=${pageObject.key}&word=${pageObject.word}&friendRequestRefuse=search";
			
			if("${param.result}"==3){
				alert(requestRefuseID+"가 이미 수락 되었습니다, 다시 선택해 주세요 ${param.result}");
			}
			event.stopPropagation();
		});
		$(".friendDeleteBTN").click(function() {
			var requestDeleteID = $(this).closest("ul").find(".requestId").text();
			
			var result = confirm(requestDeleteID+"(을)를 정말 삭제하시겠 습니까?");
			 if(result) {
		           //yes
				location = "friendDelete.do?requestDeleteID=" + requestDeleteID+"&page=${pageObject.page}&perPageNum=${pageObject.perPageNum}"
					+"&key=${pageObject.key}&word=${pageObject.word}&friendDelete=search";
		        } else {
		            //no
		        }
			
			if("${param.result}"==3){
				alert(requestDeleteID+"가 이미 수락 되었습니다, 다시 선택해 주세요 ${param.result}");
			}
			event.stopPropagation();
		});		

		$(".dataRow").click(function() {
			var id = $(this).find(".requestId").text();
// 			alert("유저이동"+id);
			
			location = "/member/mypage2.do?id="+id;
		});
		
		$("#friendListBTN").click(function() {

			location = "list.do";
		});
		
		$("#friendSuggestionListBTN").click(function() {
			location = "suggestions.do";

		});
		$("#friendRequestListBTN").click(function() {
			location = "friendRequestList.do";

		});
		
	})
</script>
</head>
<body>

	<div class="container">
		<h2>사용자 검색</h2>
		<div id="send_Btn">
			<form action="search.do" method="get" class="form-inline" id="search_Bar">
				<input name="perPageNum" value="${pageObject.perPageNum }"
					type="hidden">
					
				<div class="form-group">
					<!-- input,select, -->
					<select name="key" class="form-control">
						<option value="i" ${(pageObject.key == "i")? "selected": "" }>아이디</option>
						<option value="n" ${(pageObject.key == "n")? "selected": "" }>이름</option>
						<option value="in" ${(pageObject.key == "in")? "selected": "" }>모두</option>
					</select>
				</div>
				<div class="input-group">
					<input type="text" class="form-control" placeholder="Search"
						name="word" value="${pageObject.word }">
					<div class="input-group-btn">
						<button class="btn btn-default" type="submit">
							<i class="glyphicon glyphicon-search"></i>
						</button>
					</div>
				</div>
			</form>
			<button class="btn btn-default" id="friendListBTN">친구 리스트</button>
			<button class="btn btn-default" id="friendRequestListBTN" >받은요청</button>
			<button class="btn btn-default" id="friendSuggestionListBTN" >친구추천</button>
		</div>



<!-- 			<tr> -->
<!-- 				<th>사진</th> -->
<!-- 				<th>아이디</th> -->
<!-- 				<th>이름</th> -->
<!-- 				<th>이메일</th> -->
<!-- 				<th></th> -->
<!-- 			</tr> -->
		<div class="searchList">
			<c:forEach items="${searchList}" var="searchVO">
			<ul class="dataRow">
				<li >
					<p>
						<img src="${searchVO.photo }" class="img-circle image_float">
					</p>
<!-- 				</li> -->
<!-- 				<li> -->
					<p class="user_Data">
						<span class="requestId">${searchVO.id }</span>
						<span class="user_name">${searchVO.name }  [ ${searchVO.email } ]</span>
						
					</p>
					<p >
					</p>
				</li>
				<li class="friend_Btn">
					<c:if test="${login.id != searchVO.id}">
									<c:remove var="friendBTN"/>
							<c:set var="btn_flog" value="false"/>
							<c:forEach items="${friendIdList }" var="id">
							
								<c:if test="${id == searchVO.id }">
									<c:set var="btn_flog" value="true"/>
								</c:if>
							</c:forEach>
							<c:if test="${btn_flog == 'true' }">
									<button class="btn btn-default friendDeleteBTN">친구삭제</button>
							</c:if>
						
						<c:if test="${ btn_flog == false}">
									<c:remove var="btn_flog"/>
							<c:set var="btn_flog" value="false"/>
							<c:forEach items="${friendRequestId }" var="requestId">
							
								<c:if test="${requestId == searchVO.id }">
									<c:set var="btn_flog" value="true"/>
								</c:if>
							</c:forEach>
							<c:if test="${btn_flog == 'true' }">
								<div class="friendAcceptRefuseBTN">
								<button class="btn btn-default friendAcceptBTN">친구수락</button>
								<button class="btn btn-default friendRefuseBTN">친구거절</button>
								</div>
							</c:if>
						</c:if>
						
						<c:if test="${ btn_flog == false}">
									<c:remove var="btn_flog"/>
							<c:set var="btn_flog" value="false"/>
							<c:forEach items="${friendRequestSendId }" var="sendId">
							
								<c:if test="${sendId == searchVO.id }">
									<c:set var="btn_flog" value="true"/>
								</c:if>
							</c:forEach>
							<c:if test="${btn_flog == 'true' }">
								<button class="friendRequestCancelBTN btn btn-default">친구신청 취소</button>
							</c:if>
						</c:if>
						
						<c:if test="${btn_flog == false }">
							<button class="btn btn-default friendSendBTN">친구 신청</button>
						</c:if>
					</c:if>
				</li>
			</ul>
			</c:forEach>

					<div class="row">
						<div class="col-md-8">
							<pageNav:pageNav listURI="search.do" pageObject="${pageObject }" />
						</div>
					</div>

		</div><!-- searchList -->
	</div>
</body>
</html>