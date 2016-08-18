'use strict';

app.controller('WaiterController', ['$scope', 'WaiterService', 
    function($scope, WaiterService) {
        
        $scope.billName;
        $scope.bills;
        $scope.orders = [];

        init();

        function init() {
            return WaiterService.getStartedBills()
            .then((data) => {
                $scope.bills = data.data.bill;
                console.log(data);
            })
            .catch((error) => {
                console.log(error);
            });
        }

        $scope.startBill = function(billName) {
            var testBill = "mm";
            WaiterService.startBill(testBill)
            .then((data) => {

                console.log('data');
                // TODO: redirect to add items to order
            })
            .catch((error) => {
                console.log(error);
                //TODO: add error handling
            })
        } 


        $scope.openBill = function(bill) {
            if(bill.orders !== undefined) {
                $scope.orders = bill.orders;
            } else {
                $scope.orders = [];
            }
        }

    }
]);