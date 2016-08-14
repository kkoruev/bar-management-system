'use strict';

app.controller('WaiterController', ['$scope', 'WaiterService', 
    function($scope, WaiterService) {
        
        $scope.startBill = function(billName) {
            WaiterService.startBill(billName)
            .then((data) => {
                // TODO: redirect to add items to order
            })
            .catch((error) => {
                //TODO: add error handling
            })
        } 
    }
]);