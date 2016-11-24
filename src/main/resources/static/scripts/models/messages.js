/*jshint browser: true*/
/*global define*/
define([
  'backbone'
],
function (Backbone) {
  'use strict';

  var DialogModel = {};

  DialogModel.MessageTypes = {
    Info: 'info',
    Warning: 'warning',
    Error: 'error',
    Success: 'success'
  };

  DialogModel.Message = Backbone.Model.extend({
    defaults: {
      header: '',
      message: '',
      type: DialogModel.MessageTypes.Info
    }
  });

  DialogModel.GENERAL_ERROR_MESSAGE = function() {
    return new DialogModel.Message({
      header: 'Error',
      message: 'En feil oppstod ved opprettelse av ny arbeidsordre. Kontakt PT.',
      type: DialogModel.MessageTypes.Error
    });
  };

  DialogModel.Messages = Backbone.Collection.extend({
    model: DialogModel.Message
  });

  return DialogModel;
});
