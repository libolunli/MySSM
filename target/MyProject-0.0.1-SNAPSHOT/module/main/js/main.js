var url;

$(document).ready(function() { 
	//任何需要执行的js特效
	mainlist();
	accordion();
});

/**
 * 主页网址链接
 * @returns
 */
function mainlist(){
	var result = callService("/user/geturl.do",null);
	sacodename2List(result.urlList,$("#maintable"),null);
}

function accordion(){
	$("#accordion").empty();
	var result = callService("/user/getTab.do",null);
	var tab = '';
	if(null != result || undefined != result){
		for(var i = 0 ; i < result.tabList.length ; i++){
			if(result.tabList[i].parent != '0'){
				tab += "<div title=\"文章管理\" data-options=\"selected:true,iconCls:'icon-wenzhangs'\" style=\"padding: 10px; height: 10px;\">"
			}
		}
	}
}

function addTab(url, text, iconCls) {
	var content = "<iframe frameborder=0 scrolling='auto' style='width:100%;height:100%' src='${pageContext.request.contextPath}"
			+ url + "'></iframe>";
	$("#tabs").tabs("add", {
		title : text,
		iconCls : iconCls,
		closable : true,
		content : content
	});
}
function openTab(text, url, iconCls) {
	if ($("#tabs").tabs("exists", text)) {
		$("#tabs").tabs("close", text);
		addTab(url, text, iconCls);
		$("#tabs").tabs("select", text);
	} else {
		addTab(url, text, iconCls);
	}
}
function logout() {
	$.messager
			.confirm(
					"系统提示",
					"您确定要退出系统吗",
					function(r) {
						if (r) {
							window.location.href = "${pageContext.request.contextPath}/user/logout.do";
						}
					});
}