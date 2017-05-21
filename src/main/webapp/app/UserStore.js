Ext.define('App.UserStore', {
    extend: 'Ext.data.Store',
    model: 'App.UserModel',
    autoLoad: true,
    autoSync: true,
    storeId: 'userStore',
    proxy: {
        type: 'ajax',
        url: '/service/users/getAll',
        reader: {
            type: 'json',
            root: 'entity',
            successProperty: 'success'
        }
    }
});

