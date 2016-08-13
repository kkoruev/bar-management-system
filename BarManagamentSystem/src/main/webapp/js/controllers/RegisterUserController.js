'use strict';

app.controller("RegisterUserController", ['$scope', 'RegisterUserService',
    function($scope, RegisterUserService) {
		$scope.roles = [{roletype: 'bartender'}, {roletype: 'admin'}, {roletype: 'waiter'}];
//		$scope.roles = ['bartender', 'admin', 'waiter'];
		
		$scope.registerInfo = {};
		
		$scope.registerUser = function(registerInfo) {
			RegisterUserService.registerUser(registerInfo);
		}
	}
]);

