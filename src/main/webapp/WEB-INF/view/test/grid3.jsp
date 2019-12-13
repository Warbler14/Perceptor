<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/header.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>gridTest3</title>

<style>
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
	grid-template-columns: 200px 500px 500px 100px;
	grid-template-rows: 100px 500px 500px 100px;
	grid-template-areas: 
		"header header header header"
		"leftCol midCol1 midCol2 rightCol"
		"leftCol midCol1 midCol2 rightCol" 
		"footer footer footer footer";
	grid-gap: 5px;
}
</style>

</head>
<body>

	<div class="wrapper">
		<div class="header">Header</div>
		<div class="leftCol">LeftCol</div>
		<div class="rightCol">RightCol</div>
		<div class="midCol1">midCol1</div>
		<div class="midCol2">midCol2</div>
		<div class="footer">Footer</div>
	</div>

</body>
</html>