Ext.define('App.UserView', {
    extend: 'Ext.window.Window',
    alias: 'widget.userwindow',

    title: 'Edit',
    layout: 'fit',
    autoShow: true,

    initComponent: function () {
        this.items = [{
            xtype: 'form',
            items: [{
                xtype: 'textfield',
                name: 'name',
                fieldLabel: 'Name'
            }, {
                xtype: 'textfield',
                name: 'surname',
                fieldLabel: 'Surname'
            }, {
                xtype: 'textfield',
                name: 'email',
                fieldLabel: 'Email',
                vtype: 'email'
            }]
        }];
        this.dockedItems = [{
            xtype: 'toolbar',
            docked: 'top',
            items: [{
                text: 'Create',
                action: 'new'
            }, {
                text: 'Update',
                action: 'save'
            }, {
                text: 'Delete',
                action: 'delete'
            }]
        }];
        this.buttons = [{
            text: 'Clear',
            scope: this,
            action: 'clear'
        }];
        this.callParent(arguments);
    }
});