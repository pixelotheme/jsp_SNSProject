<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="decorator"
	uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>INBOOK<decorator:title /></title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="//code.jquery.com/ui/1.13.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>
<link rel="stylesheet" type="text/css" href="../css/common.css">

<style type="text/css">
body {
    -ms-overflow-style: none; /* IE and Edge */
    scrollbar-width: none; /* Firefox */
}
body::-webkit-scrollbar {
    display: none; /* Chrome, Safari, Opera*/
}
header, footer {
	background: AntiqueWhite;
}

pre {
	background: white;
	border: 0px;
}

/* Remove the navbar's default margin-bottom and rounded borders */
.navbar {
	margin-bottom: 0;
	border-radius: 0;
}

/* Add a gray background color and some padding to the footer */
footer {
	background-color: #eee;
	padding: 25px;
	border:  1px solid #ccc;
}

.carousel-inner img {
	width: 100%; /* Set width to 100% */
	margin: auto;
	min-height: 200px;
}

/* Hide the carousel text when the screen is less than 600 pixels wide */
@media ( max-width : 600px) {
	.carousel-caption {
		display: none;
	}
}

article {
	min-height: 400px;
	margin-top: 80px;
}

#welcome {
	color: grey;
	margin: 0 auto;
}

/* nav 메뉴 */
.inbook_nav{}
.inbook_nav li{}
.inbook_nav li a{font-size:0;}
.inbook_nav li img{width:20px;}
.navbar-brand{color:black!important; font-weight:bold;}
.navbar-brand:hover{color:black!important;}
.navbar-right{margin-right:0;}
</style>
	
<script type="text/javascript">
	$(document).ready(function() {

		<c:if test="${!empty login }">
		
		getFriendRequestCount();			

		function getFriendRequestCount(){

			setInterval(function(){		
					$.ajax({
							url:"/friendAjax/friendRequestCount.do",
		
							success:function(data){
								if(data == 'reload'){
									location.reload();
									
								}
								if(data != 0){
									$("#newFriendRequestCount").text(data);
								}
							}
					});
				 }, 500);
			
		}
		</c:if>
		
		
	});
	// tooltip 처리 - 안내풍선 도움말 - tag 안에 title 속성의 텍스트가 나타난다.
	$('[data-toggle="tooltip"]').tooltip();
</script>

<script type="text/javascript">
	$(document).ready(function(){

		$(window).scroll(function() {
				if($(this).scrollTop() > 300){
					$('#Top_go').fadeIn();
				}else{
					$('#Top_go').fadeOut();
				}
			});

			$("#Top_go a").click(function() {
				$('html, body').animate({
					scrollTop : 0
				}, 400);
				return false;
			});
	});
</script>

<decorator:head/>
</head>
<body>

	<header>
		<nav class="navbar navbar-inverse navbar-fixed-top">
			<div class="container-fluid">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse"
						data-target="#myNavbar">
						<span class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="/">INBOOK</a>
				</div>
				<div class="collapse navbar-collapse" id="myNavbar">
					<div class="navbar-right">
						<ul class="nav navbar-nav inbook_nav">
							<li><a href="/main/main.do"><img src="/upload/image/home.png" alt="HOME">HOME</a></li>
<%-- 							<c:if test="${!empty login}"> --%>
								<li>
									<a href="/friend/list.do"><img src="/upload/image/friend.png" alt="친구목록">
									친구목록
									<span class="badge" id="newFriendRequestCount" style="background: red"></span>
									</a>
								</li>
<%-- 							</c:if> --%>
							<li><a href="/board/list.do"><img src="/upload/image/board.png" alt="일반게시판">일반게시판</a></li>
							<c:if test="${!empty login }">
								<li><a href="/groupList/list.do"><img src="/upload/image/group.png" alt="그룹게시판">그룹게시판</a></li>
								<li><a href="/chatRoom/list.do"><img src="/upload/image/chat.png" alt="메시지">메시지</a></li>
								<li><a href="/member/mypage.do"><img src="/upload/image/newMypage.png" alt="마이페이지">마이페이지</a></li>
								<c:if test="${login.gradeNo == 9 }">
									<li><a href="/member/list.do"><img src="/upload/image/member.png" alt="회원관리">회원관리</a></li>
								</c:if>
							</c:if>
							<c:if test="${empty login }">
								<li><a href="/member/writeForm.do"><img src="/upload/image/join.png" alt="회원가입">회원가입</a></li>
								<li><a href="/member/loginForm.do"><img src="/upload/image/newMypage.png" alt="로그인">로그인</a></li>
							</c:if>
							<c:if test="${!empty login }">
								<!-- <li>
									<a href="/friend/list.do">친구목록
										<span class="badge" id="newFriendRequestCount" style="background: red">10</span>
									</a>
								</li> -->
								<li><a href="/member/logout.do"><img src="/upload/image/logout.png" alt="로그아웃">로그아웃</a></li>
							</c:if>
						</ul>
					</div><!-- //navbar-right  -->
				</div>
			</div>
		</nav>
	</header>
	<!-- 내가 작성한 내용삽입 위치 -->
	<article>
		<decorator:body />
	</article>
	
	<div id="Top_go"><a href=".container">상단으로 이동</a></div>
</body>
</html>