'use strict';

app.controller("RegisterUserController", ['$scope', 'RegisterUserService', 'toastr',
    function($scope, RegisterUserService, toastr) {
        $scope.registerInfo = {};
        
        $scope.registerUser = function(registerForm, registerInfo) {
            if(registerForm.$valid) {
                RegisterUserService.registerUser(registerInfo)
                .then(() => {
                    toastr.info("User successfully saved");
                    registerForm.$setPristine();
                    $scope.registerInfo = {};
                    $scope.repeatPassword = "";
                })
                .catch((error) => {
                    registerForm.$setPristine();
                    $scope.registerInfo = {};
                    $scope.repeatPassword = "";
                    toastr.error("Unable to register user: " + error);
                });
            } else {
                toastr.error("Please fill the required fields")
            }
        };
        
        getRoles();
        
        function getRoles() {
            RegisterUserService.getRoles()
            .then((roles) => {
                $scope.roles = roles;
            })
            .catch((error) => {
                console.log(error);
            })
        }
    }
]);

