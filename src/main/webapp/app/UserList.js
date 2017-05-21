Ext.define('App.UserList', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.userList',
    title: 'All Users',
    store: 'App.UserStore',
    initComponent: function() {
        this.columns = [
            {header: 'id', dataIndex: 'id', flex: 1},
            {header: 'Name', dataIndex: 'name', flex: 1},
            {header: 'Surname', dataIndex: 'surname', flex: 1},
            {header: 'Email', dataIndex: 'email', flex: 1}
        ];
        this.callParent(arguments);
    }
});