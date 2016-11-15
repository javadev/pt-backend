/*jshint browser: true*/
/*global define*/
define([
    'jquery',
    'underscore',
    'backbone'
  ],
  function ($, _, Backbone, moment) {
    'use strict';

    var Exercise = Backbone.Model.extend({
      defaults: {
        'id': null,
        'exerciseId': null,
        'nameEn': null,
        'nameNo': null,
        'category': {
          'id' : 1
        }
      },
      url: function() {
        return '/api/v1/admin/exercise' + (this.isNew() ? '' : '/' + this.get('id'));
      },
      validate: function (attrs) {
        var errors = [];
        if (_.isNull(attrs.nameEn) || _.isEmpty($.trim(attrs.nameEn))) {
          errors.push({name: 'nameEn', message: 'Please fill name in English field.'});
        } else if (_.isNull(attrs.nameNo) || _.isEmpty($.trim(attrs.nameNo))) {
          errors.push({name: 'nameNo', message: 'Please fill name in Norwegian field.'});
        } else if (_.isNull(attrs.exerciseId) || _.isEmpty($.trim(attrs.exerciseId))) {
          errors.push({name: 'exerciseId', message: 'Please fill id field.'});
        }
        return errors.length > 0 ? errors : false;
      }
    });

    var Exercises = Backbone.Collection.extend({
      model: Exercise,
      initialize: function (models, options) {
      },
      url: '/api/v1/admin/exercise'
    });

    return {
      Exercise: Exercise,
      Exercises: Exercises
    };
  });
