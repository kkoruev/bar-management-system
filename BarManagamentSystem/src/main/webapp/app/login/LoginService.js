'use strict';

app.factory('LoginService', ['$http', 'AppConstants',
    function($http, AppConstants) {

        var serviceAPI = {
            login: login
        };

        return serviceAPI; 

        function login(user) {
            return $http({
                method: "POST",
                url: AppConstants.BASE_URL + '/user/login',
                headers: {
                    'Content-Type': 'application/json'
                },
                data: {
                    "user" : user
                }
            });
        }

    }
]);