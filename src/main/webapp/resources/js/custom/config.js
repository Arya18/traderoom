
var app = angular.module('myApp', ['ui.router',,'vAccordion', 'ngAnimate']);

app.config(function($stateProvider, $urlRouterProvider) {
    
    $urlRouterProvider.otherwise('/home');
    
    $stateProvider
        
        // HOME STATES AND NESTED VIEWS ========================================
        .state('home', {
            url: '/home',
            templateUrl: 'pages/home.html'
        })
        .state('signup', {
            url: '/signup',
            templateUrl: 'pages/signup.html'
        })
        .state('login', {
            url: '/login',
            templateUrl: 'pages/login.html'
        })
        .state('about', {
            url: '/about',
            templateUrl: 'pages/about.html'
        })
        .state('features', {
            url: '/features',
            templateUrl: 'pages/features.html'
        })
        .state('docs', {
            url: '/docs',
            templateUrl: 'pages/docs.html'
        })
        .state('dashboard', {
            url: '/dashboard',
            templateUrl: 'pages/dashboard.html',
            controller: 'dashboard'
        })
        .state('pricing', {
            url: '/pricing',
            templateUrl: 'pages/pricing.html'
        });
                
});