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

.midCol {
	grid-area: midCol;
	background-color: lightgrey;
}

.footer {
	grid-area: footer;
	background-color: lightgreen;
}

.wrapper {
	display: grid;
	/* grid-template-columns: 1fr 4fr 4fr 1fr; */
	grid-template-columns: 200px 1200px 100px;
	grid-template-rows: 200px 1000px 100px;
	grid-template-areas: "header header header" "leftCol midCol rightCol"
		"leftCol midCol rightCol" "footer footer footer";
	grid-gap: 5px;
}

#form #urls {
	width: 500px;
}

#resultTable {
	width: 100%;
	border: 1px solid #444444;
}

#resultTable, th, td {
	border: 1px solid #444444;
}
</style>


</head>

<body>
<div class="wrapper">
		<div class="header">Header</div>
		<div class="leftCol">LeftCol</div>
		<div class="rightCol">RightCol</div>
		<div class="midCol">midCol1
			<input type="button" id="execute" value="execute" /><br/>
			<form id="form">
				<table>
					<tr>
						<td>
							<span>target</span>
						</td>
						<td>
							<input id="urls" type="text" name="urls" value="https://image.shutterstock.com/image-photo/colorful-flower-on-dark-tropical-260nw-721703848.jpg" />
						</td>
					</tr>
				</table>
				<table id="resultTable">
					<tr>
						<td>
							<div id="targetImg">
							</div>
						</td>
						<td>
							<div id="imageBox">
							</div>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div class="footer">Footer</div>
</div>



<script>

	$(function () { 
	});

	$('#execute').click(function() {

//		var urls = $("#urls").val();
//		$(".midCol2 .downloadImg").attr("src", urls);
		
		$.ajax({
			url : './ajaxImgHistView',
			type : 'post',
			contentType : 'application/json',
			dataType : 'json',
			data : getJsonObjectBySerializeForm($("#form")),
			success : function(data) {
				var dataMap = JSON.stringify(data.data);

				console.log(dataMap);
				var srcPath = "http://localhost:8081/viewLocalImg?urls=" + data.data.name;
				console.log(srcPath);

				addImg("resultTable", $("#urls").val(), srcPath);
				
				//https://p.bigstockphoto.com/GeFvQkBbSLaMdpKXF1Zv_bigstock-Aerial-View-Of-Blue-Lakes-And--227291596.jpg
				
			}
		})
	});

	
	function addImg(divId, orgPath, hisPath){
		var tag = "<tr><td><img src='" + orgPath + "'/></td>"
					+"<td><img src='" + hisPath + "'/></td></tr>";
		$("#" + divId).append(tag);
	}

	
</script>

</body>
</html>