'use strict';

var app = angular.module('BarManagementSystem', [ 
    'ui.bootstrap', 
    'ngRoute',
    'ngResource',
    'ui.router' 
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
        .state(RouteConstants.MANAGE_ITEMS, {
            url: '/manage-items',
            templateUrl: 'app/admin-panel/manage-items/manage-items.html',
            controller: 'ManageItemsController'
        });
        $urlRouterProvider.otherwise('/login');
	}
]);