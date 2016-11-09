/*jshint browser: true*/
/*global define*/
define([
  'underscore',
  'baseBackbone'
],
function (_, Backbone) {
  'use strict';

  // Override Backbone.sync to add some defaults
  var originalSync = Backbone.sync;
  Backbone.sync = function (method, model, options, error) {

    _.extend(options, { });

    return originalSync(method, model, options);
  };

  return Backbone;
});
