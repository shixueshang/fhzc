var setting = {
    view: {
        dblClickExpand: false
    },
    data: {
        simpleData: {
            enable: true
        }
    },
    callback: {
        beforeClick: beforeClick,
        onClick: onClick
    }
};


function beforeClick(treeId, treeNode) {
    //var check = (treeNode && !treeNode.isParent);
  //  return check;
    hideMenu();
}

function onClick(e, treeId, treeNode) {
    var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
        nodes = zTree.getSelectedNodes(),
        node_value = "",
        node_name = "";
    nodes.sort(function compare(a, b) {
        return a.id - b.id;
    });
    for (var i = 0, l = nodes.length; i < l; i++) {
        node_value += nodes[i].id + ",";
        node_name += nodes[i].name + ",";
    }
    if (node_value.length > 0){
        node_value = node_value.substring(0, node_value.length - 1);
        node_name = node_name.substring(0, node_name.length - 1);
    }
    $("#department").val(node_name);
    $("#department_value").val(node_value);
}

function showTreeData() {
    var dataObj = $("#department");
    var dataOffset = $("#department").offset();
    $("#treeContent").css({
        left: dataOffset.left + "px",
        top: dataOffset.top + dataObj.outerHeight() + "px"
    }).slideDown("fast");

    $("body").bind("mousedown", onBodyDown);
}
function hideMenu() {
    $("#treeContent").fadeOut("fast");
    $("body").unbind("mousedown", onBodyDown);
}
function onBodyDown(event) {
    if (!(event.target.id == "department" || event.target.id == "treeContent" || $(event.target).parents("#treeContent").length > 0)) {
        hideMenu();
    }
}

function setOrganValue(value, nodes){
    for(var i=0; i<nodes.length; i++){
        if(value == nodes[i].id){
            $("#department").val(nodes[i].name);
            $("#department_value").val(value);
        }
    }
}
