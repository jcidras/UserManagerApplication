define([
'jquery',
'underscore',
'backbone',
'collections/users',
'text!templates/user-list-template.html'],
function ($, _, Backbone, Users, UserListTemplate) {
   var UserList = Backbone.View.extend({
        el: $('.page'),               
        initialize: function () {
            //$('.page').append(UserListTemplate);
            this.template = _.template(UserListTemplate);
            this.collection = new Users();
            this.collection.fetch();
            this.collection.bind("reset", this.render, this);
        },
        render: function () {       
            var data = {users: this.collection.models};
            var html = this.template(data);            
            this.$el.html(html);
        }
    }); 
    return UserList;
});


