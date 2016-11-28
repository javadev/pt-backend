/*jshint browser: true*/
/*global require,define*/
require.config({
  paths: {
    jquery: 'external/jquery',
    bootstrapTab: 'external/bootstrap-tab',
    bootstrapDropdown: 'external/bootstrap-dropdown',
    bootstrapSelect: 'external/bootstrap-select',

    underscore: 'external/underscore',
    baseBackbone: 'external/backbone',
    backbone: 'backbone-extend',

    marionette: 'external/backbone.marionette',
    'backbone.wreqr': 'external/backbone.wreqr',
    'backbone.babysitter': 'external/backbone.babysitter',
    moment: 'external/moment'

  },
  shim: {
    bootstrapTab: {
      deps: ['jquery']
    },
    bootstrapDropdown: {
      deps: ['jquery']
    },
    bootstrapSelect: {
      deps: ['jquery', 'bootstrapDropdown']
    }
  }
});

define([
  'appsetup'
],
function (Appsetup) {
  'use strict';

});

