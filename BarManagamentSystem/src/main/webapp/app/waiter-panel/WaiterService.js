'use strict';

app.factory('WaiterService', ['$http', 'AppConstants', '$q',
    function($http, AppConstants, $q) {

        const baseURL = 'http://localhost:8080/BarManagamentSystem/rest/user';

        var serviceAPI = {
            getStartedBills: getStartedBills,
            startBill: startBill,
            getOrdersForBill: getOrdersForBill,
            addOrder: addOrder
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
                    "bill" : {
                        tableName: billName
                    }
                }
            });
        };

        function _transformOrderDataForRequest(bill, orderItems) {
            var data = {
                order: {
                    bill: {billId: bill.billId},
                    items: []
                }
            }     

            orderItems.forEach((item, index) => {
                for (var i = 0; i < item.quantity; i++) {
                    data.order.items.push({itemId: item.itemId});
                }
            });
            return data;
        }

        function addOrder(bill, order) {
            var data = _transformOrderDataForRequest(bill, order); 
            debugger;
            var config = {headers: { 
                'Accept': 'application/json',
                'Content-Type': 'application/json' 
            }}
            return $http.post(AppConstants.BASE_URL + "/user/orders", data, config)
            .then((data) => {
                debugger;
                return $q.resolve(data);
            })
            .catch((error) => {
                return $q.reject(error);
            })
        }

        function getOrdersForBill(bill) {
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