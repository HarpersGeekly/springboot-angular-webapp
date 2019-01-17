'use strict';

angular.module('myApp').factory('UserService', ['$http', '$q', function($http, $q){

    let factory = {
        fetchAllUsers: fetchAllUsers,
        createUser: createUser,
        updateUser:updateUser,
        deleteUser:deleteUser
    };

    return factory;

    // function fetchAllUsers() {
    //     let deferred = $q.defer();
    //     $http.get(REST_SERVICE_URI)
    //         .then(
    //             function (response) {
    //                 deferred.resolve(response.data);
    //             },
    //             function(errResponse){
    //                 console.error('Error while fetching Users');
    //                 deferred.reject(errResponse);
    //             }
    //         );
    //     return deferred.promise;
    // }

    // function createUser(user) {
    //     let deferred = $q.defer();
    //     $http.post(REST_SERVICE_URI, user)
    //         .then(
    //             function (response) {
    //                 deferred.resolve(response.data);
    //             },
    //             function(errResponse){
    //                 console.error('Error while creating User');
    //                 deferred.reject(errResponse);
    //             }
    //         );
    //     return deferred.promise;
    // }


    function updateUser(user, id) {
        let deferred = $q.defer();
        $http.put(REST_SERVICE_URI+id, user)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    console.error('Error while updating User');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

    function deleteUser(id) {
        let deferred = $q.defer();
        $http.delete(REST_SERVICE_URI+id)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    console.error('Error while deleting User');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

}]);