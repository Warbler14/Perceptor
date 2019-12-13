/**
 * 
 */


function getJsonObjectBySerializeForm(formObject) {
	var formdata = formObject.serializeArray();
	var dataMap = {};
	$(formdata).each(function(index, obj) {
		dataMap[obj.name] = obj.value;
	});
	return JSON.stringify(dataMap);
}

function isEmpty(val){
    return (val === undefined || val == null || val.length <= 0) ? true : false;
}

function setValue( object, value ){
	try {
		if(! isEmpty(value)){ 
			object.val(value); 
		}
	} catch (e) {
		console.log(e.value);
		object.val(''); 
	}
}