/*jshint browser: true*/
/*global define*/
define([
  'jquery',
  'backbone',
  'app'
],
function($, Backbone, App) {
  'use strict';
  
  // Create a place to put objects that should live across page transitions.
  App.addInitializer(function() {
    App.globals = App.globals || {};
  });
  
  // Read incoming URL, and start routing based on #
  App.addInitializer(function() {
    App.globals.incomingUrl = Backbone.history.getHash();
    Backbone.history.start();
  });
});
