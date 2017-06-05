<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- saved from url=(0083)http://trade.jd.com/shopping/dynamic/consignee/editConsignee.action?t=1448456276919 -->
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link type="text/css" rel="stylesheet" href="/mycss/ui-base.css">
	<script type="text/javascript" src="/js/jquery-1.6.4.js"></script>
	<script type="text/javascript" src="/js/base.js"></script>
	<link type="text/css" rel="stylesheet" href="/mycss/common.css" source="widget">
	<script type="text/javascript" src="/js/order.common.js"></script>
	<script type="text/javascript" src="/js/jquery.checkout.js"></script>
	
	<script type="text/javascript" src="/myjs/order_consignee.js"></script>
	<script type="text/javascript">
		function confirm(){
			var consigneeId=$("#consignee_form_id").val();
			$.ajax({
				   type: "POST",
				   url: "http://www.taotao.com/service/consignee/addConsignee/"+consigneeId,
				   data: $("#confirmSubmit").serialize(), //表单序列化，将所有的输入内容转化成K/V数据格式
				   statusCode : {
					   200 : function(){
						   window.parent.location.reload();
						   //$.messager.alert('提示','新增商品成功!');
					   },
					   500 : function(){
						   $.messager.alert('提示','新增收货人失败!');
					   }
				   }
				});
		}
	</script>
	<style type="text/css">
body{ background:#EEEEEE;margin:0; padding:0; font-family:"微软雅黑", Arial, Helvetica, sans-serif; }
a{ color:#006600; text-decoration:none;}
a:hover{color:#990000;}
.top{ margin:5px auto; color:#990000; text-align:center;}
.info select{ border:1px #993300 solid; background:#FFFFFF;}
.info{ margin:5px; text-align:center;}
.info #show{ color:#3399FF; }
.bottom{ text-align:right; font-size:12px; color:#CCCCCC; width:1000px;}
</style>
<script type="text/javascript">
var Gid  = document.getElementById ;
var showArea = function(){
	Gid('show').innerHTML = "<h3>省" + Gid('s_province').value + " - 市" + 	
	Gid('s_city').value + " - 县/区" + 
	Gid('s_county').value + "</h3>"
							};
Gid('s_county').setAttribute('onchange','showArea()');
</script>
<script type="text/javascript" src="/myjs/area.js"></script>
</head>
<body marginwidth="0" marginheight="0">
<script>if(0==1)parent.goOrder();</script>

<form  method="post" id="confirmSubmit">
<input type="hidden" id="hidden_consignee_townName_" name="townName" value="empty">
  <input type="hidden" id="hidden_consignee_provinceId_" name="provinceId" value="0">
  <input type="hidden" id="hidden_consignee_cityId_" name="cityId" value="0">
  <input type="hidden" id="hidden_consignee_countyId_" name="countryId" value="0">
  <input type="hidden" id="hidden_consignee_townId_" name="townId" value="0">
  <input type="hidden" id="isUpdateCommonAddress" name="isUpdateCommonAddress" value="0">
<div class="form" id="consignee-form" name="consignee-form">
	<div class="item" id="name_div">
		<span class="label"><span style="color:red">*</span>&nbsp;收货人：</span>
		<div class="fl">
		<c:choose>
			<c:when test="${consignee.id > 0}">
				<input type="hidden" id="consignee_form_id" name="id" value="${consignee.id}">
			</c:when>
			<c:otherwise>
				<input type="hidden" id="consignee_form_id" name="id" value="0">
			</c:otherwise>
		</c:choose>
		    <input type="hidden" id="consignee_type" name="type" value="0">
		    <input type="hidden" id="consignee_ceshi1" name="consignee_ceshi1" value="">
			<input type="text" class="itxt" id="consignee_name" name="name" value="${consignee.name}" maxlength="20"  tabindex="1">
			<span class="error-msg" id="name_div_error"></span>
		</div>
	</div>
			<!-- yanwenqi  全球购添加身份证idCard-->
				<!-- end -->
	<div class="item" id="area_div">
		<span class="label"><span style="color:red">*</span>&nbsp;所在地区：</span>
		<!-- <div class="fl">
			<span id="span_area">
			  <span id="span_province"><select class="selt" id="consignee_province" name="consigneeParam.provinceId" onchange="loadCitys()" tabindex="2"><option value="">请选择：</option><option value="1">北京</option><option value="2">上海</option><option value="3">天津</option><option value="4">重庆</option><option value="5">河北</option><option value="6">山西</option><option value="7">河南</option><option value="8">辽宁</option><option value="9">吉林</option><option value="10">黑龙江</option><option value="11">内蒙古</option><option value="12">江苏</option><option value="13">山东</option><option value="14">安徽</option><option value="15">浙江</option><option value="16">福建</option><option value="17">湖北</option><option value="18">湖南</option><option value="19">广东</option><option value="20">广西</option><option value="21">江西</option><option value="22">四川</option><option value="23">海南</option><option value="24">贵州</option><option value="25">云南</option><option value="26">西藏</option><option value="27">陕西</option><option value="28">甘肃</option><option value="29">青海</option><option value="30">宁夏</option><option value="31">新疆</option><option value="32">台湾</option><option value="42">香港</option><option value="43">澳门</option><option value="84">钓鱼岛</option></select></span>
			   <span id="span_city"><select class="selt" id="consignee_city" name="consigneeParam.cityId" tabindex="3"><option value="">请选择：</option></select></span>
			   <span id="span_county"><select class="selt" id="consignee_county" name="consigneeParam.countyId" tabindex="4"><option value="">请选择：</option></select></span>
			   <span id="span_town" style="display:none"><select class="selt" id="consignee_town" name="consigneeParam.townId" tabindex="5"><option value="">请选择：</option></select></span>
	        </span>
			<span class="error-msg" id="area_div_error"></span>
			div class="ftx-03">标“*”的为支持货到付款的地区，<a href="" target="_Blank" class="ftx-05" id="codHelpUrl">查看货到付款地区</a></div
		</div> -->
		<div class="fl">
		<span id="span_area">
	 <span id="span_province"><select class="selt" id="s_province" name="provinceName"></select></span>  
     <span id="span_city"><select class="selt" id="s_city" name="cityName"></select> </span> 
   <span id="span_county"><select class="selt" id="s_county" name="countryName"></select></span>
   <script class="resources library" src="area.js" type="text/javascript"></script>
    <script type="text/javascript">_init_area();</script>
    <span id="show"></span>
      </span>
</div>
	</div>
	<div class="item">
		<span class="label" id="address_div"><span style="color:red">*</span>&nbsp;详细地址：</span>
		<div class="fl">
			<!--span id="areaNameTxt"></span-->
			<input type="text" class="itxt itxt02" id="consignee_address" name="address" value="${consignee.address}" maxlength="50" onblur="check_Consignee(&#39;address_div&#39;)" value="" tabindex="6">
			<span class="error-msg" id="address_div_error"></span>
		</div>
	</div>
	<div class="item" id="call_div">
		<span class="label"><span style="color:red">*</span>&nbsp;手机号码：</span>
		<div class="fl">
			<input type="text" class="itxt " id="consignee_mobile" name="mobile" value="${consignee.mobile}" onblur="check_Consignee(&#39;call_mobile_div&#39;)" onfocus="if(value == defaultValue){value=&#39;&#39;;}" maxlength="11" value="" tabindex="7">
		</div>
		<div class="fl">
			<span class="label">固定电话：</span>
			<input type="text" class="itxt " id="consignee_phone" name="phone" value="${consignee.phone}" onblur="check_Consignee(&#39;call_phone_div&#39;)" onfocus="if(value == defaultValue){value=&#39;&#39;;}" maxlength="20" value="" tabindex="8">
		</div>
		<span class="error-msg" id="call_div_error"></span>
	</div>
	<div class="item" id="email_div">
		<span class="label">邮箱：</span>
		<div class="fl">
			<input type="text" class="itxt" id="consignee_email" name="email" value="${consignee.email}" maxlength="50" onblur="check_Consignee(&#39;email_div&#39;)" value="" onfocus="if(value == defaultValue){value=&#39;&#39;;}" tabindex="9">
			<span class="error-msg" id="email_div_error"></span>
			<div class="ftx-03">用来接收订单提醒邮件，便于您及时了解订单状态</div>
		</div>
	</div>
	<div class="item">
		<span class="label">&nbsp;</span>
		<div class="fl">
			<!-- <a href="javascript:void(0);" class="btn-9" onclick="save_Consignee()"><span id="saveConsigneeTitleDiv">保存收货人信息</span></a> -->
			<input  type="button" value="保存收货人信息" onclick="confirm();"/>
			<div class="loading loading-1" style="display:none"><b></b>正在提交信息，请等待！</div>
			<!--a href="#none" class="btn-9 ml10">取消</a-->
		</div>
		<div style="display:none"><input id="consignee_form_reset" name="" type="reset"></div>
	</div>
	
		<!--yanwenqi 全球购添加身份证号下面-->
			<!--end-->
</div>
</form>
<span id="addNumLimitNote" class="status error" style="display:none">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 当前地址数量已达上限，若要继续添加新地址，请先删除部分收货地址。</span>
	

<script>
$("#consignee_name").focus();
if(-1==-1) loadProvinces(); else show_ConsigneeDetail(-1);
$("#consignee_name").focus();
</script>
	

	
	

</body></html>