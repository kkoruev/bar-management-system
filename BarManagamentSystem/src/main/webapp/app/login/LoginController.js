'use strict';

app.controller("LoginController", [
    '$scope', 'LoginService', '$state', 'RouteConstants',
    function($scope, LoginService, $state, RouteConstants) {

        $scope.userCreadentials;

        $scope.login = function(userCreadentials) {
            LoginService.login(userCreadentials)
            .then((data) => {
                $state.go(RouteConstants.WAITER_ORDERS);
                // TODO add logic
            })
            .catch((error) => {
                console.log(error);
            });
        }

    }
]);
