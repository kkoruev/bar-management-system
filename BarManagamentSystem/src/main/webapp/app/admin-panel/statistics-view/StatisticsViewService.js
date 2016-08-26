'use strict';

app.factory('StatisticsViewService', ['$http', 'AppConstants', '$q',
    function($http, AppConstants, $q) {
    
        var serviceAPI = {
        	getWaiterBillsForLastWeek: getWaiterBillsForLastWeek
            
        };
        return serviceAPI;
        
        function getWaiterBillsForLastWeek() {
        	var config = {headers: {'Content-Type': 'application/json'}};
        	
        	return $http.get(AppConstants.BASE_URL + '/audit/income/week', config)
        	.then((response) => {
        		return $q.resolve(response.data.waiterCounterBills);
        	})
        	.catch((error) => {
        		return $q.reject(error);
        	});
        }
        
    }
]);