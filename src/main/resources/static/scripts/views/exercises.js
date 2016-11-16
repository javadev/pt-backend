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
        template: _.template('<td colspan="6">There are no exercises available.</td>')
  });

  var User = Marionette.ItemView.extend({
    tagName: 'tr',
    template: _.template([
      '<td>',
        '{{ exerciseId }}',
      '</td>',
      '<td>',
        '{{ nameEn }}',
      '</td>',
      '<td>',
        '{{ nameNo }}',
      '</td>',
      '<td>',
        '{{ category.nameEn }}',
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
      'click .js-edit-value': 'editExercise',
      'click .js-delete-value': 'deleteExercise'
    },
    editExercise: function() {
      this.collection.trigger('exercise:new', this.model);
    },
    deleteExercise: function() {
      event.preventDefault();
      var model = this.model;
      var collection = this.collection;
      this.model.destroy()
        .done(function() {
        })
        .fail(function (xhr) {
          App.vent.trigger('xhr:error', 'Exercise ' + model.get('id') + ' delete was failed');
        })
        .always(function() {
          collection.fetch();
        });
    }
  });

  var Exercises = Marionette.CompositeView.extend({
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
        '<h3 class="panel-title"> Exercises </h3>',
      '</div>',
      '<button class="btn btn-primary js-new-exercise" style="margin: 10px;">',
        'New exercise',
      '</button>',
      '<table class="table">',
        '<thead>',
          '<tr>',
            '<th>Exercise ID</th>',
            '<th>Name in English</th>',
            '<th>Name in Norwegian</th>',
            '<th>Category</th>',
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
      'click .js-new-exercise': 'newExercise'
    },
    newExercise: function() {
      this.collection.trigger('exercise:new');
    }
  });

  var NewExerciseLayout = Marionette.Layout.extend({
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
          return model.isNew() ? 'New exercise' : 'Edit exercise';
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
      this.buttons.show(new NewExerciseButtons({model: this.model}));
      this.inputForm.show(new NewExerciseInputForm({model: this.model}));
    }
  });

  var NewExerciseButtons = Marionette.ItemView.extend({
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
    back: function() {
      event.preventDefault();
      this.model.trigger('exercise:back');
    },
    save: function() {
      event.preventDefault();
      var model = this.model;
      this.model.save().done(function() {
        model.trigger('exercise:back');
      })
      .fail(function (xhr) {
        App.vent.trigger('xhr:error', 'Exercise save was failed');
      });
    },
    discard: function(event) {
      event.preventDefault();
      this.model.set(this._model.toJSON());
      this.model.trigger('sync');
    }
  });

  var NewExerciseInputForm = Marionette.ItemView.extend({
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
        '<label class="col-sm-3 control-label">Exercise category</label>',
        '<div class="col-sm-8">',
          '<select id="exercise-category" class="selectpicker">',
            '{{ getCategories() }}',
          '</select>',
        '</div>',
      '</div>',
      '<div class="form-group">',
        '<label class="col-sm-3 control-label">Exercise ID</label>',
        '<div class="col-sm-8">',
          '<textarea id="exercise-id" class="form-control" rows="3" placeholder="Please enter exercise id" name="exerciseId" required="true">',
            '{{ exerciseId }}',
          '</textarea>',
        '</div>',
      '</div>',
      '<div class="form-group">',
        '<label class="col-sm-3 control-label">Name in English</label>',
        '<div class="col-sm-8">',
          '<textarea id="exercise-nameEn" class="form-control" rows="3" placeholder="Please enter name in English" name="nameEn" required="true">',
            '{{ nameEn }}',
          '</textarea>',
        '</div>',
      '</div>',
      '<div class="form-group">',
        '<label class="col-sm-3 control-label">Name in Norwegian</label>',
        '<div class="col-sm-8">',
          '<textarea id="exercise-nameNo" class="form-control" rows="3" placeholder="Please enter name in Norwegian" name="nameNo" required="true">',
            '{{ nameNo }}',
          '</textarea>',
        '</div>',
      '</div>'
    ].join('')),
    templateHelpers: function() {
      var model = this.model;
      return {
        getCategories: function () {
          var categories = model._categories || [];
          var result = _.map(categories, function(item) {
            if (_.isNull(item.id)) {
              return '<option data-hidden="true"></option>';
            }
            return '<option value="' + item.id + '"' +
              (model.get('category').id === item.id ? ' selected' : '') +
              '>' + item.nameEn + '</option>';
          });
          return result;
        }
      };
    },
    modelEvents: {
      'sync': 'render'
    },
    events: {
      'input #exercise-id': 'inputId',
      'input #exercise-nameEn': 'inputNameEn',
      'input #exercise-nameNo': 'inputNameNo'
    },
    ui: {
      exerciseCategory: '#exercise-category',
      exerciseId: '#exercise-id',
      nameEn: '#exercise-nameEn',
      nameNo: '#exercise-nameNo'
    },
    inputId: function() {
      this.model.set('exerciseId', this.ui.exerciseId.val());
    },
    inputNameEn: function() {
      this.model.set('nameEn', this.ui.nameEn.val());
    },
    inputNameNo: function() {
      this.model.set('nameNo', this.ui.nameNo.val());
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
      this.ui.exerciseCategory.on('changed.bs.select', function (e) {
        view.model.set('category', {id: parseInt(e.target.value, 10) });
      });
    }
  });

  return {
    Exercises: Exercises,
    NewExerciseLayout: NewExerciseLayout
  };

});
