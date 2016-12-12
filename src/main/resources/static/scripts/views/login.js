/*jshint browser: true*/
/*global define*/
define([
  'underscore',
  'marionette'
],
function(_, Marionette) {
  'use strict';

  var template = _.template([
    '<div class="page-header app-header">',
      '<h1><span>pt admin</span></h1>',
    '</div>',
    '<form method="post" class="form-signin">',
    '<div class="alert alert-danger js-wrongpass" style="display:none">',
    ' <span class="glyphicon glyphicon-exclamation-sign"></span>',
    '  Feil brukernavn eller passord.',
    '</div>',
    '<div class="form-group">',
    '  <input type="text" class="form-control" name="username" placeholder="username" autofocus="">',
    '</div>',
    '<div class="form-group">',
    '  <input type="password" class="form-control" name="password" placeholder="password">',
    '</div>',
    '<button class="btn btn-lg btn-primary btn-block ladda-button js-login" data-style="zoom-out">',
    '  <span class="ladda-label">sign in</span>',
    '  <span class="ladda-spinner"></span>',
    '</button>',
    '</form>'
  ].join(''));

  return Marionette.ItemView.extend({
    
    template: template,

    events: { 'click .js-login': 'login' },
    modelEvents: {
      'credentials:invalid': 'unauthenticated',
      'sync': 'render'
    },

    login: function (event) {
      event.preventDefault();
      this.$('.js-wrongpass').slideUp(100);

      var username = _.escape(this.$('input[name=username]').val());
      var password = _.escape(this.$('input[name=password]').val());

      this.model.login(username, password);
    },
    unauthenticated: function () {
      this.$('.js-wrongpass').slideDown(100);
      this.$('input[name=username]').focus();
    }
  });

});
