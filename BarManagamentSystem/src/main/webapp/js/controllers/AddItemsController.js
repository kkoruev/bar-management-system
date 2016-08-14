'use strict';

app.controller('AddItemsController', [ '$scope', 'AddItemsService',
     function($scope, AddItemsService) {
	
		$scope.itemsInfo = {};
		$scope.categories;
		
		getCategories();
		
		function getCategories() {
			AddItemsService.getCategories()
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