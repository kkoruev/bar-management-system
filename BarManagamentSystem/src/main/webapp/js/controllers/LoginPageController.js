'use strict';

app.controller("LoginPageController", ['$scope', 'LoginService', '$state',
    function($scope, LoginService, $state) {

        $scope.userCreadentials;

        $scope.login = function(userCreadentials) {
            LoginService.loginUser(userCreadentials)
            .then((data) => {
                console.log("Logged it");
                console.log(data);
                $state.go("waiter-panel");
                // TODO add logic
            })
            .catch((error) => {
                console.log(error);
            });
        }

    }
]);
