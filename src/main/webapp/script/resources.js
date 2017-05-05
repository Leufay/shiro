var setting={
		//树对象
		zTreePlugin:"",
		view: {
			dblClickExpand: false,
			showLine: true,
			selectedMulti: false,
		},
		data: {
			simpleData: {
				enable:true,
				idKey: "id",		//设置节点标识为id
			},
			key:{
				name:"name",
				children:"children"
			}
		},
		initEvent:{
			//加载角色树,在页面加载时加载
			loadTree:function(){
				$.post("/learnShiro/resource/listModules.action",null,function(data){
					setting.zTreePlugin = $.fn.zTree.init($("#tree"),setting,data) ;
				});
			},
		}
} ;
$(function(){
	setting.initEvent.loadTree() ;
}) ;