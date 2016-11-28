/*jshint browser: true*/
/*global define*/
define([
    'jquery',
    'underscore',
    'backbone'
  ],
  function ($, _, Backbone) {
    'use strict';

    var Certificate = Backbone.Model.extend({
      defaults: {
        'id': null,
        'code': null,
        'amountOfDays': 30,
        'activated': false
      },
      url: function() {
        return '/api/v1/admin/certificate' + (this.isNew() ? '' : '/' + this.get('id'));
      },
      validate: function (attrs) {
        var errors = [];
        if (_.isNull(attrs.code) || _.isEmpty($.trim(attrs.code))) {
          errors.push({name: 'code', message: 'Please fill code field.'});
        } else if (_.isNull(attrs.amountOfDays) || _.isEmpty($.trim(attrs.amountOfDays))) {
          errors.push({name: 'amountOfDays', message: 'Please fill amount of days field.'});
        }
        return errors.length > 0 ? errors : false;
      },
      generate: function() {
        var passwords = [ 'ALKJVBPIQYTUIWEBVPQALZVKQRWORTUYOYISHFLKAJMZNXBVMNFGAHKJSDFALAPOQIERIUYTGSFGKMZNXBVJAHGFAKX',
                '1234567890'];
        var result = '';
        var passwordLength = 8;
        for (var index = 0; index < passwordLength; index += 1) {
            var passIndex = Math.floor(passwords.length * index / passwordLength);
            var charIndex = Math.floor(Math.random() * passwords[passIndex].length);
            result += passwords[passIndex].charAt(charIndex);
        }
        return result;
      }
    });

    var Certificates = Backbone.Collection.extend({
      model: Certificate,
      initialize: function (models, options) {
      },
      url: '/api/v1/admin/certificate'
    });

    return {
      Certificate: Certificate,
      Certificates: Certificates
    };
  });
