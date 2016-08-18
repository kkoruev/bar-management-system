'use strict';

app.factory('ManageItemsService', ['$http',
    function($http) {

        var baseURL = 'http://localhost:8080/BarManagamentSystem/rest';
        
        var servicesAPI = {
            getCategories:  getCategories
        };
        
        return servicesAPI;

        function getCategories() {
            return $http ({
                method: 'GET',
                url: baseURL + '/categories',
                headers: {
                    'Content-Type' : 'application/json'
                }
            });
        }

    }
]);