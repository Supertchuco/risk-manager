'use strict';

angular.module('riskManagerApp').factory('ClientService',
    ['$localStorage', '$http', '$q', 'urls',
        function ($localStorage, $http, $q, urls) {

            var factory = {
                loadAllClients: loadAllClients,
                getAllClients: getAllClients,
                getClient: getClient,
                createClient: createClient,
                updateClient: updateClient,
                removeClient: removeClient
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

            function createClient(client) {
                console.log('Creating Client');
                var deferred = $q.defer();
                $http.post(urls.CLIENT_SERVICE_API, client)
                    .then(
                        function (response) {
                            loadAllClients();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                           console.error('Error while creating Client : '+errResponse.data.errorMessage);
                           deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function updateClient(client, id) {
                console.log('Updating Client with id '+id);
                var deferred = $q.defer();
                $http.put(urls.CLIENT_SERVICE_API + id, client)
                    .then(
                        function (response) {
                            loadAllClients();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while updating Client with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function removeClient(id) {
                console.log('Removing Client with id '+id);
                var deferred = $q.defer();
                $http.delete(urls.CLIENT_SERVICE_API + id)
                    .then(
                        function (response) {
                            loadAllClients();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while removing Client with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

        }
    ]);