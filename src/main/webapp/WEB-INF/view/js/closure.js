goog.provide('example');
goog.provide('example.add');

goog.require('goog.events.Listenable');
goog.require('goog.dom');
goog.require('goog.ui.Button');
goog.require('goog.net.XhrIo');
goog.require('goog.json');


example.sayHello = function(message){
	goog.dom.getElement('hello').innerHTML = message;
};

example = function(){
	//var buttonId = goog.dom.getElement('save-btn');
	var buttonId = goog.dom.getElement('save-btn');
	goog.events.listen(buttonId,'click',function(){
		goog.net.XhrIo.send('ServletDbController',function(response){
			var table="<tr><th>Issue ID</th><th>Issue Title</th><th>Assignee</th><th>Priority</th></tr>";
			var rowCountDive = document.getElementById("row-count");
			var obj = goog.json.parse(response.target.xhr_.responseText);
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
		});
	});

};

example.add = function(issueId){
	console.log(issueId);
};