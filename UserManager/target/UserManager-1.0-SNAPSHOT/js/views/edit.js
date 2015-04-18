define([
'jquery',
'backbone',
'underscore',
'models/user',
'text!templates/edit-user-form.html'],
function ($, Backbone, _, User, EditUserTemplate){    
    $.fn.serializeObject = function () {
        var o = {};
        var a = this.serializeArray();
        $.each(a, function () {
            if (o[this.name] !== undefined) {
                if (!o[this.name].push) {
                    o[this.name] = [o[this.name]];
                }
                o[this.name].push(this.value || '');
            } else {
                o[this.name] = this.value || '';
            }
        });
        return o;
    };
    var EditUser = Backbone.View.extend({
        el: '.page',
        render: function (options) {   
            var that = this;
            if (options.id) {
                this.user = new User({id: options.id});                        
                this.user.fetch({
                    success: function (user) {
                        var data = {user: user};
                        var template = _.template(EditUserTemplate);
                        var html = template(data);
                        that.$el.html(html);
                    }
                });
            }else{
                var data = {user: null};
                var template = _.template(EditUserTemplate);
                var html = template(data);
                this.$el.html(html);
            }                    
        },
        events: {
            'submit .edit-user-form': 'saveUser',  
            'click .delete': 'deleteUser'
        },
        saveUser: function (ev) {
            var userDetails = $(ev.currentTarget).serializeObject();
            console.log(userDetails);
            var user = new User();
            user.save(userDetails, {
                success: function () {
                    Backbone.history.navigate('', {trigger: true});
                }
            });
            return false;
        },
        deleteUser: function (ev) {   
            this.user.destroy({
                success: function () {
                    Backbone.history.navigate('', {trigger: true});
                }
            });
            return false;
        }
    });  
    return EditUser;
});

