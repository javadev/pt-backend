/*jshint node:true*/
"use strict";

module.exports = function(grunt) {

  // Load dependencies specified in package.json
  require("matchdep").filterDev("grunt-*").forEach(grunt.loadNpmTasks);
  // … and in the `tasks` directory
  grunt.loadTasks("tasks");

  grunt.initConfig({
    jshint: {
      options: {
        jshintrc: ".jshintrc"
      },
      all: [ "scripts/**/*.js", "!scripts/external/**/*" ],
      ci: {
        options: {
          reporter: "jslint",
          reporterOutput: "target/results-jshint.xml"
        },
        files: {
          src: [ "scripts/**/*.js", "!scripts/external/**/*" ]
        }
      }
    },

    availabletasks: {
      tasks: {
        options: {
          filter: "include",
          tasks: [
            "test",
            "testem",
            "integrationtest",
            "serve"
          ]
        }
      }
    }
  });

  grunt.registerTask("mktarget", "Create needed folders", function() {
    grunt.file.delete(__dirname + "/target");
    grunt.file.mkdir(__dirname + "/target");
    grunt.log.ok("'target' folder created");
  });

  grunt.registerTask("default",
                     "Shows the available tasks in a user friendly manner.",
                     "availabletasks");

  // TODO: Add "testem" task before "integrationtest" when test are added.
  grunt.registerTask("test", "Runs unit tests and integration tests",
                     [ "mktarget", "jshint:ci" ]);
};
