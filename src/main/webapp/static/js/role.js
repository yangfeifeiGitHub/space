$(function () {
    var role_datagrid = $("#role_datagrid");
    var role_dialog = $("#role_dialog");
    var role_form = $("#role_form");
    var selfPermission = $("#selfPermission");
    var allPermission = $("#allPermission");

    role_datagrid.datagrid({
        fit: true,//自适应宽度
        fitColumns: true,//适合列
        toolbar: '#role_toolbar',//工具栏
        striped: true,//条纹
        url: '/role/list.do',
        pagination: true,//分页
        rownumbers: true,//行号
        singleSelect: true,//单选
        columns: [[
            {title: '编号', field: 'sn', width: 100},
            {title: '角色名称', field: 'name', width: 100}
        ]]
    })

    role_dialog.dialog({
        width: 600,
        height: 450,
        buttons: "#role_buttons",
        closed: true,
        onClose: function () {
            role_form.form("clear");
            selfPermission.datagrid("loadData",[]);
            allPermission.datagrid("reload");
        }
    })

    var methodObj = {
        //添加
        add: function () {
            role_form.form("clear");
            role_dialog.dialog("open");
            role_dialog.dialog("setTitle", "添加角色");
        },
        //保存
        save: function () {
            role_form.form("submit", {
                url: "/role/saveOrUpdate.do",
                //数据表格不是form表单的内容,需要另提交数据
                onSubmit:function (param) {
                    var rows = selfPermission.datagrid("getRows");
                    for(var i = 0;i<rows.length;i++){
                        param["permissions["+i+"].id"] = rows[i].id;
                    }
                },
                success: function (data) {
                    data = $.parseJSON(data);
                    if (data.success) {
                        $.messager.alert("温馨提示", "保存成功", "info", function () {
                            //关闭弹出框
                            role_dialog.dialog("close");
                            //刷新页面，返回到主页
                            role_datagrid.datagrid("load");
                            //只刷新当前页
                            //role_datagrid.datagrid("reload")
                        })
                    } else {
                        $.messager.alert("温馨提示", data.msg);
                    }
                }
            });
        },
        //取消
        cancel: function () {
            role_dialog.dialog("close");
        },
        //刷新
        fresh: function () {
            role_datagrid.datagrid("load");
        },
        //删除
        del: function () {
            var row = role_datagrid.datagrid("getSelected");
            if (!row) {
                $.messager.alert("温馨提示", "请选中一行");
                return;
            }
            $.messager.confirm("温馨提示", "确定要删除吗", function (r) {
                if (r) {
                    $.get("/role/delete.do", {id: row.id}, function (data) {
                        if (data.success) {
                            $.messager.alert("温馨提示", "操作成功", "info", function () {
                                role_datagrid.datagrid("reload");
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
            var row = role_datagrid.datagrid("getSelected");
            if (!row) {
                $.messager.alert("温馨提示", "请选中一行");
                return;
            }
            role_dialog.dialog("setTitle", "编辑角色信息");
            /*if (row.roleDir) {
                row["roleDir.parent.dirName"] = row.roleDir.parent.dirName;
                row["roleDir.dirName"] = row.roleDir.dirName;
                row["roleDir.id"] = row.roleDir.id;
            }
            //回显数据
            $.get("/role/selectRowByRoleId.do", {id: row.id}, function (data) {
                $("#role_combobox").combobox("setValues", data);
            },'json');*/
            role_form.form("load", row);
            //回显已有的权限
            selfPermission.datagrid("load",{id:row.id});
            //打开dialog
            role_dialog.dialog("open");
        }
    }

    //给按钮统一绑定事件
    $("a[data-cmd]").click(function () {
        //根据按钮的data-cmd属性值来调用方法
        var methodName = $(this).data('cmd');
        methodObj[methodName]();
    });

    //所有权限
    var allPermission = $("#allPermission");
    allPermission.datagrid({
        title:'所有权限',
        url:'/permission/selectAll.do',
        fitColumns:true,
        height:300,
        columns:[[
            {field:'name',title:'权限名称',align:'center',width:100}
        ]],
        onClickRow:function (index,row) {//点击一行的时候触发
            selfPermission.datagrid("appendRow",row);//追加一个新行
            //把已选中数据从所有权限中删掉
            allPermission.datagrid("deleteRow",index);
        }
    });
    //已有权限
    var selfPermission = $("#selfPermission");
    selfPermission.datagrid({
        title:'已有权限',
        url:'/permission/selectByRoleId.do',
        fitColumns:true,
        height:300,
        columns:[[
            {field:'name',title:'权限名称',align:'center',width:100}
        ]],
        onClickRow:function (index,row) {
            allPermission.datagrid("appendRow",row);
            selfPermission.datagrid("deleteRow",index);
        },
        onLoadSuccess:function (data) {
            console.log(data.rows);
            //已有权限集合的id
            var ids = $.map(data.rows,function (p) {
                return p.id;
            });
            //获取所有权限中的数据
            var rows = allPermission.datagrid("getRows");
            //判断所有权限中的数据是否已经在已有权限中
            for(var i = rows.length-1;i>=0;i--){
                var index = $.inArray(rows[i].id,ids);//如果不存在则返回-1
                if(index >= 0){
                    allPermission.datagrid("deleteRow",i);
                }
            }
        }
    })

})