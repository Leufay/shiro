var setting={
		//树对象
		zTreePlugin:"",
		userId:"",
		view: {
			dblClickExpand: false,
			showLine: true,
			selectedMulti: false,
		},
		data: {
			simpleData: {
				enable:true,
				idKey: "id",
				pIdKey: "pid",
				rootPId: "",
			},
			key:{
				name:"roleName"
			}
		},
		check:{
			enable:true,
			chkStyle:"checkbox"
		},
		initEvent:{
			//按钮初始化单击事件
			initClick:function(){
				$("a").each(function(){
					if($(this).text()=="分配角色"){
						$(this).unbind() ;
						$(this).bind("click", function(){
							setting.initEvent.cancelAllChecked() ;
							setting.userId = $(this).parent().siblings().eq(0).text() ;
							var userName = $(this).parent().siblings().eq(1).text() ;
							$("#userId").val(setting.userId) ;
							$("#userName").text(userName) ;
							setting.initEvent.reShowRole($(this).parent().next().text()) ;
						}) ;
					}
				}); 
			},
			//加载角色树,在页面加载时加载
			loadTree:function(){
				$.post("/learnShiro/role/listJson.action",null,function(data){
					setting.zTreePlugin = $.fn.zTree.init($("#tree"),setting,data) ;
				});
			},
			//模态框提交按钮事件
			whenSubmit:function(){
				$("#submitRole").unbind();
				$("#submitRole").bind("click",function(){
					setting.zTreePlugin = $.fn.zTree.getZTreeObj("tree");
					var nodes = setting.zTreePlugin.getCheckedNodes(true);
					var ids = "";
					var userId = $("#userId").val() ;
					for(var i = 0 ; i<nodes.length;i++){
						if(i==nodes.length-1){
							ids = ids+nodes[i].id ;
						}else{
							ids = ids+nodes[i].id+"," ;
						}
					}
					$.post("/learnShiro/user/setRoles.action",{ids:ids,userId:userId},function(data){
						$("#myModal").modal("hide") ;
						$("#myModal2").modal("show") ;
						//当前页面刷新
						history.go(0) ;
					});
				});
			},
			//回显角色
			reShowRole:function(ids){
				//回显checkbox
				var idsArray = ids.split(",") ;
				console.log(idsArray) ;
				for (var i = 0; i < idsArray.length; i++) {
					var treeObj = setting.zTreePlugin;
					var node = treeObj.getNodeByParam("id", idsArray[i], null);
					treeObj.checkNode(node, true, false); // 选中节点（回显）
				}
			},
			//取消全部勾选
			cancelAllChecked:function(){
				var nodes = setting.zTreePlugin.getCheckedNodes(true) ;
				for(var i = 0 ; i<nodes.length;i++){
					setting.zTreePlugin.checkNode(nodes[i],false,true) ;
				}
			}
		}
} ;
$(function(){
	setting.initEvent.loadTree() ;
	setting.initEvent.initClick() ;
	setting.initEvent.whenSubmit();
}) ;