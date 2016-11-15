/*jshint browser: true*/
/*global define, console*/
define([
  'jquery',
  'underscore',
  'marionette',
  'app',
  'models/users',
  'views/users',
  'models/exercises',
  'views/exercises'
],
  function($, _, Marionette, App, UsersModels, UsersViews, ExercisesModels, ExercisesViews) {
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
        
        var exercises = new ExercisesModels.Exercises();
        var exercisesView = new ExercisesViews.Exercises({
          collection: exercises
        });
        exercises.fetch();
        exercises.on('exercise:new', function(model) {
          var exercise = new ExercisesModels.Exercise();
          if (!_.isUndefined(model)) {
            exercise.set({
              id: model.get('id'),
              exerciseId: model.get('exerciseId'),
              nameEn: model.get('nameEn'),
              nameNo: model.get('nameNo')
            });
          }
          var exerciseEditView = new ExercisesViews.NewExerciseLayout({
            model: exercise
          });
          exercise.on('exercise:back', function() {
            var exercisesView = new ExercisesViews.Exercises({
              collection: exercises
            });
            exercises.fetch();
            applicationLayout.mainExercises.show(exercisesView);
          });
          applicationLayout.mainExercises.show(exerciseEditView);
        });
        applicationLayout.mainExercises.show(exercisesView);
    }
    
    return Marionette.Controller.extend({
      index: function () {
        setupApplicationLayout('');
      }
    });
  });
