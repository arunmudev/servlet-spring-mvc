goog.provide('servlet.spring.mvc');

//goog.required("goog.dom.forms"); 

//goog.require('goog.json.Serializer');

servlet.spring.mvc = function(addButton,editButton,deleteButton){
	console.log(addButton);
	console.log(editButton);
	console.log(deleteButton);
	
};

servlet.spring.mvc.prototype.getAllData = function(){
	console.log();
};

function main(){
	document.getElementById("#issue-input-form");
	var addButton = document.getElementById("add-btn");
	console.log(addButton);
	var editButton = document.getElementById("edit-btn");
	var deleteButton = document.getElementById("delete-btn");
	var color = goog.string.isNumeric("eee");
	var compoentsId = servlet.spring.mvc(addButton,editButton,deleteButton);
}
main();