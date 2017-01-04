/*jshint browser: true*/
/*global define, console*/
define([
    'jquery',
    'underscore',
    'backbone',
    'marionette',
    'moment',
    'app',
    'bootstrapTab'
],
function ($, _, Backbone, Marionette, moment, App) {
  'use strict';

  var Layout = Marionette.Layout.extend({
    regions: {
      mainUsers: '#usersMappingConfig',
      mainExercises: '#exercisesMappingConfig',
      mainGoals: '#goalsMappingConfig',
      mainPrograms: '#programsMappingConfig',
      mainCertificates: '#certificatesMappingConfig',
      mainEmails: '#emailsMappingConfig'
    },
    template: _.template([
      '<!-- Nav tabs -->',
      '<ul class="nav nav-tabs">',
      '  <li class="active"><a href="#user" data-toggle="tab">Users</a></li>',
      '  <li><a href="#exercise" data-toggle="tab">Exercises</a></li>',
      '  <li><a href="#goal" data-toggle="tab">Goals</a></li>',
      '  <li><a href="#program" data-toggle="tab" class="js-admin-config">Programs</a></li>',
      '  <li><a href="#certificate" data-toggle="tab" class="js-admin-config">Certificates</a></li>',
      '  <li><a href="#email" data-toggle="tab" class="js-admin-config">Email templates</a></li>',
      '</ul>',
      '<!-- Tab panes -->',
      '<div class="tab-content">',
      '  <div class="tab-pane active" id="user">',
      '    <div id="usersMappingConfig"></div>',
      '  </div>',
      '  <div class="tab-pane" id="exercise">',
      '    <div id="exercisesMappingConfig"></div>',
      '  </div>',
      '  <div class="tab-pane" id="goal">',
      '    <div id="goalsMappingConfig"></div>',
      '  </div>',
      '  <div class="tab-pane" id="program">',
      '    <div id="programsMappingConfig"></div>',
      '  </div>',
      '  <div class="tab-pane" id="certificate">',
      '    <div id="certificatesMappingConfig"></div>',
      '  </div>',
      '  <div class="tab-pane" id="email">',
      '    <div id="emailsMappingConfig"></div>',
      '  </div>',
      '</div>'
    ].join(''))
  });
  
  var EmptyView = Marionette.ItemView.extend({
        tagName: 'tr',
        template: _.template('<td colspan="7">There are no users available.</td>')
  });
  
  var EmptyProgramTableItemView = Marionette.ItemView.extend({
        tagName: 'tr',
        template: _.template('<td colspan="5">There are no programs</td>')
  });

  var EmptyWorkoutTableItemView = Marionette.ItemView.extend({
        tagName: 'tr',
        template: _.template('<td colspan="4">There are no workouts</td>')
  });

  var EmptyExerciseTableItemView = Marionette.ItemView.extend({
        tagName: 'tr',
        template: _.template('<td colspan="3">There are no exercises</td>')
  });

  var User = Marionette.ItemView.extend({
    tagName: 'tr',
    template: _.template([
      '<td>',
        '{{ id }}',
      '</td>',
      '<td>',
        '{{ name }}',
      '</td>',
      '<td>',
        '{{ email }}',
      '</td>',
      '<td>',
        '{{ type == null ? "" : type.nameEn }}',
      '</td>',
      '<td>',
        '<button type="button" class="btn btn-default btn-sm js-view-value">',
          '<i class="glyphicon glyphicon-expand"></i>',
        '</button>',
      '</td>',
      '<td>',
        '<button type="button" class="btn btn-default btn-sm js-edit-value">',
          '<i class="glyphicon glyphicon-edit"></i>',
        '</button>',
      '</td>',
      '<td>',
        '<button type="button" class="btn btn-default btn-sm js-delete-value">',
          '<i class="glyphicon glyphicon-remove"></i>',
        '</button>',
      '</td>'
    ].join('')),

    initialize: function(options) {
      this.collection = options.collection;
    },
    events: {
      'click .js-view-value': 'viewUser',
      'click .js-edit-value': 'editUser',
      'click .js-delete-value': 'deleteUser'
    },
    viewUser: function() {
      this.collection.trigger('user:view', this.model);
    },
    editUser: function() {
      this.collection.trigger('user:new', this.model);
    },
    deleteUser: function(evt) {
      evt.preventDefault();
      var model = this.model;
      var collection = this.collection;
      this.model.destroy()
        .done(function() {
        })
        .fail(function (xhr) {
          App.vent.trigger('xhr:error', 'User ' + model.get('id') + ' delete was failed');
        })
        .always(function() {
          collection.fetch();
        });
    }
  });

  var Users = Marionette.CompositeView.extend({
    itemViewContainer: 'tbody',
    itemView: User,
    emptyView: EmptyView,
    tagName: 'div',
    className: 'js-users-mapping-config',
    ui: {
      table: '.table'
    },
    itemViewOptions : function () {
      return { collection: this.collection };
    },
    initialize: function() {
    },
    template: _.template([
    '<div class="panel panel-primary">',
      '<div class="panel-heading">',
        '<h3 class="panel-title"> Users </h3>',
      '</div>',
      '<button class="btn btn-primary js-new-user" style="margin: 10px;">',
        'New user',
      '</button>',
      '<table class="table">',
        '<thead>',
          '<tr>',
            '<th>ID</th>',
            '<th>Name</th>',
            '<th>E-mail</th>',
            '<th>Type</th>',
            '<th></th>',
            '<th></th>',
            '<th></th>',
          '</tr>',
        '</thead>',
        '<tbody></tbody>',
      '</table>',
    '</div>'
    ].join('')),
    collectionEvents: {
      'sync': 'render'
    },
    events: {
      'click .js-new-user': 'newUser'
    },
    newUser: function() {
      this.collection.trigger('user:new');
    }
  });

  var ViewUserLayout = Marionette.Layout.extend({
    template: _.template([
      '<div class="panel panel-primary">',
        '<div class="panel-heading">',
          '<h3 class="panel-title"> View user </h3>',
        '</div>',
        '<div id="buttons"/>',
        '<div id="inputForm"/>',
        '<div id="programTable"/>',
        '<div id="workoutTable"/>',
        '<div id="exerciseTable"/>',
      '</div>'
    ].join('')),
    tagName: 'form',
    className: 'form-horizontal',
    regions: {
      buttons: '#buttons',
      inputForm: '#inputForm',
      programTable: '#programTable',
      workoutTable: '#workoutTable',
      exerciseTable: '#exerciseTable'
    },
    onShow: function() {
      this.buttons.show(new ViewUserButtons({model: this.model}));
      this.inputForm.show(new ViewUserInputForm({model: this.model}));
      this.programTable.show(new ProgramTableForm({model: this.model,
          collection: new Backbone.Collection(_.last(this.model.get('programs'), 3))}));
      var workouts = new Backbone.Collection();
      this.workoutTable.show(new WorkoutTableForm({model: this.model,
          collection: workouts}));
      this.model.on('refresh:workouts', function(data) {
          workouts.set(data);
          workouts.trigger('sync');
      });
      var exercises = new Backbone.Collection();
      this.exerciseTable.show(new ExerciseTableForm({model: this.model,
          collection: exercises}));
      this.model.on('refresh:exercises', function(data) {
          exercises.set(data);
          exercises.trigger('sync');
      });
    }
  });

  var NewUserLayout = Marionette.Layout.extend({
    template: _.template([
      '<div class="panel panel-primary">',
        '<div class="panel-heading">',
          '<h3 class="panel-title"> {{ getHeader() }} </h3>',
        '</div>',
        '<div id="buttons"/>',
        '<div id="inputForm"/>',
      '</div>'
    ].join('')),
    templateHelpers: function() {
      var model = this.model;
      return {
        getHeader: function () {
          return model.isNew() ? 'New user' : 'Edit user';
        }
      };
    },
    tagName: 'form',
    className: 'form-horizontal',
    regions: {
      buttons: '#buttons',
      inputForm: '#inputForm'
    },
    onShow: function() {
      this.buttons.show(new NewUserButtons({model: this.model}));
      this.inputForm.show(new NewUserInputForm({model: this.model}));
    }
  });

  var ViewUserButtons = Marionette.ItemView.extend({
    template: _.template([
      '<div class="btn-group">',
        '<button class="btn btn-default js-back" style="margin: 10px 0 0 10px;">',
          'Back',
        '</button>',
      '</div>'
    ].join('')),
    events: {
      'click .js-back': 'back'
    },
    modelEvents: {
      'change': 'render'
    },
    initialize: function(options) {
      this._model = options.model.clone();
    },
    back: function(evt) {
      evt.preventDefault();
      this.model.trigger('user:back');
    }
  });

  var NewUserButtons = Marionette.ItemView.extend({
    template: _.template([
      '<div class="btn-group">',
        '<button class="btn btn-default js-back" style="margin: 10px 0 0 10px;">',
          'Back',
        '</button>',
        '<button class="btn btn-danger js-save  {{ getSaveDisabled() }}" style="margin: 10px 0 0 0; min-width: 100px;">',
          'Save',
        '</button>',
        '<button class="btn btn-default js-discard" style="margin: 10px 0 0 0px;">',
          'Discard',
        '</button>',
      '</div>'
    ].join('')),
    templateHelpers: function() {
      var view = this;
      return {
        getSaveDisabled: function () {
          return view.model.isValid() ? '' : 'disabled';
        }
      };
    },
    events: {
      'click .js-back': 'back',
      'click .js-save': 'save',
      'click .js-discard': 'discard'
    },
    modelEvents: {
      'change': 'render'
    },
    initialize: function(options) {
      this._model = options.model.clone();
    },
    back: function(evt) {
      evt.preventDefault();
      this.model.trigger('user:back');
    },
    save: function(evt) {
      evt.preventDefault();
      var model = this.model;
      this.model.save().done(function() {
        model.trigger('user:back');
      })
      .fail(function (xhr) {
        App.vent.trigger('xhr:error', 'User save was failed');
      });
    },
    discard: function(evt) {
      evt.preventDefault();
      this.model.set(this._model.toJSON());
      this.model.trigger('sync');
    }
  });
  
  var ProgramTableItem = Marionette.ItemView.extend({
    tagName: 'tr',
    template: _.template([
      '<td>',
        '{{ id }}',
      '</td>',
      '<td>',
        '{{ name }}',
      '</td>',
      '<td>',
        '{{ _.map(workouts, function(item) {return item.name;}) }}',
      '</td>',
      '<td>',
        '{{ formatDate(created) }}',
      '</td>',
      '<td>',
        '<button type="button" class="btn btn-default btn-sm js-view-workouts">',
          '<i class="glyphicon glyphicon-expand"></i>',
        '</button>',
      '</td>'
    ].join('')),
    templateHelpers: function() {
      return {
        formatDate: function(dateTime) {
          return moment(dateTime).format('DD.MM.YYYY HH:mm');
        }
      };
    },
    initialize: function(options) {
      this._model = options.rootModel;
    },
    events: {
      'click .js-view-workouts': 'reloadData'
    },
    reloadData: function(evt) {
      evt.preventDefault();
      this._model._programName = this.model.get('name');
      this._model.trigger('refresh:workouts', this.model.get('workouts'));
      this._model._workoutName = '';
      this._model.trigger('refresh:exercises', []);
    }
  });

  var WorkoutTableItem = Marionette.ItemView.extend({
    tagName: 'tr',
    template: _.template([
      '<td>',
        '{{ id }}',
      '</td>',
      '<td>',
        '{{ name }}',
      '</td>',
      '<td>',
        '{{ _.map(items, function(item) {return item.exercise_name;}) }}',
      '</td>',
      '<td>',
        '<button type="button" class="btn btn-default btn-sm js-view-exercises">',
          '<i class="glyphicon glyphicon-expand"></i>',
        '</button>',
      '</td>'
    ].join('')),
    initialize: function(options) {
      this._model = options.rootModel;
    },
    events: {
      'click .js-view-exercises': 'reloadData'
    },
    reloadData: function(evt) {
      evt.preventDefault();
      this._model._workoutName = this.model.get('name');
      this._model.trigger('refresh:exercises', this.model.get('items'));
    }
  });

  var ExerciseTableItem = Marionette.ItemView.extend({
    tagName: 'tr',
    template: _.template([
      '<td>',
        '{{ id }}',
      '</td>',
      '<td>',
        '{{ exercise_name }}',
      '</td>',
      '<td>',
        '{{ getInputs() }}',
      '</td>',
      '<td>',
        '{{ getOutputs() }}',
      '</td>'
    ].join('')),
    templateHelpers: function() {
      var model = this.model;
      return {
        getInputs: function () {
          return 'sets ' + model.get('sets') +
                  (model.get('repetitions') === null ? '' : ', repetitions ' + model.get('repetitions')) +
                  (model.get('weight') === null ? '' : ', weight ' + model.get('weight'));
        },
        getOutputs: function () {
          return (_.isNull(model.get('reportSets')) ? '' : 'sets ' + model.get('reportSets')) +
                  (_.isEmpty(_.compact(model.get('reportRepetitions'))) ? '' : ', repetitions ' + model.get('reportRepetitions').join(', ')) +
                  (_.isEmpty(_.compact(model.get('reportWeight'))) ? '' : ', weight ' + model.get('reportWeight').join(', '));
        }
      };
    }
  });

  var ProgramTableForm = Marionette.CompositeView.extend({
    itemViewContainer: 'tbody',
    itemView: ProgramTableItem,
    emptyView: EmptyProgramTableItemView,
    tagName: 'div',
    className: 'js-users-mapping-config',
    ui: {
      table: '.table'
    },
    itemViewOptions : function () {
      return { collection: this.collection, rootModel: this.model };
    },
    initialize: function() {
    },
    template: _.template([
    '<div class="panel panel-primary">',
      '<div class="panel-heading">',
        '<h3 class="panel-title"> Assigned programs </h3>',
      '</div>',
      '<button class="btn btn-primary js-reload-data" style="margin: 10px;">',
        'Refresh',
      '</button>',
      '<table class="table">',
        '<thead>',
          '<tr>',
            '<th>ID</th>',
            '<th>Program name</th>',
            '<th>Workouts</th>',
            '<th>Updated</th>',
            '<th></th>',
          '</tr>',
        '</thead>',
        '<tbody></tbody>',
      '</table>',
    '</div>'
    ].join('')),
    modelEvents: {
      'sync': 'render'
    },
    collectionEvents: {
      'sync': 'render'
    },
    events: {
      'click .js-reload-data': 'reloadData'
    },
    reloadData: function(evt) {
      evt.preventDefault();
      var view = this;
      this.model.clone().fetch().done(function(data) {
        view.collection.set(_.last(data.programs, 3));
        view.model._programName = '';
        view.model._workoutName = '';
        view.model.trigger('refresh:workouts', []);
        view.model.trigger('refresh:exercises', []);
      });
    }
  });

  var WorkoutTableForm = Marionette.CompositeView.extend({
    itemViewContainer: 'tbody',
    itemView: WorkoutTableItem,
    emptyView: EmptyWorkoutTableItemView,
    tagName: 'div',
    className: 'js-users-mapping-config',
    ui: {
      table: '.table'
    },
    itemViewOptions : function () {
      return { collection: this.collection, rootModel: this.model };
    },
    initialize: function() {
    },
    template: _.template([
    '<div class="panel panel-primary">',
      '<div class="panel-heading">',
        '<h3 class="panel-title"> Assigned workouts {{ _.isEmpty(getProgramName()) ? "" : "for " + getProgramName() }}</h3>',
      '</div>',
      '<table class="table">',
        '<thead>',
          '<tr>',
            '<th>ID</th>',
            '<th>Workout name</th>',
            '<th>Exercises</th>',
            '<th></th>',
          '</tr>',
        '</thead>',
        '<tbody></tbody>',
      '</table>',
    '</div>'
    ].join('')),
    templateHelpers: function() {
      var view = this;
      return {
        getProgramName: function () {
          return view.model._programName;
        }
      };
    },
    modelEvents: {
      'sync': 'render'
    },
    collectionEvents: {
      'sync': 'render'
    }
  });

  var ExerciseTableForm = Marionette.CompositeView.extend({
    itemViewContainer: 'tbody',
    itemView: ExerciseTableItem,
    emptyView: EmptyExerciseTableItemView,
    tagName: 'div',
    className: 'js-users-mapping-config',
    ui: {
      table: '.table'
    },
    itemViewOptions : function () {
      return { collection: this.collection };
    },
    initialize: function() {
    },
    template: _.template([
    '<div class="panel panel-primary">',
      '<div class="panel-heading">',
        '<h3 class="panel-title"> Assigned exercises {{ _.isEmpty(getWorkoutName()) ? "" : "for " + getWorkoutName() }}</h3>',
      '</div>',
      '<table class="table">',
        '<thead>',
          '<tr>',
            '<th>ID</th>',
            '<th>Exercise name</th>',
            '<th>Inputs</th>',
            '<th>Outputs</th>',
          '</tr>',
        '</thead>',
        '<tbody></tbody>',
      '</table>',
    '</div>'
    ].join('')),
    templateHelpers: function() {
      var view = this;
      return {
        getWorkoutName: function () {
          return view.model._workoutName;
        }
      };
    },
    modelEvents: {
      'sync': 'render'
    },
    collectionEvents: {
      'sync': 'render'
    }
  });

  var ViewUserInputForm = Marionette.ItemView.extend({
    className: 'user-input-form',
    template: _.template([
      '<div class="form-group">',
        '<label class="col-sm-3 control-label">ID</label>',
        '<div class="col-sm-8">',
          '<p class="form-control-static">',
            ' {{ id }}',
          '</p>',
        '</div>',
      '</div>',
      '<div class="form-group">',
        '<label class="col-sm-3 control-label">Type</label>',
        '<div class="col-sm-8">',
          '{{ getTypes() }}',
        '</div>',
      '</div>',
      '<div class="form-group">',
        '<label class="col-sm-3 control-label">Gender</label>',
        '<div class="col-sm-8">',
          '{{ getGenders() }}',
        '</div>',
      '</div>',
      '<div class="form-group">',
        '<label class="col-sm-3 control-label">Level</label>',
        '<div class="col-sm-8">',
          '{{ getLevels() }}',
        '</div>',
      '</div>',
      '<div class="form-group">',
        '<label class="col-sm-3 control-label">Goals</label>',
        '<div class="col-sm-8">',
          '{{ getGoals() }}',
        '</div>',
      '</div>',
      '<div class="form-group">',
        '<label class="col-sm-3 control-label">Name</label>',
        '<div class="col-sm-8">',
          '<textarea id="user-name" class="form-control" rows="3" placeholder="Please enter name" name="address" required="true" readonly>',
            '{{ name }}',
          '</textarea>',
        '</div>',
      '</div>',
      '<div class="form-group">',
        '<label class="col-sm-3 control-label">Email</label>',
        '<div class="col-sm-8">',
          '<textarea id="user-email" class="form-control" rows="3" placeholder="Please enter email" name="address" required="true" readonly>',
            '{{ email }}',
          '</textarea>',
        '</div>',
      '</div>'
    ].join('')),
    templateHelpers: function() {
      var model = this.model;
      return {
        getTypes: function() {
          var types = model._types || [];
          var result = _.compact(_.map(types, function(item) {
            if (_.isNull(item.id)) {
              return '';
            }
            return !!model.get('type') && model.get('type').id === item.id ? item.nameEn : null;
          }));
          return result.join('');
        },
        getGenders: function() {
          var genders = model._genders || [];
          var result = _.compact(_.map(genders, function(item) {
            if (_.isNull(item.id)) {
              return '';
            }
            return !!model.get('gender') && model.get('gender') === item.id ? item.name : null;
          }));
          return result.join('');
        },
        getLevels: function() {
          var levels = model._levels || [];
          var result = _.compact(_.map(levels, function(item) {
            if (_.isNull(item.id)) {
              return '';
            }
            return !!model.get('level') && model.get('level') === item.id ? item.name : null;
          }));
          return result.join('');
        },
        getGoals: function() {
          var goals = model._goals || [];
          var modelGoals = _.map(model.get('goals'), function(item) {
             return item.id;
          });
          var result = _.map(goals, function(item) {
            if (_.isNull(item.id)) {
              return '';
            }
            return _.contains(modelGoals, item.id) ? _.compact([item.title, item.title2]).join(',') : '';
          });
          return _.compact(result).join(', ');
        }
      };
    },
    modelEvents: {
      'sync': 'render'
    },
    events: {
      'input #user-name': 'inputName',
      'input #user-email': 'inputEmail'
    },
    ui: {
      userType: '#user-type',
      name: '#user-name',
      email: '#user-email'
    },
    inputName: function() {
      this.model.set('name', this.ui.name.val());
    },
    inputEmail: function() {
      this.model.set('email', this.ui.email.val());
    },
    onShow: function() {
      this.onRender();
    },
    onRender: function() {
      var view = this;
      $('.selectpicker').selectpicker({
        style: 'btn-default',
        size: false
      });
      this.ui.userType.on('changed.bs.select', function (e) {
        view.model.set('type', {id: parseInt(e.target.value, 10) });
      });
    }
  });

  var NewUserInputForm = Marionette.ItemView.extend({
    className: 'user-input-form',
    template: _.template([
      '<div class="form-group">',
        '<label class="col-sm-3 control-label">ID</label>',
        '<div class="col-sm-8">',
          '<p class="form-control-static">',
            ' {{ id }}',
          '</p>',
        '</div>',
      '</div>',
      '<div class="form-group">',
        '<label class="col-sm-3 control-label">Type</label>',
        '<div class="col-sm-8">',
          '<select id="user-type" class="selectpicker show-tick">',
            '{{ getTypes() }}',
          '</select>',
        '</div>',
      '</div>',
      '<div class="form-group">',
        '<label class="col-sm-3 control-label">Gender</label>',
        '<div class="col-sm-8">',
          '<select id="user-gender" class="selectpicker show-tick">',
            '{{ getGenders() }}',
          '</select>',
        '</div>',
      '</div>',
      '<div class="form-group">',
        '<label class="col-sm-3 control-label">Level</label>',
        '<div class="col-sm-8">',
          '<select id="user-level" class="selectpicker show-tick">',
            '{{ getLevels() }}',
          '</select>',
        '</div>',
      '</div>',
      '<div class="form-group">',
        '<label class="col-sm-3 control-label">Goals</label>',
        '<div class="col-sm-8">',
          '<select id="user-goal" class="selectpicker show-tick" multiple data-max-options="2">',
            '{{ getGoals() }}',
          '</select>',
        '</div>',
      '</div>',
      '<div class="form-group">',
        '<label class="col-sm-3 control-label">Name</label>',
        '<div class="col-sm-8">',
          '<textarea id="user-name" class="form-control" rows="3" placeholder="Please enter name" name="address" required="true">',
            '{{ name }}',
          '</textarea>',
        '</div>',
      '</div>',
      '<div class="form-group">',
        '<label class="col-sm-3 control-label">Email</label>',
        '<div class="col-sm-8">',
          '<textarea id="user-email" class="form-control" rows="3" placeholder="Please enter email" name="address" required="true">',
            '{{ email }}',
          '</textarea>',
        '</div>',
      '</div>'
    ].join('')),
    templateHelpers: function() {
      var model = this.model;
      return {
        getTypes: function() {
          var types = model._types || [];
          var result = _.map(types, function(item) {
            if (_.isNull(item.id)) {
              return '<option data-hidden="true"></option>';
            }
            return '<option value="' + item.id + '"' +
              (!!model.get('type') && model.get('type').id === item.id ? ' selected' : '') +
              '>' + item.nameEn + '</option>';
          });
          return result;
        },
        getGenders: function() {
          var genders = model._genders || [];
          var result = _.map(genders, function(item) {
            if (_.isNull(item.id)) {
              return '<option></option>';
            }
            return '<option value="' + item.id + '"' +
              (!!model.get('gender') && model.get('gender') === item.id ? ' selected' : '') +
              '>' + item.name + '</option>';
          });
          return result;
        },
        getLevels: function() {
          var levels = model._levels || [];
          var result = _.map(levels, function(item) {
            if (_.isNull(item.id)) {
              return '<option></option>';
            }
            return '<option value="' + item.id + '"' +
              (!!model.get('level') && model.get('level') === item.id ? ' selected' : '') +
              '>' + item.name + '</option>';
          });
          return result;
        },
        getGoals: function() {
          var goals = model._goals || [];
          var modelGoals = _.map(model.get('goals'), function(item) {
             return item.id;
          });
          var result = _.map(goals, function(item) {
            if (_.isNull(item.id)) {
              return '<option data-hidden="true"></option>';
            }
            return '<option value="' + item.id + '"' +
              (_.contains(modelGoals, item.id) ? ' selected' : '') +
              '>' + item.title + (_.isNull(item.title2) ? '' : ', ' + item.title2) + '</option>';
          });
          return result;
        }
      };
    },
    modelEvents: {
      'sync': 'render'
    },
    events: {
      'input #user-name': 'inputName',
      'input #user-email': 'inputEmail'
    },
    ui: {
      userType: '#user-type',
      userGender: '#user-gender',
      userLevel: '#user-level',
      userGoal: '#user-goal',
      name: '#user-name',
      email: '#user-email'
    },
    inputName: function() {
      this.model.set('name', this.ui.name.val());
    },
    inputEmail: function() {
      this.model.set('email', this.ui.email.val());
    },
    onShow: function() {
      this.onRender();
    },
    onRender: function() {
      var view = this;
      $('.selectpicker').selectpicker({
        style: 'btn-default',
        size: false
      });
      this.ui.userType.on('changed.bs.select', function (e) {
        view.model.set('type', {id: parseInt(e.target.value, 10) });
      });
      this.ui.userGender.on('changed.bs.select', function (e) {
        view.model.set('gender', _.isEmpty(e.target.value) ? null : e.target.value);
      });
      this.ui.userLevel.on('changed.bs.select', function (e) {
        view.model.set('level', _.isEmpty(e.target.value) ? null : parseInt(e.target.value, 10));
      });
      this.ui.userGoal.on('changed.bs.select', function (e) {
        var selectedGoals = _.map(e.target.selectedOptions, function(item) {
            return {id: parseInt(item.value, 10) };
        });
        view.model.set('goals', selectedGoals);
      });
    }
  });

  return {
    Users: Users,
    ViewUserLayout: ViewUserLayout,
    NewUserLayout: NewUserLayout,
    Layout: Layout
  };

});
