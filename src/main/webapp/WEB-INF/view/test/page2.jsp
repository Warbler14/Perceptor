<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

</head>
<body>
testPage

<p>time : <span id="time"></span></p>
<form id="form">
  
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

<script type="text/javascript"  src="./js/common/jquery-3.4.1.min.js"></script>
<script>
    $('#execute').click(function(){
    	//--------------------------------------------------
    	// test
    	//--------------------------------------------------
    	var dataForm = {
    		timezone : $("#timezone").val(),
    		format : $("#format").val()
    	}
    	
    	var formdata = $("#form").serializeArray();
    	var dataMap = {};
    	$(formdata ).each(function(index, obj){
    		dataMap[obj.name] = obj.value;
    	});
    	
    	console.log( $("#timezone").val() );
    	console.log( $("#format").val() );
    	console.log($('#form').serialize());
    	console.log($('#form').serializeArray());
    	console.log(dataMap);
    	console.log(dataForm);
    	//--------------------------------------------------
    	
        $.ajax({
        	url:'./ajaxTest2',
            type:'post',
            contentType: 'application/json',
            dataType:'json',
            //data : JSON.stringify(dataForm),
            //data : JSON.stringify(dataMap),
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