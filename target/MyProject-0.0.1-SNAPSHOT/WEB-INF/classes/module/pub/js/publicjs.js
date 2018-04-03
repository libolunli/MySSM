/**
 *公用js方法
 */
function callService(serviceUrl,json){
	var value = {};
	$.ajax({
        type: "GET",
        url: path+serviceUrl,
        data: json,
        dataType: "json",
        async:false,
        success: function(data){
        			if(data == undefined){
        				value = null;
        			}
        			value = data;
                 }
    });
	return value;
}

function sacodename2List(urlList,obj,option){
	obj.empty();
	var tab='';
	if(null != option){
		tab += "<div align=\"center\" style=\"padding-top: 20px;\">"+option+"</div>";
	}
	for(var i=0 ; i<urlList.length ; i++){
		tab += "<div align=\"center\" style=\"padding-top: 20px;\">";
		tab += "<a href=\""+urlList[i].codevalue+"\" target=\"_blank\" style=\"font-size: 20px;\">"+urlList[i].codekey+"</a>";
		tab += "</div>";
	}
	obj.append(tab);
}

function addTab(url, text, iconCls) {
	var content = "<iframe frameborder=0 scrolling='auto' style='width:100%;height:100%' src='${pageContext.request.contextPath}/views/"
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