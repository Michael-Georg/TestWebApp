Ext.define('App.UserController', {
    extend: 'Ext.app.Controller',
    views: ['App.UserList', 'App.UserView'],
    stores: ['App.UserStore'],
    models: ['App.UserModel'],

    init: function () {
        this.control({
            'viewport > userList': {
                itemdblclick: this.editUser
            },
            'userwindow button[action=new]': {
                click: this.createUser
            },
            'userwindow button[action=save]': {
                click: this.updateUser
            },
            'userwindow button[action=delete]': {
                click: this.deleteUser
            },
            'userwindow button[action=clear]': {
                click: this.clearForm
            }

        });
    },
    updateUser: function (button) {
        var win = button.up('window'),
            form = win.down('form'),
            values = form.getValues();
        values.id = form.getRecord().get('id');
        Ext.Ajax.request({
            url: 'service/users/update',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            params: Ext.encode(values),
            success: function (response, options) {
                var data = Ext.decode(response.responseText);
                var store = Ext.widget('userList').getStore();
                store.load();
                Ext.Msg.alert('Success', 'User information changed')
            },
            failure: function (response, options) {
                var msg = Ext.decode(response.responseText);
                Ext.Msg.alert('Error', msg);
            }
        });
    },
    createUser: function (button) {
        var win = button.up('window'),
            form = win.down('form'),
            values = Ext.encode(form.getValues());
        Ext.Ajax.request({
            url: 'service/users/create',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            params: values,
            success: function (response, options) {
                var data = Ext.decode(response.responseText);
                var store = Ext.widget('userList').getStore();
                store.load();
                win.close();
                Ext.Msg.alert('Success', 'New user successfully added');
            },
            failure: function (response, options) {
                var data = Ext.decode(response.responseText);
                Ext.Msg.alert('Error', data.msg);
            }
        });
    },
    deleteUser: function (button) {
        var win = button.up('window'),
            form = win.down('form'),
            id = form.getRecord().get('id');
        Ext.Ajax.request({
            url: 'service/users/delete',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            method: 'GET',
            params: {id: id},
            success: function (response) {
                var data = Ext.decode(response.responseText);
                var store = Ext.widget('userList').getStore();
                var record = store.getById(id);
                store.remove(record);
                win.close();
                Ext.Msg.alert('Success', 'User deleted');
            },
            failure: function (response) {
                var msg = Ext.decode(response.responseText).msg;
                Ext.Msg.alert('Error', msg);
            }
        });
    },
    clearForm: function (grid, record) {
        var view = Ext.widget('userwindow');
        view.down('form').getForm().reset();
    },
    editUser: function (grid, record) {
        var view = Ext.widget('userwindow');
        view.down('form').loadRecord(record);
    }
});