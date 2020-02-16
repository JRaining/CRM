$(function () {
    $("#menuTree").tree({
        url: '/findMenu',

        onClick: function (node) {

            //在选项卡中是否已经有该节点的面板.
            if ($("#myTabs").tabs("exists", node.text)) {
                //选中面板
                $("#myTabs").tabs("select", node.text);
            } else {
                // 没有，新增
                $("#myTabs").tabs("add", {
                    title: node.text,
                    closable: true,
                    // href: node.attributes.url
                    content: "<iframe src='" + node.attributes + "' style='width:100%;height:100%' frameborder=0 scrolling='auto'></iframe>"
                });
            }
        }
    });
});