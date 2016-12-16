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
        template: _.template('<td colspan="5">There are no email templates available.</td>')
  });

  var Email = Marionette.ItemView.extend({
    tagName: 'tr',
    template: _.template([
      '<td>',
        '{{ id }}',
      '</td>',
      '<td>',
       '{{ type == null ? "" : type.name }}',
      '</td>',
      '<td>',
        '{{ emailSubjectEn }}',
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
      'click .js-edit-value': 'editEmail',
      'click .js-delete-value': 'deleteEmail'
    },
    editEmail: function() {
      this.collection.trigger('email:new', this.model);
    },
    deleteEmail: function(evt) {
      evt.preventDefault();
      var model = this.model;
      var collection = this.collection;
      this.model.destroy()
        .done(function() {
        })
        .fail(function (xhr) {
          App.vent.trigger('xhr:error', 'Email template ' + model.get('id') + ' delete was failed');
        })
        .always(function() {
          collection.fetch();
        });
    }
  });

  var Emails = Marionette.CompositeView.extend({
    itemViewContainer: 'tbody',
    itemView: Email,
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
        '<h3 class="panel-title"> Email templates </h3>',
      '</div>',
      '<button class="btn btn-primary js-new-emailTemplate" style="margin: 10px;">',
        'New email template',
      '</button>',
      '<table class="table">',
        '<thead>',
          '<tr>',
            '<th>ID</th>',
            '<th>Type</th>',
            '<th>Email subject</th>',
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
      'click .js-new-emailTemplate': 'newEmailTemplate'
    },
    newEmailTemplate: function() {
      this.collection.trigger('email:new');
    }
  });

  var NewEmailLayout = Marionette.Layout.extend({
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
          return model.isNew() ? 'New email template' : 'Edit email template';
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
      this.buttons.show(new NewEmailButtons({model: this.model}));
      this.inputForm.show(new NewEmailInputForm({model: this.model}));
    }
  });

  var NewEmailButtons = Marionette.ItemView.extend({
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
      this.model.trigger('email:back');
    },
    save: function(evt) {
      evt.preventDefault();
      var model = this.model;
      this.model.save().done(function() {
        model.trigger('email:back');
      })
      .fail(function (xhr) {
        App.vent.trigger('xhr:error', 'Email template save was failed');
      });
    },
    discard: function(evt) {
      evt.preventDefault();
      this.model.set(this._model.toJSON());
      this.model.trigger('sync');
    }
  });

  var NewEmailInputForm = Marionette.ItemView.extend({
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
          '<select id="email-message-type" class="selectpicker show-tick">',
            '{{ getTypes() }}',
          '</select>',
        '</div>',
      '</div>',
      '<div class="form-group">',
        '<label class="col-sm-3 control-label">Email subject in English</label>',
        '<div class="col-sm-8">',
          '<textarea id="emailSubjectEn" class="form-control" rows="3" placeholder="Please enter email subject in English" name="emailSubjectEn" required="true">',
            '{{ emailSubjectEn }}',
          '</textarea>',
        '</div>',
      '</div>',
      '<div class="form-group">',
        '<label class="col-sm-3 control-label">Email subject in Norwegian</label>',
        '<div class="col-sm-8">',
          '<textarea id="emailSubjectNo" class="form-control" rows="3" placeholder="Please enter email subject in Norwegian" name="emailSubjectNo" required="true">',
            '{{ emailSubjectNo }}',
          '</textarea>',
        '</div>',
      '</div>',
      '<div class="form-group">',
        '<label class="col-sm-3 control-label">Email text in English</label>',
        '<div class="col-sm-8">',
          '<textarea id="emailTextEn" class="form-control" rows="3" placeholder="Please enter email text in English" name="emailTextEn" required="true">',
            '{{ emailTextEn }}',
          '</textarea>',
        '</div>',
      '</div>',
      '<div class="form-group">',
        '<label class="col-sm-3 control-label">Email text in Norwegian</label>',
        '<div class="col-sm-8">',
          '<textarea id="emailTextNo" class="form-control" rows="3" placeholder="Please enter email text in Norwegian" name="emailTextNo" required="true">',
            '{{ emailTextNo }}',
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
      'input #emailSubjectEn': 'inputEmailSubjectEn',
      'input #emailSubjectNo': 'inputEmailSubjectNo',
      'input #emailTextEn': 'inputEmailTextEn',
      'input #emailTextNo': 'inputEmailTextNo'
    },
    ui: {
      emailMessageType: '#email-message-type',
      emailSubjectEn: '#emailSubjectEn',
      emailSubjectNo: '#emailSubjectNo',
      emailTextEn: '#emailTextEn',
      emailTextNo: '#emailTextNo'
    },
    inputEmailSubjectEn: function() {
      this.model.set('emailSubjectEn', this.ui.emailSubjectEn.val());
    },
    inputEmailSubjectNo: function() {
      this.model.set('emailSubjectNo', this.ui.emailSubjectNo.val());
    },
    inputEmailTextEn: function() {
      this.model.set('emailTextEn', this.ui.emailTextEn.val());
    },
    inputEmailTextNo: function() {
      this.model.set('emailTextNo', this.ui.emailTextNo.val());
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
      this.ui.emailMessageType.on('changed.bs.select', function (e) {
        view.model.set('type', _.isEmpty(e.target.value) ? null : {id: parseInt(e.target.value, 10) });
      });
    }
  });

  return {
    Emails: Emails,
    NewEmailLayout: NewEmailLayout
  };

});
