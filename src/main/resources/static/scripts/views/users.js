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
      mainPrograms: '#programsMappingConfig',
      mainCertificates: '#certificatesMappingConfig'
    },
    template: _.template([
      '<!-- Nav tabs -->',
      '<ul class="nav nav-tabs">',
      '  <li class="active"><a href="#user" data-toggle="tab">Users</a></li>',
      '  <li><a href="#exercise" data-toggle="tab">Exercises</a></li>',
      '  <li><a href="#program" data-toggle="tab" class="js-admin-config">Programs</a></li>',
      '  <li><a href="#certificate" data-toggle="tab" class="js-admin-config">Certificates</a></li>',
      '</ul>',
      '<!-- Tab panes -->',
      '<div class="tab-content">',
      '  <div class="tab-pane active" id="user">',
      '    <div id="usersMappingConfig"></div>',
      '  </div>',
      '  <div class="tab-pane" id="exercise">',
      '    <div id="exercisesMappingConfig"></div>',
      '  </div>',
      '  <div class="tab-pane" id="program">',
      '    <div id="programsMappingConfig"></div>',
      '  </div>',
      '  <div class="tab-pane" id="certificate">',
      '    <div id="certificatesMappingConfig"></div>',
      '  </div>',
      '</div>'
    ].join(''))
  });
  
  var EmptyView = Marionette.ItemView.extend({
        tagName: 'tr',
        template: _.template('<td colspan="4">There are no users available.</td>')
  });
  
  var EmptyTableItemView = Marionette.ItemView.extend({
        tagName: 'tr',
        template: _.template('<td colspan="4">There are no programs available.</td>')
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
      'click .js-edit-value': 'editUser',
      'click .js-delete-value': 'deleteUser'
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

  var NewUserLayout = Marionette.Layout.extend({
    template: _.template([
      '<div class="panel panel-primary">',
        '<div class="panel-heading">',
          '<h3 class="panel-title"> {{ getHeader() }} </h3>',
        '</div>',
        '<div id="buttons"/>',
        '<div id="inputForm"/>',
        '<div id="programTable"/>',
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
      inputForm: '#inputForm',
      programTable: '#programTable'
    },
    onShow: function() {
      this.buttons.show(new NewUserButtons({model: this.model}));
      this.inputForm.show(new NewUserInputForm({model: this.model}));
      this.programTable.show(new ProgramTableForm({model: this.model,
          collection: new Backbone.Collection(this.model.get('programs'))}));
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
  
  var TableItem = Marionette.ItemView.extend({
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
      '</td>'
    ].join('')),
    templateHelpers: function() {
      return {
        formatDate: function(dateTime) {
          return moment(dateTime).format('DD.MM.YYYY HH:mm');
        }
      };
    }
  });

  var ProgramTableForm = Marionette.CompositeView.extend({
    itemViewContainer: 'tbody',
    itemView: TableItem,
    emptyView: EmptyTableItemView,
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
            '<th>Created</th>',
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
      this.model.fetch().done(function() {
        view.collection.set(view.model.get('programs'));
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

  return {
    Users: Users,
    NewUserLayout: NewUserLayout,
    Layout: Layout
  };

});
