$(function () {
    var user_datagrid = $("#user_datagrid");
    var user_dialog = $("#user_dialog");
    var user_form = $("#user_form");
    var resetPassword_dialog = $("#resetPassword_dialog");
    var resetPassword_form = $("#resetPassword_form");

    user_datagrid.datagrid({
        fit: true,//自适应宽度
        fitColumns: true,//适合列
        toolbar: '#user_toolbar',//工具栏
        striped: true,//条纹
        url: '/user/list.do',
        pagination: true,//分页
        rownumbers: true,//行号
        singleSelect: true,//单选
        columns: [[
            {title: '姓名', field: 'username', width: 100},
            {title: '邮箱', field: 'email', width: 100},
            {title: '编号', field: 'sn', width: 100}
        ]]
    })

    user_dialog.dialog({
        width: 450,
        height: 400,
        buttons: '#user_buttons',
        closed: true,
        onClose: function () {
            user_form.form('clear');
        }
    });

    resetPassword_dialog.dialog({
        width: 330,
        height: 320,
        buttons: '#resetPassword_buttons',
        closed: true,
        onClose: function () {
            resetPassword_dialog.form('clear');
        }
    });

    var methodObj = {
        //添加
        add: function () {
            user_form.form("clear");
            user_dialog.dialog("open");
            user_dialog.dialog("setTitle", "增加用户");
        },
        //保存
        save: function () {
            user_form.form("submit", {
                url: "/user/saveOrUpdate.do",
                onSubmit:function (param) {
                    //获取角色下拉框中的数据
                    var ids = $("#role_combobox").combobox("getValues");
                    for(var i = 0;i<ids.length;i++){
                        param["roles["+i+"].id"]=ids[i];
                    }
                },
                success: function (data) {
                    data = $.parseJSON(data);
                    if (data.success) {
                        $.messager.alert("温馨提示", "保存成功", "info", function () {
                            //关闭弹出框
                            user_dialog.dialog("close");
                            //刷新页面，返回到主页
                            user_datagrid.datagrid("load");
                            //只刷新当前页
                            //user_datagrid.datagrid("reload")
                        })
                    } else {
                        $.messager.alert("温馨提示", data.msg);
                    }
                }
            });
        },
        //取消
        cancel: function () {
            user_dialog.dialog("close");
        },
        //刷新
        fresh: function () {
            user_datagrid.datagrid("load");
        },
        //删除
        del: function () {
            var row = user_datagrid.datagrid("getSelected");
            if (!row) {
                $.messager.alert("温馨提示", "请选中一行");
                return;
            }
            $.messager.confirm("温馨提示", "确定要删除吗", function (r) {
                if (r) {
                    $.get("/user/delete.do", {id: row.id}, function (data) {
                        if (data.success) {
                            $.messager.alert("温馨提示", "操作成功", "info", function () {
                                user_datagrid.datagrid("reload");
                            });
                        } else {
                            $.messager.alert("温馨提示", data.msg);
                        }
                    });
                }
            })
        },
        //编辑
        edit: function () {
            var row = user_datagrid.datagrid("getSelected");
            if (!row) {
                $.messager.alert("温馨提示", "请选中一行");
                return;
            }
            //回显角色
            $.get("/role/selectByUserId.do",{id:row.id},function (data) {
                $("#role_combobox").combobox('setValues',data);
                console.log(data);
            });
            /*if (row.userDir) {
                row["userDir.parent.dirName"] = row.userDir.parent.dirName;
                row["userDir.dirName"] = row.userDir.dirName;
                row["userDir.id"] = row.userDir.id;
            }
            //回显数据
            $.get("/user/selectRowByUserId.do", {id: row.id}, function (data) {
                $("#user_combobox").combobox("setValues", data);
            },'json');*/
            user_form.form("load", row);
            //打开dialog
            user_dialog.dialog("open");
        },
        //重置密码
        resetPassword: function () {
            var row = user_datagrid.datagrid("getSelected");
            if (!row) {
                $.messager.alert("温馨提示", "请选中一行");
                return;
            }
            resetPassword_dialog.dialog("setTitle","修改密码");
            resetPassword_dialog.dialog("open");
        },
        //保存新密码
        savePassword: function () {
            $.messager.confirm("温馨提示", "确定进行重置密码吗？", function (r) {
                if (r) {
                    var row = user_datagrid.datagrid("getSelected");
                    resetPassword_form.form("submit", {
                        url: "/user/resetPassword.do",
                        success: function (data) {
                            data = $.parseJSON(data);
                            if (data.success) {
                                $.messager.alert("温馨提示", "更改成功", "info", function () {
                                    //关闭弹出框
                                    resetPassword_dialog.dialog("close");
                                    //刷新页面，返回到主页
                                    user_datagrid.datagrid("load");
                                    //只刷新当前页
                                    //emp_datagrid.datagrid("reload")
                                })
                            } else {
                                $.messager.alert("温馨提示", data.msg);
                            }
                        },
                        onSubmit: function (param) {
                            param["id"] = row.id;
                            param["username"] = row.username;
                        }

                    });
                }
            });
        },
        //修改密码的取消按钮
        pwdCancel: function () {
            resetPassword_dialog.dialog("close");
        },
        searchs: function () {
            //获取关键字的内容
            //textbox返回文本框对象,getValue获取组件的值
            var keyword = $("#keyword").textbox('getValue');
            var sendData = {keyword: keyword}
            user_datagrid.datagrid("load", sendData);
        }
    }

    //给按钮统一绑定事件
    $("a[data-cmd]").click(function () {
        //根据按钮的data-cmd属性值来调用方法
        var methodName = $(this).data('cmd');
        methodObj[methodName]();
    });
})