<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
	const cp ='${cp}';
	
	function goPage(path,page) {
		console.log(cp+"/"+path+"/"+page)
		location.href = cp+"/"+path+"/"+page;
	}
	
    function goPageParam(path,page, ...params){
        let param = params.map(
            p => p+'='+document.getElementById(p).value
            )
            .join('&');
        location.href = cp+"/"+path+"/"+page+"?"+param;
    }
</script>