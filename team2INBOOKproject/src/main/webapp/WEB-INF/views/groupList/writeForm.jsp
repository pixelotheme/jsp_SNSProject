<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>그룹 등록</title>


<style type="text/css">
/*
th,td{
	border: 1px solid #444;
	padding: 5px;
}
th{
	background: #000;
	color: #fff;
}
*/
</style>
<!-- 외부 js 파일 불러오기 : script tag 사이에 있는 코드는 무시 당한다. -->
<script type="text/javascript" src="/js/regEx.js"></script>
<script type="text/javascript" src="/js/form.js"></script>

<script type="text/javascript">
function sendData(){
// 	alert("submit!");
	
	// 필수 입력 항목 체크
	if(!checkEmpty("title", "제목")) return false;
	if(!checkEmpty("content", "내용")) return false;
	if(!checkEmpty("hashtag", "해시태그")) return false;
	if(!checkEmpty("fileName", "이미지")) return false;

	// 입력 데이터의 패턴 체크 - 정규표현식
	if(!checkReg("title", "제목", reg_title, reg_title_msg)) return false;
	if(!checkReg("content", "내용", reg_content, reg_content_msg)) return false;
	if(!checkReg("hashtag", "해시태그", reg_hashtag, reg_hashtag_msg)) return false;
}
</script>

</head>
<body>
<div class="container">
	<h2>그룹등록</h2>
	<form action="write.do" method="post" enctype="multipart/form-data">
	  <input name="perPageNum" value="${param.perPageNum }" type="hidden">
<!-- 	  	<div class="form-group"> -->
<!-- 			<label for="id">아이디</label> -->
<%-- 			<input name="id" type="text" value="${vo.id }" readonly="readonly" class="form-control" id="id"/> --%>
<!-- 		</div> -->
		<div class="form-group">
		    <label for="title">제목</label>
		   	<input type="text" placeholder="제목 입력" class="form-control" id="title" name="title" maxlength="100">
	  	</div>
	  	<div class="form-group">
		    <label for="content">내용</label>
		    <textarea rows="7" placeholder="내용 입력" name="content" id="content" class="form-control" ></textarea> 
	  	</div>
	  	<div class="form-group">
		    <label for="hashtag">해시태그</label>
		    <textarea rows="1" placeholder="태그 입력" name="hashtag" id="hashtag" class="form-control" ></textarea> 
	  	</div>
	 	<div class="form-group">
			<label for="fileName">이미지</label>
			<input name="fileName" id="fileName" class="form-control" type="file">
		</div>
	  
	  
		<!-- button type - submit:데이터 전달, reset:처음상태, button:동작이 없다. -->
		<button type="submit" onclick="return sendData();" class="btn btn-default">등록</button>
		<button type="reset" class="btn btn-info">새로고침</button>
		<button type="button" onclick="history.back()" class="btn btn-danger">취소</button>
	</form>
</div>
</body>
</html>