/*jshint browser: true*/
/*global define, console*/
define([
  'jquery',
  'underscore',
  'marionette',
  'app',
  'models/users',
  'views/users'
],
  function($, _, Marionette, App, UsersModels, UsersViews) {
  'use strict';

    function setupApplicationLayout(filterData) {
      var applicationLayout = new UsersViews.Layout();
      var users = new UsersModels.Users();
        var usersView = new UsersViews.Users({
          collection: users
        });
        users.fetch();
        users.on('user:new', function(model) {
          var user = new UsersModels.User();
          if (!_.isUndefined(model)) {
            user.set({
              id: model.get('id'),
              name: model.get('name')
            });
          }
          var userEditView = new UsersViews.NewUserLayout({
            model: user
          });
          user.on('user:back', function() {
            var usersView = new UsersViews.Users({
              collection: users
            });
            users.fetch();
            applicationLayout.mainUsers.show(usersView);
          });
          applicationLayout.mainUsers.show(userEditView);
        });
        App.mainRegion.show(applicationLayout);
        applicationLayout.mainUsers.show(usersView);
    }
    
    return Marionette.Controller.extend({
      index: function () {
        setupApplicationLayout('');
      }
    });
  });
