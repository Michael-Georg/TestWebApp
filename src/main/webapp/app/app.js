Ext.application({
    requires: ['Ext.container.Viewport'],
    name: 'App',
    appFolder: 'app',
    controllers: ['App.UserController'],

    launch: function () {
        Ext.create('Ext.container.Viewport', {
            layout: 'fit',
            items: {
                xtype: 'userList'
            }
        });
    }
});