var userCredentials = null;

var ingAtmLocatorModule = angular.module("ingAtmLocator", [ "ngRoute", "ngMaterial", "atmModule" ])
 .factory('AuthInterceptor', [function() {
	 return {
		 'request': function(config) {
			 
			 config.headers = config.headers || {};
			 
			 if (userCredentials) config.headers.authorization = 'Basic '+ btoa(userCredentials.username + ":" + userCredentials.password);
			 
			 return config;
		 }
	 };
 }])
.controller("ingAtmLocatorController", function($scope, $route, $location, $mdMedia, $mdSidenav, $rootScope, $http) {
	
	var authenticate = function(credentials, callback) {
		
		userCredentials = credentials;

		$http.get('api/user').success(function(data) {
			
			if (data.name) {$rootScope.authenticated = true;} 
			else {$rootScope.authenticated = false;}
			
			callback && callback($rootScope.authenticated);
		})
		.error(function() {
			$rootScope.authenticated = false;
			callback && callback(false);
		});

	}

	authenticate(null, function() {
		if ($rootScope.authenticated) {
			$location.path("/");
		} else {
			$location.path("/login");
		}
    });
	
	$rootScope.logout = function() {
		$http.post('logout', {}).finally(function() {
			$rootScope.authenticated = false;
			$location.path("/login");
			userCredentials = null;
		});
	}
	
})
.controller("loginController", function($scope, $route, $location, $mdMedia, $mdSidenav, $rootScope, $http) {

	var authenticate = function(credentials, callback) {
		
		userCredentials = credentials;

		$http.get('api/user').success(function(data) {
			
			if (data.name) {$rootScope.authenticated = true;} 
			else {$rootScope.authenticated = false;}
			
			callback && callback($rootScope.authenticated);
		})
		.error(function() {
			
			$rootScope.authenticated = false;
			callback && callback(false);
		});

	}

	$scope.credentials = {};
	
	$scope.login = function() {
		authenticate($scope.credentials, function() {
			if ($rootScope.authenticated) {
				$location.path("/");
				$scope.failedAuth = false;
			} else {
				$location.path("/login");
				$scope.failedAuth = true;
			}
		});
	};

})
.config(function($httpProvider, $routeProvider, $locationProvider) {

	$httpProvider.interceptors.push('AuthInterceptor');
	$httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';

	$routeProvider	.when('/', {templateUrl : 'pages/atmList.html', controller : 'atmController'})
					.when('/login', {templateUrl : 'pages/login.html', controller : 'loginController'});

});
