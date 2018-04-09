
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="/static/common/common.jsp" %>
    <script type="text/javascript" src="/static/js/landApply.js"></script>
</head>
<body>
<h3>申请信息录入</h3>
<div id="landApply_datagrid"></div>
<div id="landApply_toolbar">
    <a class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" data-cmd="add">新增</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" data-cmd="edit">编辑</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true" data-cmd="fresh">刷新</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true" data-cmd="del">删除</a>
</div>
    <form id="landApply_form" method="post">
        <input type="hidden" name="id"/>
        <table align="center" style="margin-top:10px">
            <tr data-options="fit:true">
                <td>申请场地:</td>
                <td>
                    <input class="easyui-combobox" name="siterinfo.id" data-options="panelHeight:'auto',
                    valueField:'id',textField:'name',url:'/siteInfo/selectAll.do',multiple:true" required="true">
                </td>

                <td>申请使用日期:</td>
                <td>
                    <input class="easyui-datebox" name="applyDate">
                </td>

                <td>申请使用时段:</td>
                <td><input class="easyui-combobox" name="timeQuantum" data-options="panelHeight:'auto',
                valueField: 'label',
		        textField: 'value',
                data: [{
                label: '1',
                value: '上午'
                },{
                label: '2',
                value: '下午'
                },{
                label: '3',
                value: '晚上']"></td>
            </tr>
        </table>
    </form>
<div id="landApply_buttons">
    <a class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-save'" data-cmd="save">提交</a>
    <a class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-cancel'" data-cmd="cancel">取消</a>
</div>

</body>
</html>
