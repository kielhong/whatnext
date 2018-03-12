var app = angular.module('taskApp', []);

app.directive('ngHolder', function() {
    return {
        link: function(scope, element, attrs) {
            attrs.$set('data-src', attrs.ngHolder);
            Holder.run({images:element.get(0), nocss:true});
        }
    };
});

app.controller('taskCtrl', function($scope, $http) {
    $scope.findAllTasks = function() {
        $http.get("/api/tasks")
            .then(function(response) {
                console.log(response.data);
                $scope.tasks = response.data;
            });
    }

    $scope.findAllTasks();
});