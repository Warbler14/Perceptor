<%@ include file="/WEB-INF/view/include/header.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>test page 3</title>

</head>
<body>
testPage

<p>time : <span id="time"></span></p>
<form id="form">
  
  	<input id="targetPath" name="targetPath" value="" /><br/>
  
    <select id="timezone" name="timezone">
        <option value="Asia/Seoul" selected>asia/seoul</option>
        <option value="America/New_York">America/New_York</option>
    </select>
    <select id="format" name="format">
        <option value="Y-m-d H:i:s" selected>Y-m-d H:i:s</option>
        <option value="Y-m-d">Y-m-d</option>
    </select>
     
</form>
<input type="button" id="execute" value="execute" />
<script>
    $('#execute').click(function(){
    	
        $.ajax({
        	url:'./ajaxTest3',
            type:'post',
            contentType: 'application/json',
            dataType:'json',
            data : getJsonObjectBySerializeForm('form'),
            success:function(data){
            	var time = data.data.time;
                $('#time').text(time);
            }
        })
    })
    
    function getJsonObjectBySerializeForm( id ){
    	var formdata = $("#" + id).serializeArray();
    	var dataMap = {};
    	$(formdata ).each(function(index, obj){
    		dataMap[obj.name] = obj.value;
    	});
    	return JSON.stringify(dataMap);    	
    }
</script>

</body>
</html>