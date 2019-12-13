<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/header.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>testPage6</title>

<style type="text/css">
.header {
	grid-area: header;
	background-color: LightSeaGreen;
}

.leftCol {
	grid-area: leftCol;
	background-color: orange;
}

.rightCol {
	grid-area: rightCol;
	background-color: lightblue;
}

.midCol1 {
	grid-area: midCol1;
	background-color: lightgrey;
}

.midCol2 {
	grid-area: midCol2;
	background-color: pink;
}

.footer {
	grid-area: footer;
	background-color: lightgreen;
}

.wrapper {
	display: grid;
	/* grid-template-columns: 1fr 4fr 4fr 1fr; */
	grid-template-columns: 200px 600px 600px 100px;
	grid-template-rows: 200px 500px 500px 100px;
	grid-template-areas: 
		"header header header header"
		"leftCol midCol1 midCol2 rightCol"
		"leftCol midCol1 midCol2 rightCol" 
		"footer footer footer footer";
	grid-gap: 5px;
}

#form #urls {
	width: 500px;
}

</style>


</head>

<body>
<div class="wrapper">
		<div class="header">Header</div>
		<div class="leftCol">LeftCol</div>
		<div class="rightCol">RightCol</div>
		<div class="midCol1">midCol1
			<form id="form">
				<table>
					<tr>
						<td>
							<span>id</span>
						</td>
						<td>
							<input id="userId" type="text" name="userId" value="testUser" />
						</td>
					</tr>
					<tr>
						<td>
							<span>password</span>
						</td>
						<td>
							<input id="password" type="text" name="password" value="testUser" />
						</td>
					</tr>
					<tr>
						<td>
							<span>target</span>
						</td>
						<td>
							<input id="urls" type="text" name="urls" value="https://image.shutterstock.com/image-photo/colorful-flower-on-dark-tropical-260nw-721703848.jpg" />
						</td>
					</tr>
				</table>
			</form>	
			<input type="button" id="execute" value="execute" /><br/>
		
		</div>
		<div class="midCol2">midCol2<br/>
			<img class="downloadImg" alt="downloadImg" src="">
		</div>
		
		<div class="footer">Footer</div>
</div>



<script>

	$(function () { 
	});

	$('#execute').click(function() {

		var urls = $("#urls").val();
		$(".midCol2 .downloadImg").attr("src", urls);
		
		$.ajax({
			url : './ajaxDownloadFile',
			type : 'post',
			contentType : 'application/json',
			dataType : 'json',
			data : getJsonObjectBySerializeForm($("#form")),
			success : function(data) {
				var dataMap = JSON.stringify(data.data);

				console.log(dataMap);
				
			}
		})
	});
	

	
</script>

</body>
</html>