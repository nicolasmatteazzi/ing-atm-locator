
var atmModule = angular.module("atmModule", []);
	
atmModule.controller("atmController", [ '$scope', '$http', '$mdMedia', function($scope, $http, $mdMedia) {

	$scope.init = function() {
		
		$scope.gtSm = $mdMedia('gt-sm');

		$scope.searchAtms();
	}
	
	/**
	 * Triggers the search for atms in the specified city
	 */
	$scope.searchAtms = function() {
		
		$scope.searching = true;
		
		var cityFilter = $scope.searchedCity == null ? '' : '?city=' + $scope.searchedCity;

		$http.get("api/atms" + cityFilter).success(function(data) {
			
			$scope.atms = data.atms;
			
			$scope.searching = false;
		});
	}
	
	$scope.init();
	
} ]);

