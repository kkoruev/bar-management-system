'use strict';

app.factory('WaiterService', ['$http', 'AppConstants', '$q',
    function($http, AppConstants, $q) {

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

        function getOrdersForBill(bill) {
            return $http.get(AppConstants.BASE_URL + '/user/orders')
            .then((data) => {
                $q.resolve(data);
            })
            .catch((error) => {
                $q.reject(error);
            })
        }


    }
]);