'use strict';

app.controller('WaiterController', ['$scope', 'WaiterService', 
    function($scope, WaiterService) {
        
        $scope.billName;

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
    }
]);