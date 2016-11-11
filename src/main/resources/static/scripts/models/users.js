/*jshint browser: true*/
/*global define*/
define([
    'underscore',
    'backbone'
  ],
  function (_, Backbone, moment) {
    'use strict';

    var User = Backbone.Model.extend({
      defaults: {
        'id': null,
        'name': null
      },
      url: function() {
        return '/api/v1/admin/user' + (this.isNew() ? '' : '/' + this.get('id'));
      },
      validate: function (attrs) {
        var errors = [];
        if (_.isNull(attrs.name) || _.isEmpty(attrs.name)) {
          errors.push({name: 'name', message: 'Please fill name field.'});
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
