$(function () {
    var site_datagrid = $("#site_datagrid");
    var site_dialog = $("#site_dialog");
    var site_form = $("#site_form");

    site_datagrid.datagrid({
        fit: true,//自适应宽度
        fitColumns: true,//适合列
        toolbar: '#site_toolbar',//工具栏
        striped: true,//条纹
        url: '/siteInfo/list.do',
        pagination: true,//分页
        rownumbers: true,//行号
        singleSelect: true,//单选
        columns: [[
            {title: '用地编码', field: 'sn', width: 100},
            {title: '用地名称', field: 'name', width: 100},
            {title: '用地面积', field: 'area', width: 100},
            {title: '用地状态', field: 'status', width: 100,formatter:function (value,row,index) {
                    return value?"启用":"停用";
                }
            }
        ]]
    })

    site_dialog.dialog({
        width: 450,
        height: 400,
        buttons: '#site_buttons',
        closed: true,
        onClose: function () {
            site_form.form('clear');
        }
    });


    var methodObj = {
        //添加
        add: function () {
            site_form.form("clear");
            site_dialog.dialog("open");
            site_dialog.dialog("setTitle", "新建用地");
        },
        //保存
        save: function () {
            site_form.form("submit", {
                url: "/siteInfo/saveOrUpdate.do",
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
                            site_dialog.dialog("close");
                            //刷新页面，返回到主页
                            site_datagrid.datagrid("load");
                            //只刷新当前页
                            //site_datagrid.datagrid("reload")
                        })
                    } else {
                        $.messager.alert("温馨提示", data.msg);
                    }
                }
            });
        },
        //取消
        cancel: function () {
            site_dialog.dialog("close");
        },
        //刷新
        fresh: function () {
            site_datagrid.datagrid("load");
        },
        //删除
        del: function () {
            var row = site_datagrid.datagrid("getSelected");
            if (!row) {
                $.messager.alert("温馨提示", "请选中一行");
                return;
            }
            $.messager.confirm("温馨提示", "确定要删除吗", function (r) {
                if (r) {
                    $.get("/siteInfo/delete.do", {id: row.id}, function (data) {
                        if (data.success) {
                            $.messager.alert("温馨提示", "操作成功", "info", function () {
                                site_datagrid.datagrid("reload");
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
            var row = site_datagrid.datagrid("getSelected");
            if (!row) {
                $.messager.alert("温馨提示", "请选中一行");
                return;
            }
            //回显角色
            /*$.get("/role/selectBySiteId.do",{id:row.id},function (data) {
                $("#role_combobox").combobox('setValues',data);
                console.log(data);
            });*/
            /*if (row.siteDir) {
                row["siteDir.parent.dirName"] = row.siteDir.parent.dirName;
                row["siteDir.dirName"] = row.siteDir.dirName;
                row["siteDir.id"] = row.siteDir.id;
            }
            //回显数据
            $.get("/site/selectRowBySiteId.do", {id: row.id}, function (data) {
                $("#site_combobox").combobox("setValues", data);
            },'json');*/
            site_form.form("load", row);
            //打开dialog
            site_dialog.dialog("open");
        },
        searchs: function () {
            //获取关键字的内容
            //textbox返回文本框对象,getValue获取组件的值
            var keyword = $("#keyword").textbox('getValue');
            var sendData = {keyword: keyword}
            site_datagrid.datagrid("load", sendData);
        }
    }

    //给按钮统一绑定事件
    $("a[data-cmd]").click(function () {
        //根据按钮的data-cmd属性值来调用方法
        var methodName = $(this).data('cmd');
        methodObj[methodName]();
    });
})