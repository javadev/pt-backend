/*jshint browser: true*/
/*global define*/
define([
  'jquery',
  'backbone',
  'app',
  'models/login',
  'models/messages',
  'views/messages'
],
function($, Backbone, App, LoginModel, DialogModel, MessagesView) {
  'use strict';
  
  // Create a place to put objects that should live across page transitions.
  App.addInitializer(function() {
    App.globals = App.globals || {};
  });

  // Setup the global user object, and initiate login check.
  App.addInitializer(function() {
    App.globals.user = new LoginModel();
    App.globals.user.checkLoginStatus();
  });

  // Act on authorization status changes on the user.
  App.addInitializer(function() {
   App.globals.user.on('change:authorized', function(model, authorized) {
      if (authorized) {
        var toUrl = ('login' === App.globals.incomingUrl) ? '' : App.globals.incomingUrl;
        App.appRouter.navigate(toUrl, { trigger: true });
      } else {
        App.appController.login();
        App.appRouter.navigate('/login');
      }
      if (!Backbone.History.started) {
        Backbone.history.start();
      }
    });
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
  });
});
