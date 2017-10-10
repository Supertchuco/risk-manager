var app = angular.module('riskManagerApp',['ui.router','ngStorage']);

app.constant('urls', {
    BASE: 'http://localhost:8080/riskManager',
    CLIENT_SERVICE_API : 'http://localhost:8080/riskManager/api/client/',
    RISK_SERVICE_API : 'http://localhost:8080/riskManager/api/risk/'
});

app.config(['$stateProvider', '$urlRouterProvider',
    function($stateProvider, $urlRouterProvider) {

        $stateProvider
            .state('home', {
                url: '/',
                templateUrl: 'partials/list',
                controller:'ClientController',
                controllerAs:'ctrl',
                resolve: {
                    clients: function ($q, ClientService) {
                        console.log('Load all clients');
                        var deferred = $q.defer();
                        ClientService.loadAllClients().then(deferred.resolve, deferred.resolve);
                        return deferred.promise;
                    }
                }
            });
        $urlRouterProvider.otherwise('/');
    }]);

