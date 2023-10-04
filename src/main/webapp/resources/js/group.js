const makeGroup = document.makeGroup;

function sendit(){
    
    const name = makeGroup.groupName;
    if(name.value == ""){
        alert("그룹명을 입력하세요!")
        return false;
    }

    
    const result = document.getElementById("result");
    if(result.innerHTML == "&nbsp;"){
    	alert("그룹명 중복검사를 진행해주세요!");
    	return false;
    }
    if(result.innerHTML == "중복된 그룹이 있습니다!"){
    	alert("중복체크 통과 후 생성이 가능합니다!");
    	return false;
    }
    


    return true;
}

function checkGroupName(){
	const xhr = new XMLHttpRequest();
	const result = document.getElementById("result");
	const name = document.makeGroup.groupName;
	if(name.value == ""){
		alert("그룹명을 입력하세요!");
		name.focus();
		return false;
	}
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4){
			if(xhr.status == 200){
				let txt = xhr.responseText;
				txt = txt.trim();
				if(txt == "O"){
					result.innerHTML = "사용할 수 있는 그룹입니다!";
				}
				else{
					result.innerHTML = "중복된 그룹이 있습니다!";
					name.value = "";
				}
			}
		}
	}
	
	xhr.open("GET",cp+"/group/checkGroupName?groupName="+name.value,true);
	xhr.send();
}









