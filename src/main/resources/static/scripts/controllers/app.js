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
  'views/exercises',
  'models/programs',
  'views/programs'
],
  function($, _, Marionette, App, UsersModels, UsersViews, ExercisesModels, ExercisesViews,
    ProgramsModels, ProgramsViews) {
  'use strict';

    function setupApplicationLayout() {
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
        $.get('/api/v1/admin/exercise-category').done(function(data) {
          exercises._categories = _.union({id: null, nameEn: ''}, data);
          exercises.trigger('sync');
        });
        $.get('/api/v1/admin/exercise-type').done(function(data) {
          exercises._types = _.union({id: null, nameEn: ''}, data);
          exercises.trigger('sync');
        });
        exercises.on('exercise:new', function(model) {
          var exercise = new ExercisesModels.Exercise();
          exercise._categories = exercises._categories;
          exercise._types = exercises._types;
          if (!_.isUndefined(model)) {
            exercise.set({
              id: model.get('id'),
              category: model.get('category'),
              types: model.get('types'),
              exerciseId: model.get('exerciseId'),
              nameEn: model.get('nameEn'),
              nameNo: model.get('nameNo'),
              descriptionEn: model.get('descriptionEn'),
              descriptionNo: model.get('descriptionNo')
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

        var programs = new ProgramsModels.Programs();
        var programsView = new ProgramsViews.Programs({
          collection: programs
        });
        programs.fetch();
        programs.on('program:new', function(model) {
          var program = new ProgramsModels.Program();
          if (!_.isUndefined(model)) {
            program.set({
              id: model.get('id'),
              name: model.get('name'),
              fileName: model.get('fileName'),
              fileSize: model.get('fileSize'),
              fileType: model.get('fileType'),
              dataUrl: model.get('dataUrl')
            });
          }
          var programEditView = new ProgramsViews.NewProgramLayout({
            model: program
          });
          program.on('program:back', function() {
            var programsView = new ProgramsViews.Programs({
              collection: programs
            });
            programs.fetch();
            applicationLayout.mainPrograms.show(programsView);
          });
          program.on('program:parseFile', function() {
            console.log(this.get('fileName'));
          });
          applicationLayout.mainPrograms.show(programEditView);
        });
        applicationLayout.mainPrograms.show(programsView);
    }
    
    return Marionette.Controller.extend({
      index: function () {
        setupApplicationLayout('');
      }
    });
  });
