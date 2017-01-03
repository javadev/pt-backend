/*jshint browser: true*/
/*global require,define*/
require.config({
  paths: {
    jquery: 'external/jquery',
    bootstrapTab: 'external/bootstrap-tab',
    bootstrapDropdown: 'external/bootstrap-dropdown',
    bootstrapSelect: 'external/bootstrap-select',
    bootstrapModal: 'external/bootstrap-modal',
    bootstrapModals: 'external/backbone-bootstrap-modals',

    underscore: 'external/underscore',
    baseBackbone: 'external/backbone',
    backbone: 'backbone-extend',

    marionette: 'external/backbone.marionette',
    'backbone.wreqr': 'external/backbone.wreqr',
    'backbone.babysitter': 'external/backbone.babysitter',
    moment: 'external/moment'

  },
  shim: {
    jquery: {
      exports: 'jQuery'
    },
    bootstrapTab: {
      deps: ['jquery']
    },
    bootstrapDropdown: {
      deps: ['jquery']
    },
    bootstrapSelect: {
      deps: ['jquery', 'bootstrapDropdown']
    },
    bootstrapModal: {
      deps: ['jquery']
    },
    bootstrapModals: {
      deps: ['jquery', 'bootstrapModal']
    }
  }
});

define([
  'appsetup'
],
function (Appsetup) {
  'use strict';

});

