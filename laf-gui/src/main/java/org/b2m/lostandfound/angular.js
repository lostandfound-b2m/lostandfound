angular.module('lf', [])
    .controller('Hello', function ($scope, $http) {
        $scope.cities = ["Gdańsk","Kraków","Warszawa"];
        $scope.city = "Kraków";
        $scope.desc = "";
        $scope.$watch('desc', function() {
            fetch();
        });
        $scope.$watch('city', function() {
            fetch();
        });
        function fetch() {
            if ($scope.desc==="") {
                $http.get('http://localhost:8080/request?city=' + $scope.city)
                    .then(function successCallback(response) {
                        $scope.greeting = response.data;
                    }, function errorCallback(response) {
                        console.log("Unable to perform get request");

                    });
            }
            else {
                $http.get('http://localhost:8080/request?city=' + $scope.city + '&desc=' + $scope.desc)
                    .then(function successCallback(response) {
                        $scope.greeting = response.data;
                    }, function errorCallback(response) {
                        console.log("Unable to perform get request");

                    });
            }

        }
        $scope.orderByMe = function(x) {
            $scope.myOrderBy = x;
        };
        $scope.update = function(cityName){
            $scope.city = cityName;
        };

    });