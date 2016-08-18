'use strict'


app.factory('LoginService', ['$http', 
    function($http) {

        var baseURL = 'http://localhost:8080/BarManagamentSystem/rest/user';

        var serviceAPI = {
            login: login
        };

        return serviceAPI; 

        function login(user) {
            return $http({
                method: "POST",
                url: baseURL + '/login',
                headers: {
                    'Content-Type': 'application/json'
                },
                data: {
                    "userDTO" : user
                }
            });
        }

    }
]);