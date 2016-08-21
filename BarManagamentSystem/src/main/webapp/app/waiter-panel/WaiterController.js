'use strict';

app.controller('WaiterController', ['$scope', 'WaiterService', 'toastr', 'ManageItemsService',
    function($scope, WaiterService, toastr, ManageItemsService) {
        
        $scope.billName;
        $scope.bills;
        $scope.openedBill = {};
        $scope.items;
        $scope.selectedItem = {};

        $scope.previousOrdersGrid = [{name: "potatoes",quantity: 3,price: 4.7},{name: "Pizza Palermo",quantity: 2,price: 7.8}];
        $scope.newOrderGrid = [];

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
            $scope.openedBill = bill;

            WaiterService.getOrdersForBill(bill)
            .then((data) => {
            })
            .catch((error) => {
            })
            if(bill.orders !== undefined) {
                $scope.orders = bill.orders;
            } else {
                $scope.orders = [];
            }
        }

        // TODO: clear the input fields after adding an item
        $scope.addToOrder = function(selectedItem) {
            $scope.newOrderGrid.push({
                itemId: selectedItem.item.itemId,
                name: selectedItem.item.name,
                price: selectedItem.item.price,
                quantity: selectedItem.quantity
            });
        }

        $scope.submitOrder = function() {
            WaiterService.addOrder($scope.openedBill, $scope.newOrderGrid)
            .then((data) => {
                debugger
            })
            .catch((error) => {
                debugger;
            })
        }

    }
]);