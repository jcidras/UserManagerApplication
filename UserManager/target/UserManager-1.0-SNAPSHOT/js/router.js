define([
'backbone',
'views/list',
'views/edit'],
function (Backbone, UserList, EditUser) {
    var AppRouter = Backbone.Router.extend({
        routes: {
            '': 'home',
            'new': 'editUser',
            'edit/:id': 'editUser'
        }
    });
    
    var initialize = function () {
        var appRouter = new AppRouter();
        appRouter.on('route:home', function () {
            var userList = new UserList();
            userList.render();            
        });
        appRouter.on('route:editUser', function (id){
            var editUser = new EditUser();            
            editUser.render({id: id});
        });
        Backbone.history.start();
    };
    return {
        initialize: initialize
    };
});


