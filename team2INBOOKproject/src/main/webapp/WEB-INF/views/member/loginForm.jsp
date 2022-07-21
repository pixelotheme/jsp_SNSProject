<%@ page language="java" contentType="text/html; charset=UTF-8" errorPage="/error/loginError.jsp"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<style type="text/css">
html, body {
   width: 100%;
   height: 100%;
}

body {
   margin: 0 auto;
   display: table;
   text-align: center;
   font-family: 'Open Sans', sans-serif;
/*    background: #81b5d6; */
   max-width: 33em;
}

.wrap {
   margin-top: 50px;
}

input::placeholder {
  color: red;
  font-style: italic;
}

.flip-container {
   perspective: 1000;
   border-radius: 50%;
   margin: 0 auto 10px auto;
}

.logged-in {
   transform: rotateY(180deg);
}

.flip-container, .front, .back, .back-logo {
   width: 130px;
   height: 130px;
}

.flipper {
   transition-duration: 0.6s;
   transform-style: preserve-3d;
}

.front, .back {
   backface-visibility: hidden;
   position: absolute;
   top: 0;
   left: 0;
   background-size: cover;
}

.front {
   background: url(https://s8.postimg.org/y7z5wso29/Flip_Img.png) 0 0
      no-repeat;
}

.back {
   transform: rotateY(180deg);
   background: url(https://s8.postimg.org/u04do1mmp/Flip_Img2.png) 0 0
      no-repeat;
}

h1 {
   font-size: 30px;
   color: black;
}

h1 span {
   font-weight: 300;
}

input[type=text], input[type=password] {
   color: #fff;
   background: linear-gradient(45deg, #68add8 10%, #8cbede 50%);
   width: 250px;
   height: 40px;
   margin: 0 auto 10px auto;
   font-size: 14px;
   padding-left: 15px;
   border: none;
   box-shadow: -3px 3px #679acb;
   -webkit-appearance: none;
   border-radius: 0;
   border-top: 1px solid #92c5e2;
   border-right: 1px solid #92c5e2;
}

#welcome {
    color: white;
    margin: 0 auto;
    margin: 0 0 30px 0px;
}

span {
	font-size: 20px;
}



input::-webkit-input-placeholder { 
    color: red;
 } 

input:focus {
   outline: none;
}

input[type=submit] {
   color: #fff;
   background-color: #3f88b8;
   font-size: 14px;
   height: 40px;
   border: none;
   margin: 0 0 0 175px;
   padding: 0 20px 0 20px;
   -webkit-appearance: none;
   border-radius: 0;
   cursor: pointer;
}

input[type=submit]:hover {
   background-color: #3f7ba2;
}

 #box { 
    width : 100%;
    padding : 60px 0px 20px 0;
 	background: linear-gradient(45deg, #68add8 10%, #8cbede 30%);
    box-shadow: -8px 8px #679acb;
   -webkit-appearance: none;
   border-radius: 0;
   border-top: 1px solid #92c5e2;
   border-right: 1px solid #92c5e2;
 } 
</style>

</head>

<body>
<div class="wrap">
  
  <div class="flip-container" id='flippr'>
    <div class="flipper">
      <div class="front"></div>
      <div class="back"></div>
    </div>
  </div>
   <div class='container' id = 'box'>
      <h1 class="text" id="welcome">
         <span>INBOOK LOGIN</span>
      </h1>

      <form action="login.do" method='post' id="theForm">
         <input type='text' id="username" name='id' placeholder='Username'
            class="form-control" required="required" pattern="[a-zA-Z0-9]{4,20}">
         <input type='password' id='password' name='pw' placeholder='Password'
            class="form-control" required="required" pattern=".{4,20}">

         <div class='login'>
            <input type='submit' value='Login'>
         </div>
         <!-- /login -->
      </form>
      </div><!-- /wrap -->
   </div>
</body>
</html>