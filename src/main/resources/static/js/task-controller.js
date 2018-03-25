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
    function isStatus(task) {
        return task.status == this;
    }

    function remove(array, id) {
        return array.filter(e => e.id != id)
    }

    $scope.findTasks = function() {
        $http.get("/api/tasks")
            .then(function(response) {
                $scope.todo = response.data.filter(isStatus, 'TODO');
                $scope.done = response.data.filter(isStatus, 'DONE');
            });
    }

    $scope.doneTask = function(id) {
        var doneTask = $scope.todo.filter(function(element) { return element.id == id})[0];
        $scope.todo = remove($scope.todo, doneTask.id);
        $scope.done.unshift(doneTask)

        var config = {headers : {'Content-Type': 'application/json;charset=utf-8;',}};
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        $http.defaults.headers.common[header] = token;
        $http.put("/api/tasks/" + id + "?status=DONE", [], config)
            .then(function(response) {
                console.log(response.data);
            }, function(response) {
                console.log(response.statusText);
            });
    }

    $scope.findTasks();
});