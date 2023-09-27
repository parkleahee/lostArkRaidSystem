const joinForm = document.joinForm;

function sendit(){
    
    const userid = joinForm.userid;
    if(userid.value == ""){
        alert("아이디를 입력하세요!")
        userid.focus();
        return false;
    }
    if(userid.value.length < 5 || userid.value.length > 12){
        alert("아이디는 5자 이상 12자 이하로 입력하세요!");
        userid.focus();
        return false;
    }
    
    const result = document.getElementById("result");
    if(result.innerHTML == "&nbsp;"){
    	alert("아이디 중복검사를 진행해주세요!");
    	userid.focus();
    	return false;
    }
    if(result.innerHTML == "중복된 아이디가 있습니다!"){
    	alert("중복체크 통과 후 가입이 가능합니다!");
    	userid.focus();
    	return false;
    }
    
    const userpw = joinForm.userpw;
    const userpw_re = joinForm.userpw_re;
    if(userpw.value == ""){
        alert("비밀번호를 입력하세요!");
        userpw.focus();
        return false;
    }
    const reg = /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[~?!@#$%^&*_-]).{8,}$/;
    if(!reg.test(userpw.value)){
    	alert("비밀번호는 8자 이상, 숫자, 대문자, 소문자, 특수문자를 모두 하나 이상 포함해야 합니다!");
    	userpw.focus();
    	return false;
    }
    if(/(\w)\1\1\1/.test(userpw.value)){
    	alert("같은 문자를 4번 연속해서 사용할 수 없습니다!")
    	userpw.focus();
    	return false;
    }
    if(userpw.value.search(/\s/) != -1){
    	alert("비밀번호는 공백을 포함할 수 없습니다!");
    	userpw.focus();
    	return false;
    }
    if(userpw_re.value == ""){
    	alert("비밀번호 확인을 해주세요!");
    	userpw_re.focus();
    	return false;
    }
    if(userpw.value != userpw_re.value){
    	alert("비밀번호 확인을 다시 해주세요!");
    	userpw.focus();
    	return false;
    }
    
    const username = joinForm.username;
    if(username.value == ""){
        alert("이름을 입력하세요!");
        username.focus();
        return false;
    }
    const exp_name = /^[가-힣]+$/;
    if(!exp_name.test(username.value)){
        alert("이름에는 한글만 입력하세요!");
        username.focus();
        return false;
    }
    
    const character = joinForm.character;
    if(character.value==''){
    	alert("캐릭터 선택을 진행하세요!");
    	return false;
    }


    return true;
}

function checkId(){
	const xhr = new XMLHttpRequest();
	const result = document.getElementById("result");
	const userid = document.joinForm.userid;
	if(userid.value == ""){
		alert("아이디를 입력하세요!");
		userid.focus();
		return false;
	}
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4){
			if(xhr.status == 200){
				let txt = xhr.responseText;
				txt = txt.trim();
				if(txt == "O"){
					result.innerHTML = "사용할 수 있는 아이디입니다!";
					document.joinForm.userpw.focus();
				}
				else{
					result.innerHTML = "중복된 아이디가 있습니다!";
					userid.value = "";
					userid.focus();
				}
			}
		}
	}
	
	xhr.open("GET",cp+"/user/checkidok?userid="+userid.value,true);
	xhr.send();
}



function searchCharacter() {
	const xhr = new XMLHttpRequest();
	const userApiKey = document.getElementById("userApiKey");
	const charcaterName = document.joinForm.search_characterName;
	
    let character = document.getElementById('character');
    while (character.firstElementChild) {
    	character.removeChild(character.firstElementChild);
    }
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4){
			if(xhr.status == 200){
//				let txt = xhr.responseText;
//				console.log(txt);
//				console.log(xhr.response);
				const parser = new DOMParser();
				const xmlDoc = parser.parseFromString(xhr.response, 'text/xml');

				// item 엘리먼트를 모두 가져오기
				const itemElements = xmlDoc.getElementsByTagName('item');

				// 각 item을 순회하면서 필요한 정보 추출
				for (let i = 0; i < itemElements.length; i++) {
				    const item = itemElements[i];
				    const characterName = item.getElementsByTagName('characterName')[0].textContent;
				    const characterLevel = item.getElementsByTagName('characterLevel')[0].textContent;
				    const characterClassName = item.getElementsByTagName('characterClassName')[0].textContent;
				    const itemAvgLevel = item.getElementsByTagName('itemAvgLevel')[0].textContent;
				    const itemMaxLevel = item.getElementsByTagName('itemMaxLevel')[0].textContent;
				    const serverName = item.getElementsByTagName('serverName')[0].textContent;
				    
				    const option = document.createElement('option');
		            option.value = characterName;
		            option.innerHTML =serverName+" : "+characterName +" LV"+itemMaxLevel;
		            character.appendChild(option);
				    
				    // 추출한 정보를 활용하여 원하는 작업 수행
/*				    console.log(`Character Name: ${characterName}`);
				    console.log(`Character Level: ${characterLevel}`);
				    console.log(`Character Class Name: ${characterClassName}`);
				    console.log(`Item Avg Level: ${itemAvgLevel}`);
				    console.log(`Item Max Level: ${itemMaxLevel}`);
				    console.log(`serverName: ${serverName}`);*/
			}
		}
	}
	}
	//lostArk','getAllInfo
	xhr.open("GET",cp+"/user/searchCharcater?characterName="+charcaterName.value+'&userApiKey='+userApiKey.value,true);
	xhr.send();
}





