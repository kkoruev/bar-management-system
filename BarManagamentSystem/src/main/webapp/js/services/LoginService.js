'use strict'


app.factory('LoginService', ['$http', 
    function($http) {

        var baseURL = 'http://localhost:8080/BarManagamentSystem/rest/user';

        var serviceAPI = {
            loginUser: loginUser
        }

        return serviceAPI; 

        function loginUser(user) {
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