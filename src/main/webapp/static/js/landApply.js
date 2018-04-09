$(function () {
    var landApply_datagrid = $("#landApply_datagrid");
    var landApply_dialog = $("#landApply_dialog");
    var landApply_form = $("#landApply_form");

    var methodObj = {
        //保存
        save: function () {
            landApply_form.form("submit", {
                url: "/landApply/saveOrUpdate.do",
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
                            landApply_dialog.dialog("close");
                            //刷新页面，返回到主页
                            landApply_datagrid.datagrid("load");
                            //只刷新当前页
                            //landApply_datagrid.datagrid("reload")
                        })
                    } else {
                        $.messager.alert("温馨提示", data.msg);
                    }
                }
            });
        },
        //取消
        cancel: function () {
            landApply_dialog.dialog("close");
        },
        //编辑
        edit: function () {
            var row = landApply_datagrid.datagrid("getSelected");
            if (!row) {
                $.messager.alert("温馨提示", "请选中一行");
                return;
            }
            /*if (row.landApplyDir) {
                row["landApplyDir.parent.dirName"] = row.landApplyDir.parent.dirName;
                row["landApplyDir.dirName"] = row.landApplyDir.dirName;
                row["landApplyDir.id"] = row.landApplyDir.id;
            }
            //回显数据
            $.get("/landApply/selectRowByLandApplyId.do", {id: row.id}, function (data) {
                $("#landApply_combobox").combobox("setValues", data);
            },'json');*/
            landApply_form.form("load", row);
            //打开dialog
            landApply_dialog.dialog("open");
        },
    };

    //给按钮统一绑定事件
    $("a[data-cmd]").click(function () {
        //根据按钮的data-cmd属性值来调用方法
        var methodName = $(this).data('cmd');
        methodObj[methodName]();
    });
})