var dynamicDomain = parent.dynamicDomain;
$("#codHelpUrl").attr("href", "http://help.jd.com/user/issue/103-983.html");
if(parent.$("#ui-dialog-close").size() != 0)
{
	if(parent.checkIsNewUser()||parent.$("#ui-dialog-close").val()==1){
		parent.$(".ui-dialog-close").css("display", "none");
		parent.$("#ui-dialog-close").val("");
	}
}
/**
 * 保存收货地址（包含保存常用人收货地址，根据id区分）
 */
function save_Consignee() {
	// 如果不隐藏重新获取用户填写的信息
	var consignee_id = null;
	var consignee_type = null;
	var consignee_provinceId = null;
	var consignee_cityId = null;
	var consignee_countyId = null;
	var consignee_townId = null;
	var consignee_name = null;
	var consignee_email = null;
	var consignee_address = null;
	var consignee_mobile = null;
	//yanwenqi 全球购添加身份证号
	var consignee_idCard = null;
	//end
	var consignee_phone = null;
	var consignee_provinceName = null;
	var consignee_cityName = null;
	var consignee_countyName = null;
	var consignee_townName = null;
	var consignee_ceshi1 = null;
	var isUpdateCommonAddress = 0;
	var consignee_commons_size = parent.$("#hidden_consignees_size").val();
	var giftSender_consignee_name   = "";
	var giftSender_consignee_mobile = "";
	var noteGiftSender = false;
	if(parent.isGiftBuy()){
		noteGiftSender= true;
		giftSender_consignee_name   = parent.$("#consigneeList_giftSenderConsigneeName").val();
		giftSender_consignee_mobile = parent.$("#consigneeList_giftSenderConsigneeMobile").val();		
	}
	// 如果展开之后需要添加常用地址列表
	isUpdateCommonAddress = 1;
	consignee_id = $("#consignee_form_id").val();
	
	if (parent.checkMaxConsigneeSize() && consignee_id == "") {
		parent.showMessageWarn("您的地址已经大于最大限制!");
		return;
	}

	consignee_type = $("#consignee_type").val();
	consignee_provinceId = $("#consignee_province").find("option:selected").val();
	consignee_cityId = $("#consignee_city").find("option:selected").val();
	consignee_countyId = $("#consignee_county").find("option:selected").val();
	consignee_townId = $("#consignee_town").find("option:selected").val();
	consignee_provinceName = $("#consignee_province").find("option:selected").text();
	consignee_cityName = $("#consignee_city").find("option:selected").text();
	consignee_countyName = $("#consignee_county").find("option:selected").text();
	if (!$("#span_town").is(":hidden"))
		consignee_townName = $("#consignee_town").find("option:selected").text();
	consignee_name = $("#consignee_name").val();
	consignee_address = $("#consignee_address").val();
	consignee_mobile = $("#consignee_mobile").val();
	//yanwenqi 全球购添加身份证号idCard
	consignee_idCard = $("#consignee_idCard").val();
	if(consignee_idCard == null || consignee_idCard == "undefined"){
		consignee_idCard = "";
	}
	//end
	consignee_phone = $("#consignee_phone").val();
	consignee_email = $("#consignee_email").val();
	consignee_ceshi1 = $("#consignee_ceshi1").val();

	if (consignee_email == null || consignee_email == "undefined")
		consignee_email = "";
	if (consignee_phone == null || consignee_phone == "undefined")
		consignee_phone = "";

	var checkConsignee = true;
	// 验证收货人信息是否正确
	if (!check_Consignee("name_div"))
		checkConsignee = false;
	// 验证地区是否正确
	if (!check_Consignee("area_div"))
		checkConsignee = false;
	// 验证收货人地址是否正确
	if (!check_Consignee("address_div"))
		checkConsignee = false;
	// 验证手机号码是否正确
	if (!check_Consignee("call_mobile_div"))
		checkConsignee = false;
	
	// yanwenqi 全球购增加身份证号 验证身份证号
	if (!check_Consignee("idCard_div"))
		checkConsignee = false;
	//end
	
	// 验证电话是否正确
	if (!check_Consignee("call_phone_div"))
		checkConsignee = false;
	// 验证邮箱是否正确
	if (!check_Consignee("email_div"))
		checkConsignee = false;
	if (!checkConsignee)
		return;
	var areaName = null;
	consignee_provinceName = consignee_provinceName.replace("*", "");
	consignee_cityName = consignee_cityName.replace("*", "");
	consignee_countyName = consignee_countyName.replace("*", "");
	areaName = consignee_provinceName + " " + consignee_cityName + " " + consignee_countyName + " ";
	if (consignee_townName != null && consignee_townName != "")
		areaName = areaName + consignee_townName.replace("*", "");
	if (consignee_type == "")
		consignee_type = 1;
	// 如果使用常用联系人列表，则不对四级地址进行显示判断
	if (isEmpty(consignee_townId) || $("#span_town").is(":hidden"))
		consignee_townId = 0;

	var param = "consigneeParam.id=" + consignee_id + "&consigneeParam.type=" + consignee_type 
		+ "&consigneeParam.name=" + consignee_name + "&consigneeParam.provinceId=" + consignee_provinceId 
		+ "&consigneeParam.cityId=" + consignee_cityId + "&consigneeParam.countyId=" + consignee_countyId 
		+ "&consigneeParam.townId=" + consignee_townId + "&consigneeParam.address=" + consignee_address 
		+ "&consigneeParam.mobile=" + consignee_mobile + "&consigneeParam.email=" + consignee_email 
		+ "&consigneeParam.phone=" + consignee_phone + "&consigneeParam.provinceName=" + consignee_provinceName 
		+ "&consigneeParam.cityName=" + consignee_cityName + "&consigneeParam.countyName=" + consignee_countyName 
		+ "&consigneeParam.townName=" + consignee_townName + "&consigneeParam.commonConsigneeSize=" + consignee_commons_size 
		+ "&consigneeParam.isUpdateCommonAddress=" + isUpdateCommonAddress + "&consigneeParam.giftSenderConsigneeName=" + giftSender_consignee_name 
		+ "&consigneeParam.giftSendeConsigneeMobile=" + giftSender_consignee_mobile + "&consigneeParam.noteGiftSender=" + noteGiftSender
		+ "&consignee_ceshi1="+consignee_ceshi1 + "&consigneeParam.idCard="+consignee_idCard;
	param = parent.addFlowTypeParam(param);
	var actionUrl = OrderAppConfig.DynamicDomain + "/consignee/saveConsignee.action";
	jQuery.ajax({
		type: "POST",
		dataType: "json",
		url: actionUrl,
		data: param,
		cache: false,
		success: function(consigneeResult, textStatus) {
			if (parent.isUserNotLogin(consigneeResult)) {
				parent.goToLogin();
				return;
			}
			if (consigneeResult.success) {
				var invoiceHtml = parent.$("#part-inv").html();
		        if (consigneeResult.restInvoiceByAddress == 22) {
		        	parent. $("#part-inv").html(invoiceHtml.replace("办公用品", "办公用品（附购物清单）"));
		        }
		        if (consigneeResult.restInvoiceByAddress == 2) {
		        	parent.$("#part-inv").html(invoiceHtml.replace("（附购物清单）", ""));
		        }
		        if (consigneeResult.supportElectro) {
		        	if(null != consigneeResult.restInvoiceCompanyName){
		        		parent.$("#part-inv").html("<span class='mr10'>普通发票（纸质）&nbsp; </span><span class='mr10'> "+consigneeResult.restInvoiceCompanyName+"&nbsp; </span><span class='mr10'>明细&nbsp; </span><a onclick='edit_Invoice()' class='ftx-05 invoice-edit' href='#none'>修改</a>");
		        	}else{
		        		parent.$("#part-inv").html("<span class='mr10'>普通发票（纸质）&nbsp; </span><span class='mr10'> 个人&nbsp; </span><span class='mr10'>明细&nbsp; </span><a onclick='edit_Invoice()' class='ftx-05 invoice-edit' href='#none'>修改</a>");
		        	}
		        }
		        if (consigneeResult.defaultElectro) {
		        	parent.$("#part-inv").html("<span class='mr10'>普通发票（电子）&nbsp; </span><span class='mr10'> 个人&nbsp; </span><span class='mr10'>明细&nbsp; </span><a onclick='edit_Invoice()' class='ftx-05 invoice-edit' href='#none'>修改</a>");
		        }
		        
				parent.$("#isNeedOpenInvoice").val(consigneeResult.openInvoice); // 隐藏域，判断修改地址后，是否需要修改发票信息，广州机构比较特殊
				if (consigneeResult.resultCode == "isRefreshArea") {
					show_ConsigneeDetail(consignee_id);
				} else {
					consignee_mobile = consigneeResult.consigneeShowView.mobile;
					var areaIds=consigneeResult.consigneeShowView.provinceId + "-" + consigneeResult.consigneeShowView.cityId + "-" + consigneeResult.consigneeShowView.countyId + "-" + consigneeResult.consigneeShowView.townId;
					// 弹出对应提示
					parent.$("#consignee-ret").html(consigneeResult.consigneeHtml);
					parent.$('#consignee_id').val(consigneeResult.consigneeShowView.id);
					parent.$('#hideAreaIds').val(areaIds);
					if (parent.isBigItemChange())
						parent.bigItemChangeArea();
					// 预售电话修改
					if (parent.$("#isPresale").val() == "true") {
						parent.$("#hiddenUserMobileByPresale").val(consignee_mobile);
						if (parent.$("#presaleMobile input").size() > 0) {
							parent.$("#presaleMobile input").val(consignee_mobile);
						} else if (parent.$("#userMobileByPresale").size() > 0) {
							parent.$("#userMobileByPresale").html(consignee_mobile);
						}
					}
					if (parent.hasTang9())
						parent.tang9ChangeArea();
					var commonConsigeeSize = parent.$("#hidden_consignees_size").val();
					var consigneeSize = parseInt(!commonConsigeeSize ? 0 : commonConsigeeSize);
					if (consignee_id == "") {
						consignee_id = parent.$("#consignee_id").val();
						var liHtml  =  "<li class='ui-switchable-panel' style='display: list-item;'  id='consignee_index_" + consignee_id + "' style='cursor: pointer;'>" +
											"<div class='consignee-item item-selected' consigneeId='" + consignee_id + "'>" +
												"<span limit='3'>" + consignee_name + "</span><b></b>" +
											"</div>" +
											"<div class='addr-detail'>" +
												"<span class='addr-name' limit='6'>" + consignee_name + "</span>" +
												"<span class='addr-info' limit='32'>" + areaName + consignee_address +"</span>" +
												"<span class='addr-tel'>" + consignee_mobile + "</span>" +
											"</div>" +
											"<div class='op-btns' consigneeId='" + consignee_id + "'>" +
												"<a href='#none' class='ftx-05 setdefault-consignee' fid='" + consignee_id + "'>设为默认地址</a>" +
												"<a href='#none' class='ftx-05 edit-consignee' fid='" + consignee_id + "'>编辑</a>" +
												"<a href='#none' class='ftx-05 del-consignee " + ((consigneeSize == 0 || consigneeSize == 1) ? " hide" : "") + "' fid='" + consignee_id + "'>删除</a>" +
											"</div>" +
										"</li>";
						//yanwenqi 全球购添加身份证号
						if(parent.$("#flowType").val() == 10){
							if(consignee_idCard == "undefined" || consignee_idCard == null || consignee_idCard == ""){
								liHtml  =  "<li class='ui-switchable-panel' style='display: list-item;'  id='consignee_index_" + consignee_id + "' style='cursor: pointer;'>" +
								"<div class='consignee-item item-selected' consigneeId='" + consignee_id + "'>" +
									"<span limit='3'>" + consignee_name + "</span><b></b>" +
								"</div>" +
								"<div class='addr-detail'>" +
									"<span class='addr-name' limit='6'>" + consignee_name + "</span>" +
									"<span class='addr-info' limit='32'>" + areaName + consignee_address +"</span>" +
									"<span class='addr-tel'>" + consignee_mobile + "</span>" +
									"<span class='addr-idCard'>未完善身份证信息</span>" +
								"</div>" +
								"<div class='op-btns' consigneeId='" + consignee_id + "'>" +
									"<a href='#none' class='ftx-05 setdefault-consignee' fid='" + consignee_id + "'>设为默认地址</a>" +
									"<a href='#none' class='ftx-05 edit-consignee' fid='" + consignee_id + "'>编辑</a>" +
									"<a href='#none' class='ftx-05 del-consignee " + ((consigneeSize == 0 || consigneeSize == 1) ? " hide" : "") + "' fid='" + consignee_id + "'>删除</a>" +
								"</div>" +
							"</li>";
							}
						}
						//end
						parent.$("#consignee-list .consignee-item").removeClass("item-selected");
						// update by lilong 产品经理zhaojingjing确认，如果当前页面有默认地址，新增地址在默认地址之后
						var first_li = parent.$(".consignee-item").parents("li").last();//当前列表第一项
						var _tempstr = first_li.find("div span").first().html();
						if(_tempstr && _tempstr.indexOf("默认地址") > -1) {
						  $(liHtml).insertAfter(first_li);// 1.插入在默认地址之后
						} else {
						  $(liHtml).insertBefore(first_li);// 2.插入在地址列表第一位
						}
						parent.$("#hidden_consignees_size").val("" + (consigneeSize = consigneeSize + 1));
						if (parent.$("#isNull").val() == "-1") 
							parent.goOrder();
						if (consigneeSize > 1){
							var selectDiv = parent.$(".consignee-item").next().next();
							selectDiv.find(".del-consignee").removeClass("hide");
							selectDiv.find(".setdefault-consignee").removeClass("hide");
					        parent.$("#consigneeItemAllClick").addClass("hide");
					        parent.$("#consigneeItemHideClick").removeClass("hide");
						}
						if (parent.checkIsNewUser()) {
							parent.$("#isOpenConsignee").val("0");
						}
						//重新加载优惠券列表
						parent.reloadCoupon(consigneeResult.reloadCoupon);
						$("#del_consignee_type").val("0");
						parent.window.show_ConsigneeAll();
					} else {
						var defAddress = "<a href='#none' class='ftx-05 setdefault-consignee " + ((consigneeSize == 0 || consigneeSize == 1) ? " hide" : "") + "' fid='" + consignee_id + "'>设为默认地址</a>";
						if(parent.$("#consignee_index_"+consignee_id).find(".op-btns").find("a").size() == 2)
							defAddress = "<span></span>";
						var divHtml="<div class='consignee-item item-selected' consigneeId='" + consignee_id + "'>" +
										"<span limit='3'>" + ((defAddress.indexOf("span")>-1)?"默认地址":consignee_name) + "</span><b></b>" +				
									"</div>" +
									"<div class='addr-detail'>" +
										"<span class='addr-name' limit='6'>" + consignee_name + "</span>" +
										"<span class='addr-info' limit='32'>" + areaName + consignee_address +"</span>" +
										"<span class='addr-tel'>" + consignee_mobile + "</span>" +
									"</div>" +
									"<div class='op-btns' consigneeId='" + consignee_id + "'>" + defAddress +
										"<a href='#none' class='ftx-05 edit-consignee' fid='" + consignee_id + "'>编辑</a>" +
										"<a href='#none' class='ftx-05 del-consignee " + ((consigneeSize == 0 || consigneeSize == 1) ? " hide" : "") + "' fid='" + consignee_id + "'>删除</a>" +
									"</div>";
						//yanwenqi 全球购添加身份证号
						if(parent.$("#flowType").val() == 10){
							if(consignee_idCard == "undefined" || consignee_idCard == null || consignee_idCard == ""){
								liHtml  =  "<li class='ui-switchable-panel' style='display: list-item;'  id='consignee_index_" + consignee_id + "' style='cursor: pointer;'>" +
								"<div class='consignee-item item-selected' consigneeId='" + consignee_id + "'>" +
									"<span limit='3'>" + consignee_name + "</span><b></b>" +
								"</div>" +
								"<div class='addr-detail'>" +
									"<span class='addr-name' limit='6'>" + consignee_name + "</span>" +
									"<span class='addr-info' limit='32'>" + areaName + consignee_address +"</span>" +
									"<span class='addr-tel'>" + consignee_mobile + "</span>" +
									"<span class='addr-idCard'>未完善身份证信息</span>" +
								"</div>" +
								"<div class='op-btns' consigneeId='" + consignee_id + "'>" +
									"<a href='#none' class='ftx-05 setdefault-consignee' fid='" + consignee_id + "'>设为默认地址</a>" +
									"<a href='#none' class='ftx-05 edit-consignee' fid='" + consignee_id + "'>编辑</a>" +
									"<a href='#none' class='ftx-05 del-consignee " + ((consigneeSize == 0 || consigneeSize == 1) ? " hide" : "") + "' fid='" + consignee_id + "'>删除</a>" +
								"</div>" +
							"</li>";
							}
						}
						//end
						parent.$("#consignee-list .consignee-item").removeClass("item-selected");
						if (consignee_id == "-1")
							parent.$(".consignee-item").first().html(divHtml);
						else
							parent.$("#consignee_index_" + consignee_id).html(divHtml);
					}
					// 新用户保存后将值写回
					parent.setResetFlag(0, '1');
					parent.save_Pay();
					parent.$("#isOpenConsignee").val("0");
					parent.$("#isRefreshArea").val("0");
					parent.subStrConsignee();
					parent.consigneeInfo();
					//重新加载优惠券列表
					parent.reloadCoupon(consigneeResult.reloadCoupon);
				}
			} else {
				if (consigneeResult.message != null && consigneeResult.message != ""){					
					parent.showMessageWarn(consigneeResult.message);
				}
			}
			setTimeout(function(){
				window.location.href = "about:blank";
				parent.$.closeDialog();
			});
		},
		error: function(XMLHttpResponse) {
			parent.$.closeDialog();
		}
	});
}

/**
 * 编辑常用收货地址,展开对应信息
 */
function show_ConsigneeDetail(id) {
	// 隐藏20个数量的限制的提示
	parent.$("#addNumLimitNote").css("display", "none");
	
	var consignee_provinceName = $("#hidden_consignee_provinceName_" + id).val();
	var consignee_cityName = $("#hidden_consignee_cityName_" + id).val();
	var consignee_countyName = $("#hidden_consignee_countyName_" + id).val();
	var consignee_townName = $("#hidden_consignee_townName_" + id).val();
	var consignee_provinceId = $("#hidden_consignee_provinceId_" + id).val();
	var consignee_cityId = $("#hidden_consignee_cityId_" + id).val();
	var consignee_countyId = $("#hidden_consignee_countyId_" + id).val();
	var consignee_townId = $("#hidden_consignee_townId_" + id).val();

	// 展开三级地址
	$("#consignee_province").empty();
	$("#consignee_city").empty();
	$("#consignee_county").empty();
	$("#consignee_town").empty();
	$("#consignee_province").append("<option value='" + consignee_provinceId + "' selected >" + consignee_provinceName + "</option>");
	$("#consignee_city").append("<option value='" + consignee_cityId + "' selected >" + consignee_cityName + "</option>");
	$("#consignee_county").append("<option value='" + consignee_countyId + "' selected >" + consignee_countyName + "</option>");
	// 判断是否有四级地址，如果有则加载四级地址信息
	if (consignee_townId != null && consignee_townId != "0" && !isNaN(consignee_townId)) {
		$("#consignee_town").append("<option value='" + consignee_townId + "' selected >" + consignee_townName + "</option>");
		$("#span_town").show();
	}
	removeConsingeeMessage();
	// 异步加载四级地址
	loadAreaDetail(id, consignee_provinceId, consignee_cityId, consignee_countyId, consignee_townId);
}

/**
 * 加载四级地址，并选中默认值
 * 
 * @param provinceId
 * @param cityId
 * @param countyId
 * @param townId
 */
function loadAreaDetail(id, provinceId, cityId, countyId, townId) {
  var param = "consigneeParam.provinceId=" + provinceId + "&consigneeParam.cityId=" + cityId + "&consigneeParam.countyId=" + countyId + "&consigneeParam.townId=" + townId;
  var actionUrl = OrderAppConfig.DynamicDomain + "/consignee/loadAllAreas.action";
  jQuery.ajax({
    type: "POST",
    dataType: "text",
    url: actionUrl,
    data: param,
    async: true,
    cache: false,
    success: function(dataResult, textStatus) {
      // 没有登录跳登录
      if (parent.isUserNotLogin(dataResult)) {
        parent.goToLogin();
        return;
      }
      if (parent.isHasMessage(dataResult)) {
        var message = parent.getMessage(dataResult);
        parent.showMessageWarn(message);
      } else {
        $("#span_area").html(dataResult);
      }
    },
    error: function(XMLHttpResponse) {
      //parent.showMessageWarn("系统繁忙，请稍后再试！");
      return false;
    }
  });
}

/**
 * 验证收货地址消息提示
 * 
 * @param divId
 * @param value
 */
function check_Consignee(divId) {
	var errorFlag = false;
	var errorMessage = null;
	var value = null;
	var consignee_id = $("#consignee_form_id").val();
	
	var flowType = parent.$("#flowType").val();
	// 验证收货人名称
	if (divId == "name_div") {
		value = $("#consignee_name").val();
		if (isEmpty(value)) {
			errorFlag = true;
			errorMessage = "请您填写收货人姓名";
		}
		if(flowType == 10){
			if(!/^([\u4e00-\u9fa5])([\u4e00-\u9fa5·]){0,23}([\u4e00-\u9fa5])$/i.test(value)){
				errorFlag = true;
			}else{
				if(value.search(/·{2,}/) > -1){
					errorFlag = true;
				}
			}
			if(errorFlag){
				errorMessage = "收件人姓名仅支持简体中文，长度不超过25个字";
			}
		}else{
			if (value.length > 25) {
				errorFlag = true;
				errorMessage = "收货人姓名不能大于25位";
			}
			if (!is_forbid(value)) {
				errorFlag = true;
				errorMessage = "收货人姓名中含有非法字符";
			}
		}
	}
	// 验证邮箱格式
	else if (divId == "email_div") {
		value = $("#consignee_email").val();
		if (!isEmpty(value)) {
			if (value.length > 50) {
				errorFlag = true;
				errorMessage = "邮箱长度不能大于50位";
			}
			if (!check_email_new(value)) {
				errorFlag = true;
				errorMessage = "邮箱格式不正确";
			}
			if(consignee_id == ""){
				if(value.indexOf("*") > -1){
					errorFlag = true;
					errorMessage = "邮箱格式不正确";
				}
			}
		}
	}
	// 验证地区是否完整
	else if (divId == "area_div") {
		var provinceId = $("#consignee_province").find("option:selected").val();
		var cityId = $("#consignee_city").find("option:selected").val();
		var countyId = $("#consignee_county").find("option:selected").val();
		var townId = $("#consignee_town").find("option:selected").val();
		// 验证地区是否正确
		if (isEmpty(provinceId) || isEmpty(cityId) || isEmpty(countyId)
				|| ($("#span_town").html() != null && $("#span_town").html() != "" && !$("#span_town").is(":hidden") && isEmpty(townId))) {
			errorFlag = true;
			errorMessage = "请您填写完整的地区信息";
		}
	}
	// 验证收货人地址
	else if (divId == "address_div") {
		value = $("#consignee_address").val();
		if (isEmpty(value)) {
			errorFlag = true;
			errorMessage = "请您填写收货人详细地址";
		}
		if (!is_forbid(value)) {
			errorFlag = true;
			errorMessage = "收货人详细地址中含有非法字符";
		}
		if (value.length > 50) {
			errorFlag = true;
			errorMessage = "收货人详细地址过长";
		}
	}
	// 验证手机号码
	else if (divId == "call_mobile_div") {
		value = $("#consignee_mobile").val();
		divId = "call_div";
		if (isEmpty(value)) {
			errorFlag = true;
			errorMessage = "请您填写收货人手机号码";
		} else {
			if (!check_mobile_new(value)) {
				errorFlag = true;
				errorMessage = "手机号码格式不正确";
			}
			if(consignee_id == "" && value.indexOf("*") > -1){
				errorFlag = true;
				errorMessage = "手机号码格式不正确";
			}
		}

		if (!errorFlag) {
			value = $("#consignee_phone").val();
			if (!isEmpty(value)) {
				if (!is_forbid_new(value)) {
					errorFlag = true;
					errorMessage = "固定电话号码中含有非法字符";
				}
				if (!checkPhoneNew($("#consignee_mobile").val(),value)) {
					errorFlag = true;
					errorMessage = "固定电话号码格式不正确";
				}
				if(consignee_id == "" && value.indexOf("*") > -1){
					errorFlag = true;
					errorMessage = "固定电话号码格式不正确";
				}
			}
		}
	}
	//yanwenqi 全球购增加身份证号
	// 验证身份证号码
	else if (divId == "idCard_div") {
		if(flowType == 10){
			value = $("#consignee_idCard").val();
			if (isEmpty(value)) {
				errorFlag = true;
				errorMessage = "请您填写身份证号码";
			} else {
				//consignee_id为""，说明是新建地址，新建地址不能有*
				if(consignee_id == "" && value.indexOf("*") > -1){
					errorFlag = true;
					errorMessage = "身份证号码格式不正确";
				}
				//新增的地址判断身份证是否合法
				if(consignee_id == ""){
					if (!check_idCard(value)) {
						errorFlag = true;
						errorMessage = "身份证号码格式不正确";
					}
				}else{
					if(!check_idCard_new(value)){
						errorFlag = true;
						errorMessage = "身份证号码格式不正确";
					}
				}
			}
		}
	}
	//end
	// 验证电话号码
	else if (divId == "call_phone_div") {
		value = $("#consignee_phone").val();
		divId = "call_div";
		if (!isEmpty(value)) {
			if (!is_forbid_new(value)) {
				errorFlag = true;
				errorMessage = "固定电话号码中含有非法字符";
			}
			if (!checkPhoneNew($("#consignee_mobile").val(),value)) {
				errorFlag = true;
				errorMessage = "固定电话号码格式不正确";
			}
			if(consignee_id == "" && value.indexOf("*") > -1){
				errorFlag = true;
				errorMessage = "固定电话号码格式不正确";
			}
		}
		if (true) {
			value = $("#consignee_mobile").val();
			if (isEmpty(value)) {
				errorFlag = true;
				errorMessage = "请您填写收货人手机号码";
			} else {
				if (!check_mobile_new(value)) {
					errorFlag = true;
					errorMessage = "手机号码格式不正确";
				}
				if(consignee_id == "" && value.indexOf("*") > -1){
					errorFlag = true;
					errorMessage = "手机号码格式不正确";
				}
			}
		}
	}
	
	if (errorFlag) {
		$("#" + divId + "_error").html(errorMessage);
		$("#" + divId + "_error").addClass("message");
		return false;
	} else {
		$("#" + divId + "_error").removeClass("message");
		$("#" + divId + "_error").html("");
	}
	return true;
}

/**
 * 删除收货人验证提示信息
 */
function removeConsingeeMessage() {
	$("#name_div_error").removeClass("message");
	$("#area_div_error").removeClass("message");
	$("#address_div_error").removeClass("message");
	$("#call_div_error").removeClass("message");
	$("#email_div_error").removeClass("message");
	//yanwenqi 全球购添加身份证号
	$("#idCard_div_error").removeClass("message");
	//end
	$("#name_div_error").html("");
	$("#area_div_error").html("");
	$("#address_div_error").html("");
	$("#call_div_error").html("");
	$("#email_div_error").html("");
	//yanwenqi 全球购添加身份证号
	$("#idCard_div_error").html("");
	//end
}

/**
 * 获取省份列表
 */
function loadProvinces() {
	var actionUrl = OrderAppConfig.DynamicDomain + "/consignee/getProvinces.action";
	jQuery.ajax({
		type : "POST",
		dataType : "text",
		url : actionUrl,
		data : null,
		cache : false,
		success : function(dataResult, textStatus) {
			// 没有登录跳登录
			if (parent.isUserNotLogin(dataResult)) {
				parent.goToLogin();
				return;
			}
			if (parent.isHasMessage(dataResult)) {
				var message = parent.getMessage(dataResult);
				parent.showMessageWarn(message);
			} else {
				$("#span_province").html(dataResult);
			}
			tabIndex();
		},
		error : function(XMLHttpResponse) {
			//parent.showMessageWarn("系统繁忙，请稍后再试！");
			return false;
		}
	});
}

/**
 * 获取城市列表
 */
function loadCitys() {
	var provinceId = $("#consignee_province").find("option:selected").val();
	var provinceName = isEmpty(provinceId) ? "" : $("#consignee_province").find("option:selected").text().replace("*", "");
	$("#areaNameTxt").html(provinceName);
	if (provinceId == null || provinceId == "") {
		$("#span_city").html("<select class='selt' id=\"consignee_city\" name=\"consigneeParam.city\"><option selected=\"\" value=\"\">请选择：</option></select>");
		$("#span_county").html("<select class='selt' id=\"consignee_county\" name=\"consigneeParam.countyId\"><option selected=\"\" value=\"\">请选择：</option></select>");
		$("#span_town").html("");
		return;
	}
	var param = "consigneeParam.provinceId=" + provinceId;
	var actionUrl = OrderAppConfig.DynamicDomain + "/consignee/getCitys.action";
	jQuery.ajax({
		type : "POST",
		dataType : "text",
		url : actionUrl,
		data : param,
		cache : false,
		success : function(dataResult, textStatus) {
			// 没有登录跳登录
			if (parent.isUserNotLogin(dataResult)) {
				parent.goToLogin();
				return;
			}
			if (parent.isHasMessage(dataResult)) {
				var message = parent.getMessage(dataResult);
				parent.showMessageWarn(message);
			} else {
				$("#span_city").html(dataResult);
				$("#span_county").html(
						"<select class='selt' id=\"consignee_county\" name=\"consigneeParam.countyId\"><option selected=\"\" value=\"\">请选择：</option></select>");
				$("#span_town").html("");
			}
			tabIndex();
			$("#consignee_city").focus();
		},
		error : function(XMLHttpResponse) {
			//parent.showMessageWarn("系统繁忙，请稍后再试！");
			return false;
		}
	});
}

/**
 * 获取县级列表
 */
function loadCountys() {
	var cityId = $("#consignee_city").find("option:selected").val();
	var provinceName = $("#consignee_province").find("option:selected").text().replace("*", "");
	var cityName = isEmpty(cityId) ? "" : $("#consignee_city").find("option:selected").text().replace("*", "");
	$("#areaNameTxt").text(provinceName + cityName);
	var param = "consigneeParam.cityId=" + cityId;
	var actionUrl = OrderAppConfig.DynamicDomain + "/consignee/getCountys.action";
	jQuery.ajax({
		type : "POST",
		dataType : "text",
		url : actionUrl,
		data : param,
		cache : false,
		success : function(dataResult, textStatus) {
			// 没有登录跳登录
			if (parent.isUserNotLogin(dataResult)) {
				parent.goToLogin();
				return;
			}
			if (parent.isHasMessage(dataResult)) {
				var message = parent.getMessage(dataResult);
				parent.showMessageWarn(message);
			} else {
				if (dataResult != null) {
					$("#span_county").html(dataResult);
					$("#span_town").html("");
				}
			}
			tabIndex();
			$("#consignee_county").focus();
		},
		error : function(XMLHttpResponse) {
			//parent.showMessageWarn("系统繁忙，请稍后再试！");
			return false;
		}
	});
}

/**
 * 获取乡镇街道列表
 */
function loadTowns() {
	var countyId = $("#consignee_county").find("option:selected").val();
	var provinceName = $("#consignee_province").find("option:selected").text().replace("*", "");
	var cityName = $("#consignee_city").find("option:selected").text().replace("*", "");
	var countyName = isEmpty(countyId) ? "" : $("#consignee_county").find("option:selected").text().replace("*", "");
	$("#areaNameTxt").text(provinceName + cityName + countyName);

	var param = "consigneeParam.countyId=" + countyId;
	var actionUrl = OrderAppConfig.DynamicDomain + "/consignee/getTowns.action";
	jQuery.ajax({
		type : "POST",
		dataType : "text",
		url : actionUrl,
		data : param,
		cache : false,
		success : function(dataResult, textStatus) {
			// 没有登录跳登录
			if (parent.isUserNotLogin(dataResult)) {
				parent.goToLogin();
				return;
			}
			if (parent.isHasMessage(dataResult)) {
				var message = parent.getMessage(dataResult);
				parent.showMessageWarn(message);
			} else {
				if (dataResult != null && dataResult != "area_empty") {
					$("#span_town").html(dataResult);
					$("#span_town").show();
				} else {
					$("#span_town").html("");
					$("#span_town").hide();
				}
			}
			tabIndex();
			$("#consignee_town").focus();
		},
		error : function(XMLHttpResponse) {
			//parent.showMessageWarn("系统繁忙，请稍后再试！");
			return false;
		}
	});
}

function setTownName() {
	var provinceName = $("#consignee_province").find("option:selected").text().replace("*", "");
	var cityName = $("#consignee_city").find("option:selected").text().replace("*", "");
	var countyName = $("#consignee_county").find("option:selected").text().replace("*", "");
	var townName = $("#consignee_town").find("option:selected").text().replace("*", "");

	var provinceId = $("#consignee_province").find("option:selected").val();
	var cityId = $("#consignee_city").find("option:selected").val();
	var countyId = $("#consignee_county").find("option:selected").val();
	var townId = $("#consignee_town").find("option:selected").val();
	$("#areaNameTxt").text(provinceName + cityName + countyName + townName);
	tabIndex();
}

function tabIndex(){
	$("#consignee_name").attr("tabindex","1");
	$("#consignee_province").attr("tabindex","2");
	$("#consignee_city").attr("tabindex","3");
	$("#consignee_county").attr("tabindex","4");
	$("#consignee_town").attr("tabindex","5");
	$("#consignee_address").attr("tabindex","6");
	$("#consignee_mobile").attr("tabindex","7");
	$("#consignee_phone").attr("tabindex","8");
	$("#consignee_email").attr("tabindex","9");
}

/**
 * 手机号中间4位为*，所以去除*的校验
 * @param temp_str
 * @returns {Boolean}
 */
function is_forbid_new(temp_str){
    temp_str = trimTxt(temp_str);
	temp_str = temp_str.replace('--',"@");
	temp_str = temp_str.replace('/',"@");
	temp_str = temp_str.replace('+',"@");
	temp_str = temp_str.replace('\'',"@");
	temp_str = temp_str.replace('\\',"@");
	temp_str = temp_str.replace('$',"@");
	temp_str = temp_str.replace('^',"@");
	temp_str = temp_str.replace('.',"@");
	temp_str = temp_str.replace(';',"@");
	temp_str = temp_str.replace('<',"@");
	temp_str = temp_str.replace('>',"@");
	temp_str = temp_str.replace('"',"@");
	temp_str = temp_str.replace('=',"@");
	temp_str = temp_str.replace('{',"@");
	temp_str = temp_str.replace('}',"@");
	var forbid_str=new String('@,%,~,&');
	var forbid_array=new Array();
	forbid_array=forbid_str.split(',');
	for(i=0;i<forbid_array.length;i++){
		if(temp_str.search(new RegExp(forbid_array[i])) != -1)
		return false;
	}
	return true;
}

/**
 * 邮箱校验新规则
 * @param id
 * @returns {Boolean}
 */
function check_consignee_email(id){
	var errorFlag = false;
	var errorMessage ="";
	value = $("#"+id).val();
	if (!isEmpty(value)) {
		if (value.length > 50) {
			errorFlag = true;
			errorMessage = "邮箱长度不能大于50位";
		}
		if (!check_email_new(value)) {
			errorFlag = true;
			errorMessage = "邮箱格式不正确";
		}
	}
	if (errorFlag) {
		$("#email_div_error").html(errorMessage);
		$("#email_div").addClass("message");
		return false;
	} else {
		$("#email_div").removeClass("message");
		$("#email_div_error").html("");
	}
	return true;
}