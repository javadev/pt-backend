/*jshint browser: true*/
/*global define*/
define([
  'underscore',
  'marionette',
  'models/messages'
],
function(_, Marionette, DialogModel) {
  'use strict';

  var template = _.template([
    '<div class="alert {{ className(type) }}">',
    '<a class="close" data-dismiss="alert">&times;</a>',
      '{% if(header != "") { %}',
      '<strong>{{ header }}</strong>',
      '{% } %}',
      '<p>{{ message }}</p>',
    '</div>'
  ].join(''));

  var MessageView = Marionette.ItemView.extend({
    className: 'component',
    template: template,
    templateHelpers: {
      className: function(type) {
        switch (type) {
          case DialogModel.MessageTypes.Error:
            return 'alert-danger';
          case DialogModel.MessageTypes.Success:
            return 'alert-success';
          case DialogModel.MessageTypes.Warning:
            return 'alert-warning';
          default:
            return 'alert-info';
        }
      }
    },
    events: { 'click': 'close' }
  });
  
  return Marionette.CollectionView.extend({
    className: 'messages empty-page-header',
    itemView: MessageView
  });
});
