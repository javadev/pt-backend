/*jshint browser: true*/
/*global define, console*/
define([
    'jquery',
    'underscore',
    'marionette',
    'app',
    'bootstrapTab',
    'bootstrapSelect'
],
function ($, _, Marionette, App) {
  'use strict';

  var EmptyView = Marionette.ItemView.extend({
        tagName: 'tr',
        template: _.template('<td colspan="7">There are no goals available.</td>')
  });

  var Goal = Marionette.ItemView.extend({
    tagName: 'tr',
    className: function() {
      return this.collection._modelId === this.model.get('id') ? 'selectedId' : '';
    },
    template: _.template([
      '<td>',
        '{{ id }}',
      '</td>',
      '<td>',
        '{{ titleEn }}',
      '</td>',
      '<td>',
        '{{ title2En }}',
      '</td>',
      '<td>',
        '{{ type == null ? "" : type.name }}',
      '</td>',
      '<td>',
        '{{ _.map(parameters, function(item) {return item.name;}).join(", ") }}',
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
      'click .js-edit-value': 'editGoal',
      'click .js-delete-value': 'deleteGoal'
    },
    editGoal: function() {
      this.collection.trigger('goal:new', this.model);
    },
    deleteGoal: function(evt) {
      evt.preventDefault();
      var model = this.model;
      var collection = this.collection;
      this.model.destroy()
        .done(function() {
        })
        .fail(function (xhr) {
          App.vent.trigger('xhr:error', 'Goal ' + model.get('id') + ' delete was failed');
        })
        .always(function() {
          collection.fetch();
        });
    }
  });

  var Goals = Marionette.CompositeView.extend({
    itemViewContainer: 'tbody',
    itemView: Goal,
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
        '<h3 class="panel-title"> Goals </h3>',
      '</div>',
      '<button class="btn btn-primary js-new-goal" style="margin: 10px;">',
        'New goal',
      '</button>',
      '<table class="table">',
        '<thead>',
          '<tr>',
            '<th>ID</th>',
            '<th>Title</th>',
            '<th>Title2</th>',
            '<th>Type</th>',
            '<th>Parameters</th>',
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
      'click .js-new-goal': 'newGoal'
    },
    newGoal: function() {
      this.collection.trigger('goal:new');
    }
  });

  var NewGoalLayout = Marionette.Layout.extend({
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
          return model.isNew() ? 'New goal' : 'Edit goal';
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
      this.buttons.show(new NewGoalButtons({model: this.model}));
      this.inputForm.show(new NewGoalInputForm({model: this.model}));
    }
  });

  var NewGoalButtons = Marionette.ItemView.extend({
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
      this.model.trigger('goal:back', this.model.get('id'));
    },
    save: function(evt) {
      evt.preventDefault();
      var model = this.model;
      this.model.save().done(function(data) {
        model.trigger('goal:back', model.get('id'), data);
      })
      .fail(function (xhr) {
        App.vent.trigger('xhr:error', 'Goal save was failed');
      });
    },
    discard: function(evt) {
      evt.preventDefault();
      this.model.set(this._model.toJSON());
      this.model.trigger('sync');
    }
  });

  var NewGoalInputForm = Marionette.ItemView.extend({
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
          '<select id="goal-type" class="selectpicker show-tick">',
            '{{ getTypes() }}',
          '</select>',
        '</div>',
      '</div>',
      '<div class="form-group">',
        '<label class="col-sm-3 control-label">Parameters</label>',
        '<div class="col-sm-8">',
          '<select id="goal-parameters" class="selectpicker show-tick" multiple>',
            '{{ getParameters() }}',
          '</select>',
        '</div>',
      '</div>',
      '<div class="form-group">',
        '<label class="col-sm-3 control-label">Title in English</label>',
        '<div class="col-sm-8">',
          '<textarea id="goal-titleEn" class="form-control" rows="3" placeholder="Please enter title in English" name="titleEn" required="true">',
            '{{ titleEn }}',
          '</textarea>',
        '</div>',
      '</div>',
      '<div class="form-group">',
        '<label class="col-sm-3 control-label">Title in Norwegian</label>',
        '<div class="col-sm-8">',
          '<textarea id="goal-titleNo" class="form-control" rows="3" placeholder="Please enter title in Norwegian" name="titleNo" required="true">',
            '{{ titleNo }}',
          '</textarea>',
        '</div>',
      '</div>',
      '<div class="form-group">',
        '<label class="col-sm-3 control-label">Title2 in English</label>',
        '<div class="col-sm-8">',
          '<textarea id="goal-title2En" class="form-control" rows="3" placeholder="Please enter title2 in English" name="title2En" required="true">',
            '{{ title2En }}',
          '</textarea>',
        '</div>',
      '</div>',
      '<div class="form-group">',
        '<label class="col-sm-3 control-label">Title2 in Norwegian</label>',
        '<div class="col-sm-8">',
          '<textarea id="goal-title2No" class="form-control" rows="3" placeholder="Please enter title2 in Norwegian" name="title2No" required="true">',
            '{{ title2No }}',
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
              return '<option></option>';
            }
            return '<option value="' + item.id + '"' +
              (!!model.get('type') && model.get('type').id === item.id ? ' selected' : '') +
              '>' + item.name + '</option>';
          });
          return result;
        },
        getParameters: function() {
          var parameters = model._parameters || [];
          var modelParameters = _.map(model.get('parameters'), function(item) {
             return item.id;
          });
          var result = _.map(parameters, function(item) {
            if (_.isNull(item.id)) {
              return '<option data-hidden="true"></option>';
            }
            return '<option value="' + item.id + '"' +
              (_.contains(modelParameters, item.id) ? ' selected' : '') +
              '>' + item.name + '</option>';
          });
          return result;
        }
      };
    },
    modelEvents: {
      'sync': 'render'
    },
    events: {
      'input #goal-titleEn': 'inputTitleEn',
      'input #goal-titleNo': 'inputTitleNo',
      'input #goal-title2En': 'inputTitle2En',
      'input #goal-title2No': 'inputTitle2No'
    },
    ui: {
      goalType: '#goal-type',
      goalParameters: '#goal-parameters',
      titleEn: '#goal-titleEn',
      titleNo: '#goal-titleNo',
      title2En: '#goal-title2En',
      title2No: '#goal-title2No'
    },
    inputTitleEn: function() {
      this.model.set('titleEn', this.ui.titleEn.val());
    },
    inputTitleNo: function() {
      this.model.set('titleNo', this.ui.titleNo.val());
    },
    inputTitle2En: function() {
      this.model.set('title2En', this.ui.title2En.val());
    },
    inputTitle2No: function() {
      this.model.set('title2No', this.ui.title2No.val());
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
      this.ui.goalType.on('changed.bs.select', function (e) {
        view.model.set('type', {id: parseInt(e.target.value, 10) });
      });
      this.ui.goalParameters.on('changed.bs.select', function (e) {
        var selectedParameters = _.map(e.target.selectedOptions, function(item) {
            return {id: parseInt(item.value, 10) };
        });
        view.model.set('parameters', selectedParameters);
      });
    }
  });

  return {
    Goals: Goals,
    NewGoalLayout: NewGoalLayout
  };

});
