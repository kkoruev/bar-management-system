'use strict';

app.controller("RegisterUserController", ['$scope', 'RegisterUserService',
    function($scope, RegisterUserService) {
		$scope.roles = ["ADMIN", "WAITER", "BARTENDER"];
		
		$scope.registerInfo = {};
		
		$scope.registerUser = function(registerForm, registerInfo) {
			if(registerForm.$valid) {
				RegisterUserService.registerUser(registerInfo);
			} else {
				//TODO: show required fields message
			}
		};
		
		getRoles();
		
		function getRoles() {
//			RegisterUserService.getRoles()
//			.then(function(data) {
//				$scope.roles = data;
//			});
		}
	}
]);

