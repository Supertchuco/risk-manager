'use strict';

angular.module('riskManagerApp').controller('RiskController',
    ['RiskService', '$scope',  function( RiskService, $scope) {

        var self = this;
        self.risk = {};
        self.risks=[];

        self.submit = submit;
        self.getAllClients = getAllRisks;
        self.reset = reset;

        self.successMessage = '';
        self.errorMessage = '';
        self.done = false;

        self.onlyIntegers = /^\d+$/;
        self.onlyNumbers = /^\d+([,.]\d+)?$/;



        function getAllRisks(){
            return RiskService.getAllRisks();
        }

        function reset(){
            self.successMessage='';
            self.errorMessage='';
            self.client={};
            $scope.myForm.$setPristine();
        }
    }
    ]);