'use strict';

angular.module('riskManagerApp').controller('RiskController',
    ['RiskService', '$scope',  function( RiskService, $scope) {



         $scope.listOfRisks = loadAllRisks();

        function loadAllRisks(){
            var data = [];
            var risks =  RiskService.loadAllRisks();
            for(var i = 0; i < risks.length; i++){
                  data.push(risks[i].riskName +" - "+ risks[i].taxRisk) + "%";
            }

            return data;
        }

        function reset(){
            self.successMessage='';
            self.errorMessage='';
            self.client={};
            $scope.myForm.$setPristine();
        }
    }
    ]);