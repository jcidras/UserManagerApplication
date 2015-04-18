define(['backbone'],
function(Backbone) {
    var User = Backbone.Model.extend({
        urlRoot: 'http://localhost:8080/UserManager/v1/manager/users'
    });
    return User;
});


