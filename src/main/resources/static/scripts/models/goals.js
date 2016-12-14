/*jshint browser: true*/
/*global define*/
define([
    'jquery',
    'underscore',
    'backbone'
  ],
  function ($, _, Backbone) {
    'use strict';

    var Goal = Backbone.Model.extend({
      defaults: {
        'id': null,
        'titleEn': null,
        'titleNo': null,
        'title2En': null,
        'title2No': null,
        'parameters': []
      },
      url: function() {
        return '/api/v1/admin/goal' + (this.isNew() ? '' : '/' + this.get('id'));
      },
      validate: function (attrs) {
        var errors = [];
        if (_.isNull(attrs.titleEn) || _.isEmpty($.trim(attrs.titleEn))) {
          errors.push({name: 'titleEn', message: 'Please fill title in English field.'});
        } else if (_.isNull(attrs.titleNo) || _.isEmpty($.trim(attrs.titleNo))) {
          errors.push({name: 'titleNo', message: 'Please fill title in Norwegian field.'});
        }
        return errors.length > 0 ? errors : false;
      }
    });

    var Goals = Backbone.Collection.extend({
      model: Goal,
      initialize: function (models, options) {
      },
      url: '/api/v1/admin/goal'
    });

    return {
      Goal: Goal,
      Goals: Goals
    };
  });
