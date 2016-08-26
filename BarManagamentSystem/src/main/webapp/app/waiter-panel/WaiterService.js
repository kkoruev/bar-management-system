'use strict';

app.factory('WaiterService', ['$http', 'AppConstants', '$q',
    function($http, AppConstants, $q) {

        const baseURL = 'http://localhost:8080/BarManagamentSystem/rest/user';

        var serviceAPI = {
            getStartedBills: getStartedBills,
            startBill: startBill,
            getOrdersForBill: getOrdersForBill,
            addOrder: addOrder,
            completeBill: completeBill
        };

        return serviceAPI; 
        
        function getStartedBills() {
            return $http.get(AppConstants.BASE_URL + "/user/bills/open")
            .then((data) => {
                return $q.resolve(data.data.bill);
            })
            .catch((error) => {
                return $q.reject(error);
            });
        }

        function startBill(billName) {
            var data = {"bill": {tableName: billName}};
            return $http.post(AppConstants.BASE_URL + "/user/bills", data, {headers: {'Content-Type': 'application/json'}})
            .then((data) => {
                return $q.resolve(data.data.bill);
            })
            .catch((error) => {
                return $q.reject(error);
            })
        };

        function _transformOrderDataForRequest(bill, orderItems) {
            var data = {
                orderUnit: {
                    items: []
                }
            }     

            orderItems.forEach((item, index) => {
                for (var i = 0; i < item.quantity; i++) {
                    data.orderUnit.items.push({itemId: item.itemId});
                }
            });
            return data;
        }

        function addOrder(bill, order) {
            var data = _transformOrderDataForRequest(bill, order); 
            var config = {headers: { 
                'Accept': 'application/json',
                'Content-Type': 'application/json' 
            }};
            return $http.post(AppConstants.BASE_URL + "/user/bills/" + bill.billId + "/orders", data, config)
            .then((response) => {
                return $q.resolve(response.data.item);
            })
            .catch((error) => {
                return $q.reject(error);
            })
        }

        function getOrdersForBill(bill) {
            var config = {headers: {"Accept" : "application/json"}};
            return $http.get(AppConstants.BASE_URL + "/user/bills/" + bill.billId + "/orders", config)
            .then((response) => {
                var items = response.data.item;
                return $q.resolve(items);
            })
            .catch((error) => {
                return $q.reject(error);
            })
        }

        function completeBill(bill) {
            return $http.put(AppConstants.BASE_URL + "/user/bills/" + bill.billId + "/complete");
        }


    }
]);