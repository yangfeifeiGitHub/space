
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="/static/common/common.jsp" %>
    <script type="text/javascript" src="/static/js/role.js"></script>
</head>
<body>
<div id="role_datagrid"></div>
<div id="role_toolbar">
    <a class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" data-cmd="add">新增</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" data-cmd="edit">编辑</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true" data-cmd="fresh">刷新</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true" data-cmd="del">删除</a>
</div>
<div id="role_dialog">
    <form id="role_form" method="post">
        <input type="hidden" name="id"/>
        <table align="center" style="margin-top:10px">
            <tr data-options="fit:true">
                <td>角色编号:<input class="easyui-textbox" name="sn"></td>
                <td>角色名称<input class="easyui-textbox" name="name"></td>
            </tr>
            <tr>
                <td><table id="allPermission"></table></td>
                <td><table id="selfPermission"></table></td>
            </tr>

        </table>
    </form>
</div>

<div id="role_buttons">
    <a class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-save'" data-cmd="save">保存</a>
    <a class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-cancel'" data-cmd="cancel">取消</a>
</div>

</body>
</html>
