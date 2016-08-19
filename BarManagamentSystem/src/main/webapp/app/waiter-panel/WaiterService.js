'use strict';

app.factory('WaiterService', ['$http', 'AppConstants', '$q',
    function($http, AppConstants, $q) {

        const baseURL = 'http://localhost:8080/BarManagamentSystem/rest/user';

        var serviceAPI = {
            getStartedBills: getStartedBills,
            startBill: startBill,
            getOrdersForBill: getOrdersForBill,
            addOrders: addOrders
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

        function _transformOrderDataForRequest(orders) {
            // var modifiedOrders = [];
            // orders.forEach((order, index) => {
            //     for (var i = 0; i < order.quantity; i++) {
            //         modifiedOrders.push({name: order.name});
            //     }
            // });
        }

        function addOrders(bill, orders) {
            var data = {
                bill: {
                    billId: bill.billId,
                    orders: _transformOrderDataForRequest(orders)
                }
            }
            return $http.post(AppConstants.BASE_URL + "/orders", data, AppConstants.CONFIG)
            .then((data) => {
                debugger;
                return $q.resolve(data);
            })
            .catch((error) => {
                return $q.reject(error);
            })
        }

        function getOrdersForBill(bill) {
            debugger;
            return $http({
                method: "GET",
                url: AppConstants.BASE_URL + '/user/orders',
                headers: AppConstants.CONFIG,
                params: {
                    billId: bill.billId 
                }
            })
            .then((data) => {
                return $q.resolve(data);
            })
            .catch((error) => {
                return $q.reject(error);
            })
        }


    }
]);