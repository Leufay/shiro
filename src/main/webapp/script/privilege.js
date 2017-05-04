var setting={
		//树对象
		zTreePlugin:"",
		permId:"",
		view: {
			dblClickExpand: false,
			showLine: true,
			selectedMulti: false,
		},
		data: {
			simpleData: {
				enable:true,
				idKey: "url",
			},
			key:{
				name:"name"
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
					if($(this).text()=="分配资源"){
						$(this).unbind() ;
						$(this).bind("click", function(){
							setting.initEvent.cancelAllChecked() ;
							setting.permId = $(this).parent().siblings().eq(0).text() ;
							var permName = $(this).parent().siblings().eq(2).text() ;
							$("#permId").val(setting.permId) ;
							$("#permName").text(permName) ;
							setting.initEvent.reShowRole($(this).parent().next().text()) ;
						}) ;
					}
				}); 
			},
			//加载角色树,在页面加载时加载
			loadTree:function(){
				$.post("/learnShiro/resource/listModules.action",null,function(data){
					setting.zTreePlugin = $.fn.zTree.init($("#tree"),setting,data) ;
				});
			},
			//模态框提交按钮事件
			whenSubmit:function(){
				$("#submitRole").unbind();
				$("#submitRole").bind("click",function(){
					setting.zTreePlugin = $.fn.zTree.getZTreeObj("tree");
					var nodes = setting.zTreePlugin.getCheckedNodes(true);
					var modUrl = "";					//模块url 如system
					var menuUrl = "";					//菜单url
					var buttonUrl = "" ;				//按钮url
					var permId = $("#permId").val() ;
					var permUrls = new Array() ;		//声明一个数组用来装组合的url
					var index = 0 ;						//permUlr下标
					//进行url拼接,如system:user:add
					for(var i = 0 ; i<nodes.length;i++){
						
						var isRoot = nodes[i].getParentNode() ;
						if(isRoot==null){				//根节点
							
							modUrl =nodes[i].url+":" ;
						}else{
							if(nodes[i].isParent){		//二级子节点说明是菜单
								menuUrl = nodes[i].url+":" ;
							}else{						//底层节点是按钮
								buttonUrl = nodes[i].url ;
								//当遍历到底层节点时进行一次拼接
								permUrls[index++] = modUrl+menuUrl+buttonUrl ;
								//拼接完对每个url重置
							
							}
						}
					}
					$.ajax({
						url:"/learnShiro/perm/setResources.action",
						type:"post",
						data:{"permUrls":permUrls,"permId":permId},
						traditional:true,								//设置可以异步传递数组参数
						success:function(){
							$("#myModal").modal("hide") ;
							$("#myModal2").modal("show") ;
						}
					});
//					$.post("/learnShiro/perm/setResources.action",{"permUrls":permUrls,"permId":permId},function(data){
//						$("#myModal").modal("hide") ;
//						$("#myModal2").modal("show") ;
//					});
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