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
        template: _.template('<td colspan="8">There are no exercises available.</td>')
  });

  var Exercise = Marionette.ItemView.extend({
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
        '{{ bodypart == null ? "" : bodypart.nameEn }}',
      '</td>',
      '<td>',
        '{{ equipmentType == null ? "" : equipmentType.nameEn }}',
      '</td>',
      '<td>',
        '{{ type == null ? "" : type.name }}',
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
    deleteExercise: function(evt) {
      evt.preventDefault();
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
    itemView: Exercise,
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
            '<th>Bodypart</th>',
            '<th>Equipment type</th>',
            '<th>Type</th>',
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
    back: function(evt) {
      evt.preventDefault();
      this.model.trigger('exercise:back');
    },
    save: function(evt) {
      evt.preventDefault();
      var model = this.model;
      this.model.save().done(function() {
        model.trigger('exercise:back');
      })
      .fail(function (xhr) {
        App.vent.trigger('xhr:error', 'Exercise save was failed');
      });
    },
    discard: function(evt) {
      evt.preventDefault();
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
        '<label class="col-sm-3 control-label">Bodypart</label>',
        '<div class="col-sm-8">',
          '<select id="exercise-bodypart" class="selectpicker show-tick">',
            '{{ getBodyparts() }}',
          '</select>',
        '</div>',
      '</div>',
      '<div class="form-group">',
        '<label class="col-sm-3 control-label">Equipment type</label>',
        '<div class="col-sm-8">',
          '<select id="exercise-equipment-type" class="selectpicker show-tick">',
            '{{ getEquipmentTypes() }}',
          '</select>',
        '</div>',
      '</div>',
      '<div class="form-group">',
        '<label class="col-sm-3 control-label">Type</label>',
        '<div class="col-sm-8">',
          '<select id="exercise-type" class="selectpicker show-tick">',
            '{{ getTypes() }}',
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
      '</div>',
      '<div class="form-group">',
        '<label class="col-sm-3 control-label">Description in English</label>',
        '<div class="col-sm-8">',
          '<textarea id="exercise-descriptionEn" class="form-control" rows="3" placeholder="Please enter description in English" name="descriptionEn" required="true">',
            '{{ descriptionEn }}',
          '</textarea>',
        '</div>',
      '</div>',
      '<div class="form-group">',
        '<label class="col-sm-3 control-label">Description in Norwegian</label>',
        '<div class="col-sm-8">',
          '<textarea id="exercise-descriptionNo" class="form-control" rows="3" placeholder="Please enter description in Norwegian" name="descriptionNo" required="true">',
            '{{ descriptionNo }}',
          '</textarea>',
        '</div>',
      '</div>'
    ].join('')),
    templateHelpers: function() {
      var model = this.model;
      return {
        getBodyparts: function() {
          var bodyparts = model._bodyparts || [];
          var result = _.map(bodyparts, function(item) {
            if (_.isNull(item.id)) {
              return '<option></option>';
            }
            return '<option value="' + item.id + '"' +
              (!!model.get('bodypart') && model.get('bodypart').id === item.id ? ' selected' : '') +
              '>' + item.nameEn + '</option>';
          });
          return result;
        },
        getEquipmentTypes: function() {
          var equipmentTypes = model._equipmentTypes || [];
          var result = _.map(equipmentTypes, function(item) {
            if (_.isNull(item.id)) {
              return '<option></option>';
            }
            return '<option value="' + item.id + '"' +
              (!!model.get('equipmentType') && model.get('equipmentType').id === item.id ? ' selected' : '') +
              '>' + item.nameEn + '</option>';
          });
          return result;
        },
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
        }
      };
    },
    modelEvents: {
      'sync': 'render'
    },
    events: {
      'input #exercise-id': 'inputId',
      'input #exercise-nameEn': 'inputNameEn',
      'input #exercise-nameNo': 'inputNameNo',
      'input #exercise-descriptionEn': 'inputDescriptionEn',
      'input #exercise-descriptionNo': 'inputDescriptionNo'
    },
    ui: {
      exerciseBodypart: '#exercise-bodypart',
      exerciseEquipmentType: '#exercise-equipment-type',
      exerciseType: '#exercise-type',
      exerciseId: '#exercise-id',
      nameEn: '#exercise-nameEn',
      nameNo: '#exercise-nameNo',
      descriptionEn: '#exercise-descriptionEn',
      descriptionNo: '#exercise-descriptionNo'
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
    inputDescriptionEn: function() {
      this.model.set('descriptionEn', this.ui.descriptionEn.val());
    },
    inputDescriptionNo: function() {
      this.model.set('descriptionNo', this.ui.descriptionNo.val());
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
      this.ui.exerciseBodypart.on('changed.bs.select', function (e) {
        view.model.set('bodypart', _.isEmpty(e.target.value) ? null : {id: parseInt(e.target.value, 10) });
      });
      this.ui.exerciseEquipmentType.on('changed.bs.select', function (e) {
        view.model.set('equipmentType', _.isEmpty(e.target.value) ? null : {id: parseInt(e.target.value, 10) });
      });
      this.ui.exerciseType.on('changed.bs.select', function (e) {
        view.model.set('type', _.isEmpty(e.target.value) ? null : {id: parseInt(e.target.value, 10) });
      });
    }
  });

  return {
    Exercises: Exercises,
    NewExerciseLayout: NewExerciseLayout
  };

});
