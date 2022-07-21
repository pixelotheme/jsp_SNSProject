<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<style type="text/css">

#friend_UL_Btn{
   list-style:none;
	float : right;
   margin-top: 7px;
   margin-right: 40px; 
   padding-left: 5px;
   }


</style>
<script type="text/javascript">
	$(function() {

		$(".friendSendBTN").click(function() {
			var requestSendID = "${vo.id}";
			alert("아이디클릭" + requestSendID);
			location = "/friend/friendRequestSend.do?requestSendID=" + requestSendID+"&friendRequestSend=/member/mypage2";
			
			if("${param.result}"==3){
				alert(requestSendID+"가 이미 수락 되었습니다, 다시 선택해 주세요 ${param.result}");
			}
			event.stopPropagation();
		});
		$(".friendRequestCancelBTN").click(function() {
			var requestCancelID = "${vo.id}";
			alert("아이디클릭" + requestCancelID);
			location = "/friend/friendRequestCancel.do?requestCancelID=" + requestCancelID+"&friendRequestCancel=/member/mypage2";
			
			if("${param.result}"==3){
				alert(requestCancelID+"가 이미 수락 되었습니다, 다시 선택해 주세요 ${param.result}");
			}
			event.stopPropagation();
		});
		$(".friendAcceptBTN").click(function() {
			var requestAcceptID = "${vo.id}";
			alert("아이디클릭" + requestAcceptID);
			location = "/friend/friendRequestAccept.do?requestAcceptID=" + requestAcceptID+"&friendRequestAccept=/member/mypage2";
			
			if("${param.result}"==3){
				alert(requestAcceptID+"가 이미 수락 되었습니다, 다시 선택해 주세요 ${param.result}");
			}
			event.stopPropagation();
		});
		$(".friendRefuseBTN").click(function() {
			var requestRefuseID = "${vo.id}";
			alert("아이디클릭" + requestRefuseID);
			location = "/friend/friendRequestRefuse.do?requestRefuseID=" + requestRefuseID+"&friendRequestRefuse=/member/mypage2";
			
			if("${param.result}"==3){
				alert(requestRefuseID+"가 이미 수락 되었습니다, 다시 선택해 주세요 ${param.result}");
			}
			event.stopPropagation();
		});
		$(".friendDeleteBTN").click(function() {
			var requestDeleteID = "${vo.id}";
			alert("아이디클릭" + requestDeleteID);
			location = "/friend/friendDelete.do?requestDeleteID=" + requestDeleteID+"&friendDelete=/member/mypage2";
			
			if("${param.result}"==3){
				alert(requestDeleteID+"가 이미 수락 되었습니다, 다시 선택해 주세요 ${param.result}");
			}
			event.stopPropagation();
		});		
	})
</script>

		<!-- friendButton -->
		<ul id="friend_UL_Btn">
			<li class="friend_Btn">
					<c:if test="${login.id != vo.id}">
									<c:remove var="friendBTN"/>
							<c:set var="btn_flog" value="false"/>
							<c:forEach items="${friendIdList }" var="id">
							
								<c:if test="${id == vo.id }">
									<c:set var="btn_flog" value="true"/>
								</c:if>
							</c:forEach>
							<c:if test="${btn_flog == 'true' }">
									<button class="btn btn-default friendDeleteBTN">친구삭제</button>
							</c:if>
						
						<c:if test="${btn_flog == false}">
									<c:remove var="btn_flog"/>
							<c:set var="btn_flog" value="false"/>
							<c:forEach items="${friendRequestId }" var="requestId">
							
								<c:if test="${requestId == vo.id }">
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
						
						<c:if test="${btn_flog == false}">
									<c:remove var="btn_flog"/>
							<c:set var="btn_flog" value="false"/>
							<c:forEach items="${friendRequestSendId }" var="sendId">
							
								<c:if test="${sendId == vo.id }">
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

