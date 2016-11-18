/*jshint browser: true*/
/*global define*/
define([
    'jquery',
    'underscore',
    'backbone'
  ],
  function ($, _, Backbone) {
    'use strict';

    var Program = Backbone.Model.extend({
      defaults: {
        'id': null,
        'name': null,
        'fileName': '',
        'fileSize': 0,
        'fileType': '',
        'dataUrl': null
      },
      url: function() {
        return '/api/v1/admin/program' + (this.isNew() ? '' : '/' + this.get('id'));
      },
      validate: function (attrs) {
        var errors = [];
        if (_.isNull(attrs.name) || _.isEmpty($.trim(attrs.name))) {
          errors.push({name: 'name', message: 'Please fill name field.'});
        } else if (_.isNull(attrs.dataUrl)) {
          errors.push({name: 'dataUrl', message: 'Please upload excel file.'});
        }
        return errors.length > 0 ? errors : false;
      }
    });

    var Programs = Backbone.Collection.extend({
      model: Program,
      initialize: function (models, options) {
      },
      url: '/api/v1/admin/program'
    });

    return {
      Program: Program,
      Programs: Programs
    };
  });
