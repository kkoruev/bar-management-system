'use strict';

app.controller('WaiterController', ['$scope', 'WaiterService', 'toastr', 'ManageItemsService',
    function($scope, WaiterService, toastr, ManageItemsService) {
        
        $scope.billName;
        $scope.bills;
        $scope.currentBill = {};
        $scope.items;
        $scope.selectedItem = {};

        $scope.previousOrdersGrid = [{name: "potatoes",quantity: 3,price: 4.7},{name: "Pizza Palermo",quantity: 2,price: 7.8}];
        $scope.newOrdersGrid = [];

        init();

        function init() {
            return WaiterService.getStartedBills()
            .then((data) => {
                $scope.bills = data.data.bill;
                return ManageItemsService.getItems();
            })
            .then((items) => {
                $scope.items = items;
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
            $scope.currentBill = bill;
            WaiterService.getOrdersForBill(bill)
            .then((data) => {
                debugger;
            })
            .catch((error) => {
                debugger;
            })
            if(bill.orders !== undefined) {
                $scope.orders = bill.orders;
            } else {
                $scope.orders = [];
            }
        }

        $scope.addToOrder = function(selectedItem) {
            $scope.newOrdersGrid.push({
                name: selectedItem.item.name,
                price: selectedItem.item.price,
                quantity: selectedItem.quantity
            });
        }

        $scope.submitOrder = function() {
            WaiterService.addOrders($scope.currentBill, $scope.newOrdersGrid)
            .then((data) => {
                debugger
            })
            .catch((error) => {
                debugger;
            })
        }

    }
]);