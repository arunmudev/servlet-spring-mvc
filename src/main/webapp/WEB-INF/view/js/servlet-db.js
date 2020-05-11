'use strict';


$(document).ready(function(){

	$("#issue-input-form").submit(function(e){
		e.preventDefault();
	});	
	getAllData();
	createNewIssue();
	selectGridRow();
	updateExistingIssue();
	deleteData();
});

/**
 * getAll Data from db
 */
function getAllData(){
	$.get('ServletDbController',function(response) {
		$("#id-input").val('');
		$("#issue-input").val('');
		$("#assignee-input").val('');
		$("#priority-input").val('Low');
		var table="<tr><th>Issue ID</th><th>Issue Title</th><th>Assignee</th><th>Priority</th></tr>";
		var rowCountDive = document.getElementById("row-count");
		var a = document.getElementById("issue_tracker");
		var obj = JSON.parse(response);
		var rowCount = obj.length;
		rowCountDive.innerHTML = rowCount + " rows";
		for(var i=0;i<obj.length;i++){				
			table += "<tr class='selected'>" +
			"<td>" +obj[i].issueId +"</td>"+
			"<td>" +obj[i].issueTitle +"</td>"+
			"<td>" +obj[i].assignee +"</td>"+
			"<td>" +obj[i].priority +"</td>"+
			"</tr>";
			document.getElementById("issue_tracker").innerHTML = table;	
		}	
		selectGridRow();
		updateExistingIssue();
	});
}

/**
 * create new issue
 * 
 */
function createNewIssue(){
	$("#add-btn").click(function(e){
		window.location.href="/servlet-spring-mvc/create";
		$("#id-input").focus();
	});
}

/**
 * selectGridRow function
 */
function selectGridRow(){	
	$("tr").click(function(e){
		$('.selected').removeClass('selected');
		$(this).find('td').addClass('selected');
		var issueId=$(this).find('td')[0].textContent;
		$(this).css("background-color","#205dac");
		updateExistingIssue(issueId);
		deleteData(issueId);
	});
}

/**
 * update Existing issue
 * 
 */
function updateExistingIssue(issueId){
	$("#edit-btn").click(function(e){
		if(issueId!=null){
			$.get("update",issueId,function(){
				window.location.href="/servlet-spring-mvc/update?issueId="+issueId;
			});
		}
	});

	$("tr").dblclick(function(e){
		var deferred = new $.Deferred();
		var currentRow = $(this).closest("tr");
		var issueId = currentRow.find("td:eq(0)").text();
		$.get("update",issueId,function(){
			window.location.href="/servlet-spring-mvc/update?issueId="+issueId;
			deferred.resolve();
		});
	});
}

/**
 * delete record from DB
 * 
 */
function deleteData(issueId){
	$("#delete-btn").click(function(e){
		var url = "delete";
		var param = {
				idInput : issueId
		};
		if(param["idInput"]!=null){
		$.post(url,param,function(response){
			//alert('Delete success');
			getAllData();
		});
		}
	});
}