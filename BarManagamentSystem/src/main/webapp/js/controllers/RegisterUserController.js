'use strict';

app.controller("RegisterUserController", ['$scope', 
    function($scope) {
		$scope.roles = [{roletype: 'bartender'}, {roletype: 'admin'}, {roletype: 'waiter'}];
	}
]);

