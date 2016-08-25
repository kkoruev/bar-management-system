'use strict';

var app = angular.module('BarManagementSystem', [ 
    'ui.bootstrap', 
    'ngRoute',
    'ngResource',
    'ui.router',
    'toastr',
    'ngAnimate',
    'mgcrea.ngStrap',
    'ng-fusioncharts'
]);

app.config(['$stateProvider', '$urlRouterProvider', 'RouteConstants',
    function($stateProvider, $urlRouterProvider, RouteConstants) {
        $stateProvider
        .state(RouteConstants.LOGIN, {
            url: '/login',
            templateUrl: 'app/login/login-page.html',
            controller: 'LoginController'
        })
        .state(RouteConstants.WAITER_ORDERS, {
            url: '/waiter-panel',
            templateUrl: 'app/waiter-panel/waiter-panel.html',
            controller: 'WaiterController'
        })
        .state(RouteConstants.REGISTER_USER, {
            url: '/register-user',
            templateUrl: 'app/admin-panel/register-user/register-user.html',
            controller: 'RegisterUserController'
        })
        .state(RouteConstants.STATISTICS_VIEW, {
            url: '/statistics-view',
            templateUrl: 'app/admin-panel/statistics-view/statistics-view.html',
            controller: 'StatisticsViewController'
        })
        .state(RouteConstants.MANAGE_ITEMS, {
            url: '/manage-items',
            templateUrl: 'app/admin-panel/manage-items/manage-items.html',
            controller: 'ManageItemsController'
        });
        $urlRouterProvider.otherwise('/login');
	}
]);

app.run(function($rootScope, RouteConstants) {
    $rootScope.RouteConstants = RouteConstants;
});

app.config(function(toastrConfig) {
    angular.extend(toastrConfig, {
        autoDismiss: true,
        containerId: 'toast-container',
        maxOpened: 1,
        newestOnTop: true,
        positionClass: 'toast-bottom-center',
        target: 'body',
        timeOut: 3500,
        extendedTimeOut: 2000,
        tapToDismiss: true
    });
});
