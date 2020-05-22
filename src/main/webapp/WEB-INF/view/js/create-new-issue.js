'use strict';

$(document).ready(function(){

	$("#book-input-form").submit(function(e){
		e.preventDefault();
	});	
	insertData();
	
});

/**
 * Insert record in db
 */
function insertData(){
	var deferred = $.Deferred();
	$.Deferred().resolve().then(function(){
	$("#save-btn").click(function(e){
		var url="insert";
		var id1 = $("#id-input").val();
		var id2 = id1.trim();
		if(id2!==null){
		var issue = $("#issue-input").val();
		var assignee = $("#assignee-input").val();
		var priority = $("#priority-input").val();
		var param = {
				issueId : id2,
				issueTitle : issue,
				assignee : assignee,
				priority : priority		 		
		};
		$.post(url,param,function(response){
			if(JSON.parse(response)[0].status==true) {
				window.location.href="/servlet-spring-mvc/insertSuccess";
			}else {
				alert(JSON.parse(response)[0].errorMessage.split(":")[1]);
				$("#id-input").focus();
			}
		});
		}else{
			alert("Enter IssueId");
			$("#id-input").focus();
		}
		
	});
	
	}).then(function(){
		deferred.resolve();
	}).fail(function(){
		deferred.reject(); 
	});
	
	$("#exit-btn").click(function(e){
		window.location.href="/servlet-spring-mvc";
	});
	
	$("#add-btn").click(function(e){
		window.location.href="/servlet-spring-mvc/create";
		$("#id-input").focus();
	});	
	
	$("#exit-btn-main").click(function(e){
			window.location.href="/servlet-spring-mvc";	
		});	
	return deferred.promise();
}

