'use strict';

var app = angular.module('BarManagementSystem', [ 
    'ui.bootstrap', 
    'ngRoute',
    'ngResource',
    'ui.router' 
]);

app.config(['$stateProvider', '$urlRouterProvider', function($stateProvider, $urlRouterProvider) {
    $stateProvider
    .state('login-page', {
        url: '/',
        templateUrl: 'templates/login-page.html',
        controller: 'LoginPageController'
    })
   .state('bartender-panel', {
        url: '/bartender-panel',
        templateUrl: 'templates/bartender-panel.html',
        controller: 'BartenderController'
    })
    .state('waiter-panel', {
        url: '/waiter-panel',
        templateUrl: 'templates/waiter-panel.html',
        controller: 'WaiterController'
    })
    .state('add-items', {
        url: '/add-items',
        templateUrl: 'templates/add-items.html',
        controller: 'AddItemsController'
    })    
     .state('register-user', {
        url: '/register-user',
        templateUrl: 'templates/register-user.html',
        controller: 'RegisterUserController'
    });
    /*
    .state('configure-products', {
        url: '/configure-products',
        templateUrl: 'templates/configure-products.html',
        controller: 'ConfigureController',
        authenticate: false,
        data: {
            viewClass: 'body-background-gray' 
        }
    })
    .state('review-part-list', {
        url: '/part-list',
        templateUrl: 'templates/part-list.html',
        controller: 'PartListController',
        authenticate: false   
    })
    .state('forbidden', {
        url: '/forbidden',
        templateUrl: 'templates/page-forbidden.html',
    })*/
    $urlRouterProvider.otherwise('/register-user');
	}
]);