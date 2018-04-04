$(function () {
    $("#menu").tree({
        url: '/menu/getRootMenu.do',
        //当前点击的节点对象
        onClick: function (node) {
            //判断标题选项卡是否存在,如果已经存在,就选中,否则添加一个选项卡
            if ($("#tabs").tabs("exists", node.text)) {
                $("#tabs").tabs("select", node.text);
            } else {
                $("#tabs").tabs("add", {
                    title: node.text,
                    selected: true,
                    closable:true,
                    content: "<iframe src='" + node.url + "'width='100%' height='99%' frameborder='0'>"
                })
            }
        }
    })
})