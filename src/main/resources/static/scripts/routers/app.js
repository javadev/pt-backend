/*jshint browser: true*/
/*global define*/
define([
  'marionette',
  'app'
],
function(Marionette, App) {
  'use strict';
  
  return Marionette.AppRouter.extend({
    //`index` etc must be a method in AppRouter's controller
    appRoutes: {
      'login': 'login',
      '': 'index'
    },
    initialize: function (options) {
      var controller = options.controller;
      App.vent.on('redirect:default', function() {
        controller.index();
        this.navigate('');
      }, this);

      App.vent.on('login:required', function() {
        controller.login();
        this.navigate('login');
      }, this);
    }
  });
});
