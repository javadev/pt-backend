/*jshint browser: true*/
/*global define*/
define([
    'jquery',
    'underscore',
    'backbone'
  ],
  function ($, _, Backbone) {
    'use strict';

    var Email = Backbone.Model.extend({
      defaults: {
        'id': null,
        'emailSubjectEn': null,
        'emailSubjectNo': null,
        'emailTextEn': null,
        'emailTextNo': null,
        'type': {
          'id' : null
        }
      },
      url: function() {
        return '/api/v1/admin/email-message-template' + (this.isNew() ? '' : '/' + this.get('id'));
      },
      validate: function (attrs) {
        var errors = [];
        if (_.isNull(attrs.emailSubjectEn) || _.isEmpty($.trim(attrs.emailSubjectEn))) {
          errors.push({name: 'emailSubjectEn', message: 'Please fill email subject in English field.'});
        } else if (_.isNull(attrs.emailSubjectNo) || _.isEmpty($.trim(attrs.emailSubjectNo))) {
          errors.push({name: 'emailSubjectNo', message: 'Please fill email subject in Norwegian field.'});
        } else if (_.isNull(attrs.emailTextEn) || _.isEmpty($.trim(attrs.emailTextEn))) {
          errors.push({name: 'emailTextEn', message: 'Please fill email text in English field.'});
        } else if (_.isNull(attrs.emailTextNo) || _.isEmpty($.trim(attrs.emailTextNo))) {
          errors.push({name: 'emailTextNo', message: 'Please fill email text in Norwegian field.'});
        }
        return errors.length > 0 ? errors : false;
      }
    });

    var Emails = Backbone.Collection.extend({
      model: Email,
      initialize: function (models, options) {
      },
      url: '/api/v1/admin/email-message-template'
    });

    return {
      Email: Email,
      Emails: Emails
    };
  });
