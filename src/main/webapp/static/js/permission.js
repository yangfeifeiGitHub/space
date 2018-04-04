$(function () {
    var permission_datagrid = $("#permission_datagrid");
    var permission_dialog = $("#permission_dialog");
    var permission_form = $("#permission_form");

    permission_datagrid.datagrid({
        fit: true,//自适应宽度
        fitColumns: true,//适合列
        toolbar: '#permission_toolbar',//工具栏
        striped: true,//条纹
        url: '/permission/list.do',
        pagination: true,//分页
        rownumbers: true,//行号
        singleSelect: true,//单选
        columns: [[
            {title: '名称', field: 'name', width: 100},
            {title: '权限表达式', field: 'resource', width: 100}
        ]]
    })


    var methodObj = {
        //添加
        /*add: function () {
            permission_form.form("clear");
            permission_dialog.dialog("open");
            permission_dialog.dialog("setTitle", "增加用户");
        },*/
        //加载权限
        add: function () {
            $.messager.confirm("温馨提示", "确定要加载权限吗", function (r) {
                if (r) {
                    $.get("/permission/load.do", function (data) {
                        if (data.success) {
                            $.messager.alert("温馨提示", "加载成功", "info", function () {
                                permission_datagrid.datagrid("reload");
                            });
                        } else {
                            $.messager.alert("温馨提示", data.msg);
                        }
                    });
                }
            })
        },
        //取消
        cancel: function () {
            permission_dialog.dialog("close");
        },
        //刷新
        fresh: function () {
            permission_datagrid.datagrid("load");
        },
        //删除
        del: function () {
            var row = permission_datagrid.datagrid("getSelected");
            if (!row) {
                $.messager.alert("温馨提示", "请选中一行");
                return;
            }
            $.messager.confirm("温馨提示", "确定要删除吗", function (r) {
                if (r) {
                    $.get("/permission/delete.do", {id: row.id}, function (data) {
                        if (data.success) {
                            $.messager.alert("温馨提示", "操作成功", "info", function () {
                                permission_datagrid.datagrid("reload");
                            });
                        } else {
                            $.messager.alert("温馨提示", data.msg);
                        }
                    });
                }
            })
        }

    }

    //给按钮统一绑定事件
    $("a[data-cmd]").click(function () {
        //根据按钮的data-cmd属性值来调用方法
        var methodName = $(this).data('cmd');
        methodObj[methodName]();
    });
})