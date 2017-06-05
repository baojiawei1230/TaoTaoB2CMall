$(function(){
	//选中当前收货人时,去掉其他收货人CSS样式
	$("#consignee-list li").click(function(){
		$(this).parent().find("div.consignee-item").removeClass("item-selected");
		$(this).find("div.consignee-item").addClass("item-selected");
		//将要提交到后台的值,填到orderForm的隐藏域中
		$("input[name='orderShipping.receiverName']").val($(this).find("div.user-name > div.fl > strong").text());
		$("input[name='orderShipping.receiverMobile']").val($(this).find("div.user-name > div.fr").text());
		$("input[name='orderShipping.receiverState']").val($(this).find("span:nth-child(1)").text());
		$("input[name='orderShipping.receiverCity']").val($(this).find("span:nth-child(2)").text());
		$("input[name='orderShipping.receiverDistrict']").val($(this).find("span:nth-child(3)").text());
		$("input[name='orderShipping.receiverAddress']").val($(this).find(" div.adr-m").text());
		
	});
	
});