/*jshint browser: true*/
/*global define, document*/
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

  var EmptyView = Marionette.ItemView.extend({
    tagName: 'tr',
    template: _.template('<td colspan="7">There are no programs available.</td>')
  });

  var EmptyExercisView = Marionette.ItemView.extend({
    tagName: 'tr',
    template: _.template('<td colspan="7">There are no exercises available.</td>')
  });

  var EmptyParseView = Marionette.ItemView.extend({
    tagName: 'tr',
    template: _.template('<td colspan="4">There are no goals available.</td>')
  });

  var Program = Marionette.ItemView.extend({
    tagName: 'tr',
    template: _.template([
      '<td>',
        '{{ id }}',
      '</td>',
      '<td>',
        '{{ name }}',
      '</td>',
      '<td>',
        '{{ fileName }}',
      '</td>',
      '<td>',
        '{{ updated == null ? "" : formatDate(updated) }}',
      '</td>',
      '<td>',
        '<a type="button" class="btn btn-default btn-sm" href="/api/v1/admin/program-file/{{ id }}/{{ fileName }}" download>',
          '<i class="glyphicon glyphicon-download"></i>',
        '</a>',
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
    templateHelpers: function() {
      return {
        formatDate: function(dateTime) {
          return moment(dateTime).format('DD.MM.YYYY HH:mm');
        }
      };
    },
    initialize: function(options) {
      this.collection = options.collection;
    },
    events: {
      'click .js-edit-value': 'editProgram',
      'click .js-delete-value': 'deleteProgram'
    },
    editProgram: function() {
      this.collection.trigger('program:new', this.model);
    },
    deleteProgram: function(evt) {
      evt.preventDefault();
      var model = this.model;
      var collection = this.collection;
      this.model.destroy()
        .done(function() {
        })
        .fail(function (xhr) {
          App.vent.trigger('xhr:error', 'Program ' + model.get('id') + ' delete was failed');
        })
        .always(function() {
          collection.fetch();
        });
    }
  });

  var ParseResult = Marionette.ItemView.extend({
    tagName: 'tr',
    template: _.template([
      '<td>',
        '{{ id }}',
      '</td>',
      '<td>',
        '{{ name }}',
      '</td>',
      '<td>',
        '{{ _.map(userGroups, function(item) {return item.name;}) }}',
      '</td>',
      '<td>',
        '{{ errors }}',
      '</td>',
      '<td>',
        '<button type="button" class="btn btn-default btn-sm js-view-userGroups">',
          '<i class="glyphicon glyphicon-expand"></i>',
        '</button>',
      '</td>'
    ].join('')),
    initialize: function(options) {
      this._model = options.rootModel;
    },
    events: {
      'click .js-view-userGroups': 'reloadData'
    },
    reloadData: function(evt) {
      evt.preventDefault();
      this._model._goalName = this.model.get('name');
      this._model.trigger('refresh:userGroups', this.model.get('userGroups'));
      this._model._roundName = '';
      this._model.trigger('refresh:rounds', []);
      this._model._partName = '';
      this._model.trigger('refresh:parts', []);
      this._model._workoutName = '';
      this._model.trigger('refresh:workouts', []);
      this._model.trigger('refresh:workoutItems', []);
    }
  });

  var ParseExerciseResult = Marionette.ItemView.extend({
    tagName: 'tr',
    template: _.template([
      '<td>',
        '{{ exercise_id }}',
      '</td>',
      '<td>',
        '{{ exercise_name }}',
      '</td>',
      '<td>',
        '{{ user_group_1_percent }}',
      '</td>',
      '<td>',
        '{{ user_group_2_percent }}',
      '</td>',
      '<td>',
        '{{ user_group_2_percent }}',
      '</td>',
      '<td>',
        '{{ user_group_4_percent }}',
      '</td>',
      '<td>',
        '{{ basis_for_calculations }}',
      '</td>'
    ].join('')),
    initialize: function(options) {
      this._model = options.rootModel;
    }
  });

  var ParseUserGroupResult = Marionette.ItemView.extend({
    tagName: 'tr',
    template: _.template([
      '<td>',
        '{{ id }}',
      '</td>',
      '<td>',
        '{{ name }}',
      '</td>',
      '<td>',
        '{{ _.map(rounds, function(item) {return item.name;}) }}',
      '</td>',
      '<td>',
        '<button type="button" class="btn btn-default btn-sm js-view-round">',
          '<i class="glyphicon glyphicon-expand"></i>',
        '</button>',
      '</td>'
    ].join('')),
    initialize: function(options) {
      this._model = options.rootModel;
    },
    events: {
      'click .js-view-round': 'reloadData'
    },
    reloadData: function(evt) {
      evt.preventDefault();
      this._model._userGroupName = this.model.get('name');
      this._model.trigger('refresh:rounds', this.model.get('rounds'));
      this._model.trigger('refresh:parts', []);
      this._model._workoutName = '';
      this._model.trigger('refresh:workouts', []);
      this._model.trigger('refresh:workoutItems', []);
    }
  });

  var ParseRoundResult = Marionette.ItemView.extend({
    tagName: 'tr',
    template: _.template([
      '<td>',
        '{{ id }}',
      '</td>',
      '<td>',
        '{{ name }}',
      '</td>',
      '<td>',
        '{{ _.map(parts, function(item) {return item.name;}) }}',
      '</td>',
      '<td>',
        '<button type="button" class="btn btn-default btn-sm js-view-round">',
          '<i class="glyphicon glyphicon-expand"></i>',
        '</button>',
      '</td>'
    ].join('')),
    initialize: function(options) {
      this._model = options.rootModel;
    },
    events: {
      'click .js-view-round': 'reloadData'
    },
    reloadData: function(evt) {
      evt.preventDefault();
      this._model._roundName = this.model.get('name');
      this._model.trigger('refresh:parts', this.model.get('parts'));
      this._model._partName = '';
      this._model.trigger('refresh:workouts', []);
      this._model._workoutName = '';
      this._model.trigger('refresh:workoutItems', []);
    }
  });

  var ParsePartResult = Marionette.ItemView.extend({
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
        '<button type="button" class="btn btn-default btn-sm js-view-workout">',
          '<i class="glyphicon glyphicon-expand"></i>',
        '</button>',
      '</td>'
    ].join('')),
    initialize: function(options) {
      this._model = options.rootModel;
    },
    events: {
      'click .js-view-workout': 'reloadData'
    },
    reloadData: function(evt) {
      evt.preventDefault();
      this._model._partName = this.model.get('name');
      this._model.trigger('refresh:workouts', this.model.get('workouts'));
      this._model._workoutName = '';
      this._model.trigger('refresh:workoutItems', []);
    }
  });

  var ParseWorkoutResult = Marionette.ItemView.extend({
    tagName: 'tr',
    template: _.template([
      '<td>',
        '{{ id }}',
      '</td>',
      '<td>',
        '{{ name }}',
      '</td>',
      '<td>',
        '{{ _.map(workoutItems, function(item) {return item.name;}) }}',
      '</td>',
      '<td>',
        '<button type="button" class="btn btn-default btn-sm js-view-workout">',
          '<i class="glyphicon glyphicon-expand"></i>',
        '</button>',
      '</td>'
    ].join('')),
    initialize: function(options) {
      this._model = options.rootModel;
    },
    events: {
      'click .js-view-workout': 'reloadData'
    },
    reloadData: function(evt) {
      evt.preventDefault();
      this._model._workoutName = this.model.get('name');
      this._model.trigger('refresh:workoutItems', this.model.get('workoutItems'));
    }
  });

  var ParseWorkoutItemResult = Marionette.ItemView.extend({
    tagName: 'tr',
    template: _.template([
      '<td>',
        '{{ id }}',
      '</td>',
      '<td>',
        '{{ name }}',
      '</td>',
      '<td>',
        '{{ getInputs() }}',
      '</td>'
    ].join('')),
    templateHelpers: function() {
      var model = this.model;
      return {
        getInputs: function () {
          var repetitions = _.compact(_.map(model.get('sets'), function(item) { return item.repetitions; }));
          var weight = _.compact(_.map(model.get('sets'), function(item) { return item.weigh; }));
          var timeInMin = _.compact(_.map(model.get('sets'), function(item) { return item['time_in_min']; }));
          return 'sets ' + model.get('sets').length +
                  (_.isEmpty(repetitions) ? '' : ', repetitions ' + repetitions.join(',')) +
                  (_.isEmpty(weight) ? '' : ', weight ' + weight.join(',')) +
                  (_.isEmpty(timeInMin) ? '' : ', time ' + timeInMin.join(','));
        }
      };
    },
    initialize: function(options) {
      this._model = options.rootModel;
    },
    events: {
      'click .js-view-workout': 'reloadData'
    },
    reloadData: function(evt) {
      evt.preventDefault();
      this._model._workoutName = this.model.get('name');
      this._model.trigger('refresh:workoutItemss', this.model.get('workoutItems'));
    }
  });

  var Programs = Marionette.CompositeView.extend({
    itemViewContainer: 'tbody',
    itemView: Program,
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
        '<h3 class="panel-title"> Programs </h3>',
      '</div>',
      '<button class="btn btn-primary js-new-program" style="margin: 10px;">',
        'New program',
      '</button>',
      '<table class="table">',
        '<thead>',
          '<tr>',
            '<th>ID</th>',
            '<th>Name</th>',
            '<th>File name</th>',
            '<th>Updated</th>',
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
      'click .js-new-program': 'newProgram'
    },
    newProgram: function() {
      this.collection.trigger('program:new');
    }
  });

  var NewProgramLayout = Marionette.Layout.extend({
    template: _.template([
      '<div class="panel panel-primary">',
        '<div class="panel-heading">',
          '<h3 class="panel-title"> {{ getHeader() }} </h3>',
        '</div>',
        '<div id="buttons"/>',
        '<div id="inputForm"/>',
        '<div id="parseResultTable"/>',
        '<div id="parseGoalResultTable"/>',
        '<div id="parseUserGroupResultTable"/>',
        '<div id="parseRoundResultTable"/>',
        '<div id="parsePartResultTable"/>',
        '<div id="parseWorkoutResultTable"/>',
        '<div id="parseWorkoutItemResultTable"/>',
      '</div>'
    ].join('')),
    templateHelpers: function() {
      var model = this.model;
      return {
        getHeader: function () {
          return model.isNew() ? 'New program' : 'Edit program';
        }
      };
    },
    tagName: 'form',
    className: 'form-horizontal',
    regions: {
      buttons: '#buttons',
      inputForm: '#inputForm',
      parseResultTable: '#parseResultTable',
      parseGoalResultTable: '#parseGoalResultTable',
      parseUserGroupResultTable: '#parseUserGroupResultTable',
      parseRoundResultTable: '#parseRoundResultTable',
      parsePartResultTable: '#parsePartResultTable',
      parseWorkoutResultTable: '#parseWorkoutResultTable',
      parseWorkoutItemResultTable: '#parseWorkoutItemResultTable'
    },
    onShow: function() {
      this.buttons.show(new NewProgramButtons({model: this.model}));
      this.inputForm.show(new NewProgramInputForm({model: this.model}));
      this.parseResultTable.show(new ParseExerciseResultForm({model: this.model,
          collection: new Backbone.Collection(this.model.get('parseExercises'))}));
      this.parseGoalResultTable.show(new ParseResultForm({model: this.model,
          collection: new Backbone.Collection(this.model.get('parseGoals'))}));
      var userGroups = new Backbone.Collection();
      this.parseUserGroupResultTable.show(new ParseUserGroupForm({model: this.model,
          collection: userGroups}));
      this.model.on('refresh:userGroups', function(data) {
          userGroups.set(data);
          userGroups.trigger('sync');
      });
      var rounds = new Backbone.Collection();
      this.parseRoundResultTable.show(new ParseRoundForm({model: this.model,
          collection: rounds}));
      this.model.on('refresh:rounds', function(data) {
          rounds.set(data);
          rounds.trigger('sync');
      });
      var parts = new Backbone.Collection();
      this.parsePartResultTable.show(new ParsePartForm({model: this.model,
          collection: parts}));
      this.model.on('refresh:parts', function(data) {
          parts.set(data);
          parts.trigger('sync');
      });
      var workouts = new Backbone.Collection();
      this.parseWorkoutResultTable.show(new ParseWorkoutForm({model: this.model,
          collection: workouts}));
      this.model.on('refresh:workouts', function(data) {
          workouts.set(data);
          workouts.trigger('sync');
      });
      var workoutItems = new Backbone.Collection();
      this.parseWorkoutItemResultTable.show(new ParseWorkoutItemForm({model: this.model,
          collection: workoutItems}));
      this.model.on('refresh:workoutItems', function(data) {
          workoutItems.set(data);
          workoutItems.trigger('sync');
      });
    }
  });

  var NewProgramButtons = Marionette.ItemView.extend({
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
      this.model.trigger('program:back');
    },
    save: function(evt) {
      evt.preventDefault();
      var model = this.model;
      this.model.save().done(function() {
        model.trigger('program:back');
      })
      .fail(function (xhr) {
        App.vent.trigger('xhr:error', 'Program save was failed');
      });
    },
    discard: function(evt) {
      evt.preventDefault();
      this.model.set(this._model.toJSON());
      this.model.trigger('sync');
    }
  });

  var ParseResultForm = Marionette.CompositeView.extend({
    itemViewContainer: 'tbody',
    itemView: ParseResult,
    emptyView: EmptyParseView,
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
        '<h3 class="panel-title"> Goals </h3>',
      '</div>',
      '<table class="table">',
        '<thead>',
          '<tr>',
            '<th>ID</th>',
            '<th>Goal name</th>',
            '<th>User groups</th>',
            '<th>Errors</th>',
            '<th></th>',
          '</tr>',
        '</thead>',
        '<tbody></tbody>',
      '</table>',
    '</div>'
    ].join('')),
    modelEvents: {
      'change': 'render'
    },
    collectionEvents: {
      'sync': 'render'
    }
  });

  var ParseExerciseResultForm = Marionette.CompositeView.extend({
    itemViewContainer: 'tbody',
    itemView: ParseExerciseResult,
    emptyView: EmptyExercisView,
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
        '<h3 class="panel-title"> Exercises </h3>',
      '</div>',
      '<button class="btn btn-primary js-parse-file" style="margin: 10px;" {{ getReparseDisabled() }}>',
        'Reparse',
      '</button>',
      '<table class="table">',
        '<thead>',
          '<tr>',
            '<th>ID</th>',
            '<th>Name</th>',
            '<th>User group 1</th>',
            '<th>User group 2</th>',
            '<th>User group 3</th>',
            '<th>User group 4</th>',
            '<th>Basis for calculations</th>',
          '</tr>',
        '</thead>',
        '<tbody></tbody>',
      '</table>',
    '</div>'
    ].join('')),
    templateHelpers: function() {
      var view = this;
      return {
        getReparseDisabled: function () {
          return view.model.isValid() ? '' : 'disabled';
        }
      };
    },
    modelEvents: {
      'change': 'render'
    },
    collectionEvents: {
      'sync': 'render'
    },
    events: {
      'click .js-parse-file': 'parseFile'
    },
    parseFile: function(evt) {
      evt.preventDefault();
      var model = this.model;
      var view = this;
      this.model.save().done(function() {
        view.collection.reset();
        view.collection.set(model.get('parseGoals'));
      })
      .fail(function (xhr) {
        App.vent.trigger('xhr:error', 'Program save was failed');
      });
    }
  });

  var ParseUserGroupForm = Marionette.CompositeView.extend({
    itemViewContainer: 'tbody',
    itemView: ParseUserGroupResult,
    emptyView: EmptyParseView,
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
        '<h3 class="panel-title"> User groups {{ _.isEmpty(getGoalName()) ? "" : "for goal " + getGoalName() }} </h3>',
      '</div>',
      '<table class="table">',
        '<thead>',
          '<tr>',
            '<th>ID</th>',
            '<th>User group</th>',
            '<th>Rounds</th>',
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
        getGoalName: function () {
          return view.model._goalName;
        }
      };
    },
    modelEvents: {
      'change': 'render'
    },
    collectionEvents: {
      'sync': 'render'
    }
  });

  var ParseRoundForm = Marionette.CompositeView.extend({
    itemViewContainer: 'tbody',
    itemView: ParseRoundResult,
    emptyView: EmptyParseView,
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
        '<h3 class="panel-title"> Rounds {{ _.isEmpty(getUserGroupName()) ? "" : "for user group " + getUserGroupName() }} </h3>',
      '</div>',
      '<table class="table">',
        '<thead>',
          '<tr>',
            '<th>ID</th>',
            '<th>Round</th>',
            '<th>Parts</th>',
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
        getUserGroupName: function () {
          return view.model._userGroupName;
        }
      };
    },
    modelEvents: {
      'change': 'render'
    },
    collectionEvents: {
      'sync': 'render'
    }
  });

  var ParsePartForm = Marionette.CompositeView.extend({
    itemViewContainer: 'tbody',
    itemView: ParsePartResult,
    emptyView: EmptyParseView,
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
        '<h3 class="panel-title"> Parts {{ _.isEmpty(getRoundName()) ? "" : "for round " + getRoundName() }} </h3>',
      '</div>',
      '<table class="table">',
        '<thead>',
          '<tr>',
            '<th>ID</th>',
            '<th>Part</th>',
            '<th>Workouts</th>',
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
        getRoundName: function () {
          return view.model._roundName;
        }
      };
    },
    modelEvents: {
      'change': 'render'
    },
    collectionEvents: {
      'sync': 'render'
    }
  });
  
  var ParseWorkoutForm = Marionette.CompositeView.extend({
    itemViewContainer: 'tbody',
    itemView: ParseWorkoutResult,
    emptyView: EmptyParseView,
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
        '<h3 class="panel-title"> Workouts {{ _.isEmpty(getPartName()) ? "" : "for part " + getPartName() }} </h3>',
      '</div>',
      '<table class="table">',
        '<thead>',
          '<tr>',
            '<th>ID</th>',
            '<th>Workout</th>',
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
        getPartName: function () {
          return view.model._partName;
        }
      };
    },
    modelEvents: {
      'change': 'render'
    },
    collectionEvents: {
      'sync': 'render'
    }
  });

  var ParseWorkoutItemForm = Marionette.CompositeView.extend({
    itemViewContainer: 'tbody',
    itemView: ParseWorkoutItemResult,
    emptyView: EmptyParseView,
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
        '<h3 class="panel-title"> Exercises {{ _.isEmpty(getWorkoutName()) ? "" : "for workout " + getWorkoutName() }} </h3>',
      '</div>',
      '<table class="table">',
        '<thead>',
          '<tr>',
            '<th>ID</th>',
            '<th>Exercise</th>',
            '<th>Inputs</th>',
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
      'change': 'render'
    },
    collectionEvents: {
      'sync': 'render'
    }
  });

  var NewProgramInputForm = Marionette.ItemView.extend({
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
        '<label class="col-sm-3 control-label">Name</label>',
        '<div class="col-sm-8">',
          '<textarea id="program-name" class="form-control" rows="3" placeholder="Please enter program name" name="programName" required="true">',
            '{{ name }}',
          '</textarea>',
        '</div>',
      '</div>',
      '<div class="form-group">',
        '<label class="col-sm-3 control-label">File name</label>',
        '<div class="col-sm-8">',
          '<p class="form-control-static">',
            ' {{ fileName }}',
          '</p>',
        '</div>',
      '</div>',
      '<div class="form-group">',
        '<label class="col-sm-3 control-label">Excel file (xlsx)</label>',
        '<div class="col-sm-8">',
           '<button class="btn btn-default js-file-upload">Upload file</button>',
           '<input type="file" id="addFile" name="files[]" multiple />',
        '</div>',
      '</div>'
    ].join('')),
    ui: {
      name: '#program-name',
      fileButton: '#addFile'
    },
    events: {
      'input #program-name': 'inputName',
      'click .js-file-upload': 'redirectFileUploading',
      'change #addFile': 'handleFileSelect'
    },
    modelEvents: {
      'sync': 'render'
    },
    inputName: function() {
      this.model.set('name', this.ui.name.val());
    },
    redirectFileUploading: function(evt) {
      evt.preventDefault();
      this.ui.fileButton.click();
    },
    _filerFiles: function(files) {
      return _.filter(files, function(file) {
        return file.type.match(/.*vnd\.openxmlformats-officedocument\.spreadsheetml\.sheet/) ||
            file.name.match(/.*\.xlsx$/);
      });
    },
    handleFileSelect: function(evt) {
      // FileList object
      var files = evt.target.files;
      var view = this;
      // Only process excel documents.
      var filteredFiles = this._filerFiles(files);
      _.each(filteredFiles, function(file) {
        view.model.set({
          fileName: file.name,
          fileSize: file.size,
          fileType: file.type || 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'
        });
        view.model.trigger('sync');
        var reader = new FileReader();
        // Closure to capture the file information.
        reader.onload = (function () {
          return function (e) {
            view.model.set('dataUrl', e.target.result);
            view.model.trigger('sync');
          };
        })();
        // Read in the image file as a data URL.
        reader.readAsDataURL(file);
      });
    }
  });

  return {
    Programs: Programs,
    NewProgramLayout: NewProgramLayout
  };

});
