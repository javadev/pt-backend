/*jshint browser: true*/
/*global define*/
define([
  'underscore',
  'backbone',
  'marionette'
],
function (_, Backbone, Marionette) {
  'use strict';

  // Curly bracket notation in _ templates, eg {{ name }} and
  // {%foo(name) %}
  _.templateSettings = {
    evaluate: /\{\%([\s\S]+?)\%\}/g,
    interpolate: /\{\{([\s\S]+?)\}\}/g,
    escape: /\{\{-(.+?)\}\}/g
  };

  var App = new Marionette.Application();

  App.addRegions({
    mainRegion: '#main',
    headerRegion: '#header'
  });

  return App;

});
