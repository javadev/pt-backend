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
  'views/programs',
  'models/certificates',
  'views/certificates',
  'models/goals',
  'views/goals',
  'models/emails',
  'views/emails',
  'views/login'
],
  function($, _, Marionette, App, UsersModels, UsersViews, ExercisesModels, ExercisesViews,
    ProgramsModels, ProgramsViews, CertificatesModels, CertificatesViews,
    GoalsModels, GoalsViews, EmailsModels, EmailsViews, LoginView) {
  'use strict';

    function setupApplicationLayout() {
      var applicationLayout = new UsersViews.Layout();
      var users = new UsersModels.Users();
        var usersView = new UsersViews.Users({
          collection: users
        });
        users.fetch();
        $.get('/api/v1/admin/user-type').done(function(data) {
          users._types = _.union({id: null, nameEn: ''}, data);
          users.trigger('sync');
        });
        $.get('/api/v1/admin/user-gender').done(function(data) {
          users._genders = _.union({id: null, name: ''}, data);
          users.trigger('sync');
        });
        $.get('/api/v1/admin/user-level').done(function(data) {
          users._levels = _.union({id: null, name: ''}, data);
          users.trigger('sync');
        });
        $.get('/api/v1/admin/user-goal').done(function(data) {
          users._goals = _.union({id: null, name: ''}, data);
          users.trigger('sync');
        });
        users.on('user:view', function(model) {
          var user = new UsersModels.User();
          user._types = users._types;
          user._genders = users._genders;
          user._levels = users._levels;
          user._goals = users._goals;
          if (!_.isUndefined(model)) {
            user.set({
              id: model.get('id'),
              name: model.get('name'),
              email: model.get('email'),
              type: model.get('type'),
              gender: model.get('gender'),
              level: model.get('level'),
              goals: model.get('goals'),
              weight: model.get('weight'),
              programs: model.get('programs')
            });
          }
          var userEditView = new UsersViews.ViewUserLayout({
            model: user
          });
          user.on('user:back', function(modelId) {
            users._modelId = modelId;
            var usersView = new UsersViews.Users({
              collection: users
            });
            users.fetch();
            applicationLayout.mainUsers.show(usersView);
          });
          applicationLayout.mainUsers.show(userEditView);
        });
        users.on('user:new', function(model) {
          var user = new UsersModels.User();
          user._types = users._types;
          user._genders = users._genders;
          user._levels = users._levels;
          user._goals = users._goals;
          if (!_.isUndefined(model)) {
            user.set({
              id: model.get('id'),
              name: model.get('name'),
              email: model.get('email'),
              type: model.get('type'),
              gender: model.get('gender'),
              level: model.get('level'),
              goals: model.get('goals'),
              weight: model.get('weight'),
              programs: model.get('programs')
            });
          }
          var userEditView = new UsersViews.NewUserLayout({
            model: user
          });
          user.on('user:back', function(modelId, data) {
            users._modelId = modelId;
            if (!!data && !!users.get(modelId)) {
              users.get(modelId).set(data);
            }
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
        $.get('/api/v1/admin/exercise-bodypart').done(function(data) {
          exercises._bodyparts = _.union({id: null, nameEn: ''}, data);
          exercises.trigger('sync');
        });
        $.get('/api/v1/admin/exercise-equipment-type').done(function(data) {
          exercises._equipmentTypes = _.union({id: null, nameEn: ''}, data);
          exercises.trigger('sync');
        });
        $.get('/api/v1/admin/exercise-type').done(function(data) {
          exercises._types = _.union({id: null, name: ''}, data);
          exercises.trigger('sync');
        });
        $.get('/api/v1/admin/exercise-input').done(function(data) {
          exercises._inputs = _.union({id: null, name: ''}, data);
          exercises.trigger('sync');
        });
        $.get('/api/v1/admin/exercise-output').done(function(data) {
          exercises._outputs = _.union({id: null, name: ''}, data);
          exercises.trigger('sync');
        });
        exercises.on('exercise:new', function(model) {
          var exercise = new ExercisesModels.Exercise();
          exercise._bodyparts = exercises._bodyparts;
          exercise._equipmentTypes = exercises._equipmentTypes;
          exercise._types = exercises._types;
          exercise._inputs = exercises._inputs;
          exercise._outputs = exercises._outputs;
          if (!_.isUndefined(model)) {
            if (!_.isEmpty(model.get('files'))) {
              $.get('/api/v1/admin/exercise-file/' + _.map(model.get('files'), function(item) {return item.id;})).done(function(data) {
                exercise._files.set(data);
                exercise._model.set('files', data);
                exercise._files.trigger('sync');
              });
            }
            exercise.set({
              id: model.get('id'),
              bodypart: model.get('bodypart'),
              equipmentType: model.get('equipmentType'),
              types: model.get('types'),
              inputs: model.get('inputs'),
              outputs: model.get('outputs'),
              cardioPercent: model.get('cardioPercent'),
              exerciseId: model.get('exerciseId'),
              nameEn: model.get('nameEn'),
              nameNo: model.get('nameNo'),
              descriptionEn: model.get('descriptionEn'),
              descriptionNo: model.get('descriptionNo'),
              files: model.get('files')
            });
          }
          var exerciseEditView = new ExercisesViews.NewExerciseLayout({
            model: exercise
          });
          exercise.on('exercise:back', function(modelId, data) {
            exercises._modelId = modelId;
            if (!!data && !!exercises.get(modelId)) {
              exercises.get(modelId).set(data);
            }
            var exercisesView = new ExercisesViews.Exercises({
              collection: exercises
            });
            exercises.fetch();
            applicationLayout.mainExercises.show(exercisesView);
          });
          applicationLayout.mainExercises.show(exerciseEditView);
        });
        applicationLayout.mainExercises.show(exercisesView);

        var goals = new GoalsModels.Goals();
        var goalsView = new GoalsViews.Goals({
          collection: goals
        });
        goals.fetch();
        $.get('/api/v1/admin/goal-parameter').done(function(data) {
          goals._parameters = _.union({id: null, name: ''}, data);
          goals.trigger('sync');
        });
        $.get('/api/v1/admin/goal-type').done(function(data) {
          goals._types = _.union({id: null, name: ''}, data);
          goals.trigger('sync');
        });
        goals.on('goal:new', function(model) {
          var goal = new GoalsModels.Goal();
          goal._parameters = goals._parameters;
          goal._types = goals._types;
          if (!_.isUndefined(model)) {
            goal.set({
              id: model.get('id'),
              parameters: model.get('parameters'),
              type: model.get('type'),
              titleEn: model.get('titleEn'),
              titleNo: model.get('titleNo'),
              title2En: model.get('title2En'),
              title2No: model.get('title2No')
            });
          }
          var goalEditView = new GoalsViews.NewGoalLayout({
            model: goal
          });
          goal.on('goal:back', function(modelId, data) {
            goals._modelId = modelId;
            if (!!data && !!goals.get(modelId)) {
              goals.get(modelId).set(data);
            }
            var goalsView = new GoalsViews.Goals({
              collection: goals
            });
            goals.fetch();
            applicationLayout.mainGoals.show(goalsView);
          });
          applicationLayout.mainGoals.show(goalEditView);
        });
        applicationLayout.mainGoals.show(goalsView);

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
              dataUrl: model.get('dataUrl'),
              parseExercises:  model.get('parseExercises'),
              parseGoals: model.get('parseGoals')
            });
          }
          var programEditView = new ProgramsViews.NewProgramLayout({
            model: program
          });
          program.on('program:back', function(modelId, data) {
            programs._modelId = modelId;
            if (!!data && !!programs.get(modelId)) {
              programs.get(modelId).set(data);
            }
            var programsView = new ProgramsViews.Programs({
              collection: programs
            });
            programs.fetch();
            applicationLayout.mainPrograms.show(programsView);
          });
          applicationLayout.mainPrograms.show(programEditView);
        });
        applicationLayout.mainPrograms.show(programsView);

        var certificates = new CertificatesModels.Certificates();
        var certificatesView = new CertificatesViews.Certificates({
          collection: certificates
        });
        certificates.fetch();
        certificates.on('certificate:new', function(model) {
          var certificate = new CertificatesModels.Certificate();
          if (!_.isUndefined(model)) {
            certificate.set({
              id: model.get('id'),
              code: model.get('code'),
              amountOfDays: model.get('amountOfDays'),
              activated: model.get('activated')
            });
          } else {
            certificate.set('code', certificate.generate());
          }
          var certificateEditView = new CertificatesViews.NewCertificateLayout({
            model: certificate
          });
          certificate.on('certificate:back', function(modelId, data) {
            certificates._modelId = modelId;
            if (!!data && !!certificates.get(modelId)) {
              certificates.get(modelId).set(data);
            }
            var certificatesView = new CertificatesViews.Certificates({
              collection: certificates
            });
            certificates.fetch();
            applicationLayout.mainCertificates.show(certificatesView);
          });
          applicationLayout.mainCertificates.show(certificateEditView);
        });
        applicationLayout.mainCertificates.show(certificatesView);
        
        var emails = new EmailsModels.Emails();
        var emailsView = new EmailsViews.Emails({
          collection: emails
        });
        emails.fetch();
        $.get('/api/v1/admin/email-message-type').done(function(data) {
          emails._types = _.union({id: null, name: ''}, data);
          emails.trigger('sync');
        });
        emails.on('email:new', function(model) {
          var email = new EmailsModels.Email();
          email._types = emails._types;
          if (!_.isUndefined(model)) {
            email.set({
              id: model.get('id'),
              emailSubjectEn: model.get('emailSubjectEn'),
              emailSubjectNo: model.get('emailSubjectNo'),
              emailTextEn: model.get('emailTextEn'),
              emailTextNo: model.get('emailTextNo'),
              type: model.get('type')
            });
          }
          var emailEditView = new EmailsViews.NewEmailLayout({
            model: email
          });
          email.on('email:back', function(modelId, data) {
            emails._modelId = modelId;
            if (!!data && !!emails.get(modelId)) {
              emails.get(modelId).set(data);
            }
            var emailsView = new EmailsViews.Emails({
              collection: emails
            });
            emails.fetch();
            applicationLayout.mainEmails.show(emailsView);
          });
          applicationLayout.mainEmails.show(emailEditView);
        });
        applicationLayout.mainEmails.show(emailsView);
    }

    return Marionette.Controller.extend({
      login: function() {
        // If the user is already logged in,
        // show the default route.
        if (App.globals.user.authorized()) {
          App.vent.trigger('redirect:default');
          return;
        }
        App.mainRegion.show(new LoginView({ model: App.globals.user }));
      },
      index: function () {
        if (!App.globals.user.authorized()) {
          App.vent.trigger('login:required');
          return;
        }
        setupApplicationLayout('');
      }
    });
  });
