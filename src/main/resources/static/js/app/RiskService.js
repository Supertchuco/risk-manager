'use strict';

angular.module('riskManagerApp').factory('RiskService',
    ['$localStorage', '$http', '$q', 'urls',
        function ($localStorage, $http, $q, urls) {

            var factory = {
                loadAllRisks: loadAllRisks,
                getAllRisks: getAllRisks,
                getRisk: getRisk,
            };

            return factory;

            function loadAllClients() {
                console.log('Fetching all Clients');
                var deferred = $q.defer();
                $http.get(urls.CLIENT_SERVICE_API)
                    .then(
                        function (response) {
                            console.log('Fetched successfully all Clients');
                            $localStorage.clients = response.data;
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                            console.error('Error while loading Clients');
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function getAllClients(){
                return $localStorage.clients;
            }

            function getClient(id) {
                console.log('Fetching Client with id :'+id);
                var deferred = $q.defer();
                $http.get(urls.CLIENT_SERVICE_API + id)
                    .then(
                        function (response) {
                            console.log('Fetched successfully Client with id :'+id);
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while loading Client with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

        }
    ]);