requirejs.config({
    paths: {
        underscore: '//cdnjs.cloudflare.com/ajax/libs/underscore.js/1.4.2/underscore-min',
        jquery: '//cdnjs.cloudflare.com/ajax/libs/jquery/1.8.2/jquery.min',
        backbone: '//cdnjs.cloudflare.com/ajax/libs/backbone.js/0.9.2/backbone-min',        
        text: 'text'
    },
    shim: {
        underscore: {
            exports: '_'
        },
        backbone: {
            deps: ['underscore', 'jquery'],
            exports: 'Backbone'
        }
    },
    waitSeconds: 15
});

requirejs([
'app'], 
function (App) {    
    App.initialize();
});


