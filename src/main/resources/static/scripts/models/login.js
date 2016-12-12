/*jshint browser: true*/
/*global define*/
define([
  'jquery',
  'backbone'
],
function($, Backbone) {
  'use strict';

  var userInfoUrl = '/api/v1/auth/user',
    sessionLoginUrl = '/login',
    sessionLogoutUrl = '/logout';

  var User = Backbone.Model.extend({
    defaults: {
      displayName: 'unknown',
      username: 'Not available',
      permissions: [],
      authenticated: false,
      authorized: false
    },

    initialize: function (options) {
      this.on('sync', this.handleLoginSuccess, this);
      this.on('error', this.handleLoginCheckError, this);
    },

    url: function () {
      return userInfoUrl;
    },

    checkLoginStatus: function () {
      return this.fetch();
    },

    authorized: function () {
      return this.get('authenticated') && this.get('authorized');
    },

    /***
     * Make the server verify the provided username and password.
     * If successful, fetch user information.
     */
    login: function (username, password) {
      return $.ajax({
        url: sessionLoginUrl,
        type: 'POST',
        data: { username: username, password: password },
        context: this,
        success: function () {
          this.fetch();
        },
        error: function () {
          this.trigger('credentials:invalid');
        }
      });
    },

    /***
     * Request the invalidation of the session in the backend API.
     * @return jQuery.Deferred instance for the request
     */
    logout: function () {
      return $.ajax({
        url: sessionLogoutUrl,
        type: 'GET',
        dataType: 'json',
        context: this,
        complete: function () {
          this.clear();
        }
      });
    },

    /**
     * Called upon successful retrieval of user information. This indicates
     * that the server has confirmed the identity of the user.
     * If the proper permissions are set, the user is also authorized.
     */
    handleLoginSuccess: function () {
      this.set('authenticated', true);
      this.set('authorized', true);
    },
    handleLoginCheckError: function () {
      this.unset('authenticated');
      this.unset('authorized');
    }
  });

  return User;

});
