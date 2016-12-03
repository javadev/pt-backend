({
    // see https://github.com/jrburke/r.js/blob/master/build/example.build.js
    // for an explanation of these fields

    baseUrl: '${basedir}/src/main/resources/static/scripts/',
    inlineText: true,
    useStrict: false,
    name: 'external/devhelpers/almond',
    include: ['main'],
    out: '${project.build.directory}/${project.build.finalName}/scripts/admin-app.js',
    insertRequire: ['main'],
    wrap: false,
    mainConfigFile: '${basedir}/src/main/resources/static/scripts/main.js',
    generateSourceMaps: true,
    preserveLicenseComments: false,
    logLevel: 0,
    stubModules: ['text', 'hbs'],
    optimize: 'uglify2'
})
