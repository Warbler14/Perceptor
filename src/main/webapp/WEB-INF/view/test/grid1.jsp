<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/header.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>gridTest1</title>

<style>
.wrapper {
	display: flex;
	justify-content: space-around;
	border: 1px solid green;
	padding: 10px;
	flex-wrap: wrap;
}

.item {
	border: 1px solid blue;
	width: 400px;
}

.outer {
	display: table;
	position: absolute;
	top: 0;
	left: 0;
	height: 100%;
	width: 100%;
}

.middle {
	display: table-cell;
	vertical-align: middle;
	width: 600px;
	height: 600px;
	/* background-color: black; */
}

.inner {
	margin-left: auto;
	margin-right: auto;
	/* width: 200px; */
	/*whatever width you want*/
	background-color: orange;
}

.innerImg {
	margin-left: auto;
	margin-right: auto;
	width:200px;
	height:100px;
}
</style>

</head>
<body>

	<div class="wrapper">
		<div class="item">1</div>
		<div class="item">2</div>
		<div class="item">3</div>
		<div class="item">4</div>
		<div class="item">5</div>
	</div>

	<div class="outer">
		<div class="middle">
			<div class="inner">
				<!-- <h1>The Content</h1> -->
				<img class="innerImg" src="https://image.shutterstock.com/image-photo/sample-wood-chipboard-wooden-laminate-600w-1343662607.jpg" >
			</div>
		</div>
	</div>
	
<script>

	$(function () {
		resize();
	});

	function resize(){
		var width = $(".innerImg").width() + 'px';
		
		$(".inner").width(width);
		alert(width);
	}
	

	
</script>
</body>
</html>