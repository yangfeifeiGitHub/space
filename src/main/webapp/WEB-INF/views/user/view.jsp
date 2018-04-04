
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="/static/common/common.jsp" %>
    <script type="text/javascript" src="/static/js/user.js"></script>
</head>
<body>
<div id="user_datagrid"></div>
<div id="user_toolbar">
    <a class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" data-cmd="add">新增</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" data-cmd="edit">编辑</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true" data-cmd="fresh">刷新</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true" data-cmd="del">删除</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true" data-cmd="resetPassword">重置密码</a>

    <input class="easyui-textbox" id="keyword" prompt="请输入用户名或编号">
    <a class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" data-cmd="searchs"></a>
</div>
<div id="user_dialog">
    <form id="user_form" method="post">
        <input type="hidden" name="id"/>
        <table align="center" style="margin-top:20px">
            <tr>
                <%--validType="remote['/static/json/username_remote.json','name']"--%>
                <td>姓名:</td>
                <td><input name="username" class="easyui-textbox" required="true"
                           <%--data-options="panelHeight:'auto',valueField:'id',textField:'name',
                           url:'/outComeType/selectAll.do'"--%>></td>
            </tr>
            <tr>
                <td>密码:</td>
                <td><input name="password" class="easyui-passwordbox" required="true"></td>
            </tr>
            <tr>
                <td>邮箱:</td>
                <td><input name="email" class="easyui-validatebox"
                           data-options="required:true,validType:'email'"></td>
            </tr>
            <tr>
                <td>编号:</td>
                <td><input name="sn" class="easyui-textbox" required="true"></td>
            </tr>
            <tr>
                <td>管理员:</td>
                <td><input class="easyui-combobox" name="admin" data-options="panelHeight:'auto',
                valueField: 'label',
		        textField: 'value',
                data: [{
                label: '1',
                value: '是'
                },{
                label: '0',
                value: '否'
                }]"></td>
            </tr>
            <tr>
                <td>角色</td>
                <td><input id="role_combobox" class="easyui-combobox" name="roles.id"
                 data-options="panelHeight:'auto',
                valueField:'id',textField:'name',url:'/role/selectAll.do',multiple:true" required="true"></td>
            </tr>

        </table>
    </form>
</div>

<div id="user_buttons">
    <a class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-save'" data-cmd="save">保存</a>
    <a class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-cancel'" data-cmd="cancel">取消</a>
</div>

<div id="resetPassword_dialog">
    <form id="resetPassword_form" method="post">
        <table align="center" style="margin-top:20px">
            <tr>
                <%--validType="remote['/static/json/username_remote.json','name']"--%>
                <td>新密码:<input id="pwd" name="newPassword" class="easyui-passwordbox" required="true"
                ></td>
            </tr>
            <tr>
                <td>确认密码:<input class="easyui-passwordbox" required="true" validType="equals['#pwd']"
                ></td>
            </tr>
        </table>
    </form>
</div>

<div id="resetPassword_buttons">
    <a class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-save'" data-cmd="savePassword">更改密码</a>
    <a class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-cancel'" data-cmd="pwdCancel">取消</a>
</div>
</body>
</html>
