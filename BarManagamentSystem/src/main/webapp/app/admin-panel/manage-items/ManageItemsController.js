'use strict';

app.controller('ManageItemsController', [
    '$scope', 'ManageItemsService', 
    function($scope, ManageItemsService) {

        $scope.itemsInfo = {};
        $scope.categories;

        getCategories();

        function getCategories() {
            ManageItemsService.getCategories()
            .then(function(data) {
                console.log("success");
                $scope.categories = data.data.category;
            })
            .catch((error) => {
                console.log(error);
            });
        }

        $scope.showMessage = function(name) {
            debugger;
            console.log('hey');
        }
    }
]);