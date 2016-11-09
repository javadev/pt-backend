/*jshint browser: true*/
/*global define, console*/
define([
  'jquery',
  'underscore',
  'backbone',
  'marionette'
],
  function($, _, Backbone, Marionette) {
  'use strict';

    return Marionette.Controller.extend({
      login: function() {
        // If the user is already logged in,
        // show the default route.
        if (App.globals.user.authorized()) {
          App.vent.trigger('redirect:default');
          return;
        }
      },
    });
  });
