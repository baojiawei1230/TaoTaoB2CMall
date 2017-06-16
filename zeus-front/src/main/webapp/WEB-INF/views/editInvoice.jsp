<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="/info/orderInfo1.css">
<script type="text/javascript" src="/js/jquery-1.6.4.js"></script>
<script type="text/javascript" src="/info/orderInfo.js"></script>
<link type="text/css" rel="stylesheet" href="/info/orderInfo2.css"
	source="widget">
<script type="text/javascript" src="/info/order.common.js"></script>
<script type="text/javascript" src="/info/order_invoice.js"></script>
<script type="text/javascript" src="/info/invoice.js"></script>
<script type="text/javascript">
	$(function(){
		$(".invoice-item").each(function(){
			$(this).click(function(){
				$(this).siblings($(".invoice-item :selected")).removeClass(".invoice-item invoice-item-selected");
				$(this).addClass(".invoice-item invoice-item-selected");
			});
		});
	});
	
	$(function(){
		$(".tab-nav-item").each(function(){
			$(this).click(function(){
				$(this).siblings($(".invoice-item :selected")).removeClass(".tab-nav-item tab-item-selected");
				$(this).addClass(".tab-nav-item tab-item-selected");
			});
		});
		
	})
	//取消修改操作.
	function quxiao(){
		window.location.href = "about:blank";
		window.parent.jQuery.closeDialog();
	}
	
	
	
</script>
</head>
<body marginwidth="0" marginheight="0">
	<input type="hidden" id="hasBook" value="false">
	<input type="hidden" id="hasCommon" value="true">
	<input type="hidden" id="invokeInvoiceBasicService" value="true">
	
	<div class="invoice-thickbox" id="invoice-tab">
		<div class="tab-nav">
			<ul>
				<li id="click_1" class="tab-nav-item tab-item-selected" value="1">普通发票<b></b></li>
				<li id="click_2" class="tab-nav-item" value="3">电子发票<b></b></li>
				<li id="click_3" class="tab-nav-item" value="2">增值税发票<b></b></li>






			</ul>
		</div>
		<div id="fapiao" class="form" style="display: block;">
			<div class="item">
				<span class="label">发票抬头：</span>
				<div class="fl">
					<div class="invoice-list invoice-tit-list" id="invoice-tit-list">
						<div class="invoice-item invoice-item-selected"
							style="cursor: pointer">
							<input type="hidden" id="commonInvoiceSize">
							<div id="invoice-1" style="cursor: pointer">
								<span class="hide"><input type="hidden" value="4"></span>
								<span class="fore2" id="" name="usualInvoiceList" value="1100"><input
									type="text" style="cursor: pointer" class="itxt" data-r="个人"
									value="个人" readonly="true"><b></b></span>
							</div>
						</div>

						<div id="save-invoice" class="invoice-item hide">
							<div class="add-invoice-tit">
								<input type="text" name="" class="itxt itxt04"
									placeholder="新增单位发票抬头">
								<div class="btns">
									<a href="#none" class="ftx-05 save-tit">保存</a>
								</div>
							</div>
						</div>

					</div>
					<div id="add-invoice" class="add-invoice" style="display: block;">
						<a href="#none" class="ftx-05 add-invoice-btn"
							onclick="add_save()">新增单位发票</a>
					</div>

				</div>
			</div>
		</div>
		<div class="tab-box">
			<div class="tab-con ui-switchable-panel-selected"
				style="display: none;">
				<div class="form">
					<div class="item">
						<span class="label">发票内容：</span>

						<div class="fl">
							<div class="invoice-list">
								<ul id="electro_book_content_radio">
									<li class="invoice-item" id="electro-invoice-content-1"
										name="normal-normalContent" value="1" style="cursor: pointer">明细<b></b>
									</li>
									<li class="invoice-item" id="electro-invoice-content-2"
										name="normal-normalContent" value="2" style="cursor: pointer">
										办公用品<b></b> <!-- invoice-item-selected -->
									</li>
									<li class="invoice-item" id="electro-invoice-content-3"
										name="normal-normalContent" value="3" style="cursor: pointer">
										电脑配件<b></b>
									</li>
									<li class="invoice-item" id="electro-invoice-content-19"
										name="normal-normalContent" value="19" style="cursor: pointer">
										耗材<b></b>
									</li>
								</ul>
							</div>
						</div>
					</div>

					<div class="item">

						<div class="fl">
							<div class="invoice-list">
								<ul>
								</ul>
							</div>
						</div>
					</div>

					<div id="giftInvoicePart" class="hide">
						<div class="item">
							<span class="label">配送方式：</span>
							<div class="fl">
								<div class="invoice-list">
									<ul>
										<li class="invoice-item   invoice-item-selected "
											onclick="changeGiftType(0)" style="cursor: pointer">随礼品寄送<b></b></li>
										<li class="invoice-item  " onclick="changeGiftType(3)"
											style="cursor: pointer">单独寄送<b></b></li>
									</ul>
								</div>
							</div>
						</div>


						<input type="radio" style="display: none" id="invoiceSendType_0"
							value="0" name="invoiceSendType" checked=""> <input
							type="radio" style="display: none" id="invoiceSendType_3"
							value="3" name="invoiceSendType">

						<div id="sendSeparateView" style="display: none">

							<div class="item" id="generalInvoice_name_div">
								<span class="label"><em>*</em>收票人姓名：</span>
								<div class="fl">
									<input type="text" class="itxt itxt04"
										id="generalInvoice_consignee_name"
										name="invoiceParam.consigneeName" value="" maxlength="20"
										onblur="check_InvoiceConsignee(&#39;generalInvoice_name_div&#39;,true)">
									<span class="message" id="generalInvoice_name_div_error"></span>
								</div>
							</div>
							<div class="item" id="generalInvoice_call_div">
								<span class="label"><em>*</em>收票人手机：</span>
								<div class="fl">
									<input type="text" class="itxt itxt04"
										id="generalInvoice_consignee_mobile"
										name="invoiceParam.consigneePhone" value=""
										onblur="check_InvoiceConsignee(&#39;generalInvoice_call_phone_div&#39;,true)"
										maxlength="11"
										onfocus="if(value == defaultValue){value=&#39;&#39;;}">
									<span class="message" id="generalInvoice_call_div_error"></span>
								</div>
							</div>

							<div class="item" id="generalInvoice_area_div">
								<span class="label"><em>*</em>收票人省份：</span>
								<div class="fl" id="generalInvoice_span_area">
									<span id="span_province"><select class="selt"
										id="consignee_province" name="consigneeParam.provinceId"
										onchange="loadCitys()"><option value="">请选择：</option>
											<option value="1">北京</option>
											<option value="2">上海</option>
											<option value="3">天津</option>
											<option value="4">重庆</option>
											<option value="5">河北</option>
											<option value="6">山西</option>
											<option value="7">河南</option>
											<option value="8">辽宁</option>
											<option value="9">吉林</option>
											<option value="10">黑龙江</option>
											<option value="11">内蒙古</option>
											<option value="12">江苏</option>
											<option value="13">山东</option>
											<option value="14">安徽</option>
											<option value="15">浙江</option>
											<option value="16">福建</option>
											<option value="17">湖北</option>
											<option value="18">湖南</option>
											<option value="19">广东</option>
											<option value="20">广西</option>
											<option value="21">江西</option>
											<option value="22">四川</option>
											<option value="23">海南</option>
											<option value="24">贵州</option>
											<option value="25">云南</option>
											<option value="26">西藏</option>
											<option value="27">陕西</option>
											<option value="28">甘肃</option>
											<option value="29">青海</option>
											<option value="30">宁夏</option>
											<option value="31">新疆</option>
											<option value="32">台湾</option>
											<option value="42">香港</option>
											<option value="43">澳门</option>
											<option value="84">钓鱼岛</option></select></span><span id="span_city"><select
										id="consignee_city" name="invoiceParam.consigneeCity"
										class="selt"><option value="">请选择</option></select></span><span
										id="span_county"><select id="consignee_county"
										name="invoiceParam.consigneeCountry" class="selt"><option
												value="">请选择</option></select></span><span id="span_town"
										style="display: none"><select class="selt"
										id="consignee_town" name="invoiceParam.consigneeTown"><option
												value="">请选择</option></select></span>
								</div>
								<span id="generalInvoice_area_div_error"></span>
							</div>
							<div class="item" id="generalInvoice_address_div">
								<span class="label"><em>*</em>详细地址：</span>
								<div class="fl">
									<input type="text" class="itxt itxt04"
										id="generalInvoice_consignee_address"
										name="invoiceParam.consigneeAddress" value="" maxlength="50"
										onblur="check_InvoiceConsignee(&#39;generalInvoice_address_div&#39;,true)">
								</div>
								<span class="message" id="generalInvoice_address_div_error"></span>
							</div>
						</div>
					</div>

					<div class="item">
						<span class="label">&nbsp;</span>
						<div class="fl">
							<div class="op-btns">
								<a href="javascript:void(0);" class="btn-9"
									onclick="save_Invoice(true)">保存发票信息</a> <a
									href="javascript:void(0);" class="btn-9 ml10"
									onclick="quxiao()">取消</a>
							</div>
							<div class="ftx-03 mt10">
								温馨提示：发票的开票金额不包括京东卡/京东E卡、优惠券和京豆支付部分<br> <a
									href="http://help.jd.com/user/issue/list-182.html"
									target="_blank" class="ftx-05">发票信息相关问题&gt;&gt;</a>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="tab-con" style="display: block;">
				<div class="prompt-box">
					电子发票是税务局认可的有效收付款凭证，具有售后维权的法律效力，暂不支持企业报销<br> <a target="_blank"
						class="ftx-05"
						href="http://help.jd.com/user/issue/list-182-184.html">什么是电子发票</a>
				</div>
				<input type="hidden" id="invoice_ceshi1" name="invoice_ceshi1"
					value="">
				<div class="form">
					<div class="item">
						<span class="label">发票抬头：</span>
						<div class="fl">
							<div class="invoice-list invoice-tit-list"
								id="invoice-tit-list_lest">
								<div class="invoice-item invoice-item-selected"
									style="cursor: pointer">
									<div id="invoice-2" style="cursor: pointer">
										<span class="hide"><input type="hidden" value="4"></span>
										<span class="fore2"><input type="text"
											style="cursor: pointer" class="itxt" id="fff" value="个人"
											readonly="true"><b></b></span>
									</div>
								</div>


							</div>
						</div>
					</div>
					<div class="item">
						<span class="label">发票内容：</span>
						<div class="fl">
							<div class="invoice-list">
								<ul id="electro_book_content_radio">
									<li class="invoice-item invoice-item-selected"
										id="electro-invoice-content-1" name="electro-normalContent"
										value="1" style="cursor: pointer">明细<b></b>
									</li>
									<li class="invoice-item" id="electro-invoice-content-2"
										name="electro-normalContent" value="2" style="cursor: pointer">
										办公用品<b></b>
									</li>
									<li class="invoice-item" id="electro-invoice-content-3"
										name="electro-normalContent" value="3" style="cursor: pointer">
										电脑配件<b></b>
									</li>
									<li class="invoice-item" id="electro-invoice-content-19"
										name="electro-normalContent" value="19"
										style="cursor: pointer">耗材<b></b>
									</li>
								</ul>
							</div>
						</div>
					</div>

					<div class="item">
						<div class="fl">
							<div class="invoice-list">
								<ul>
								</ul>
							</div>
						</div>
					</div>
					<div class="item">
						<span class="label"><em>*</em>收票人手机：</span>
						<div class="fl">
							<input type="text" class="itxt itxt03" id="e_consignee_mobile"
								name="invoiceParam.electroInvoicePhone" value="182****4625"
								onblur="check_electroInvoicePhone()" maxlength="11"
								onfocus="if(value == defaultValue){value=&#39;&#39;;}">
						</div>
						<span class="message" id="e_consignee_mobile_error"></span>
					</div>
					<div class="item">
						<span class="label">收票人邮箱：</span>
						<div class="fl">
							<input type="text" class="itxt itxt03" id="e_consignee_email"
								name="invoiceParam.electroInvoiceEmail" value=""
								onblur="check_electroInvoiceEmail()"
								onfocus="if(value == defaultValue){value=&#39;&#39;;}">
						</div>
						<span class="message" id="e_consignee_email_error"></span>
					</div>

					<div class="item">
						<span class="label">&nbsp;</span>
						<div class="fl">
							<div class="op-btns">
								<a href="javascript:void(0);" class="btn-9"
									onclick="save_Invoice()">保存发票信息</a> <a
									href="javascript:void(0);" class="btn-9 ml10"
									onclick="quxiao()">取消</a>
							</div>
							<div class="ftx-03 mt10">
								温馨提示：发票的开票金额不包括京东卡/京东E卡、优惠券和京豆支付部分<br> <a
									href="http://help.jd.com/user/issue/list-182.html"
									target="_blank" class="ftx-05">发票信息相关问题&gt;&gt;</a>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="tab-con" style="display: none;">
				<div class="prompt-box">
					<span class="ftx-05"><a
						href="http://storage.jd.com/doc/%E6%8E%88%E6%9D%83%E5%A7%94%E6%89%98%E4%B9%A6.docx"
						target="_blank" class="ftx-05">委托书下载</a> | <a
						href="http://help.jd.com/user/issue/list-182-313.html"
						target="_blank" class="ftx-05">发票制度说明</a> | <a
						href="http://help.jd.com/user/issue/list-182-185.html"
						target="_blank" class="ftx-05">首次开具增值税发票阅读</a></span>
				</div>
				<div class="form" id="invoice-box-03">
					<ul class="invoice-status">
						<li class="fore1 curr">1.填写公司信息<b></b></li>
						<li class="fore2 ">2.填写收票人信息<b></b></li>
					</ul>
					<div class="steps">
						<div class="step step1">
							<input type="hidden" id="vatCanEdit" value="false">
							<div class="item">
								<span class="label" id="vat_companyName_div"><em>*</em>单位名称：</span>
								<div class="fl">
									<input type="text" class="itxt itxt04 vat-step-1"
										name="vat_companyName" id="vat_companyName" value=""
										onblur="check_Invoice(&#39;vat_companyName&#39;, this.value)">
									<span class="message error-msg" id="vat_companyName_error">单位名称不能为空！</span>
								</div>
							</div>
							<div class="item" id="vat_code_div">
								<span class="label"><em>*</em>纳税人识别码：</span>
								<div class="fl">
									<input type="text" class="itxt itxt04 vat-step-1"
										name="vat_code" id="vat_code" value=""
										onblur="check_Invoice(&#39;vat_code&#39;, this.value)">
									<span class="message" id="vat_code_error"></span>
								</div>
							</div>
							<div class="item" id="vat_address_div">
								<span class="label"><em>*</em>注册地址：</span>
								<div class="fl">
									<input type="text" class="itxt itxt04 vat-step-1"
										name="vat_address" id="vat_address" value=""
										onblur="check_Invoice(&#39;vat_address&#39;, this.value)">
									<span class="message" id="vat_address_error"></span>

								</div>
							</div>
							<div class="item" id="vat_phone_div">
								<span class="label"><em>*</em>注册电话：</span>
								<div class="fl">
									<input type="text" class="itxt itxt04 vat-step-1"
										name="vat_phone" id="vat_phone" value=""
										onblur="check_Invoice(&#39;vat_phone&#39;, this.value)">
									<span class="message" id="vat_phone_error"></span>
								</div>
							</div>
							<div class="item" id="vat_bankName_div">
								<span class="label"><em>*</em>开户银行：</span>
								<div class="fl">
									<input type="text" class="itxt itxt04 vat-step-1"
										name="vat_bankName" id="vat_bankName" value=""
										onblur="check_Invoice(&#39;vat_bankName&#39;, this.value)">
									<span class=" message" id="vat_bankName_error"></span>
								</div>
							</div>
							<div class="item" id="vat_bankAccount_div">
								<span class="label"><em>*</em>银行账户：</span>
								<div class="fl">
									<input type="text" class="itxt itxt04 vat-step-1"
										name="vat_bankAccount" id="vat_bankAccount" value=""
										onblur="check_Invoice(&#39;vat_bankAccount&#39;, this.value)">
									<span class="message" id="vat_bankAccount_error"></span>
								</div>
							</div>
							<div class="item">
								<span class="label">&nbsp;</span>
								<div class="fl">
									<div class="op-btns">
										<input id="vat-btn-save" type="button" class="btn-9"
											onclick="nextAvt()" value="下一步"> <input
											id="vat-btn-cancel" type="button" class="btn-9"
											onclick="quxiao()" value="取消">
									</div>
									<div class="ftx-03 mt10">
										温馨提示：发票的开票金额不包括京东卡/京东E卡、优惠券和京豆支付部分<br> <a
											href="http://help.jd.com/user/issue/list-182.html"
											target="_blank" class="ftx-05">发票信息相关问题&gt;&gt;</a>
									</div>
								</div>
							</div>
						</div>
						<div class="step step2 hide">
							<div class="item">
								<span class="label">发票内容：</span>
								<div class="fl">
									<div class="invoice-list">
										<ul>
											<li class="invoice-item invoice-item-selected"
												id="vat-invoice-content-$bookContent.value"
												name="vat-normalContent" value="1" style="cursor: pointer">明细<b></b>
											</li>
										</ul>
									</div>
								</div>
							</div>
							<input type="hidden" id="vatConsigneeInfo" value=",,0,0,0,0,">
							<div class="item" id="name_div">
								<span class="label"><em>*</em>收票人姓名：</span>
								<div class="fl">
									<input type="text" class="itxt itxt04" id="consignee_name"
										name="invoiceParam.consigneeName" value="" maxlength="20"
										onblur="check_InvoiceConsignee(&#39;name_div&#39;)"> <span
										class="message" id="name_div_error"></span>
								</div>
							</div>
							<div class="item" id="call_div">
								<span class="label"><em>*</em>收票人手机：</span>
								<div class="fl">
									<input type="text" class="itxt itxt04" id="consignee_mobile"
										name="invoiceParam.consigneePhone" value=""
										onblur="check_InvoiceConsignee(&#39;call_phone_div&#39;)"
										maxlength="11"
										onfocus="if(value == defaultValue){value=&#39;&#39;;}">
									<span class="message" id="call_div_error"></span>
								</div>
							</div>

							<div class="item" id="area_div">
								<span class="label"><em>*</em>收票人省份：</span>
								<div class="fl" id="span_area"></div>
								<span id="area_div_error"></span>
							</div>
							<div class="item" id="address_div">
								<span class="label"><em>*</em>详细地址：</span>
								<div class="fl">
									<input type="text" class="itxt itxt04" id="consignee_address"
										name="invoiceParam.consigneeAddress" value="" maxlength="50"
										onblur="check_InvoiceConsignee(&#39;address_div&#39;)">
								</div>
								<span class="message" id="address_div_error"></span>
							</div>
							<div class="item">
								<span class="label">&nbsp;</span>
								<div class="fl">
									<div class="op-btns">
										<a href="#none" class="btn-9" onclick="save_Invoice()">保存</a>
										<a href="javascript:void(0);" class="btn-9 ml10"
											onclick="quxiao()">取消</a> <a href="javascript:void(0);"
											class="ftx-05 ml10 prev" onclick="prev()">返回上一步</a>
									</div>
									<div class="ftx-03 mt10">
										温馨提示：发票的开票金额不包括京东卡/京东E卡、优惠券和京豆支付部分<br> <a
											href="http://help.jd.com/user/issue/list-182.html"
											target="_blank" class="ftx-05">发票信息相关问题&gt;&gt;</a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

	</div>
	<script type="text/javascript">

//编辑抬头
if(2 != 2 || false){
  $('#click_3').addClass('disabled');
  $('#click_3').attr('title', '您的订单中部分商品不支持此发票类型');
}

if(3 != 3){
  $('#click_2').addClass('disabled');
  $('#click_2').attr('title', '您的订单中部分商品不支持此发票类型');
}

if(true){
  $('#fapiao').show();
  $('#add-invoice').show();
}

if(false){
  $('#add-invoice').hide();
  $('#fapiao').hide();         
}
  
if(false){
  $('#add-invoice').hide();
  $('#fapiao').hide();
}

</script>


</body>
</html>