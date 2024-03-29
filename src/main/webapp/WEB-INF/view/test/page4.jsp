<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/header.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>testPage4</title>

<style type="text/css">
 	 .box {
        border: 4px groove #bcbcbc;
      }
      .jstreeDiv {
        border: 3px groove #bcbcbc;
      }
</style>


<script type="text/javascript" src="./js/common/common.js"></script>

<!-- 
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jstree/3.3.2/themes/default/style.min.css" />
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jstree/3.3.2/jstree.min.js"></script>
 -->
<link rel="stylesheet" href="./jstree/themes/default/style.min.css" />
<script src="./jstree/jstree.min.js"></script>

</head>
<body>
<span>${pageContext.request.contextPath}</span>


<p>data : <span id="dataMap"></span></p>


<input type="button" id="execute" value="execute" /><br/>
<form id="form">
  
  	<input id="targetPath" type="text" name="targetPath" value="C:\Intel" /><br/>

</form>

<div id="fileInfo1" class="box">
	<div class="jstreeDiv"></div>
	
	<span>nodeId : </span><input class="nodeId" type="text" value=""/><br/>
	<span>fileName : </span><input class="targetPath" type="text" value=""/><br/>

</div>

<div id="fileInfo2" class="box">
	<div class="jstreeDiv"></div>

	<span>nodeId : </span><input class="nodeId" type="text" value=""/><br/>
	<form id="fileForm">
		<span>fileName : </span><input class="targetPath" type="text" name="targetPath"  value=""/><br/>
	</form>
	
	<input type="button" id="getFileInfo" value="getFileInfo" /><br/>
	<p>data : <span id="textFileInfo"></span></p>
</div>


<script>

/* 	{
	  id          : "string" // will be autogenerated if omitted
	  text        : "string" // node text
	  icon        : "string" // string for custom
	  state       : {
	    opened    : boolean  // is the node open
	    disabled  : boolean  // is the node disabled
	    selected  : boolean  // is the node selected
	  },
	  children    : []  // array of strings or objects
	  li_attr     : {}  // attributes for the generated LI node
	  a_attr      : {}  // attributes for the generated A node
	}
	  */
	  
	// jsTree 사용예
	$(function () { 
		console.log('start');
		jsTree01();
		console.log('end');
	});
	  
	var sample1 = 
			[
	      {                            		 // 자료구조를 지켜서 넣거나
		         'text' : 'Root node 1',
		         'icon' : 'jstree-folder',
		         'state' : {
		           'opened' : true,
		           'selected' : true
		         },
		         'children' : [              // 자식노드들은 Array에 넣어야 한다
		           { 'text' : 'Child 1',
		        	 'icon' : 'jstree-file',
		        	 "data": {
		        	      "name": "1.txt",
		        	      "canonicalPath": "C:\\Intel\\1.txt",
		        	      "parent": "C:\\Intel",
		        	      "type": "file"
		        	 }
		           },
		           { 'text' : 'Child 2', 
		        	 'icon' : 'jstree-file',
		        	 "data": {
		        	      "name": "2.txt",
		        	      "canonicalPath": "C:\\Intel\\2.txt",
		        	      "parent": "C:\\Intel",
		        	      "type": "file"
		        	 }
		        	}
		         ]
		   },
	       {                             // 자료구조를 지켜서 넣거나
	         'text' : 'Root node 2',
	         'children' : [              // 자식노드들은 Array에 넣어야 한다
	           { 'text' : 'Child 1',
	        	   "data": {
		        	      "name": "3.txt",
		        	      "canonicalPath": "C:\\Intel\\3.txt",
		        	      "parent": "C:\\Intel",
		        	      "type": "file"
		        	 }
	        	},
	           'Child 2'
	         ]
	      },
	      'Simple root node',           // 단독으로 text만 넣거나
	    ]
	;
	
	var sample2 = [{"text":"g.txt","icon":"jstree-file",
						"data":{"name":"g.txt","canonicalPath":"C:\\Intel\\g.txt","parent":"C:\\Intel","type":"file"},
						"children":null},
						{"text":"Logs","icon":"jstree-file",
						"data":{"name":"Logs","canonicalPath":"C:\\Intel\\Logs","parent":"C:\\Intel","type":"folder"},
						"children":[{"text":"IntelChipset.log","icon":"jstree-file",
										"data":{"name":"IntelChipset.log","canonicalPath":"C:\\Intel\\Logs\\IntelChipset.log","parent":"C:\\Intel\\Logs","type":"file"},
										"children":null},
										{"text":"IntelCPHS.log","icon":"jstree-file",
										"data":{"name":"IntelCPHS.log","canonicalPath":"C:\\Intel\\Logs\\IntelCPHS.log","parent":"C:\\Intel\\Logs","type":"file"},
										"children":null},
										{"text":"IntelGFX.log","icon":"jstree-file",
										"data":{"name":"IntelGFX.log","canonicalPath":"C:\\Intel\\Logs\\IntelGFX.log","parent":"C:\\Intel\\Logs","type":"file"},
										"children":null},
										{"text":"IntelGFXCoin.log","icon":"jstree-file",
										"data":{"name":"IntelGFXCoin.log","canonicalPath":"C:\\Intel\\Logs\\IntelGFXCoin.log","parent":"C:\\Intel\\Logs","type":"file"},
										"children":null},
										{"text":"IntelIRST.log","icon":"jstree-file",
										"data":{"name":"IntelIRST.log","canonicalPath":"C:\\Intel\\Logs\\IntelIRST.log","parent":"C:\\Intel\\Logs","type":"file"},
										"children":null}]
										}
						]
	function jsTree01(){
		
		
		var children01 = {
				'text' : 'children04',
				'data' : {
	        		 'fileName' : 'folder children01'
	        	 },
		         'children' : [
		           { 'text' : 'Child 1',
		        	   "data": {
			        	      "name": "4.txt",
			        	      "canonicalPath": "C:\\Intel\\4.txt",
			        	      "parent": "C:\\Intel",
			        	      "type": "file"
			        	 }
		        	}
		         ]
		      };
		
		sample1.push(children01);
		
		var target = $('#fileInfo1 .jstreeDiv');
		jstreeLoad(target, sample1);
		
	}
	
	function jstreeLoad( target, data){
		target.jstree({
			  'plugins': ["wholerow"],
			  'core' : {
			  'data' : data
			  }
		});
	}
	  
	$('#fileInfo1 .jstreeDiv').on('activate_node.jstree', function (e, data) {
		if (data == undefined || data.node == undefined) {
			return;
		}
		
		try{
			var nodeId = data.node.id;
			var nodeData = data.node.data;
			var name = nodeData.name;
			var canonicalPath = nodeData.canonicalPath;
			var parent = nodeData.parent;
			
			
			console.log('nodeId: ' + nodeId 
					+ '\n name:' + name 
					+ '\n canonicalPath:' + canonicalPath
					+ '\n parent:' + parent);
			
			setValue($("#fileInfo1 .nodeId"), nodeId);
			setValue($("#fileInfo1 .targetPath"), canonicalPath);
			
		} catch (e){
			console.log(e.value);
		}
	});
	
	$('#fileInfo2 .jstreeDiv').on('activate_node.jstree', function (e, data) {
		if (data == undefined || data.node == undefined) {
			return;
		}
		
		try{
			var nodeId = data.node.id;
			var nodeData = data.node.data;
			var name = nodeData.name;
			var canonicalPath = nodeData.canonicalPath;
			var parent = nodeData.parent;
			
			
			console.log('nodeId: ' + nodeId 
					+ '\n name:' + name 
					+ '\n canonicalPath:' + canonicalPath
					+ '\n parent:' + parent);
			
			setValue($("#fileInfo2 .nodeId"), nodeId);
			setValue($("#fileInfo2 .targetPath"), canonicalPath);
			
		} catch (e){
			console.log(e.value);
		}
	});

	$('#execute').click(function() {

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
				
				jstreeLoad($('#fileInfo2 .jstreeDiv'), data.data);
				
			}
		})
	})
	
	$('#getFileInfo').click(function() {
		
		console.log($("#gg").val() );
		var dd = getJsonObjectBySerializeForm($("#fileForm"));
		console.log( dd );
		
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
	})

	
</script>

</body>
</html>