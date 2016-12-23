/*jshint browser: true*/
/*global define*/
define([
    'jquery',
    'underscore',
    'backbone'
  ],
  function ($, _, Backbone) {
    'use strict';

    var Exercise = Backbone.Model.extend({
      defaults: {
        'id': null,
        'exerciseId': null,
        'nameEn': null,
        'nameNo': null,
        'descriptionEn': null,
        'descriptionNo': null,
        'cardioPercent': 0,
        'bodypart': {
          'id' : null
        },
        'equipmentType': {
          'id' : null
        },
        'types': [],
        'images': []
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
          errors.push({name: 'exerciseId', message: 'Please fill exerciseId field.'});
        } else if (!_.isNull(attrs.bodypart) && _.isNull(attrs.bodypart.id)) {
          errors.push({name: 'bodypart', message: 'Please fill bodypart field.'});
        } else if (parseInt(attrs.cardioPercent, 10) < 0 || parseInt(attrs.cardioPercent, 10) > 100) {
          errors.push({name: 'cardioPercent', message: 'Please fill cardio percent field.'});
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
