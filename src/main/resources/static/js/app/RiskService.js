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

            function loadAllRisks() {
                console.log('Fetching all Risks');
                var deferred = $q.defer();
                $http.get(urls.RISK_SERVICE_API)
                    .then(
                        function (response) {
                            console.log('Fetched successfully all Risks');
                            $localStorage.risks = response.data;
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                            console.error('Error while loading Risks');
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function getAllRisks(){
                return $localStorage.risks;
            }

            function getRisk(id) {
                console.log('Fetching Risk with id :'+id);
                var deferred = $q.defer();
                $http.get(urls.RISK_SERVICE_API + id)
                    .then(
                        function (response) {
                            console.log('Fetched successfully Risk with id :'+id);
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while loading Risk with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

        }
    ]);