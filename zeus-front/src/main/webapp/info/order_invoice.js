function nextAvt() {
	var check_ok = false;
  $(".vat-step-1").each(function(){
    if(check_Invoice(this.name, this.value)) {
      check_ok = true;
    }else{
    	check_ok = false;
    }
  });
  if(check_ok) {
	$('#invoice-box-03 .steps .step2').removeClass('hide').siblings().addClass('hide');
    $('#invoice-box-03 .invoice-status .fore2').addClass('curr').siblings().removeClass('curr');
    $('#consignee_name').focus();
  }
}
function prev() {
  $('#invoice-box-03 .steps .step1').removeClass('hide').siblings().addClass('hide');
  $('#invoice-box-03 .invoice-status .fore1').addClass('curr').siblings().removeClass('curr');
}

seajs.use(['jdf/1.0.0/ui/switchable/1.0.0/switchable'],function(switchable){
	$(function(){
		$(document).scrollTop(0);
	});
	$('#invoice-tab').switchable({
		navItem:'tab-nav-item',
		navSelectedClass:'tab-item-selected',
		mainClass:'tab-con',
		event:'click',
		delay :'1',
		callback:function(i){
			var len =$('#invoice-tit-list').find('.invoice-item').length;
			if(i==0){
				$('#fapiao').show();
			}
			if(len<11){
				$('#add-invoice').show();
			}else{
				$('#add-invoice').hide();
			}
			var _obj = this.main[i];
			switch(i){
				case 0:
					var actionUrl = OrderAppConfig.DynamicDomain + "/invoice/getGenenalInvoice.action";
					var invokeInvoiceBasicService = $("#invokeInvoiceBasicService").val();
					var param = "invokeInvoiceBasicService=" + invokeInvoiceBasicService;
					param = param += "&invoiceParam.usualInvoiceId=" + 0;
					param = parent.addFlowTypeParam(param);
					var len =$('#invoice-tit-list').find('.invoice-item').length;
					$('#fapiao').show();
					if(len<11){
						$('#add-invoice').show();
						
					}else{
						$('#add-invoice').hide();
					}			
					jQuery.ajax({
						type : "POST",
						url : actionUrl,
						data : param,
						cache : false,
						asyc: false,
						success : function(dataResult, textStatus) {
							// 没有登录跳登录
							if (isUserNotLogin(dataResult)) {
								goToLogin();
								return;
							} // 服务器返回异常处理,如果有消息div则放入,没有则弹出
							if (isHasMessage(dataResult)) {
								
								$(_obj).html(dataResult);
							}
							// 成功后如果有divID直接放入div，没有则返回结果
							else {
								$(_obj).html(dataResult).show().siblings('.tab-con').hide();
								if (parent.isGiftBuy()) {
									$("#giftInvoicePart").removeClass("hide");
							    }
							}
							$('.invoice-list .invoice-item').bind('click',function(){
								$(this).addClass('invoice-item-selected').siblings().removeClass('invoice-item-selected');
							});
						},
						error : function(XMLHttpResponse) {
						}
					});
					break;
				case 1:
					$('#fapiao').hide();
					$('#add-invoice').hide();
					var actionUrl = OrderAppConfig.DynamicDomain + "/invoice/getElectroInvoice.action";
					var invokeInvoiceBasicService = $("#invokeInvoiceBasicService").val();
					var param = "invokeInvoiceBasicService=" + invokeInvoiceBasicService;
					param = parent.addFlowTypeParam(param);
					jQuery.ajax({
						type : "POST",
						url : actionUrl,
						data : param,
						cache : false,
						asyc: false,
						success : function(dataResult, textStatus) {				
							// 没有登录跳登录
							if (isUserNotLogin(dataResult)) {
								goToLogin();
								return;
							} // 服务器返回异常处理,如果有消息div则放入,没有则弹出
							if (isHasMessage(dataResult)) {
								
								$(_obj).html(dataResult);
							}
							// 成功后如果有divID直接放入div，没有则返回结果
							else {
								$(_obj).html(dataResult).show().siblings('.tab-con').hide();
							}
						},
						error : function(XMLHttpResponse) {
						}
					});
					break;
				case 2:
					$('#fapiao').hide();
					var actionUrl = OrderAppConfig.DynamicDomain + "/invoice/getVatInvoice.action";
					var invokeInvoiceBasicService = $("#invokeInvoiceBasicService").val();
					var param = "invokeInvoiceBasicService=" + invokeInvoiceBasicService;
					param = parent.addFlowTypeParam(param);
					jQuery.ajax({
						type : "POST",
						url : actionUrl,
						data : param,
						cache : false,
						asyc: false,
						success : function(dataResult, textStatus) {
							// 没有登录跳登录
							if (isUserNotLogin(dataResult)) {
								goToLogin();
								return;
							} // 服务器返回异常处理,如果有消息div则放入,没有则弹出
							if (isHasMessage(dataResult)) {
								
								$(_obj).html(dataResult);
							}
							// 成功后如果有divID直接放入div，没有则返回结果
							else 
							{
								$(_obj).html(dataResult).show().siblings('.tab-con').hide();
								$("#vat_companyName").focus();
								
							}
						},
						error : function(XMLHttpResponse) {
							
						}
					});
					break;
			}
		}
	});
});
	
$('#invoice-tit-list')
.delegate('.invoice-item','click',function(){//点击切换选中状态
	$(this).addClass('invoice-item-selected').siblings().removeClass('invoice-item-selected');
	var _$save = $(this).attr('id');
	if(_$save){
		return;
	}else{
		var len = $('#invoice-tit-list').find('.invoice-item').length;
		if (len < 11) {
			if($('#add-invoice').is(":hidden")){
				$('#save-invoice').hide();
				$('#add-invoice').show();
			}
		} else {
			$('#add-invoice').hide();
		}
	}
})
.delegate('.invoice-item','mouseover',function(){
	$(this).addClass('hover');
})
.delegate('.invoice-item','mouseout',function(){
	$(this).removeClass('hover');
})
.delegate('.btns','mouseover',function(){
	$(this).parents('.invoice-item').addClass('btn-hover');
})
.delegate('.btns','mouseout',function(){
	$(this).parents('.invoice-item').removeClass('btn-hover');
})
.delegate('.invoice-item .itxt','blur',function(){//失去焦点回复初始值及状态
	var _r = $(this).attr('data-r');
	var _val = $(this).val();
	var _isEmpty = isEmpty(_val);
	if(_r){//编辑的输入框
		if(_isEmpty){
			$(this).val(_r);
		}
		if($(this).parents('.invoice-item').hasClass('btn-hover')) return;//点击本身的按钮失去焦点，返回
		$('.invoice-item-selected .btns .edit-tit').removeClass('hide').next().addClass('hide');
		$(this).attr("readonly",true);
	}
})
.delegate('.edit-tit','click',function(){// 点击编辑按钮 当前按钮隐藏 输入框变成可编辑状态	
	$(this).addClass('hide').next().removeClass('hide');	
	$(this).parent().prev().find('.fore2 .itxt').removeAttr('readonly').focus();	
})
.delegate('.update-tit','click',function(){
	update_Invoice();
})
.delegate('.save-tit','click',function(){
	save_Invoice();
});

function add_save(){
	$('#invoice-tit-list .invoice-item-selected').removeClass('invoice-item-selected');
	$('#save-invoice').addClass('invoice-item-selected').removeClass('hide').show().find('input').removeAttr('readonly').val('').focus();
	$('#invoice-tit-list').scrollTop($('#invoice-tit-list')[0].scrollHeight);
	$('#add-invoice').hide();
	
}

//编辑发票内容
$('#invoice-tit-list_lest .edit-tit').click(function(){
	$('#invoice-tit-list_lest .edit-tit').parents('.invoice-item').find('.fore2').show().siblings().hide();
});

function quxiao(){
	window.location.href = "about:blank";
	window.parent.jQuery.closeDialog();
}
/**
 * 删除常用发票信息
 * 
 * @param id
 */

function isHasMessage(data) {
	if (data.errorMessage) {
		return true;
	} else {
		try {
			if (data != null && data.indexOf("\"errorMessage\":") > -1) {
				var mesageObject = eval("(" + data + ")");
				if (mesageObject != null && mesageObject.errorMessage != null) {
					return true;
				}
			}

		} catch (e) {
		}
	}
	return false;
}


/***
 * 更新常用发票列表
 *
 */
function update_Invoice(){
	var invokeInvoiceBasicService = $("#invokeInvoiceBasicService").val();
	var invoice_hasBook = $("#hasBook").val();
	var invoice_hasCommon = $("#hasCommon").val();
	var invoice_type = $('.tab-item-selected').val();
	var invoice_companyName = $('.invoice-item-selected').children('#invoice-1').children('.fore2').children('input').val();	
	if (invoice_type == 1) {
		if (invoice_companyName == undefined) {
			invoice_companyName = $("#title").val();
			if (isEmpty(invoice_companyName)) {
				alert('不能为空');
				return;
			}
		}
		if(isEmpty(invoice_companyName)){
			alert('请填写正确的单位名称');
		}
	}
	var invoice_title = $('.invoice-item-selected').children('#invoice-1').children('.hide').children('input').val();
	if (isEmpty(invoice_title)) {
		invoice_title = 5;
	}
	var invoice_common_content = "";
	var invoice_book_content = "";
	// 赠票信息
	var vat_companyName = "";
	var vat_code = "";
	var vat_address = "";
	var vat_phone = "";
	var vat_bankName = "";
	var vat_bankAccount = "";
	// 增票地址
	var consigneeName = "";
	var consigneeAddress = "";
	var consigneePhone = "";
	var consignee_provinceId = 0;
	var consignee_province = "";
	var consignee_cityId = 0;
	var consignee_city = "";
	var consignee_countyId = 0;
	var consignee_county = "";
	var consignee_townId = 0;
	var consignee_town = "";
	var sendSeparate = false;
	// 常用发票id
	var usualInvoiceId = 0;
	// 电子发票	
	var electro_invoiceTitle = $('.invoice-item-selected').children('#invoice-2').children('.hide').children('input').val();	
	var electro_phone = $("#e_consignee_mobile").val();
	var electro_email = $("#e_consignee_email").val();
	var electro_companyName = $('.invoice-item').children('#invoice-2').children('.selec').children('input').val();
	if (invoice_type == 1) { // 普票
		if ($("li[name='normal-normalContent']").html() != null) {
			invoice_common_content = $("li:[name='normal-normalContent'].invoice-item-selected").val();
		} else {
			invoice_common_content = "";
		}
		if ($("li[name='normal-bookContent']").html() != null) {
			invoice_book_content = $("li:[name='normal-bookContent'].invoice-item-selected").val();
		} else {
			invoice_book_content = "";
		}
		// 实体礼品卡
		/*if (isLipinkaPhysical()) {
			if ($("#normal-invoice-content-lipinkaPhysical").html() != null) {
				invoice_common_content = $("#normal-invoice-content-lipinkaPhysical").val();
			} else {
				invoice_common_content = "";
			}
		}*/
	} else if (invoice_type == 2) { // 赠票
		if ($("li:[name='vat-normalContent']").html() != null) {
			invoice_common_content = $("li:[name='vat-normalContent'].invoice-item-selected").val();
		} else {
			invoice_common_content = "";
		}
		if ($("li:[name='vat-bookContent']").html() != null) {
			invoice_book_content = $("li:[name='vat-bookContent'].invoice-item-selected").val();
		} else {
			invoice_book_content = "";
		}
		// 获取增值税发票
		vat_companyName = $("#vat_companyName").val();
		vat_code        = $("#vat_code").val();
		vat_address     = $("#vat_address").val();
		vat_phone       = $("#vat_phone").val();
		vat_bankName    = $("#vat_bankName").val();
		vat_bankAccount = $("#vat_bankAccount").val();
	} else {
		// 电子发票内容
		if ($("li:[name='electro-bookContent']").html() != null) {
			invoice_book_content = $("li:[name='electro-bookContent'].invoice-item-selected").val();
		} else {
			invoice_book_content = "";
		}
		if ($("li:[name='electro-normalContent']").html() != null) {
			invoice_common_content = $("li:[name='electro-normalContent'].invoice-item-selected").val();
		} else {
			invoice_common_content = "";
		}
	}
	if (!$("#invoiceConsignee").is(":hidden")) {
		if (!$("#invoicesSendTypeDiv").is(":hidden")) {
			if ($('input:radio[name="invoiceSendType"]:checked').val() == "3") {
				sendSeparate = true;
			}
		}
		// 收票人地址信息
		consigneeName = $("#consignee_name").val();
		consigneeAddress = $("#consignee_address").val();
		consigneePhone = $("#consignee_mobile").val();
		consignee_provinceId = parseInt($("#consignee_province").find("option:selected").val());
		consignee_province = $("#consignee_province").find("option:selected").text().replace("*", "");
		consignee_cityId = parseInt($("#consignee_city").find("option:selected").val());
		consignee_city = $("#consignee_city").find("option:selected").text().replace("*", "");
		consignee_countyId = $("#consignee_county").find("option:selected").val();
		consignee_county = $("#consignee_county").find("option:selected").text().replace("*", "");
		consignee_townId = $("#consignee_town").find("option:selected").val();
		consignee_town = $("#consignee_town").find("option:selected").text().replace("*", "");
		consignee_countyId = (consignee_county == '' || consignee_county == undefined) ? 0 : parseInt(consignee_countyId);
		consignee_townId = (consignee_townId == '' || consignee_townId == undefined) ? 0 : parseInt(consignee_townId);
	}

	// 发票类型验证
	if (isEmpty(invoice_type)) {
		alert("请选择发票类型！");
		return;
	}
	// 普通发票验证
	if (invoice_type == 1) {
		// 发票抬头验证
		if (isEmpty(invoice_title)) {
			alert("请选择发票抬头！");
			return;
		} else {
			// 抬头如果是单位验证
			if (invoice_title == 5) {
				if (isEmpty(invoice_companyName)) {
					alert("请输入单位名称！");
					return;
				} else {
					if (checkLength(invoice_companyName) < 2) {
						alert("请填写完整单位名称！");
						return;
					}
					if (100 < checkLength(invoice_companyName)) {
						alert("单位名称过长,请重新输入！");
						return;
					}
					if (!is_forbid(invoice_companyName)) {
						alert("单位名称含有非法字符！");
						return;
					}
				}
			}
		}
		// 礼品购 发票单独邮寄 校验
		if (sendSeparate) {
			var checkInvoice = true;
			// 验证收货人信息是否正确
			if (!check_InvoiceConsignee("name_div")) {
				checkInvoice = false;
			}
			// 验证手机号码是否正确
			if (!check_InvoiceConsignee("call_phone_div")) {
				checkInvoice = false;
			}
			// 验证收货人地址是否正确
			if (!check_InvoiceConsignee("address_div")) {
				checkInvoice = false;
			}
			// 验证地区是否正确
			if (!check_InvoiceConsignee("area_div")) {
				checkInvoice = false;
			}
			if (!checkInvoice) {
				return;
			}
		}
	} else if (invoice_type == 2) { // 增值税发票验证
		var checkAddValed = true;
		// 增值税单位名称验证
		if (!check_Invoice("vat_companyName", vat_companyName)) {
			checkAddValed = false;
		}
		// 增值税纳税人识别号验证
		if (!check_Invoice("vat_code", vat_code)) {
			checkAddValed = false;
		}
		// 增值税注册注册地址验证
		if (!check_Invoice("vat_address", vat_address)) {
			checkAddValed = false;
		}
		// 增值税注册电话验证
		if (!check_Invoice("vat_phone", vat_phone)) {
			checkAddValed = false;
		}
		// 增值税开户银行验证
		if (!check_Invoice("vat_bankName", vat_bankName)) {
			checkAddValed = false;
		}
		// 增值税开户银行账户验证
		if (!check_Invoice("vat_bankAccount", vat_bankAccount)) {
			checkAddValed = false;
		}
		// 验证收货人信息是否正确

		if (!check_InvoiceConsignee("name_div")) {
			checkAddValed = false;
		}
		// 验证手机号码是否正确
		if (!check_InvoiceConsignee("call_phone_div")) {
			checkAddValed = false;
		}
		// 验证收货人地址是否正确
		if (!check_InvoiceConsignee("address_div")) {
			checkAddValed = false;
		}
		// 验证地区是否正确
		var provinceId = $("#consignee_province").find("option:selected").val();
		var cityId = $("#consignee_city").find("option:selected").val();
		var countyId = $("#consignee_county").find("option:selected").val();
		var townId = $("#consignee_town").find("option:selected").val();
		if (isEmpty(provinceId) || isEmpty(cityId) || isEmpty(countyId) || ($("#span_town").html() != "" && !$("#span_town").is(":hidden") && isEmpty(townId))) {
			checkAddValed = false;
			$("#area_div_error").html("请填完整地区信息");
			$("#area_div_error").addClass("error-msg");
		}
		if (!checkAddValed) {
			return;
		}
	} else if (invoice_type == 3) { // 电子发票
		if (!check_electroInvoicePhone()) {
			return;
		}
		if (!check_electroInvoiceEmail()) {
			return;
		}
		// 电子发票抬头如果是单位验证单位名称
		if (electro_invoiceTitle == 5) {
			if (isEmpty(electro_companyName)) {
				alert("请输入单位名称！");
				return;
			} else {
				if (checkLength(electro_companyName) < 2) {
					alert("请填写完整单位名称！");
					return;
				}
				if (100 < checkLength(electro_companyName)) {
					alert("单位名称过长,请重新输入！");
					return;
				}
				if (!is_forbid(electro_companyName)) {
					alert("单位名称含有非法字符！");
					return;
				}
			}
		}
	}
	usualInvoiceId = $('.invoice-item-selected').children('#invoice-1').children('.fore2').attr("value");
	var param = "invoiceParam.selectedInvoiceType=" + invoice_type +
				"&invoiceParam.companyName=" + invoice_companyName +
				"&invoiceParam.selectInvoiceTitle=" + invoice_title + 
				"&invoiceParam.selectBookInvoiceContent=" + invoice_book_content +
				"&invoiceParam.selectNormalInvoiceContent=" + invoice_common_content + 
				"&invoiceParam.vatCompanyName=" + vat_companyName + 
				"&invoiceParam.code=" + vat_code + 
				"&invoiceParam.regAddr=" + vat_address + 
				"&invoiceParam.regPhone=" + vat_phone + 
				"&invoiceParam.regBank=" + vat_bankName + 
				"&invoiceParam.regBankAccount=" + vat_bankAccount + 
				"&invoiceParam.hasCommon=" + invoice_hasCommon + 
				"&invoiceParam.hasBook=" + invoice_hasBook + 
				"&invoiceParam.consigneeName=" + consigneeName + 
				"&invoiceParam.consigneePhone=" + consigneePhone + 
				"&invoiceParam.consigneeAddress=" + consigneeAddress + 
				"&invoiceParam.consigneeProvince=" + consignee_province + 
				"&invoiceParam.consigneeProvinceId=" + consignee_provinceId + 
				"&invoiceParam.consigneeCity=" + consignee_city + 
				"&invoiceParam.consigneeCityId=" + consignee_cityId + 
				"&invoiceParam.consigneeCounty=" + consignee_county + 
				"&invoiceParam.consigneeCountyId=" + consignee_countyId + 
				"&invoiceParam.consigneeTown=" + consignee_town + 
				"&invoiceParam.consigneeTownId=" + consignee_townId + 
				"&invoiceParam.sendSeparate=" + sendSeparate + 
				"&invoiceParam.usualInvoiceId=" + usualInvoiceId +
				"&invoiceParam.selectElectroTitle=" + electro_invoiceTitle + 
				"&invoiceParam.electroCompanyName=" + electro_companyName + 
				"&invoiceParam.electroInvoiceEmail=" + electro_email + 
				"&invoiceParam.electroInvoicePhone=" + electro_phone;
	param = param + "&invokeInvoiceBasicService=" + invokeInvoiceBasicService;
	param = parent.addFlowTypeParam(param);	
	jQuery.ajax({
		type: "POST",
		dataType: "text",
		url: OrderAppConfig.DynamicDomain + "/invoice/saveInvoice.action",
		data: param,
		cache: false,
		success: function(dataResult, textStatus) {
			// 没有登录跳登录
			if (isUserNotLogin(dataResult)) {
				goToLogin();
				return;
			}
			// 服务器返回异常处理,如果有消息div则放入,没有则弹出
			if (isHasMessage(dataResult)) {
				var message = getMessage(dataResult);
				alert(message);			
			}
			// 成功后如果有divID直接放入div，没有则返回结果
			else {
				$("#part-inv", parent.document).html(dataResult);
				$('.invoice-item-selected .btns .update-tit').addClass('hide').prev().removeClass('hide');
				$('.itxt').attr("readonly",true);
				$('.itxt04').removeAttr('readonly');
				if(invoice_common_content > 0 || invoice_book_content > 0){
					$("#sopNotPutInvoice",parent.document).val("false");
				}else{
					$("#sopNotPutInvoice",parent.document).val("true");
				}
				var len = $('#invoice-tit-list').find('.invoice-item').length;
				if (len < 11) {
					$('.add-invoice-btn').removeClass('hide');
				} else {
					$('.add-invoice-btn').addClass('hide');
				}
			}
		},
		error: function(XMLHttpResponse) {}
	});
}
/**
 * 保存发票内容
 */
/*function save_Invoice(isGeneral) {
	
	var generalTag = isGeneral?"generalInvoice_":"";
	// 发票类型和内容
	var invoice_hasBook = $("#hasBook").val();
	var invoice_hasCommon = $("#hasCommon").val();
	var invoice_type = $('.tab-item-selected').val();
	var invoice_companyName = $('.invoice-item-selected').children('#invoice-1').children('.fore2').children('input').val();
	if (invoice_type == 1) {
		if (invoice_companyName == undefined) {
			invoice_companyName = $(".itxt04").val();
			if (isEmpty(invoice_companyName)) {
				alert('输入不能为空');
				return;
			}
		}
		if(isEmpty(invoice_companyName)){
			alert('请填写正确的单位名称');
		}
	}
	var invoice_title = $('.invoice-item-selected').children('#invoice-1').children('.hide').children('input').val();
	if (isEmpty(invoice_title)) {
		invoice_title = 5;
	}
	var invoice_common_content = "";
	var invoice_book_content = "";
	// 赠票信息
	var vat_companyName = "";
	var vat_code = "";
	var vat_address = "";
	var vat_phone = "";
	var vat_bankName = "";
	var vat_bankAccount = "";
	// 增票地址
	var consigneeName = "";
	var consigneeAddress = "";
	var consigneePhone = "";
	var consignee_provinceId = 0;
	var consignee_province = "";
	var consignee_cityId = 0;
	var consignee_city = "";
	var consignee_countyId = 0;
	var consignee_county = "";
	var consignee_townId = 0;
	var consignee_town = "";
	var sendSeparate = false;
	// 常用发票id
	var usualInvoiceId = 0;
	// 电子发票	
	var electro_invoiceTitle = $('.invoice-item-selected').children('#invoice-2').children('.hide').children('input').val();	
	var electro_phone = $("#e_consignee_mobile").val();
	var electro_email = $("#e_consignee_email").val();
	var electro_companyName = $('.invoice-item').children('#invoice-2').children('.selec').children('input').val();
	var invoice_ceshi1 = $("#invoice_ceshi1").val(); 
	if (invoice_type == 1) { // 普票
		if ($("li[name='normal-normalContent']").html() != null) {
			invoice_common_content = $("li:[name='normal-normalContent'].invoice-item-selected").val();
		} else {
			invoice_common_content = "";
		}
		if ($("li[name='normal-bookContent']").html() != null) {
			invoice_book_content = $("li:[name='normal-bookContent'].invoice-item-selected").val();
		} else {
			invoice_book_content = "";
		}
		// 实体礼品卡
		if (isLipinkaPhysical()) {
			if ($("#normal-invoice-content-lipinkaPhysical").html() != null) {
				invoice_common_content = $("#normal-invoice-content-lipinkaPhysical").val();
			} else {
				invoice_common_content = "";
			}
		}
	} else if (invoice_type == 2) { // 赠票
		if ($("li:[name='vat-normalContent']").html() != null) {
			invoice_common_content = $("li:[name='vat-normalContent'].invoice-item-selected").val();
		} else {
			invoice_common_content = "";
		}
		if ($("li:[name='vat-bookContent']").html() != null) {
			invoice_book_content = $("li:[name='vat-bookContent'].invoice-item-selected").val();
		} else {
			invoice_book_content = "";
		}
		// 获取增值税发票
		vat_companyName = $("#vat_companyName").val();
		vat_code        = $("#vat_code").val();
		vat_address     = $("#vat_address").val();
		vat_phone       = $("#vat_phone").val();
		vat_bankName    = $("#vat_bankName").val();
		vat_bankAccount = $("#vat_bankAccount").val();
	} else {
		// 电子发票内容
		if ($("li:[name='electro-bookContent']").html() != null) {
			invoice_book_content = $("li:[name='electro-bookContent'].invoice-item-selected").val();
		} else {
			invoice_book_content = "";
		}
		if ($("li:[name='electro-normalContent']").html() != null) {
			invoice_common_content = $("li:[name='electro-normalContent'].invoice-item-selected").val();
		} else {
			invoice_common_content = "";
		}
	}
	if (!$("#invoiceConsignee").is(":hidden")) {
		if (!$("#invoicesSendTypeDiv").is(":hidden")) {
			if ($('input:radio[name="invoiceSendType"]:checked').val() == "3") {
				sendSeparate = true;
			}
		}
		// 收票人地址信息
		consigneeName = $("#"+generalTag+"consignee_name").val();
		consigneeAddress = $("#"+generalTag+"consignee_address").val();
		consigneePhone = $("#"+generalTag+"consignee_mobile").val();
		
		consignee_provinceId = parseInt($("#consignee_province").find("option:selected").val());
		consignee_province = $("#consignee_province").find("option:selected").text().replace("*", "");
		consignee_cityId = parseInt($("#consignee_city").find("option:selected").val());
		consignee_city = $("#consignee_city").find("option:selected").text().replace("*", "");
		consignee_countyId = $("#consignee_county").find("option:selected").val();
		consignee_county = $("#consignee_county").find("option:selected").text().replace("*", "");
		consignee_townId = $("#consignee_town").find("option:selected").val();
		consignee_town = $("#consignee_town").find("option:selected").text().replace("*", "");
		consignee_countyId = (consignee_county == '' || consignee_county == undefined) ? 0 : parseInt(consignee_countyId);
		consignee_townId = (consignee_townId == '' || consignee_townId == undefined) ? 0 : parseInt(consignee_townId);
	}

	// 发票类型验证
	if (isEmpty(invoice_type)) {
		alert("请选择发票类型！");
		return;
	}
	// 普通发票验证
	if (invoice_type == 1) {
		// 发票抬头验证
		if (isEmpty(invoice_title)) {
			alert("请选择发票抬头！");
			return;
		} else {
			// 抬头如果是单位验证
			if (invoice_title == 5) {
				if (isEmpty(invoice_companyName)) {
					alert("请输入单位名称！");
					return;
				} else {
					if (checkLength(invoice_companyName) < 2) {
						alert("请填写完整单位名称！");
						return;
					}
					if (100 < checkLength(invoice_companyName)) {
						alert("单位名称过长,请重新输入！");
						return;
					}
					if (!is_forbid(invoice_companyName)) {
						alert("单位名称含有非法字符！");
						return;
					}
				}
			}
		}
		// 礼品购 发票单独邮寄 校验
		if (sendSeparate) {
			var checkInvoice = true;
			// 验证收货人信息是否正确
			if (!check_InvoiceConsignee(generalTag+"name_div",true)) {
				checkInvoice = false;
			}
			// 验证手机号码是否正确
			if (!check_InvoiceConsignee(generalTag+"call_phone_div",true)) {
				checkInvoice = false;
			}
			// 验证收货人地址是否正确
			if (!check_InvoiceConsignee(generalTag+"address_div",true)) {
				checkInvoice = false;
			}
			// 验证地区是否正确
			if (!check_InvoiceConsignee(generalTag+"area_div",true)) {
				checkInvoice = false;
			}
			if (!checkInvoice) {
				return;
			}
		}
	} else if (invoice_type == 2) { // 增值税发票验证
		var checkAddValed = true;
		// 增值税单位名称验证
		if (!check_Invoice("vat_companyName", vat_companyName)) {
			checkAddValed = false;
		}
		// 增值税纳税人识别号验证
		if (!check_Invoice("vat_code", vat_code)) {
			checkAddValed = false;
		}
		// 增值税注册注册地址验证
		if (!check_Invoice("vat_address", vat_address)) {
			checkAddValed = false;
		}
		// 增值税注册电话验证
		if (!check_Invoice("vat_phone", vat_phone)) {
			checkAddValed = false;
		}
		// 增值税开户银行验证
		if (!check_Invoice("vat_bankName", vat_bankName)) {
			checkAddValed = false;
		}
		// 增值税开户银行账户验证
		if (!check_Invoice("vat_bankAccount", vat_bankAccount)) {
			checkAddValed = false;
		}
		// 验证收货人信息是否正确

		if (!check_InvoiceConsignee("name_div")) {
			checkAddValed = false;
		}
		// 验证手机号码是否正确
		if (!check_InvoiceConsignee("call_phone_div")) {
			checkAddValed = false;
		}
		// 验证收货人地址是否正确
		if (!check_InvoiceConsignee("address_div")) {
			checkAddValed = false;
		}
		// 验证地区是否正确
		var provinceId = $("#consignee_province").find("option:selected").val();
		var cityId = $("#consignee_city").find("option:selected").val();
		var countyId = $("#consignee_county").find("option:selected").val();
		var townId = $("#consignee_town").find("option:selected").val();
		if (isEmpty(provinceId) || isEmpty(cityId) || isEmpty(countyId) || ($("#span_town").html() != "" && !$("#span_town").is(":hidden") && isEmpty(townId))) {
			checkAddValed = false;
			$("#area_div_error").html("请填完整地区信息");
			$("#area_div_error").addClass("error-msg");
		}
		if (!checkAddValed) {
			return;
		}
	} else if (invoice_type == 3) { // 电子发票
		if (!check_electroInvoicePhone()) {
			return;
		}
		if (!check_electroInvoiceEmail()) {
			return;
		}
		// 电子发票抬头如果是单位验证单位名称
		if (electro_invoiceTitle == 5) {
			if (isEmpty(electro_companyName)) {
				alert("请输入单位名称！");
				return;
			} else {
				if (checkLength(electro_companyName) < 2) {
					alert("请填写完整单位名称！");
					return;
				}
				if (100 < checkLength(electro_companyName)) {
					alert("单位名称过长,请重新输入！");
					return;
				}
				if (!is_forbid(electro_companyName)) {
					alert("单位名称含有非法字符！");
					return;
				}
			}
		}
	}
	var isUseNewInvoice = $('.invoice-item-selected').children('.add-invoice-tit').children('input').val();	
	var invokeInvoiceBasicService = $("#invokeInvoiceBasicService").val();
	if (isUseNewInvoice!='' && isUseNewInvoice!=undefined) {
		if(isEmpty(isUseNewInvoice)){
			return;
		}
		if (invoice_type == 1) {
			var param = "invoiceParam.selectInvoiceTitle=" + invoice_title + "&invoiceParam.companyName=" + isUseNewInvoice;
			param = param + "&invokeInvoiceBasicService=" + invokeInvoiceBasicService;
			param = parent.addFlowTypeParam(param);
			jQuery.ajax({
				type: "POST",
				dataType: "text",
				url: OrderAppConfig.DynamicDomain + "/invoice/addInvoiceToUsual.action",
				data: param,
				cache: false,
				success: function(dataResult, textStatus) {
					usualInvoiceId = 0;
					if (dataResult != null && dataResult != "") {
						usualInvoiceId = parseInt(dataResult);	
					}
					var param = "invoiceParam.selectedInvoiceType=" + invoice_type + 
								"&invoiceParam.companyName=" + isUseNewInvoice + 
								"&invoiceParam.selectInvoiceTitle=" + invoice_title + 
								"&invoiceParam.selectBookInvoiceContent=" + invoice_book_content + 
								"&invoiceParam.selectNormalInvoiceContent=" + invoice_common_content + 
								"&invoiceParam.vatCompanyName=" + vat_companyName + 
								"&invoiceParam.code=" + vat_code + 
								"&invoiceParam.regAddr=" + vat_address + 
								"&invoiceParam.regPhone=" + vat_phone + 
								"&invoiceParam.regBank=" + vat_bankName + 
								"&invoiceParam.regBankAccount=" + vat_bankAccount +
								"&invoiceParam.hasCommon=" + invoice_hasCommon + 
								"&invoiceParam.hasBook=" + invoice_hasBook + 
								"&invoiceParam.consigneeName=" + consigneeName + 
								"&invoiceParam.consigneePhone=" + consigneePhone + 
								"&invoiceParam.consigneeAddress=" + consigneeAddress + 
								"&invoiceParam.consigneeProvince=" + consignee_province + 
								"&invoiceParam.consigneeProvinceId=" + consignee_provinceId + 
								"&invoiceParam.consigneeCity=" + consignee_city + 
								"&invoiceParam.consigneeCityId=" + consignee_cityId + 
								"&invoiceParam.consigneeCounty=" + consignee_county + 
								"&invoiceParam.consigneeCountyId=" + consignee_countyId + 
								"&invoiceParam.consigneeTown=" + consignee_town + 
								"&invoiceParam.consigneeTownId=" + consignee_townId + 
								"&invoiceParam.sendSeparate=" + sendSeparate + 
								"&invoiceParam.usualInvoiceId=" + usualInvoiceId + 
								"&invoiceParam.selectElectroTitle=" + electro_invoiceTitle + 
								"&invoiceParam.electroCompanyName=" + electro_companyName + 
								"&invoiceParam.electroInvoiceEmail=" + electro_email + 
								"&invoiceParam.electroInvoicePhone=" + electro_phone;
					param = param + "&invokeInvoiceBasicService=" + invokeInvoiceBasicService;
					param = parent.addFlowTypeParam(param);
					jQuery.ajax({
						type: "POST",
						dataType: "text",
						url: OrderAppConfig.DynamicDomain + "/invoice/saveInvoice.action",
						data: param,
						cache: false,
						success: function(dataResult, textStatus) {
							// 没有登录跳登录
							if (isUserNotLogin(dataResult)) {
								goToLogin();
								return;
							}
							// 服务器返回异常处理,如果有消息div则放入,没有则弹出
							if (isHasMessage(dataResult)) {
								var message = getMessage(dataResult);
								alert(message);
								
							}
							// 成功后如果有divID直接放入div，没有则返回结果
							else {
								$('#save-invoice').hide();
								$('<div date-fid="fid'+usualInvoiceId+'" class="invoice-item invoice-item-selected">\
										<div id="invoice-1" style="cursor:pointer">\
										<span class="hide"><input type="hidden" value="5"></span>\
										<span class="fore2" id="invoice-r1-'+usualInvoiceId+'" name="usualInvoiceList" value="'+usualInvoiceId+'"><input type="text" class="itxt" data-r= "'+ isUseNewInvoice +'" value="' + isUseNewInvoice + '"></span>\
										</div>\
										<div class="btns">\
										<a href="javascript:void(0);" class="ftx-05 edit-tit" >编辑</a>\
										<a href="javascript:void(0);" class="ftx-05 hide update-tit">保存</a>\
											<a href="#none" class="ftx-05 ml10 del-tit" onclick="parent.delete_Invoice(' + usualInvoiceId + ')">删除</a>\
										</div>\
										<b></b>\
									</div>').insertAfter('#invoice-tit-list .invoice-item:first')
									.siblings('.invoice-item-selected').removeClass('invoice-item-selected');
								$('.itxt').attr("readonly",true);
								$("#part-inv", parent.document).html(dataResult);
								if(invoice_common_content > 0 || invoice_book_content > 0){
									$("#sopNotPutInvoice",parent.document).val("false");
								}else{
									$("#sopNotPutInvoice",parent.document).val("true");
								}
								var len = $('#invoice-tit-list').find('.invoice-item').length;
								if (len < 11) {
									$('#add-invoice').show();
								} else {
									$('#add-invoice').hide();
								}
							}
						},
						error: function(XMLHttpResponse) {	}
					});
				},
				error: function(XMLHttpResponse) {}
			});
			return;
		}
	} else {
		usualInvoiceId = $('.invoice-item-selected').children('#invoice-1').children('.fore2').attr("value");
	}
	if (invoice_type == 2 || invoice_type == 3 || usualInvoiceId == undefined) {
		usualInvoiceId = 0;
	}
	var param = "invoiceParam.selectedInvoiceType=" + invoice_type +
				"&invoiceParam.companyName=" + invoice_companyName +
				"&invoiceParam.selectInvoiceTitle=" + invoice_title + 
				"&invoiceParam.selectBookInvoiceContent=" + invoice_book_content +
				"&invoiceParam.selectNormalInvoiceContent=" + invoice_common_content + 
				"&invoiceParam.vatCompanyName=" + vat_companyName + 
				"&invoiceParam.code=" + vat_code + 
				"&invoiceParam.regAddr=" + vat_address + 
				"&invoiceParam.regPhone=" + vat_phone + 
				"&invoiceParam.regBank=" + vat_bankName + 
				"&invoiceParam.regBankAccount=" + vat_bankAccount +
				"&invoiceParam.hasCommon=" + invoice_hasCommon + 
				"&invoiceParam.hasBook=" + invoice_hasBook + 
				"&invoiceParam.consigneeName=" + consigneeName + 
				"&invoiceParam.consigneePhone=" + consigneePhone + 
				"&invoiceParam.consigneeAddress=" + consigneeAddress + 
				"&invoiceParam.consigneeProvince=" + consignee_province +
				"&invoiceParam.consigneeProvinceId=" + consignee_provinceId + 
				"&invoiceParam.consigneeCity=" + consignee_city + 
				"&invoiceParam.consigneeCityId=" + consignee_cityId + 
				"&invoiceParam.consigneeCounty=" + consignee_county + 
				"&invoiceParam.consigneeCountyId=" + consignee_countyId + 
				"&invoiceParam.consigneeTown=" + consignee_town + 
				"&invoiceParam.consigneeTownId=" + consignee_townId + 
				"&invoiceParam.sendSeparate=" + sendSeparate + 
				"&invoiceParam.usualInvoiceId=" + usualInvoiceId + 
				"&invoiceParam.selectElectroTitle=" + electro_invoiceTitle + 
				"&invoiceParam.electroCompanyName=" + electro_companyName + 
				"&invoiceParam.electroInvoiceEmail=" + electro_email + 
				"&invoiceParam.electroInvoicePhone=" + electro_phone +
				"&invokeInvoiceBasicService=" + invokeInvoiceBasicService +
				"&invoice_ceshi1="+invoice_ceshi1;
	param = parent.addFlowTypeParam(param);	
	jQuery.ajax({
		type: "POST",
		dataType: "text",
		url: OrderAppConfig.DynamicDomain + "/invoice/saveInvoice.action",
		data: param,
		cache: false,
		success: function(dataResult, textStatus) {
			// 没有登录跳登录
			if (isUserNotLogin(dataResult)) {
				goToLogin();
				return;
			}
			// 服务器返回异常处理,如果有消息div则放入,没有则弹出
			if (isHasMessage(dataResult)) {
				var message = getMessage(dataResult);
				alert(message);
			}
			// 成功后如果有divID直接放入div，没有则返回结果
			else {
				$("#part-inv", parent.document).html(dataResult);

				if(invoice_common_content > 0 || invoice_book_content > 0){
					$("#sopNotPutInvoice",parent.document).val("false");
				}else{
					$("#sopNotPutInvoice",parent.document).val("true");
				}
				
				setTimeout(function(){
					window.location.href = "about:blank";
					window.parent.jQuery.closeDialog();
				});

			}
		},
		error: function(XMLHttpResponse) {}
	});
}window.save_Invoice = save_Invoice;*/

function changeGiftType(type)
{
	if(type==0)
	{
		$("#invoiceSendType_0").attr("checked","checked");
		$("#sendSeparateView").hide();
	}
	if(type==3)
	{
		$("#invoiceSendType_3").attr("checked","checked");
		$("#sendSeparateView").show();
	}	
}window.changeGiftType = changeGiftType;

/**
 * 是否是实体礼品卡流程

function isLipinkaPhysical() {
	
	var lpkVal = $("#flowType").val();

	if (lpkVal == "4") {
		return true;
	} else {
		return false;
	}
}  */
/**
 * 判断是否是空
 * @param value
 */
function isEmpty(value){
	if(value == null || value == "" || value == "undefined" || value == undefined || value == "null"){
		return true;
	}
	else{
//		value = value.replace(/\s/g,"");
		if($.trim(value) == ""){
			return true;
		}
		return false;
	}
}

function addFlowTypeParam(params) {
	var flowType = $("#flowType").val();
	if (isEmpty(flowType)) {
		return params;
	} else {
		if (isEmpty(params)) {
			return "flowType=" + flowType;
		} else {
			return params + "&flowType=" + flowType;
		}
	}
}
/**
 * 将消息返回【此方法别动】
 * 
 * @param data
 * @return
 */
function getMessage(data) {
	if (data.errorMessage) {
		return data.errorMessage;
	} else {
		try {
			var mesageObject = eval("(" + data + ")");
			if (mesageObject != null && mesageObject.errorMessage != null && mesageObject.errorMessage != "") {
				return mesageObject.errorMessage;
			}
		} catch (e) {
		}
	}
	return null;
}window.getMessage=getMessage;

/**
 * 校验电子发票email
 * 
 * @return
 */
function check_electroInvoiceEmail() {
	var value = $("#e_consignee_email").val();
	var errorFlag = false;
	var errorMessage = "";
	if (!isEmpty(value)) {
		if (!check_email_new(value)) {
			errorFlag = true;
			errorMessage = "邮箱格式不正确";
		}
		if (value.length > 50) {
			errorFlag = true;
			errorMessage = "邮箱长度不能大于50位";
		}
	}
	if (errorFlag) {
		$("#e_consignee_email_error").html(errorMessage);
		$("#e_consignee_email_error").addClass("error-msg");
		return false;
	} else {
		$("#e_consignee_email_error").removeClass("error-msg");
		$("#e_consignee_email_error").html("");
	}
	return true;
}

/**
 * 校验电子发票手机号
 */
function check_electroInvoicePhone() {
	var value = $("#e_consignee_mobile").val();
	var errorFlag = false;
	var errorMessage = "";
	if (!isEmpty(value)) {
		if (!check_mobile_new(value)) {
			errorFlag = true;
			errorMessage = "手机号码格式不正确";
		}
	} else {
		errorFlag = true;
		errorMessage = "请输入手机号码";
	}
	if (errorFlag) {
		$("#e_consignee_mobile_error").html(errorMessage);
		$("#e_consignee_mobile_error").addClass("error-msg");
		return false;
	} else {
		$("#e_consignee_mobile_error").removeClass("error-msg");
		$("#e_consignee_mobile_error").html("");
	}
	return true;
}
/**
 * 判断用户是否登录【此方法别动】
 */
function isUserNotLogin(data) {
	if (data.error == "NotLogin") {
		return true;
	} else {
		try {
			var obj = eval("(" + data + ")");
			if (obj != null && obj.error != null && obj.error == "NotLogin") {
				return true;
			}
		} catch (e) {
		}
	}
	return false;
}window.isUserNotLogin=isUserNotLogin;

/**
 * 去登录页面
 */
function goToLogin() {
	if (isLocBuy()) {
		window.location.href = OrderAppConfig.LoginLocUrl + "?rid=" + Math.random();
	} else {
		window.location.href = OrderAppConfig.LoginUrl + "?rid=" + Math.random();
	}
}window.goToLogin=goToLogin;
/**
* 获取城市列表
*/
function loadCitys() {
var provinceId = $("#consignee_province").find("option:selected").val();
var provinceName=isEmpty(provinceId)?"":$("#consignee_province").find("option:selected").text().replace("*","");
$("#areaNameTxt").html(provinceName);
$("#codHelpUrl").attr("href", "http://help.jd.com/user/issue/103-983.html");
if (provinceId == null || provinceId == "") {
	$("#span_city").html("<select class='selt' id=\"consignee_city\" name=\"consigneeParam.city\"><option selected=\"\" value=\"\">请选择：</option></select>");
	$("#span_county").html("<select class='selt' id=\"consignee_county\" name=\"consigneeParam.countyId\"><option selected=\"\" value=\"\">请选择：</option></select>");
	$("#span_town").html("");
	return;
}
var param = "consigneeParam.provinceId=" + provinceId;
var actionUrl = OrderAppConfig.DynamicDomain + "/consignee/getCitys.action";
jQuery.ajax( {
			type : "POST",
			dataType : "text",
			url : actionUrl,
			data : param,
			cache : false,
			success : function(dataResult, textStatus) {
				// 没有登录跳登录
				if (isUserNotLogin(dataResult)) {
					goToLogin();
					return;
				}
				if (isHasMessage(dataResult)) {
					var message = getMessage(dataResult);
					alert(message);
				} else {
					$("#span_city").html(dataResult);
					//$("#span_county").html("<select id=\"consignee_county\" name=\"consigneeParam.countyId\"><option selected=\"\" value=\"\">请选择：</option></select>");
					$("#span_town").html("");
				}
			},
			error : function(XMLHttpResponse) {
				////alert("系统繁忙，请稍后再试！");
				return false;
			}
		});
}

/**
* 获取县级列表
*/
function loadCountys() {
var cityId = $("#consignee_city").find("option:selected").val();
var provinceName=$("#consignee_province").find("option:selected").text().replace("*","");
var cityName = isEmpty(cityId)? "": $("#consignee_city").find("option:selected").text().replace("*","");
$("#areaNameTxt").text(provinceName+cityName);
$("#codHelpUrl").attr("href", "http://help.jd.com/user/issue/103-983.html");
var param = "consigneeParam.cityId=" + cityId;
var actionUrl = OrderAppConfig.DynamicDomain + "/consignee/getCountys.action";
jQuery.ajax( {
	type : "POST",
	dataType : "text",
	url : actionUrl,
	data : param,
	cache : false,
	success : function(dataResult, textStatus) {
		// 没有登录跳登录
	if (isUserNotLogin(dataResult)) {
		goToLogin();
		return;
	}
	if (isHasMessage(dataResult)) {
		var message = getMessage(dataResult);
		alert(message);
	} else {
		if (dataResult != null) {
			$("#span_county").html(dataResult);
			$("#span_town").html("");
		}
	}
},
error : function(XMLHttpResponse) {
	////alert("系统繁忙，请稍后再试！");
	return false;
}
});
}

/**
* 获取乡镇街道列表
*/
function loadTowns() {
var countyId = $("#consignee_county").find("option:selected").val();
var provinceName=$("#consignee_province").find("option:selected").text().replace("*","");
var cityName =  $("#consignee_city").find("option:selected").text().replace("*","");
var countyName =isEmpty(countyId)? "": $("#consignee_county").find("option:selected").text().replace("*","");
$("#areaNameTxt").text(provinceName+cityName+countyName);
$("#codHelpUrl").attr("href", "http://help.jd.com/user/issue/103-983.html");
var param = "consigneeParam.countyId=" + countyId;
var actionUrl = OrderAppConfig.DynamicDomain + "/consignee/getTowns.action";
jQuery.ajax( {
	type : "POST",
	dataType : "text",
	url : actionUrl,
	data : param,
	cache : false,
	success : function(dataResult, textStatus) {
		// 没有登录跳登录
	if (isUserNotLogin(dataResult)) {
		goToLogin();
		return;
	}
	if (isHasMessage(dataResult)) {
		var message = getMessage(dataResult);
		alert(message);
	} else {
		if (dataResult != null && dataResult != "area_empty") {
			$("#span_town").html(dataResult);
			$("#span_town").show();
		} else {
			$("#span_town").html("");
			$("#span_town").hide();
		}
	}
},
error : function(XMLHttpResponse) {
	////alert("系统繁忙，请稍后再试！");
	return false;
		}
	});
}

function setTownName(){
var provinceName=$("#consignee_province").find("option:selected").text().replace("*","");
var cityName =  $("#consignee_city").find("option:selected").text().replace("*","");
var countyName = $("#consignee_county").find("option:selected").text().replace("*","");
var townName = $("#consignee_town").find("option:selected").text().replace("*","");

var provinceId=$("#consignee_province").find("option:selected").val();
var cityId =  $("#consignee_city").find("option:selected").val();
var countyId = $("#consignee_county").find("option:selected").val();
var townId = $("#consignee_town").find("option:selected").val();
$("#areaNameTxt").text(provinceName+cityName+countyName+townName);
$("#codHelpUrl").attr("href", "http://help.jd.com/user/issue/103-983.html");
}
function loadAreaDetailForInvoiceConsingee(provinceId, cityId, countyId, townId, isGeneralInvoice) {
var param = "consigneeParam.provinceId=" + provinceId + "&consigneeParam.cityId=" + cityId + "&consigneeParam.countyId=" + countyId + "&consigneeParam.townId="
		+ townId;
var actionUrl = OrderAppConfig.DynamicDomain + "/consignee/loadAllAreas.action";
jQuery.ajax({
	type : "POST",
	dataType : "text",
	url : actionUrl,
	data : param,
	cache : false,
	success : function(dataResult, textStatus) {
		if (isHasMessage(dataResult)) {
			var message = getMessage(dataResult);
			alert(message);
		} else {
			if (isGeneralInvoice) {
				$("#generalInvoice_span_area").html(dataResult);
				$("#span_area").html("");
			} else {
				$("#span_area").html(dataResult);
				$("#generalInvoice_span_area").html("");
				changeGiftType(0);
			}
		}
	},
	error : function(XMLHttpResponse) {
		////alert("系统繁忙，请稍后再试！");
		return false;
	}
});
}
/**
* 验证增值税发票消息提示
* 
* @param divId
* @param value
*/
function check_Invoice(type, value) {
  var errorFlag = false;
  var errorMessage = null;
  // 验证发票单位名称
  if (type == "vat_companyName") {
    if (isEmpty(value)) {
      errorFlag = true;
      errorMessage = "单位名称不能为空！";
    } else {
      if (checkLength(value) < 2) {
        errorFlag = true;
        errorMessage = "请填写完整单位名称！";
      }
      if (checkLength(value) > 100) {
        errorFlag = true;
        errorMessage = "单位名称过长,请重新输入！";
      }
      if (!is_forbidForInvoice(value)) {
        errorFlag = true;
        errorMessage = "单位名称含有非法字符！";
      }
    }
  } else if (type == "vat_code") { // 验证纳税人识别号
    if (isEmpty(value)) {
      errorFlag = true;
      errorMessage = "纳税人识别号不能为空！";
    } else {
      var reg_number = /^([a-zA-Z0-9]){15,20}$/;
      if (!reg_number.test(value)) {
        errorFlag = true;
        errorMessage = "纳税人识别号错误，请检查！";
      }
      if (!is_forbid(value)) {
        errorFlag = true;
        errorMessage = "纳税人识别号含有非法字符！";
      }
    }
  } else if (type == "vat_address") { // 验证发票注册地址
    if (isEmpty(value)) {
      errorFlag = true;
      errorMessage = "注册地址不能为空！";
    } else {
      if (value.replace(/[^\x00-\xff]/g, "**").length < 2) {
        errorFlag = true;
        errorMessage = "注册地址错误！";
      }
      if (checkLength(value) > 250) {
        errorFlag = true;
        errorMessage = "注册地址过长，请重新输入！";
      }
      if (!is_forbidForInvoice(value)) {
        errorFlag = true;
        errorMessage = "注册地址含有非法字符！";
      }
    }
  } else if (type == "vat_phone") { // 验证增值税发票电话
    if (isEmpty(value)) {
      errorFlag = true;
      errorMessage = "注册电话不能为空！";
    } else {
      if (checkLength(value) > 50) {
        errorFlag = true;
        errorMessage = "注册电话过长，请重新输入！";
      }
      if (!is_forbidForInvoice(value)) {
        errorFlag = true;
        errorMessage = "注册电话含有非法字符，请重新输入！";
      }
    }
  } else if (type == "vat_bankName") { // 验证增值税发票开户银行
    if (isEmpty(value)) {
      errorFlag = true;
      errorMessage = "开户银行不能为空！";
    } else {
      if (value.replace(/[^\x00-\xff]/g, "**").length < 2) {
        errorFlag = true;
        errorMessage = "开户银行错误！";
      }
      if (checkLength(value) > 100) {
        errorFlag = true;
        errorMessage = "开户银行过长，请重新输入！";
      }
      if (!is_forbidForInvoice(value)) {
        errorFlag = true;
        errorMessage = "开户银行含有非法字符！";
      }
    }
  } else if (type == "vat_bankAccount") { // 验证增值税发票银行账户
    if (isEmpty(value)) {
      errorFlag = true;
      errorMessage = "银行帐户不能为空！";
    } else {
      if (!checkBankCount(value)) {
        errorFlag = true;
        errorMessage = "银行帐户含有非法字符！";
      }
    }
  }
  if (errorFlag) {
    $("#" + type + "_error").html(errorMessage);
    $("#" + type + "_error").addClass("error-msg");
    
    return false;
  } else {
    $("#" + type + "_error").removeClass("error-msg");
    $("#" + type + "_error").html("");
   
  }
  return true;
}

//增票非法字符验证
function is_forbidForInvoice(temp_str) {
temp_str = trimTxt(temp_str);
var forbid_str = new String('>,<,&');
var forbid_array = new Array();
forbid_array = forbid_str.split(',');
for (var i = 0; i < forbid_array.length; i++) {
  if (temp_str.search(new RegExp(forbid_array[i])) != -1)
    return false;
}
return true;
}

/**
* 验证发票地址消息提示，单列出方法是因为文案不同
* 
* @param divId
* @param value
*/
function check_InvoiceConsignee(divId,isGeneral) {
var errorFlag = false;
var errorMessage = null;
var value = null;
var generalTag = isGeneral?"generalInvoice_":"";
// 验证收货人名称
if (divId == generalTag+"name_div") {
  value = $("#"+generalTag+"consignee_name").val();
  if (isEmpty(value)) {
    errorFlag = true;
    errorMessage = "请您填写收票人姓名";
  }
  if (!is_forbid(value)) {
    errorFlag = true;
    errorMessage = "收票人姓名中含有非法字符";
  }
}
// 验证地区是否完整
else if (divId == generalTag+"area_div") {
  var provinceId = $("#consignee_province").find("option:selected").val();
  var cityId = $("#consignee_city").find("option:selected").val();
  var countyId = $("#consignee_county").find("option:selected").val();
	var townId = $("#consignee_town").find("option:selected").val();
	// 验证地区是否正确
	if (isEmpty(provinceId) || isEmpty(cityId) || isEmpty(countyId)
			|| ($("#span_town").html()!=null&&$("#span_town").html()!=""&&!$("#span_town").is(":hidden") && isEmpty(townId))) {
		errorFlag = true;
		errorMessage = "请您填写完整的地区信息";
	}
}
// 验证收货人地址
else if (divId == generalTag+"address_div") {
	value = $("#"+generalTag+"consignee_address").val();
	if (isEmpty(value)) {
		errorFlag = true;
		errorMessage = "请您填写收票人详细地址";
	}
	if (!is_forbid(value)) {
		errorFlag = true;
		errorMessage = "收票人详细地址中含有非法字符";
	}
}
// 验证手机号码
else if (divId == generalTag+"call_phone_div") {
	value = $("#"+generalTag+"consignee_phone").val();
	divId = generalTag+"call_div";
	if (!isEmpty(value)) {
		if (!is_forbid(value)) {
			errorFlag = true;
			errorMessage = "固定电话号码中含有非法字符";
		}
		if (!checkPhone(value)) {
			errorFlag = true;
			errorMessage = "固定电话号码格式不正确";
		}
		if(strlength >=4 && value.indexOf("*") >-1){
			if(!((new RegExp(/.+\*\*\*\*$/).test(value) && (strlength - value.indexOf("*")) < 5) || (new RegExp(/^\d{11}$/).test(value) || new RegExp(/^\d{3}\*\*\*\*\d{4}$/).test(value)))){
				errorFlag = true;
				errorMessage = "固定电话号码格式不正确";
			}
		}
	}
	if (true) {
		value = $("#"+generalTag+"consignee_mobile").val();
		if (isEmpty(value)) {
			errorFlag = true;
			errorMessage = "请您填写收票人手机号码";
		} else {
			if (!check_mobile_new(value)) {
				errorFlag = true;
				errorMessage = "手机号码格式不正确";
			}
		}
	}
}
if (errorFlag) {
	$("#"+divId + "_error").html(errorMessage);
	$("#"+divId + "_error").addClass("error-msg");
	return false;
} else {
	$("#"+divId + "_error").removeClass("error-msg");
	$("#"+divId + "_error").html("");
}
return true;
}
/**
* 获取省份列表
*/
function loadProvinces(isGeneralInvoice) {
var actionUrl = OrderAppConfig.DynamicDomain + "/consignee/getProvinces.action";
jQuery.ajax( {
	type : "POST",
	dataType : "text",
	url : actionUrl,
	data : null,
	cache : false,
	success : function(dataResult, textStatus) {
			// 没有登录跳登录
		if (isUserNotLogin(dataResult)) {
			goToLogin();
			return;
		}
		if (isHasMessage(dataResult)) {
			var message = getMessage(dataResult);
			alert(message);
		} else 
		{
			var consignee_data =  '<span id="span_province">'+ dataResult +'</span>'+
								  '<span id="span_city">'+
								  '<select id="consignee_city"  name="invoiceParam.consigneeCity" class="selt">'+	
								  '<option value="">请选择</option>'+
								  '</select>'+
								  '</span>'+
								  '<span id="span_county">'+
								  '<select id="consignee_county"  name="invoiceParam.consigneeCountry" class="selt"><option value="">请选择</option></select>'+
								  '</span>'+
								  '<span id="span_town" style="display:none">'+
								  '<select  class="selt" id="consignee_town"  name="invoiceParam.consigneeTown"><option value="">请选择</option></select>'+
								  '</span>';

			if (isGeneralInvoice) 
			{
				$("#generalInvoice_span_area").html(consignee_data);
				$("#span_area").html("");
			} 
			else 
			{
				$("#span_area").html(consignee_data);
				$("#generalInvoice_span_area").html("");
				changeGiftType(0);
			}
		}
	},
	error : function(XMLHttpResponse) {
		////alert("系统繁忙，请稍后再试！");
		return false;
	}
});
}