/*jshint browser: true*/
/*global require,define*/
require.config({
  paths: {
    jquery: 'external/jquery',
    bootstrapTab: 'external/bootstrp.tab',

    underscore: 'external/underscore',
    baseBackbone: 'external/backbone',
    backbone: 'backbone-extend',

    marionette: 'external/backbone.marionette',
    'backbone.wreqr': 'external/backbone.wreqr',
    'backbone.babysitter': 'external/backbone.babysitter'

  },
  shims: {
    bootstrapTab: {
      deps: ['jquery']
    }
  }
});

define([
  'appsetup'
],
function (Appsetup) {
  'use strict';

});

