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
                $scope.openBill(bill);
                billName = "";
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

        $scope.addToOrder = function(selectedItem) {
            $scope.newOrderGrid.push({
                itemId: selectedItem.item.itemId,
                name: selectedItem.item.name,
                price: selectedItem.item.price,
                quantity: selectedItem.quantity
            });
            $scope.selectedItem = {};
        }

        $scope.submitOrder = function() {
            WaiterService.addOrder($scope.openedBill, $scope.newOrderGrid)
            .then((items) => {
                $scope.newOrderGrid = [];
                mergeNewItemsWithPreviouslyAdded($scope.previousOrdersGrid, items);
            })
            .catch((error) => {
                toastr.error(error);
            })
        }

        $scope.completeBill = function() {
            WaiterService.completeBill($scope.openedBill)
            .then(() => {
                toastr.info("GRAND TOTAL: " + $scope.getGrandTotal($scope.previousOrdersGrid) + " €");
                for (var i = $scope.bills.length - 1; i >= 0; i--) {
                    if($scope.bills[i].billId === $scope.openedBill.billId) {
                        $scope.bills.splice(i, 1);
                        break;
                    }
                }
                $scope.previousOrdersGrid = [];
                $scope.openedBill = {};
            })
            .catch((error) => {
                toastr.error(error);
            })
        }

        function mergeNewItemsWithPreviouslyAdded(existingItems, newItems) {
            var hasExisting;
            newItems.forEach((item, index) => {
                hasExisting = false;
                existingItems.forEach((existing) => {
                    if(existing.name === item.name) {
                        existing.quantity += item.quantity;
                        hasExisting = true; 
                    } 
                });
                if(!hasExisting) {
                    existingItems.push(item);
                } 
            });
        }

    }
]);