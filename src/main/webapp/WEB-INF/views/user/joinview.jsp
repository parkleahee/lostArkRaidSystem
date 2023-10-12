<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원가입</title>
    <script>
    const cp = '${cp}';
    </script>
<style>
	*{
		font-family: '123RF';
		font-size: 20px;
		color: white;
	}
    #wrap{
        width:600px;
        margin: 0 auto;
        
    }
   body{
      background-color:rgb(11,18,19);
   }
   input{
      box-sizing: border-box;
      cursor:pointer;
   }
   table{
      border-collapse: collapse;
   }
   th{
      text-align: left;
   }
   th::after{
      content:"";
      display:inline-block;
      box-sizing:border-box;
      width:1px;
      height:14px;
      
   }
   th,td{
      padding:5px;
   }
   td{
      padding-left:20px;
      width:400px;
   }
   input[type=text], input[type=password], input[type=email],input[type=date],input[type=tel],select{
      padding:10px 15px 10px 10px;
      border:1px solid #ccc;
      width:280px;
      color: navy;
   }
   input:focus{
      outline:none;
      border:1px solid rgb(0,200,80);
   }
  	#wrap>p:first-child{
  	text-align: center;
  	font-size: 30px;
  	margin-top: 30px;
  	}
   
   td > input[type=text]+input[type=button], td > input[type=email]+input[type=button], td > input[type=hidden]+input[type=button]{
      margin-left:10px;
      padding:8px 10px;
      background-color:rgb(50,50,50);
      color:#fff;
      font-size:14px;
      font-weight:bold;
      border:none;
      border-radius:5px;
      width:100px;
   }
/*       td > input[type=email]+input[type=button]{
      margin-left:10px;
      padding:8px 10px;
      background-color:rgb(50,50,50);
      color:#fff;
      font-size:14px;
      font-weight:bold;
      border:none;
      border-radius:5px;
      width:100px;
   }
      td > input[type=hidden]+input[type=button]{
      margin-left:10px;
      padding:8px 10px;
      background-color:rgb(50,50,50);
      color:#fff;
      font-size:14px;
      font-weight:bold;
      border:none;
      border-radius:5px;
      width:100px;
   } */
   .gender_area > td{
      font-size:16px;
   }
   .zipcode_area > td > input[type=text]{
      width:200px;
   }
   .zipcode_area > td > input[type=button]{
      width:130px !important;
   }
   .addr_area > td > input[type=text], .addr_area+tr > td > input[type=text], .addr_area+tr+tr > td > input[type=text]{
      width:340px;
   }
   .hobby_area > td > div{
        display: flex;
      width:360px;
      flex-wrap: wrap;
   }
   .hobby_area > td > div > div{
      padding:10px;
      flex:1 1 40%;
   }
   .hobby_area > td > div > div:nth-child(2n){
      border-left:1px solid #ccc;
   }
   th input[type=submit]{
      margin:0 auto;
      padding:10px 10px;
      display : block;
      background-color:rgb(50,50,50);
      color:#fff;
      font-size:20px;
      font-weight:bold;
      border:none;
      border-radius:5px;
      width:200px;
   }
   #result{
      color:white;
      font-weight: bold;
   }
   #clone_proof{
 	 position: absolute;
   visibility: hidden;
   }
	#proofchecktr{
	display: none;
	}
	#pwtxt{
		font-size: 15px;
		text-align: center;
	}
	#character > option{
		color: black;
	}
</style>
	<link rel="stylesheet" href="${cp}/resources/css/index_join_login.css">
</head>
<body>
	 <input type="hidden" name="message" id="message" >
	     <video muted autoplay loop>
			 <source src="${cp}/resources/images/v.mp4" type="video/mp4" >
			 <strong>Your browser does not support the video tag.</strong>
		</video>
	<div id="black_box"></div>
   <div id="wrap">
  		<p> 회원가입 </p>
        <form name="joinForm" method="post" action="${cp }/user/joinok" onsubmit="return sendit()">
            <table>
                <tr>
                    <th><label for="userid">아이디</label></th>
                    <td><input type="text" name="userid" id="userid"><input type="button" value="중복검사" onclick="checkId()"></td>
                </tr>
                <tr>
                    <td id="result" colspan="2"></td>
                </tr>         
                <tr>
                    <th><label for="userpw">비밀번호</label></th>
                    <td><input type="password" name="userpw" id="userpw" onkeyup="pwcheck()"></td>
                </tr>
                <tr>
                    <th><label for="userpw_re">비밀번호 확인</label></th>
                    <td><input type="password" name="userpw_re" id="userpw_re" onkeyup="pwcheck()"></td>
                </tr>
                <tr>
                    <td colspan="2"><span id="pwtxt">비밀번호는 대문자와 소문자 특수문자를 넣은 8자 이상의 문자열입니다</span></td>
                </tr>
                 <tr>
                    <th><label for="username">이름</label></th>
                    <td><input type="text" name="username" id="username"></td>
                </tr>
                <tr>
                    <th><label for="useremail">Email</label></th>
                    <td><input type="email" name="useremail" placeholder="이메일을 입력해 주세요">
                    </td>
                </tr>
                <tr>
                    <th><label for="apikey">LostArkAPIKEY</label></th>
                    <td><input type="text" name="userApiKey" id="userApiKey"><input type="button" value="중복검사" onclick="checkApikey()"></td>
                </tr>
                <tr>
                    <th><label for="apikey">캐릭터 검색</label></th>
                    <td><input type="text" name="search_characterName" id="search_characterName">
                    <input type="button" value="캐릭터검색" onclick="searchCharacter()"></td>
                </tr>
                <tr>
                    <th><label for="character">대표캐릭터 선택</label></th>
                    <td>
                    		<select name="character" id="character">
                    		</select>
                    </td>
                </tr>
                <tr>
                    <th><label for="discordName">DiscordName</label></th>
                    <td><input type="text" name="discordName" id="discordName"><input type="button" value="인증번호 발송" onclick="centificationSend()"></td>
                </tr>
                <tr>
                    <th colspan="2">
                        <input type="submit" value="가입 완료">
                        <!-- <button></button> input:submit -->
                    </th>
                </tr>
            </table>
        </form>
    </div>
</body>
 <script src="${cp}/resources/js/user.js"></script>
</html>