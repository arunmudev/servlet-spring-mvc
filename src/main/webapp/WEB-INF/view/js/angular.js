function Hello($scope,$http){
	$scope.getUserDetails = function(){
		$http.get('http://localhost:8080/servlet-spring-mvc/').
		success(function(data){
			
		});
	}
}