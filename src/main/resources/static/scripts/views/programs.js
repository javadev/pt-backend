/*jshint browser: true*/
/*global define, console*/
define([
    'jquery',
    'underscore',
    'marionette',
    'app',
    'bootstrapTab'
],
function ($, _, Marionette, App) {
  'use strict';

  var EmptyView = Marionette.ItemView.extend({
        tagName: 'tr',
        template: _.template('<td colspan="4">There are no programs available.</td>')
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
      'click .js-edit-value': 'editProgram',
      'click .js-delete-value': 'deleteProgram'
    },
    editProgram: function() {
      this.collection.trigger('program:new', this.model);
    },
    deleteProgram: function() {
      event.preventDefault();
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
      inputForm: '#inputForm'
    },
    onShow: function() {
      this.buttons.show(new NewProgramButtons({model: this.model}));
      this.inputForm.show(new NewProgramInputForm({model: this.model}));
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
    back: function() {
      event.preventDefault();
      this.model.trigger('program:back');
    },
    save: function() {
      event.preventDefault();
      var model = this.model;
      this.model.save().done(function() {
        model.trigger('program:back');
      })
      .fail(function (xhr) {
        App.vent.trigger('xhr:error', 'Program save was failed');
      });
    },
    discard: function(event) {
      event.preventDefault();
      this.model.set(this._model.toJSON());
      this.model.trigger('sync');
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
      '</div>'
    ].join('')),
    modelEvents: {
      'sync': 'render'
    },
    events: {
      'input #program-name': 'inputName'
    },
    ui: {
      name: '#program-name'
    },
    inputName: function() {
      this.model.set('name', this.ui.name.val());
    }
  });

  return {
    Programs: Programs,
    NewProgramLayout: NewProgramLayout
  };

});
