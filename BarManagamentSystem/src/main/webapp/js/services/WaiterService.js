'use strict';

app.factory('WaiterService', ['$http', 
	function($http) {

		var baseURL = 'http://localhost:8080/BarManagamentSystem/rest/user';

		var ServiceAPI = {
			startBill: startBill
		}

		return serviceAPI; 

		function startBill(billName) {
			return $http({
				method: "POST",
				url: baseURL + '/bills',
				headers: {
					'Content-Type': 'application/json'
				},
				data: {
					"billDTO" : {
						tableName: billName
					}
				}
			});
		}

	}
]);