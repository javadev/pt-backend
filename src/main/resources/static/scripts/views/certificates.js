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
        template: _.template('<td colspan="4">There are no certificates available.</td>')
  });

  var Certificate = Marionette.ItemView.extend({
    tagName: 'tr',
    template: _.template([
      '<td>',
        '{{ id }}',
      '</td>',
      '<td>',
        '{{ code }}',
      '</td>',
      '<td>',
        '{{ amountOfDays }}',
      '</td>',
      '<td>',
        '{{ activated }}',
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
      'click .js-edit-value': 'editCertificate',
      'click .js-delete-value': 'deleteCertificate'
    },
    editCertificate: function() {
      this.collection.trigger('certificate:new', this.model);
    },
    deleteCertificate: function(evt) {
      evt.preventDefault();
      var model = this.model;
      var collection = this.collection;
      this.model.destroy()
        .done(function() {
        })
        .fail(function (xhr) {
          App.vent.trigger('xhr:error', 'Certificate ' + model.get('id') + ' delete was failed');
        })
        .always(function() {
          collection.fetch();
        });
    }
  });

  var Certificates = Marionette.CompositeView.extend({
    itemViewContainer: 'tbody',
    itemView: Certificate,
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
        '<h3 class="panel-title"> Certificates </h3>',
      '</div>',
      '<button class="btn btn-primary js-new-certificate" style="margin: 10px;">',
        'New certificate',
      '</button>',
      '<table class="table">',
        '<thead>',
          '<tr>',
            '<th>ID</th>',
            '<th>Code</th>',
            '<th>Amount of days</th>',
            '<th>Activated</th>',
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
      'click .js-new-certificate': 'newCertificate'
    },
    newCertificate: function() {
      this.collection.trigger('certificate:new');
    }
  });

  var NewCertificateLayout = Marionette.Layout.extend({
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
          return model.isNew() ? 'New certificate' : 'Edit certificate';
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
      this.buttons.show(new NewCertificateButtons({model: this.model}));
      this.inputForm.show(new NewCertificateInputForm({model: this.model}));
    }
  });

  var NewCertificateButtons = Marionette.ItemView.extend({
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
      this.model.trigger('certificate:back');
    },
    save: function() {
      event.preventDefault();
      var model = this.model;
      this.model.save().done(function() {
        model.trigger('certificate:back');
      })
      .fail(function (xhr) {
        App.vent.trigger('xhr:error', 'Certificate save was failed');
      });
    },
    discard: function(event) {
      event.preventDefault();
      this.model.set(this._model.toJSON());
      this.model.trigger('sync');
    }
  });

  var NewCertificateInputForm = Marionette.ItemView.extend({
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
        '<label class="col-sm-3 control-label">Code</label>',
        '<div class="col-sm-8">',
          '<textarea id="certificate-code" class="form-control" rows="3" placeholder="Please enter code" name="address" required="true">',
            '{{ code }}',
          '</textarea>',
        '</div>',
      '</div>',
      '<div class="form-group">',
        '<label class="col-sm-3 control-label">Amount of days</label>',
        '<div class="col-sm-8">',
          '<textarea id="certificate-amountOfDays" class="form-control" rows="3" placeholder="Please enter amount of days" name="address" required="true">',
            '{{ amountOfDays }}',
          '</textarea>',
        '</div>',
      '</div>'
    ].join('')),
    modelEvents: {
      'sync': 'render'
    },
    events: {
      'input #certificate-code': 'inputCode',
      'input #certificate-amountOfDays': 'inputAmountOfDays'
    },
    ui: {
      code: '#certificate-code',
      amountOfDays: '#certificate-amountOfDays'
    },
    inputCode: function() {
      this.model.set('code', this.ui.code.val());
    },
    inputAmountOfDays: function() {
      this.model.set('amountOfDays', this.ui.amountOfDays.val());
    }
  });

  return {
    Certificates: Certificates,
    NewCertificateLayout: NewCertificateLayout
  };

});
