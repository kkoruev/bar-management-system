'use strict';

app.factory('ManageItemsService', ['$http', 'AppConstants', '$q',
    function($http, AppConstants, $q) {

        var _items = [];
        var config = {
           headers: {'Content-Type' : 'application/json'}
        }

                
        var servicesAPI = {
            getCategories:  getCategories,
            addItem: addItem,
            getItems: getItems
        };
        
        return servicesAPI;

        function getCategories() {
            return $http ({
                method: 'GET',
                url: AppConstants.BASE_URL + '/categories',
                headers: {
                    'Content-Type' : 'application/json'
                }
            });
        }

        function addItem(itemInfo) {
            return $http({
                method: 'POST',
                url: AppConstants.BASE_URL + '/items',
                headers: {
                    'Content-Type' : 'application/json'
                },
                data: {"item" : itemInfo}
            })
        }

        function getItems() {
            if(_items.length === 0) {
                return $http.get(AppConstants.BASE_URL + '/items', AppConstants.CONFIG)
                .then((data) => {
                    _items = data.data.item;
                    return $q.resolve(_items);
                })
                .catch((error) => {
                    return $q.reject(data);
                });
            } else {
                return $q.resolve(_items);
            }
        }
    }
]);