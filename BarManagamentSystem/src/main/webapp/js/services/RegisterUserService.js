'use strict';

app.factory('RegisterUserService', ['$http', 
	function($http) {
	
		var baseURL = 'http://localhost:8080/BarManagamentSystem/rest/user/register';
		
		var serviceAPI = {
			registerUser: registerUser	
		};
		return serviceAPI;
		
		function registerUser(registerInfo) {
			$http({
				method: 'POST',
				url: baseURL,
				headers: {
					'Content-Type': 'application/json'
				},
				data: {"userDTO" : registerInfo}
			});
		}
	}
]);