'use strict';

$(document).ready(function(){

	$("#issue-update-form").submit(function(e){
		e.preventDefault();
	});	
	getAllData();
	updateData();	
});

/**
 * 
 */
function getAllData(){
	var url = "fetchData";
	var a= window.location.href;
	var c = a.split('=');
	var issueId = c[1];
	var param = {
			idInput : issueId
	};
	$.get(url,param,function(response) {
		var obj = JSON.parse(response);
		$("#id-input").val(obj[0].issueId);
		$("#issue-input").val(obj[0].issueTitle);
		$("#assignee-input").val(obj[0].assignee);
		$("#priority-input").val(obj[0].priority);
});
}

/**
 * update record in db
 */
function updateData() {
	$("#save-btn").click(function(e){
		var url = "update";
		var id=$("#id-input").val();
		var issue = $("#issue-input").val();
		var assignee = $("#assignee-input").val();
		var priority = $("#priority-input").val();
		var param = {
				idInput : id,
				issueInput : issue,
				assigneeInput : assignee,
				priorityInput : priority,
		};
		$.post(url,param,function(response){
			var obj = JSON.parse(response);
			if(obj==true) {
				window.location.href="/servlet-spring-mvc/insertSuccess";
			}else {
				console.log("There is an Issue");
			}
		});
	});
	
	$("#exit-btn").click(function(e){
		var id=$("#id-input").val();
		if(id!=null){
			window.location.href="/servlet-spring-mvc";	
		}else{
			alert("save data")
		}
	});
}

