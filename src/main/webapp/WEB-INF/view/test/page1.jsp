<%-- 
<%@ include file="../include/header.jsp" %>
 --%>
<%@ include file="/WEB-INF/view/include/header.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

</head>
<body>
testPage

<p>time : <span id="time"></span></p>

<input type="button" id="execute" value="execute" />

<!-- 
<script type="text/javascript"  src="./js/common/jquery-3.4.1.min.js"></script>
 -->
<script>
    $('#execute').click(function(){
    	var dataForm = {
    			timezone : "Asia",
    			format : "Y-m-d H:i:s"
    	}
    	
    	
        $.ajax({
            url:'./ajaxTest1',
            type:'post',
            contentType: "application/json",
            dataType:'json',
            data : JSON.stringify(dataForm),
            success:function(data){
            	var result = JSON.stringify(data);
            	console.log(result);

            	var obj = JSON.parse(result);
            	console.log(obj.data.time);
            	
                $('#time').text(obj.data.time);
            }
        })
    })
</script>

</body>
</html>