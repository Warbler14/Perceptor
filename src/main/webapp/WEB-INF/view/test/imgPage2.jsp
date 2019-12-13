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
	/* background-color: lightgreen; */
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
	background-color: white;
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
							<span>target1</span>
						</td>
						<td>
							<input id="targetPath" type="text" name="targetPath" value="normal_main_0_0_save.png" style="width: 1000px;"/>
						</td>
					</tr>
					<tr>
						<td>
							<span>target2</span>
						</td>
						<td>
							<input id="urls" type="text" name="urls" value="http://image.hyundaihmall.com/dtv/cloud/cug/KT/720/normal_main_0_0.png" style="width: 1000px;"/>
							<br/>
							<span>http://localhost:8081/images/temp/normal_main_0_0_test.png</span>
						</td>
					</tr>
				</table>
				<table id="resultTable">
					
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
			url : './ajaxCompareImgHistView',
			type : 'post',
			contentType : 'application/json',
			dataType : 'json',
			data : getJsonObjectBySerializeForm($("#form")),
			success : function(data) {
				var dataMap = JSON.stringify(data.data);

				console.log(dataMap);

				var orgPath = "http://localhost:8081/viewLocalImg?urls=" + $("#targetPath").val();
				var compPath = $("#urls").val();
				var hisPath = "http://localhost:8081/viewLocalImg?urls=" + data.data.name;
				console.log(hisPath);
				
				addImg("resultTable", orgPath, compPath, hisPath);
				
				//https://p.bigstockphoto.com/GeFvQkBbSLaMdpKXF1Zv_bigstock-Aerial-View-Of-Blue-Lakes-And--227291596.jpg
				
			}
		})
	});

	
	function addImg(divId, orgPath, compPath, hisPath){
		var tag = "<tr><td><img src='" + orgPath + "'/></td></tr>"
					+"<tr><td><img src='" + compPath + "'/></td></tr>"
					+"<tr><td><img src='" + hisPath + "'/></td></tr>";
		$("#" + divId).append(tag);
	}

	
</script>

</body>
</html>