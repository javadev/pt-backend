/*jshint browser: true*/
/*global define*/
define([
  'app',
  'routers/app',
  'controllers/app',
  // Include initializers which register themselves on the App object.
  'initializers'
],
function(App, AppRouter, AppController) {
    'use strict';

    App.appController = new AppController();

    App.appRouter = new AppRouter({
      controller: App.appController
    });

    // When `start()` is called, App initializers are run in registered order.
    App.start();

});
