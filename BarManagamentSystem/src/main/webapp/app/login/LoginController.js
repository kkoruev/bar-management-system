'use strict';

app.controller("LoginController", [
    '$scope', '$timeout', 'LoginService', '$state', 'RouteConstants',
    function($scope, $timeout, LoginService, $state, RouteConstants) {

        $scope.userCreadentials;        
        $scope.currentIndex = 0;        
        $scope.sliderImages = [{
        	src : "assets/img/login1.jpg" 
        	}, {
        	src : "assets/img/login.jpg"
        }, {
        	src : "assets/img/login2.jpg"
        }, {
        	src : "assets/img/login3.jpg"
        }
        ];
        
        $scope.next = function() {
        	$scope.currentIndex < $scope.sliderImages.length - 1 ? $scope.currentIndex++ : $scope.currentIndex = 0;
        };
        
        $scope.$watch('currentIndex', function() {
        	$scope.sliderImages.forEach(function(image){
        		image.visible = false;
        	});
        	
        	$scope.sliderImages[$scope.currentIndex].visible = true;
        });
        
        var timer;
        var sliderFunc = function() {
          timer = $timeout(function() {
            $scope.next();
            timer = $timeout(sliderFunc, 2000);
          }, 2000);
        };

        sliderFunc();

        $scope.$on('$destroy', function() {
          $timeout.cancel(timer); // when the scope is getting destroyed, cancel the timer
        });
        
        
        
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
