<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/header.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>testPage5</title>

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
/* ============================ */
.box {
	border: 4px groove #bcbcbc;
}

.jstreeDiv {
	border: 3px groove #bcbcbc;
}
</style>


<!-- <script type="text/javascript" src="./js/common/common.js"></script> -->

<link rel="stylesheet" href="./jstree/themes/default/style.min.css" />
<script src="./jstree/jstree.min.js"></script>

</head>

<body>
<div class="wrapper">
		<div class="header">Header
			<!-- 만들어야 할 것, 해야 할 일을 명확하게 인지하는게 중요 -->
			<span>선택한 파일 정보를 표본 대상으로 등록</span><br/>
			<span>화면과 기능 설계 필요함</span>
			<span>감시 조건 파일 정보 입력(파일 이름, 예약 시간)</span><br/>
			<span>선택한 디렉토리 안의 내용을 표본 대상으로 등록</span><br/>
			<span>감시 조건 파일 정보 입력(디렉토리, 파일 전체, 일부 파일, 예약 시간)</span><br/>
			<span>감시 프로세스를 DB 에 추가</span><br/>
			<span>감시 목록 조회</span><br/>
			<span>표본 파일 백업</span><br/>
			<span>감시 프로세스 관리(시작, 중지, 예약)</span><br/>
		</div>
		<div class="leftCol">LeftCol</div>
		<div class="rightCol">RightCol</div>
		<div class="midCol1">midCol1
		
			<div id="fileInfo" class="box">
				<div class="jstreeDiv"></div>
			</div>
			<form id="form">
				<span>targetPath : </span><input id="targetPath" type="text" name="targetPath" value="C:\Intel" /><br />
			</form>	
			<input type="button" id="execute" value="execute" /><br/>
			
			<p>data : <span id="dataMap"></span></p>
		
		</div>
		<div class="midCol2">midCol2
			
			<form id="fileForm">
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
							<input class="targetPath" type="text" name="targetPath"  value=""/>
						</td>
					</tr>
					<tr>
						<td>
							<span>targetParent</span>
						</td>
						<td>
							<input class="targetParentPath" type="text" name="targetParentPath"  value=""/>
						</td>
					</tr>
				</table>
			</form>
			
			<input type="button" id="getFileInfo" value="getFileInfo" />
			<input type="button" id="addTask" value="addTask" />
			<br/>
			
			<p>data : <span id="textFileInfo"></span></p>
		</div>
		
		<div class="footer">Footer</div>
</div>



<script>

	$(function () { 
	});
	
	function jstreeLoad( target, data){
		target.jstree({
			  'plugins': ["wholerow"],
			  'core' : {
			  'data' : data
			  }
		});
	}
	  
	$('#fileInfo .jstreeDiv').on('activate_node.jstree', function (e, data) {
		if (data == undefined || data.node == undefined) {
			return;
		}
		
		try{
			var nodeData = data.node.data;
			var name = nodeData.name;
			var canonicalPath = nodeData.canonicalPath;
			var parent = nodeData.parent;
			
			
			console.log('name:' + name 
					+ '\n canonicalPath:' + canonicalPath
					+ '\n parent:' + parent);
			
			setValue($("#fileForm .targetPath"), canonicalPath);
			setValue($("#fileForm .targetParentPath"), parent);
			
		} catch (e){
			console.log(e.value);
		}
	});

	$('#execute').click(function() {

		console.log(getJsonObjectBySerializeForm($("#form")));
		
		$.ajax({
			url : './ajaxFindFolder',
			type : 'post',
			contentType : 'application/json',
			dataType : 'json',
			data : getJsonObjectBySerializeForm($("#form")),
			success : function(data) {
				var dataMap = JSON.stringify(data.data);

				console.log(dataMap);

				$('#dataMap').text(dataMap);
				
				jstreeLoad($('#fileInfo .jstreeDiv'), data.data);
				
			}
		})
	});
	
	$('#getFileInfo').click(function() {
		console.log( getJsonObjectBySerializeForm($("#fileForm")) );
		
		$.ajax({
			url : './ajaxFindFile',
			type : 'post',
			contentType : 'application/json',
			dataType : 'json',
			data : getJsonObjectBySerializeForm($("#fileForm")),
			success : function(data) {
				var textFileInfo = JSON.stringify(data.data);

				console.log(textFileInfo);

				$('#textFileInfo').text(textFileInfo);
				
			}
		})
	});
	
	$('#addTask').click(function() {
		
		$.ajax({
			url : './ajaxAddTask',
			type : 'post',
			contentType : 'application/json',
			dataType : 'json',
			data : getJsonObjectBySerializeForm($("#fileForm")),
			success : function(data) {
				
				
			}
		})
	});

	
</script>

</body>
</html>