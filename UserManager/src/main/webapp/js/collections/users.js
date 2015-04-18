define(['backbone'],
function(Backbone) {
    var Users = Backbone.Collection.extend({
        url: 'http://localhost:8080/UserManager/v1/manager/users'
    });
    return Users;
});


