<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="pageNav" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
.myWriteDate{
	display: block;
	
}
.yourWriteDate{
	display: block;
}
</style>
<script>
	$(function() {
		$("#send").click(function() {

			let obj = {
				"content" : $('#content').val(),
				"cno" : $("#cno").val()
			};
			$.ajax({
				//////content type 명시하지 않음
				type : "post",
				url : "/chatAjax/chatSendData.do",
				data : obj,
				success : function(data) {
				//	alert("성공");
					 $("#content").val("");
				},
			});
		});

	     chatGetData();         
	  
	      function chatGetData(){
	         let obj = {		
	               "maxNo" : $("#maxNo").data("max"),
	               "cno" : $("#cno").val()
	            	
	            };
	      
	      

 				setInterval(function(){
 					obj.maxNo = $("#maxNo").data("max");
	               $.ajax({
	                     url:"/chatAjax/chatGetData.do",
	      			data : obj,
	
	                     success:function(data){	
	                   		
	                        $("#overflow").append(data);
 	                         var cnoWriteNum =$(".cnoWriteNum:last").data("no");
									//alert("성공"+cnoWriteNum);
							$("#maxNo").data("max",cnoWriteNum);
	                     }
	                     
	               });
	             }, 1000);		          	   
   	      }
		
   	})
   	

//  	var interval = setInterval(function(){
 		
//  				$.ajax({
//  					url : "/chatAjax/chatGetData.do",
//  					success : function(cnt){
//  						$("#chatGetData").text(cnt);
//  					},
//  					error : function(){
//  						// 반복 처리되고 있는 것은 멈추게 한다.
//  						clearInterval(interval);
//  						location = "/chat/list.do";
//  					}
//  				});
//  			}, 1000);
	
// 	$(document).ready(function(){
			
// 		  $("#send").click(function(){
// 		    $("ol").append("<li>전송</li>");
// 		  });
// 		});
	
</script>



<meta charset="UTF-8">
<title>채팅 리스트</title>
<style type="text/css">

.front {

  display : flex;
/*       flex-direction: column; */
       justify-content: center; 
margin : 0 auto;
text-align : center;

}


#overflow {

  background: lightgray;
  color: black;
 border: 1px solid #5D5D5D;		
   width: 900px; 
  height: 300px; 
/*   margin : -150px 0px 0px -500px; */
/*   top : 30%; */
/*   left : 55%; */
/*    padding: 5px; */
  overflow: scroll;
/*  position : absolute; */
 margin: 0 auto;


/* display:inline-block; */
/* vertical-align:middle; */

  
  
}


h2 {
text-align: center;

}
textarea{
/*   border: 1px solid #5D5D5D;		  */
    width: 800px;  
     height: 50px;  
/*   margin : -25px 0px 0px -400px; */
/*   top : 50%; */
/*   left : 50%; */
/*    padding: 5px; */
/*   position : absolute; */
/*  margin: 0 auto; */
    resize:none; 
 display : inline-block;
 
}
 


.dataRow, button{
 width : 100px; 
/* margin:auto; */
/* display:block; */
/* text-align: center; */
 height: 50px; 
/*  top : 47.3%; */
/*   left : 71%; */
/*    padding: 5px; */
/*    position : absolute; */
/*  margin: 0 auto; */
/*   resize : none;   */
 display : inline-block;

 
	
}

.dataRow:hover {
	background: #eee;
	cursor: pointer;
}
</style>
<style>
/* body { padding:0px; margin:0px;} */
/* #overflow { */

/*   background: lightblue; */
/*   color: black; */
/*  border: 1px solid #5D5D5D;		 */
/*   width: 900px; */
/*   height: 300px; */
/*   margin : -150px 0px 0px -500px; */
/*   top : 30%; */
/*   left : 55%; */
/*    padding: 5px; */
/*   overflow: scroll; */
 
/*   position : absolute; */
  
  
/* } */

	

</style>	
</head>
<body>
<h2>채팅방</h2>	

<div data-max="${MaxNo}" id = "maxNo">

<div class = "chatView" id = "overflow"> </div>
<div class = "front">
<input type="hidden" value="${param.cno }" id="cno">

<div class = "chatWrite" >
<textarea rows="4" id="content" ></textarea>
</div>
<div class = "chatButton" >

<button id="send" >전송</button>	
</div>

</div>
</div>


</body>

</html>