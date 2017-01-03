/*jshint browser: true*/
/*global define*/
define([
    'underscore',
    'backbone'
  ],
  function (_, Backbone) {
    'use strict';

    var User = Backbone.Model.extend({
      defaults: {
        'id': null,
        'name': null,
        'email': null,
        'password': null,
        'type': {
          'id': null,
          'nameEn': null
        },
        'level': null,
        'goals': []
      },
      url: function() {
        return '/api/v1/admin/user' + (this.isNew() ? '' : '/' + this.get('id'));
      },
      validate: function (attrs) {
        var errors = [];
        if (_.isNull(attrs.name) || _.isEmpty(attrs.name)) {
          errors.push({name: 'name', message: 'Please fill name field.'});
        } else if (_.isNull(attrs.email) || _.isEmpty(attrs.email)) {
          errors.push({name: 'email', message: 'Please fill email field.'});
        }
        return errors.length > 0 ? errors : false;
      }
    });

    var Users = Backbone.Collection.extend({
      model: User,
      initialize: function (models, options) {
      },
      url: '/api/v1/admin/user'
    });

    return {
      User: User,
      Users: Users
    };
  });
