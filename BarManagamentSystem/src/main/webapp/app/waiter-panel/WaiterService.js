'use strict';

app.factory('WaiterService', ['$http', 
    function($http) {

        const baseURL = 'http://localhost:8080/BarManagamentSystem/rest/user';

        var serviceAPI = {
            getStartedBills: getStartedBills,
            startBill: startBill
        };

        return serviceAPI; 
        
        function getStartedBills() {
            return $http({
                method: "GET",
                url: baseURL + '/bills/open',
            });
        }

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
        };


    }
]);