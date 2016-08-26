'use strict';

app.factory('RegisterUserService', ['$http', 'AppConstants', '$q',
    function($http, AppConstants, $q) {
    
        var baseURL = 'http://localhost:8080/BarManagamentSystem/rest/user';
        
        var serviceAPI = {
            registerUser: registerUser,
            getRoles: getRoles
        };
        return serviceAPI;
        
        function registerUser(registerInfo) {
            return $http.post(AppConstants.BASE_URL + "/user/register", {"user": registerInfo}, 
                {headers:{ 'Content-Type': 'application/json'}})
            .then((response) => {
                return $q.resolve(response);
            })
            .catch((error) => {
                return $q.reject(error);
            })
        }
        
        function getRoles() {
            var config = {headers: {"Accept" : "application/json"}};
            return $http.get(AppConstants.BASE_URL + "/user/roles", config)
            .then((response) => {
                var roles = [];
                response.data.role.forEach((element, index) => {
                    roles.push(element["$"]);
                });
                return $q.resolve(roles);
            })
            .catch((error) => {
                debugger;
                return $q.reject(error);
            });
        }
    }
]);