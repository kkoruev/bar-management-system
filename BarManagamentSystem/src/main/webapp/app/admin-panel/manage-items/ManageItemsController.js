'use strict';

app.controller('ManageItemsController', [
    '$scope', 'ManageItemsService', 'toastr', 
    function($scope, ManageItemsService, toastr) {

        $scope.item = {};
        $scope.categories;

        getCategories();

        function getCategories() {
            ManageItemsService.getCategories()
            .then((data) => {
                $scope.categories = data.data.category;
            })
            .catch((error) => {
                console.log(error);
            });
        }

        $scope.addItem = function(addItemForm, item) {
            return ManageItemsService.addItem(item)
            .then((data) => {
                toastr.success("Item successfully added");
            })
            .catch((error) => {
                toastr.error("Error on adding new idem");
            })
            .finally(() => {
               addItemForm.$setPristine();
               $scope.item = {};
            });
        }
    }
]);