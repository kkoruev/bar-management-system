'use strict';

app.controller('WaiterController', ['$scope', 'WaiterService', 'toastr', 'ManageItemsService',
    function($scope, WaiterService, toastr, ManageItemsService) {
        
        $scope.billName;
        $scope.bills;
        $scope.openedBill = {};
        $scope.items;
        $scope.selectedItem = {};

        $scope.previousOrdersGrid = [];
        $scope.newOrderGrid = [];

        init();

        function init() {
            return WaiterService.getStartedBills()
            .then((bills) => {
                $scope.bills = bills;
                return ManageItemsService.getItems();
            })
            .then((items) => {
                $scope.items = items;
            })
            .catch((error) => {
                toastr.error("Error on getting opened bills " + error);
            });
        }

        $scope.isBillOpened = function() {
            if(Object.keys($scope.openedBill).length === 0) {
                return false;
            }
            return true;
        }

        $scope.startBill = function(billName) {
            WaiterService.startBill(billName)
            .then((bill) => {
                $scope.bills.push(bill);
                $scope.openedBill = bill;
            })
            .catch((error) => {
                toastr.error("Unable to start a bill " + error);
            })
        } 

        $scope.getGrandTotal = function(gridData) {
            var total = 0;
            gridData.forEach((item, index) => {
                total += item.quantity * item.price;
            });
            return total;
        }

        $scope.openBill = function(bill) {
            $scope.openedBill = bill;

            WaiterService.getOrdersForBill(bill)
            .then((data) => {
                $scope.previousOrdersGrid = data;
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