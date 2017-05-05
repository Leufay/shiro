var setting={
		//动态改变下拉框的值
		initRadio:function(){
			$(":radio[name='sourceType']").unbind("change") ;
			$(":radio[name='sourceType']").bind("change",function(){
				var type = $("input:checked").val();
				console.log(type) ;
				if(!type=="module"){
					//获取对应资源列表
					$.post("/learnShiro/resources/getSourceList.action",{"sourceType":type},function(data){
						setting.changeSelect(data) ;
					}) ;
				}
			}) ;
		},
		changeSelect:function(data){
			$("#parentMenu").html();
		}
		
} ;
$(function(){
	setting.initRadio() ;
}) ;