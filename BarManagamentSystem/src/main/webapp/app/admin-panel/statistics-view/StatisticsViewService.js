'use strict';

app.factory('StatisticsViewService', ['$http', 
    function($http) {
    
        var baseURL = 'http://localhost:8080/BarManagamentSystem/rest/audit';
        
        var serviceAPI = {
        	getWaiterBillsForLastWeek: getWaiterBillsForLastWeek,
            getRoles: getRoles
            
        };
        return serviceAPI;
        
        function registerUser(registerInfo) {
            $http({
                method: 'POST',
                url: baseURL + '/register',
                headers: {
                    'Content-Type': 'application/json'
                },
                data: {"user" : registerInfo}
            });
        }
        
        function getRoles() {
            return $http({
                method: 'GET',
                url: baseURL + '/roles',
                headers: {
                    'Content-Type' : 'application/json'
                },
                
            });
        }
    }
]);