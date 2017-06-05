<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>我的淘淘--我的订单</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<meta name="format-detection" content="telephone=no" />
<link type="text/css" rel="stylesheet"
	href="//misc.360buyimg.com/jdf/1.0.0/unit/??ui-base/1.0.0/ui-base.css,shortcut/2.0.0/shortcut.css,global-header/1.0.0/global-header.css,myjd/2.0.0/myjd.css,nav/2.0.0/nav.css,shoppingcart/2.0.0/shoppingcart.css,global-footer/1.0.0/global-footer.css,service/1.0.0/service.css,basePatch/1.0.0/basePatch.css" />



<script type="text/javascript"
	src="//misc.360buyimg.com/jdf/1.0.0/unit/??base/1.0.0/base.js,basePatch/1.0.0/basePatch.js"
	charset="gb2312"></script>

<script type="text/javascript"
	src="//misc.360buyimg.com/jdf/??lib/jquery-1.6.4.js,1.0.0/unit/libPath/1.0.0/libPath.js"></script>

<script language="javascript" type="text/javascript">
	if (window.top !== window.self) {
		window.top.location = window.location;
	}
</script>
</head>
<body myjd="_MYJD_ordercenter">
	<div id="shortcut-2014">
		<div class="w">
			<ul class="fl">
				<li class="dorpdown" id="ttbar-mycity"></li>
			</ul>
			<ul class="fr">
				<li class="fore1" id="ttbar-login"><a target="_blank"
					href="javascript:login();" class="link-login">你好，请登录</a>&nbsp;&nbsp;<a
					href="javascript:regist();" class="link-regist style-red">免费注册</a>
				</li>
				<li class="spacer"></li>
				<li class="fore2">
					<div class="dt">
						<a target="_blank" href="http://order.jd.com/center/list.action">我的订单</a>
					</div>
				</li>
				<li class="spacer"></li>
				<li class="fore3 dorpdown" id="ttbar-myjd">
					<div class="dt cw-icon">
						<i class="ci-right"><s>◇</s></i> <a target="_blank"
							href="http://home.jd.com/">我的淘淘</a>
					</div>
					<div class="dd dorpdown-layer"></div>
				</li>
				<li class="spacer"></li>
				<li class="fore4">
					<div class="dt">
						<a target="_blank" href="http://vip.jd.com/">淘淘会员</a>
					</div>
				</li>
				<li class="spacer"></li>
				<li class="fore5">
					<div class="dt">
						<a target="_blank" href="http://b.jd.com/">企业采购</a>
					</div>
				</li>
				<li class="spacer"></li>
				<li class="fore6 dorpdown" id="ttbar-apps">
					<div class="dt cw-icon">
						<i class="ci-left"></i> <i class="ci-right"><s>◇</s></i> <a
							target="_blank" href="http://app.jd.com/">手机淘淘</a>
					</div>
				</li>
				<li class="spacer"></li>
				<li class="fore7 dorpdown" id="ttbar-atte">
					<div class="dt cw-icon">
						<i class="ci-right"><s>◇</s></i>关注淘淘
					</div>
				</li>
				<li class="spacer"></li>
				<li class="fore8 dorpdown" id="ttbar-serv">
					<div class="dt cw-icon">
						<i class="ci-right"><s>◇</s></i>客户服务
					</div>
					<div class="dd dorpdown-layer"></div>
				</li>
				<li class="spacer"></li>
				<li class="fore9 dorpdown" id="ttbar-navs">
					<div class="dt cw-icon">
						<i class="ci-right"><s>◇</s></i>网站导航
					</div>
					<div class="dd dorpdown-layer"></div>
				</li>
			</ul>
			<span class="clr"></span>
		</div>
	</div>
	<div id="o-header-2013">
		<div id="header-2013" style="display: none;"></div>
	</div>
	<div class="w">
		<div id="logo-2014">
			<img src="http://static.taotao.com/images/taotao-logo.gif"
				width="270" height="60" alt="淘淘">
		</div>
		<div id="search-2014">
			<ul id="shelper" class="hide"></ul>
			<div class="form">
				<input type="text"
					onkeydown="javascript:if(event.keyCode==13) search('key');"
					autocomplete="off" id="key" accesskey="s" class="text" />
				<button onclick="search('key');return false;" class="button cw-icon">
					<i></i>搜索
				</button>
			</div>
		</div>
		<div id="settleup-2014" class="dorpdown">
			<div class="cw-icon">
				<i class="ci-left"></i> <i class="ci-right">&gt;</i> <a
					target="_blank" href="http://cart.jd.com/cart/cart.html">我的购物车</a>
			</div>
			<div class="dorpdown-layer">
				<div class="spacer"></div>
				<div id="settleup-content">
					<span class="loading"></span>
				</div>
			</div>
		</div>
		<div id="hotwords-2014"></div>
		<span class="clr"></span>
	</div>
	<div id="nav-2014">
		<div class="w">
			<div class="w-spacer"></div>
			<div id="categorys-2014" class="dorpdown" data-type="default">
				<div class="dt">
					<a target="_blank" href="http://www.taotao.com/allSort.aspx">全部商品分类</a>
				</div>
			</div>
			<div id="navitems-2014">
				<ul id="navitems-group1">
					<li id="nav-fashion" class="fore1"><a target="_blank"
						href="http://channel.jd.com/fashion.html">服装城</a></li>
					<li id="nav-beauty" class="fore2"><a target="_blank"
						href="http://channel.jd.com/beauty.html">美妆馆</a></li>
					<li id="nav-chaoshi" class="fore3"><a target="_blank"
						href="http://channel.jd.com/chaoshi.html">超市</a></li>
					<li id="nav-jdww" class="fore4"><a target="_blank"
						href="http://www.jd.hk/">全球购</a></li>
				</ul>
				<div class="spacer"></div>
				<ul id="navitems-group2">
					<li id="nav-red" class="fore1"><a target="_blank"
						href="http://red.jd.com/">闪购</a></li>
					<li id="nav-tuan" class="fore2"><a target="_blank"
						href="http://tuan.jd.com/">团购</a></li>
					<li id="nav-auction" class="fore3"><a target="_blank"
						href="http://paimai.jd.com/">拍卖</a></li>
					<li id="nav-jr" class="fore4"><a target="_blank"
						href="http://jr.jd.com/">金融</a></li>
					<li id="nav-smart" class="fore5"><a target="_blank"
						href="http://smart.jd.com/">智能</a></li>
				</ul>
			</div>
			<div id="treasure"></div>
			<span class="clr"></span>
		</div>
	</div>
	<input id="orderid" value="${order.orderId}" style="display: none;" />
	<input id="order-parentid" value="0" style="display: none;" />
	<input id="order-splittype" value="3" style="display: none;" />
	<input id="pin" value="wei0221kai_m" style="display: none;" />
	<input id="order-giskey"
		value="1D174F8DC33438B52A9DEC6EA93FDF50F948B607E87ECB6EDD199AEEAF8D12DAFD29905F7EF8DF27B5CE9472D36C9BDBD9660D44B9C341BA"
		style="display: none;" />
	<input id="giskey-pin" value="WEI0221KAI_M" style="display: none;" />
	<input id="order-wareids"
		value="1283484708,1146099848,1053916496,${item.itemId}"
		style="display: none;" />
	<input id="javascriptSign" value="00001" style="display: none;" />
	<input id="order-pickingEmployee" value="" style="display: none" />
	<input id="order-orderType" style="display: none;" value="22" />
	<input id="order-idPaymentType" style="display: none;" value="4" />
	<input id="order-state" style="display: none;" value="18" />
	<input id="order-yn" style="display: none;" value="1" />
	<link type="text/css" rel="stylesheet"
		href="//misc.360buyimg.com/user/myjd-2015/widget/??order-detail/order-detail.css" />
	<script type="text/javascript"
		src="//static.360buyimg.com/im/js/im_icon.js" charset="gb2312"></script>

	<span id="isYuShouOrder" style="display: none;">false</span>
	<span id="yuShouOrderItemJson" style="display: none;"></span>
	<div class="w">
		<div class="breadcrumb">
			<strong><a href="http://home.jd.com/">我的淘淘</a></strong><span>&nbsp;&gt;&nbsp;<a
				href="//order.jd.com/center/list.action">订单中心</a>&nbsp;&gt;&nbsp;订单：${order.orderId}<span></span></span>
		</div>
		<!--变量-->
		<span id="pay-button-order" style="display: none"></span>
		<!--状态、提示-->
		<style type="text/css">
.icon-box {
	position: relative;
}

.icon-box .warn-icon {
	background-position: -96px 0;
}

.icon-box .m-icon {
	background: url("//misc.360buyimg.com/myjd/skin/2014/i/icon48.png")
		no-repeat scroll 0 0 rgba(0, 0, 0, 0);
	display: inline-block;
	height: 48px;
	left: 0;
	position: absolute;
	top: 0;
	width: 48px;
}

.icon-box .item-fore {
	margin-left: 58px;
}

.tip-box .item-fore {
	overflow: hidden;
}

.tip-box h3 {
	font-family: "microsoft yahei";
	font-size: 16px;
	line-height: 30px;
}

.tip-box .ftx04, .tip-box .ftx-04 {
	color: #FF8A15;
}

.tip-box .ftx03, .tip-box .ftx-03 {
	color: #999999;
}

.tip-box .op-btns {
	margin-top: 20px;
}

.tip-box .btn-9:link, .tip-box .btn-9:visited, .tip-box .btn-10:link,
	.tip-box .btn-10:visited, .tip-box .btn-11:link, .tip-box .btn-11:visited,
	.tip-box .btn-12:link, .tip-box .btn-12:visited {
	color: #323333;
	text-decoration: none;
}

.tip-box a {
	color: #005EA7;
}

.tip-box .btn-9, .tip-box .btn-10, .tip-box .btn-11, .tip-box .btn-12 {
	background-color: #F7F7F7;
	background-image: linear-gradient(to top, #F7F7F7 0px, #F3F2F2 100%);
	border: 1px solid #DDDDDD;
	border-radius: 2px;
	color: #323333;
	display: inline-block;
	height: 18px;
	line-height: 18px;
	padding: 2px 14px 3px;
}

.tip-box a {
	color: #005EA7;
}

.tip-box .ml10 {
	margin-left: 10px;
}
</style>

		<script>
			$ORDER_CONFIG = {};
			$ORDER_CONFIG['toolbarOdoSwitch'] = '1';
		</script>

		<div class="m" id="orderstate">
			<div class="mt">
				<strong>订单号：${order.orderId}&nbsp;&nbsp;&nbsp;&nbsp;状态：<span
					class="ftx14">完成</span><span id="pay-button-${order.orderId}"></span> <span
					class="thirdpop"></span>
				</strong>
				<div class="fr">
					<div class="toolbar"></div>
				</div>
			</div>

			<div class="mc" style="display: show">

				订单已经完成，感谢您在淘淘商城购物，欢迎您对本次交易及所购商品进行评价。<span class="flk13"><a
					href="http://club.jd.com/JdVote/TradeComment.aspx?ruleid=${order.orderId}&ot=22&payid=4&shipmentid=70"
					target="_blank">发表评价</a></span>

			</div>

			<div class="mc" id="zxzf" style="display: none">
				<div>
					<p style="text-align: left;">尊敬的客户，您的订单商品已经从库房发出，请您做好收货准备。</p>
					<p style="text-align: left;">
						<span class="ftx-01">由淘淘提供</span> <s class="icon-bao"></s> <a
							target="_blank"
							href="http://help.jd.com/help/question-56.html#help1995">在线交易保障</a>
						<span>如果您已收到货，请确认收货，淘淘将与卖家结算。如果您对购买的商品不满意，您可在确认收货后发起返修/退换货申请!</span>
					</p>
				</div>
			</div>
		</div>


		<div class="ml10 mb10" style="font-family: 宋体;">
			重要提醒：淘淘平台及销售商不会以<strong class="ftx04">订单异常、系统升级</strong>为由，要求您点击任何网址链接进行退款操作。烦请关注淘淘平台<a
				class="ftx13" href="http://www.taotao.com/news.aspx?id=22399"
				target="_blank">“谨防诈骗公告声明”</a>
		</div>

		<div id="hbdd" class="Tip360" style="width: 600px;"
			style="display:none;">
			<div class="Tip_Title">
				<em><img src="//www.360buy.com/images/tip_close.jpg"
					class="Tip_Close" width="14" height="13" /></em> 合并订单
			</div>
			<div class="Tip_Content">
				<div id="combin_main"></div>
				<div class="align_Left" style="padding: 15px 0 5px;">
					请选择要合并到主订单的订单：</div>
				<div id="combin_item"></div>
				<div class="Tip_Submit"
					style="background: none; border: 0; padding: 20px 250px 20px 0;">
					<a id='combinBtn' href="javascript:alert('请选择要合并的订单！');"><span>合并订单</span></a>
				</div>
				<div class="align_Left">
					<font color="#FF6600">提示</font>：订单合并后，收货人信息、支付方式、配送方式等都将以主订单为准。
				</div>
			</div>
		</div>

		<div id="ddyq" class="Tip360" style="width: 360px;">
			<div class="Tip_Title">
				<em> <img src="//www.360buy.com/images/tip_close.jpg"
					class="Tip_Close" id="defercloseBtn" width="14" height="13" />
				</em>订单延期
			</div>
			<div class="Tip_Content">
				<div class="align_Left" style="padding: 5px 0 5px;">
					订单自提时间将延长至： <span id="defer_item"></span>
				</div>
				<div class="align_Left" style="padding: 5px 0 5px;">
					注：超过自提日期，您的订单商品将退库</div>
			</div>
		</div>


		<!--进度条-->

		<div id="process" class="section4">
			<div class="node fore ready">
				<ul>
					<li class="tx1">&nbsp;</li>
					<li class="tx2">提交订单</li>
					<li id="track_time_0" class="tx3"></li>
				</ul>
			</div>
			<div class="proce ready">
				<ul>
					<li class="tx1">&nbsp;</li>
				</ul>
			</div>
			<div class="node ready">
				<ul>
					<li class="tx1">&nbsp;</li>
					<li class="tx2">付款成功</li>
					<li id="track_time_4" class="tx3"></li>
				</ul>
			</div>
			<div class="proce ready">
				<ul>
					<li class="tx1">&nbsp;</li>
				</ul>
			</div>
			<div class="node ready">
				<ul>
					<li class="tx1">&nbsp;</li>
					<li class="tx2">商品出库</li>
					<li id="track_time_1" class="tx3"></li>
				</ul>
			</div>
			<div class="proce ready">
				<ul>
					<li class="tx1">&nbsp;</li>
				</ul>
			</div>
			<div class="node ready">
				<ul>
					<li class="tx1">&nbsp;</li>
					<li class="tx2">等待收货</li>
					<li id="track_time_5" class="tx3"></li>
				</ul>
			</div>
			<div class="proce ready">
				<ul>
					<li class="tx1">&nbsp;</li>
				</ul>
			</div>
			<div class="node ready">
				<ul>
					<li class="tx1">&nbsp;</li>
					<li class="tx2">完成</li>
					<li id="track_time_6" class="tx3"></li>
				</ul>
			</div>
		</div>

		<!--跟踪、付款信息、gis-->
		<div class="m" id="ordertrack">
			<ul class="tab">
				<li class="curr" clstag='click|keycount|orderinfo|ordertrack'>
					<h2>订单跟踪</h2>
				</li>

				<li clstag='click|keycount|orderinfo|btn_payinfo'>
					<h2>付款信息</h2>
				</li>
				<li id="orderlocustab" style="display: none;"
					clstag='click|keycount|orderinfo|btn_gis'>
					<h2>订单轨迹</h2>
				</li>
				<li clstag="click|keycount|orderinfo|btn_gis" style="display: none;">
					<h2>安装跟踪</h2>
				</li>
			</ul>
			<div class="clr"></div>
			<div class="mc tabcon">
				<!--订单跟踪-->
				<input type="hidden" value="2015-06-06 21:49:22"
					id="datesubmit-${order.orderId}">
					<table cellpadding="0" cellspacing="0" width="100%">
						<tbody id="tbody_track">
							<tr>
								<th width="15%"><strong>处理时间</strong></th>
								<th width="50%"><strong>处理信息</strong></th>
								<th width="35%"><strong>操作人</strong></th>
							</tr>

						</tbody>
					</table>

					<div class="extra">
						<span id="jdshfs"> 送货方式：普通快递 </span>
					</div>
			</div>


			<div class="mc tabcon hide">
				<!--付款信息-->
				<table cellpadding="0" cellspacing="0" width="100%">
					<tr>
						<td width="30%" id="daiFuName">付款方式：在线支付</td>
						<td width="30%" id="daiFuPeople"></td>
					</tr>

					<tr>
						<td>商品金额：&yen;114.60</td>
						<td>运费金额：&yen;0.00</td>
						<!--添加优惠券-->
						<td>优惠券：&yen;0.00</td>
					</tr>
					<tr>
						<td>优惠总金额：&yen;0.00</td>
						<td>实际运费：&yen;0.00</td>
						<!--添加礼品卡-->
						<td>礼品卡：&yen;0.00</td>
					</tr>
					<tr>
						<td>应支付金额：&yen;114.60</td>
						<td>余额：&yen;0.00</td>
						<td>淘豆：&yen;5.60</td>
					</tr>
					<tr>
						<td>付款时间：2015-06-06 21:51:04</td>
					</tr>
				</table>
				<div class="tb-ul"></div>
			</div>
			<div class="mc tabcon hide">
				<iframe id="gisFrame" src="" frameborder="0" height="710px"
					scrolling="no" width="100%"></iframe>
				<div id="gis">
					<strong>备注：</strong>受天气、gps信号、运营商等各类因素影响，您看到的包裹位置和实际位置有时可能会有一些差别。请您谅解！
				</div>
			</div>

			<div class="mc tabcon hide" style="display: none;">
				<!--订单跟踪-->
				<table width="100%" cellspacing="0" cellpadding="0">
					<tbody id="tbody_bigtrack">
						<tr>
							<th width="26%"><strong>处理时间</strong></th>
							<th width="72%"><strong>处理信息</strong></th>
						</tr>
					</tbody>
				</table>
			</div>

			<div class="i-ordertrack">
				<s></s><a href="http://app.jd.com/" target="_blank"
					clstag='click|keycount|orderinfo|i-ordertrack'>手机查订单，更方便</a>
			</div>
			<div id="im"></div>
		</div>
		<!--留言-->
		<!--订单信息-->
		<div class="m" id="orderinfo">
			<div class="mt">
				<strong>订单信息</strong>
			</div>
			<div class="mc">
				<!-- 节能补贴信息 -->

				<!--顾客信息-->
				<dl class="fore">
					<dt>收货人信息</dt>
					<dd>
						<ul>
							<li>收&nbsp;货&nbsp;人：${order.orderShipping.receiverName}</li>
							<li>地&nbsp;&nbsp;&nbsp;&nbsp;址：${order.orderShipping.receiverAddress}</li>
							<li>手机号码：${order.orderShipping.receiverMobile}</li>
						</ul>
					</dd>
				</dl>
				<!-- 礼品购订单展示送礼人信息 -->

				<!--配送、支付方式-->
				<dl>
					<dt>支付及配送方式</dt>
					<dd>
						<ul>
							<c:choose>
								<c:when test="${order.paymentType == 1}">
									<li>支付方式：在线支付</li>
								</c:when>
								<c:when test="${order.paymentType == 2}">
									<li>支付方式：货到付款</li>
								</c:when>
							</c:choose>


							<li>运&nbsp;&nbsp;&nbsp;&nbsp;费：&yen;<fmt:formatNumber
									groupingUsed="false" value="${order.postFee}"
									maxFractionDigits="2" minFractionDigits="2" /></li>





						</ul>
					</dd>
				</dl>
				<!--发票-->
				<dl>
					<dt>发票信息</dt>
					<dd>
						<ul>
							<li>发票类型：不开发票</li>
						</ul>
					</dd>
				</dl>
				<!-- 礼品购订单展示寄语信息 -->

				<!--订单反赠信息京豆和优惠券-->
				<dl class="order-gifts hide">
					<dt>订单返赠信息</dt>
					<dd>
						<ul>
							<li class="order-jingdou hide">京 豆：<span></span>
							</li>
							<li class="order-coupon hide">优惠券：<span></span>
							</li>
						</ul>
					</dd>
				</dl>

				<!--备注-->

				<!--商品-->
				<dl>
					<dt>
						<span class="i-mt">商品清单</span>

						<div id="fquan" class="fquan">
							<div id="eventName" onMouseOver="showCoupon()"
								onMouseOut="hideCoupon()"></div>

							<div class="prompt p-fquan" id="couponListShow">
								<div class="pc" id="couponList"></div>
							</div>
						</div>

						<div class="clr"></div>

					</dt>

					<dd class="p-list">
						<table cellpadding="0" cellspacing="0" width="100%">
							<tr>
								<th width="10%">商品编号</th>
								<th width="12%">商品图片</th>
								<th width="42%">商品名称</th>
								<th width="10%">淘淘价</th>
								<th width="8%">淘豆数量<a
									href="http://help.jd.com/user/issue/167-332.html"
									target="_blank"><b class="bean-icon thirdpop"></b></a></th>
								<th width="7%">商品数量</th>
								<th width="11%">操作</th>
							</tr>
							<c:if
								test="${order.orderItems != null && order.orderItems.size() != 0}">
								<c:forEach items="${order.orderItems}" var="item">
									<tr>
										<td>${item.itemId}</td>

										<td>
											<div class="img-list">
												<a class="img-box" target="_blank"
													href="http://www.taotao.com/rest/item/${item.itemId}"> <img
													width="50" height="50"
													src="${item.picPath}"
													title="${item.title}" />
												</a>
											</div>
										</td>

										<td>
											<div class="al fl">

												<a class="flk13" target="_blank"
													href="http://www.taotao.com/rest/item/${item.itemId}"
													clstag='click|keycount|orderinfo|product_name'>${item.title}</a>

											</div>
											<div class="clr"></div>
											<div id="coupon_${item.itemId}" class="fl"></div>
										</td>

										<td><span class="ftx04"> &yen;<fmt:formatNumber
									groupingUsed="false" value="${item.price}"
									maxFractionDigits="2" minFractionDigits="2" /></span></td>

										<td id="jingdou-${item.itemId}">0</td>


										<td>1</td>
										<td><span id="iwo${item.itemId}" class="flk13"> <br>
													<a
													href="http://myjd.jd.com/repair/ordersearchlist.action?searchString=${order.orderId}"
													target="_blank"
													clstag='click|keycount|orderinfo|product_repair'>申请返修/退换货</a></span>
											<br> </span> <a class="btn-again"
												clstag="click|keycount|orderlist|buy"
												href="http://cart.taotao.com/service/cart/${item.itemId}"
												target="_blank">还要买</a></td>
									</tr>
								</c:forEach>
							</c:if>
						</table>
					</dd>
				</dl>

				<!--条形码-->
				<div id="barcode">
					<span class="ftx13 fl">查看条形码</span>
					<ul id="sn_list" class="hide"></ul>
				</div>
				<!-- 商家运费险  -->
				<input type="hidden" id="venderIdListStr"
					value="26426,26426,26426,26426">

					<div id="yunFeiXian"></div>
			</div>
			<!--金额-->
			<div class="total">
				<ul>
					<li><span>总商品金额：</span>&yen;<fmt:formatNumber
									groupingUsed="false" value="${order.payment - order.postFee}"
									maxFractionDigits="2" minFractionDigits="2" /></li>
					<li><span>- 返现：</span>&yen;0.00</li>
					<li><span>- 京豆：</span> &yen;999</li>
					<li><span>+ 运费：</span>&yen;<fmt:formatNumber
									groupingUsed="false" value="${order.postFee}"
									maxFractionDigits="2" minFractionDigits="2" /></li>

				</ul>
				<span class="clr">ad</span> <span style="color: #EDEDED;"></span>
				<div class="extra">
					应付总额：<span class="ftx04"><b>&yen;<fmt:formatNumber
									groupingUsed="false" value="${order.payment}"
									maxFractionDigits="2" minFractionDigits="2" /></b></span>
				</div>
			</div>

			<!--进度条预计功能使用-->
			<input type="hidden" id="orderStatus" value="18" /> <input
				type="hidden" id="orderType" value="22" /> <input type="hidden"
				id="orderStoreId" value="0" /> <input type="hidden" id="pickDate"
				value="1433520000943" />
		</div>
	</div>
	<script type="text/javascript"
		src="//misc.360buyimg.com/user/myjd-2015/js/page/order/oldcommon.js"></script>
	<script type="text/javascript"
		src="//misc.360buyimg.com/user/myjd-2015/js/page/order/two-dimension-code.js"></script>

	<script type="text/javascript">
		seajs.use([ "jdf/1.0.0/unit/globalInit/2.0.0/globalInit.js",
				"jdf/1.0.0/unit/hotwords/1.0.0/hotwords" ], function(
				globalInit, hotwords) {
			globalInit();
			hotwords();
		});
	</script>

	<div id="service-2014">
		<div class="slogen">
			<span class="item fore1"> <i></i><b>多</b>品类齐全，轻松购物
			</span> <span class="item fore2"> <i></i><b>快</b>多仓直发，极速配送
			</span> <span class="item fore3"> <i></i><b>好</b>正品行货，精致服务
			</span> <span class="item fore4"> <i></i><b>省</b>天天低价，畅选无忧
			</span>
		</div>
		<div class="w">
			<dl class="fore1">
				<dt>购物指南</dt>
				<dd>
					<div>
						<a rel="nofollow" target="_blank"
							href="http://help.jd.com/user/issue/list-29.html">购物流程</a>
					</div>
					<div>
						<a rel="nofollow" target="_blank"
							href="http://help.jd.com/user/issue/list-151.html">会员介绍</a>
					</div>
					<div>
						<a rel="nofollow" target="_blank"
							href="http://help.jd.com/user/issue/list-297.html">生活旅行/团购</a>
					</div>
					<div>
						<a rel="nofollow" target="_blank"
							href="http://help.jd.com/user/issue.html">常见问题</a>
					</div>
					<div>
						<a rel="nofollow" target="_blank"
							href="http://help.jd.com/user/issue/list-136.html">大家电</a>
					</div>
					<div>
						<a rel="nofollow" target="_blank"
							href="http://help.jd.com/user/index.html">联系客服</a>
					</div>
				</dd>
			</dl>
			<dl class="fore2">
				<dt>配送方式</dt>
				<dd>
					<div>
						<a rel="nofollow" target="_blank"
							href="http://help.jd.com/user/issue/list-81-100.html">上门自提</a>
					</div>
					<div>
						<a rel="nofollow" target="_blank"
							href="http://help.jd.com/user/issue/list-81.html">211限时达</a>
					</div>
					<div>
						<a rel="nofollow" target="_blank"
							href="http://help.jd.com/user/issue/103-983.html">配送服务查询</a>
					</div>
					<div>
						<a rel="nofollow" target="_blank"
							href="http://help.jd.com/user/issue/109-188.html">配送费收取标准</a>
					</div>
					<div>
						<a target="_blank" href="http://en.jd.com/chinese.html">海外配送</a>
					</div>
				</dd>
			</dl>
			<dl class="fore3">
				<dt>支付方式</dt>
				<dd>
					<div>
						<a rel="nofollow" target="_blank"
							href="http://help.jd.com/user/issue/list-172.html">货到付款</a>
					</div>
					<div>
						<a rel="nofollow" target="_blank"
							href="http://help.jd.com/user/issue/list-173.html">在线支付</a>
					</div>
					<div>
						<a rel="nofollow" target="_blank"
							href="http://help.jd.com/user/issue/list-176.html">分期付款</a>
					</div>
					<div>
						<a rel="nofollow" target="_blank"
							href="http://help.jd.com/user/issue/list-174.html">邮局汇款</a>
					</div>
					<div>
						<a rel="nofollow" target="_blank"
							href="http://help.jd.com/user/issue/list-175.html">公司转账</a>
					</div>
				</dd>
			</dl>
			<dl class="fore4">
				<dt>售后服务</dt>
				<dd>
					<div>
						<a rel="nofollow" target="_blank"
							href="http://help.jd.com/user/issue/321-981.html">售后政策</a>
					</div>
					<div>
						<a rel="nofollow" target="_blank"
							href="http://help.jd.com/user/issue/list-132.html">价格保护</a>
					</div>
					<div>
						<a rel="nofollow" target="_blank"
							href="http://help.jd.com/user/issue/130-978.html">退款说明</a>
					</div>
					<div>
						<a rel="nofollow" target="_blank"
							href="http://myjd.jd.com/repair/repairs.action">返修/退换货</a>
					</div>
					<div>
						<a rel="nofollow" target="_blank"
							href="http://help.jd.com/user/issue/list-50.html">取消订单</a>
					</div>
				</dd>
			</dl>
			<dl class="fore5">
				<dt>特色服务</dt>
				<dd>
					<div>
						<a target="_blank"
							href="http://help.jd.com/user/issue/list-133.html">夺宝岛</a>
					</div>
					<div>
						<a target="_blank"
							href="http://help.jd.com/user/issue/list-134.html">DIY装机</a>
					</div>
					<div>
						<a rel="nofollow" target="_blank" href="http://fuwu.jd.com/">延保服务</a>
					</div>
					<div>
						<a rel="nofollow" target="_blank"
							href="http://giftcard.jd.com/market/index.action">淘淘E卡</a>
					</div>
					<div>
						<a rel="nofollow" target="_blank" href="http://mobile.jd.com/">淘淘通信</a>
					</div>
				</dd>
			</dl>
			<span class="clr"></span>
		</div>
	</div>
	<div class="w">
		<div id="footer-2014">
			<div class="links">
				<a rel="nofollow" target="_blank"
					href="http://www.taotao.com/intro/about.aspx">关于我们</a>|<a
					rel="nofollow" target="_blank"
					href="http://www.taotao.com/contact/">联系我们</a>|<a rel="nofollow"
					target="_blank" href="http://www.taotao.com/contact/joinin.aspx">商家入驻</a>|<a
					rel="nofollow" target="_blank" href="http://jzt.jd.com">营销中心</a>|<a
					rel="nofollow" target="_blank" href="http://app.jd.com/">手机淘淘</a>|<a
					target="_blank" href="http://club.jd.com/links.aspx">友情链接</a>|<a
					target="_blank" href="http://media.jd.com/">销售联盟</a>|<a
					href="http://club.jd.com/" target="_blank">淘淘社区</a>|<a
					href="http://gongyi.jd.com" target="_blank">淘淘公益</a>|<a
					href="http://en.jd.com/" target="_blank">English Site</a>|<a
					href="http://help.en.jd.com/help/question-46.html" target="_blank">Contact
					Us</a>
			</div>
			<div class="copyright">
				北京市公安局朝阳分局备案编号110105014669&nbsp;&nbsp;|&nbsp;&nbsp;京ICP证070359号&nbsp;&nbsp;|&nbsp;&nbsp;<a
					target="_blank"
					href="http://img14.360buyimg.com/da/jfs/t256/349/769670066/270505/3b03e0bb/53f16c24N7c04d9e9.jpg">互联网药品信息服务资格证编号(京)-经营性-2014-0008</a>&nbsp;&nbsp;|&nbsp;&nbsp;新出发京零&nbsp;字第大120007号<br>
					<a rel="nofollow"
					href="http://misc.360buyimg.com/skin/df/i/com/f_music.jpg"
					target="_blank">音像制品经营许可证苏宿批005号</a>&nbsp;&nbsp;|&nbsp;&nbsp;出版物经营许可证编号新出发(苏)批字第N-012号&nbsp;&nbsp;|&nbsp;&nbsp;互联网出版许可证编号新出网证(京)字150号<br>
						<a href="http://misc.360buyimg.com/wz/wlwhjyxkz.jpg"
						target="_blank">网络文化经营许可证京网文[2014]2148-348号</a>&nbsp;&nbsp;违法和不良信息举报电话：4006561155&nbsp;&nbsp;Copyright&nbsp;&copy;&nbsp;2004-2015&nbsp;&nbsp;淘淘JD.com&nbsp;版权所有<br>淘淘旗下网站：<a
							target="_blank" href="http://www.360top.com/">360TOP</a>&nbsp;&nbsp;<a
							href="http://www.paipai.com/" target="_blank">拍拍网</a>&nbsp;&nbsp;<a
							href="https://www.wangyin.com/" target="_blank">网银在线</a>
			</div>
			<div class="authentication">
				<a rel="nofollow" target="_blank"
					href="http://www.hd315.gov.cn/beian/view.asp?bianhao=010202007080200026">
					<img width="103" height="32" alt="经营性网站备案中心"
					src="//img12.360buyimg.com/da/jfs/t535/349/1185317137/2350/7fc5b9e4/54b8871eNa9a7067e.png" />
				</a>
				<script type="text/JavaScript">
					function CNNIC_change(eleId) {
						var str = document.getElementById(eleId).href;
						var str1 = str.substring(0, (str.length - 6));
						str1 += CNNIC_RndNum(6);
						document.getElementById(eleId).href = str1;
					}
					function CNNIC_RndNum(k) {
						var rnd = "";
						for (var i = 0; i < k; i++)
							rnd += Math.floor(Math.random() * 10);
						return rnd;
					}
				</script>
				<a rel="nofollow" target="_blank" id="urlknet" tabindex="-1"
					href="https://ss.knet.cn/verifyseal.dll?sn=2008070300100000031&amp;ct=df&amp;pa=294005">
					<img border="true" width="103" height="32"
					onclick="CNNIC_change('urlknet')" oncontextmenu="return false;"
					name="CNNIC_seal" alt="可信网站"
					src="//img11.360buyimg.com/da/jfs/t643/61/1174624553/2576/4037eb5f/54b8872dNe37a9860.png" />
				</a> <a rel="nofollow" target="_blank"
					href="http://www.bj.cyberpolice.cn/index.do"> <img width="103"
					height="32" alt="朝阳网络警察"
					src="//img11.360buyimg.com/da/jfs/t559/186/1172042286/2795/7d90b036/54b8874bN694454a5.png" />
				</a> <a rel="nofollow" target="_blank"
					href="https://search.szfw.org/cert/l/CX20120111001803001836"> <img
					width="103" height="32"
					src="//img11.360buyimg.com/da/jfs/t451/173/1189513923/1992/ec69b14a/54b8875fNad1e0c4c.png" />
				</a> <a target="_blank" href="http://jubao.china.cn:13225/reportform.do">
					<img width="185" height="32"
					src="//img10.360buyimg.com/da/jfs/t520/303/1151687373/1180/2f8340fc/54b8863dN8d2c61ec.png" />
				</a>
			</div>
		</div>
	</div>


	<script type="text/javascript"
		src="//misc.360buyimg.com/user/myjd-2015/js/page/??order/odo/OrderToolBarOld.js,order/qrcode.js,order/orderinfo-service.js"
		charset="utf-8"></script>



	<script type="text/javascript"
		src="//misc.360buyimg.com/user/myjd-2015/js/page/order/payOrderItem.js?sign=20150805"></script>

	<script type="text/javascript"
		src="//static.360buyimg.com/im/js/im_icon.js" charset="gb2312"></script>
	<script>
		JDIM.show({
			"icon" : "im",
			"from" : "orderDetail",
			"pid" : 1283484708,
			"charset" : "gb2312"
		});
	</script>
	<script type="text/javascript"
		src="//misc.360buyimg.com/user/myjd-2015/js/page/order/formyjd.js"></script>

	<script type="text/javascript">
		var sidePanle = new pageConfig.FN_InitSidebar();
		sidePanle
				.addItem("<a class='research' target='_blank' href='http://surveys.jd.com/index.php?r=survey/index/sid/682448/lang/zh-Hans'><b></b>调查问卷</a>");
		sidePanle.setTop();
		sidePanle.scroll();
	</script>


</body>
</html>

