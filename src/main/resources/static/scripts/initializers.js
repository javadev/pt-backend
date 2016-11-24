/*jshint browser: true*/
/*global define*/
define([
  'jquery',
  'backbone',
  'app',
  'models/messages',
  'views/messages'
],
function($, Backbone, App, DialogModel, MessagesView) {
  'use strict';
  
  // Create a place to put objects that should live across page transitions.
  App.addInitializer(function() {
    App.globals = App.globals || {};
  });

  // Shows messages based on events on the App vent.
  App.addInitializer(function() {
    var messages = new DialogModel.Messages();
    var messagesView = new MessagesView({
      collection: messages
    });
    App.headerRegion.show(messagesView);

    var addErrorMessage = function(message) {
      var m = new DialogModel.Message({ header: 'Feil', message: message,
        type: DialogModel.MessageTypes.Error });
      messages.add(m);
    };

    var addInfoMessage = function(message) {
      var m = new DialogModel.Message({ message: message });
      messages.add(m);
    };

    App.vent.on('xhr:error', addErrorMessage);
    App.vent.on('message', addInfoMessage);
  });

  // Read incoming URL, and start routing based on #
  App.addInitializer(function() {
    App.globals.incomingUrl = Backbone.history.getHash();
    Backbone.history.start();
  });
});
