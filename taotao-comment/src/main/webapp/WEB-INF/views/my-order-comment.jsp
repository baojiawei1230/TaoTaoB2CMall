<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Cache-Control" content="max-age=300" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>我的订单 - 淘淘</title>
<meta name="Keywords" content="java,淘淘java" />
<meta name="description"
	content="在淘淘中找到了29910件java的类似商品，其中包含了“图书”，“电子书”，“教育音像”，“骑行运动”等类型的java的商品。" />
<link rel="stylesheet" type="text/css" href="/css/base.css" media="all" />
<link rel="stylesheet" type="text/css" href="/css/myjd.common.css"
	media="all" />
<link rel="stylesheet" type="text/css" href="/css/myjd.commentImg.css"
	media="all" />
<script type="text/javascript" src="/js/jquery-1.2.6.min.js"></script>
<link rel="stylesheet" href="/css/default.css" />


<script type="text/javascript">
	$(function() {

		//当用户点击心得的输入框的时候，修改js效果，清空默认文字，将灰色css去掉
		$("textarea[name='content']").click(function() {
			$(this).empty();
			$(this).attr("class", "area");
		});

		//给评价按钮添加点击事件，准备提交数据，包含orderId,并校验带星号的几个字段的数据是否有值，发送ajax请求，保存评价信息，
		//根据返回结果，作出相应的效果,true: 解除评价按钮的点击事件  false:弹框提醒用户评价失败 
		$("#evaluate").click(
				function() {
					//取出图片中地址值，拼接成字符串传输
					var imgs = "";
					$("img[name ='upload']").each(function(){
						imgs = imgs + this.src + ",";
					 }); 
					imgs = imgs.substring(0, imgs.length - 1);
					//封装页面上需要提交的数据,评分，标签，心得，晒单图片地址，
					//score tags content referenceImage 
					//选取所有选中的标签的name拼接成字符串 
					var tags = "";
					$("li > input[checked='checked']").each(function() {
						tags = tags + $(this).parent().text() + ",";
					});
					tags = tags.substring(0, tags.length - 1);
					var params = {
						"score" : $("input[name='score']").val(),
						"tags" : tags,
						"content" : $("textarea[name='content']").val(),
						"referenceImage" : imgs,
						"itemId" : $("input[name='itemId']").val(),
						"orderId" : $("input[name='orderId']").val()
					};
					var checkImage = $("#app > font[color='green']").text();
					if (!params.score) {
						alert('请给我们的宝贝评分哦！亲！');
						return;
					} else if (!params.tags) {
						alert('请选择一些标签哦！亲！');
						return;
					} else if (!params.content
							|| params.content == "商品是否给力？快分享你的购买心得吧~") {
						alert('请写一点评价哦！亲！');
						return;
					} else if (checkImage != "正确") {
						alert('请填写正确的验证码哦！亲！');
						return;
					}

					$.ajax({
						type : "POST",
						url : "/service/comment",
						data : params,
						success : function(data) {
							if (data) {
								var r = confirm('评价商品成功！是否收起评价信息页面！');
								if (r) {
									$("#hidden").hide("slow");
								} else {
									$("span[class='pingjiaEl']").attr("class",
											"pingjiaEl area01");
								}
								$("#evaluate").unbind("click");
								$("span[class='num-comment']").text("0");
								$("a[class='pj']").text("已评价");
							} else {
								alert('评价商品失败!请稍后重试哦！亲！');
							}
						}
					//接收状态码
					// 				statusCode : {
					// 					201 : function() {
					// 						alert(statusCode);
					// 						$("#hidden").hide("slow");
					// 						$("span[class='num-comment']").text("0");
					// 						$("a[class='pj']").text("已评价");
					// 					},
					// 					500 : function() {
					// 						$.messager.alert('提示', '评价商品失败!请稍后重试哦！亲！');
					// 					}
					// 				}
					});
				});

		//自定义标签,点击时将隐藏的div打开，让用户输入值 根据回车键发送ajax请求，传递参数到后台保存 
		$("div[class='fore1']").click(function() {
			$("#input_entry").attr("class", "fore2");
			$("li[vid='keydown']").attr("class", "");
		});

	});

	//离焦事件
	function hide(){
		$("input[class='itxt']").attr("name","hide");
		enterPress();
	}
	//输入标签后回车调用的方法
	function enterPress() {
		if (event.keyCode == "13" || $("input[class='itxt']").attr("name")=="hide") {//keyCode=13是回车键
			var params = {
				"name" : $("input[class='itxt']").val(),
				"productId" : $("input[name='itemId']").val()
			};
			if (!params.name || params.name == "") {
				//alert($("input[class='itxt']").val());
				//alert(params.name);
				//alert("提交前请先填写标签哦！");
				$("#input_entry").attr("class", "fore2 hide");
				$("li[vid='keydown']").attr("class", "hide");
				return;
			}
			$.ajax({
				type : "POST",
				url : "/service/comment/tags",
				data : params,
				//执行成功后，返回i标签的id，并写入新标签的li标签中 
				statusCode : {
					200 : function(data) {
						var flag = true;
						//追加标签前先遍历比较是否与之前显示的标签重复 
						$(".tips-list > li").each(function() {
							if ($(this).text() == params.name) {
								$(this).children().attr("checked", "checked");
								flag = false;
							}
						});
						if (flag) {
							//在隐藏标签前 追加一个li标签 ,默认为选中状态
							$("li[vid='keydown']").before(
									"<li vid='"+data.id+"'><input type='checkbox' checked='checked'/>"
											+ params.name + "</li>");
							//继续隐藏提示标签和输入框,清空输入框
						}
						$("input[class='itxt']").val("");
						$("input[class='itxt']").removeAttr("name");;
						$("#input_entry").attr("class", "fore2 hide");
						$("li[vid='keydown']").attr("class", "hide");
					},
					500 : function() {
						alert('提示,标签提交失败!请重试一次哦！亲！');
					}
				}
			});
		}
	}

	//选择标签
	function checkbox_checked(e) {
		var checkbox = $(e).find("input");
		if ($(checkbox).attr("checked")) {
			$(checkbox).removeAttr("checked");
		} else {
			$(checkbox).attr("checked", "checked");
		}
	}
</script>

</head>
<body>
	<script type="text/javascript" src="/js/base-2011.js" charset="utf-8"></script>
	<!-- header start -->
	<jsp:include page="../commons/header.jsp" />
	<!-- header end -->

	<div id="container">
		<div class="w">

			<div id="main">
				<div class="g-0">
					<div id="content-div" class="c-3-5">

						<div class="mod-main mod-comm extra-main" id="evalu01">
							<div class="mt">
								<h3>商品评价</h3>
								<div class="extra-l ftx03 ml10">
									<span id="tip-num">( <span class="num-comment"> 1
									</span>个待评价 )
									</span>
								</div>
							</div>
							<div class="mc">
								<div class="tb-void tb-line">
									<table class="tb-void tb-line">
										<colgroup>
											<col width="490">
												<col width="130">
													<col width="">
										</colgroup>
										<thead>
											<tr>
												<th>商品信息</th>
												<th>购买时间</th>
												<th>评价状态</th>
											</tr>
										</thead>
									</table>
									<table class="tb-void tb-line">
										<input type="hidden" name="orderId" value="${item.orderId }">
											<input type="hidden" name="itemId" value="${item.itemId }">
												<tbody>
													<tr>
														<td>
															<ul class="pro-info" oid="${item.orderId }" pid="975788">
																<li class="fore1">
																	<div class="p-info clearfix">
																		<!--  -->
																		<div class="p-img fl">
																			<a target="_blank"
																				href="http://www.taotao.com/${item.itemId }.html">
																				<img width="50" height="50" title="${item.title }"
																				data-img="1" src="${item.picPath}"
																				class="err-product">
																			</a>
																		</div>
																		<div class="p-name fl">
																			<a target="_blank"
																				href="http://www.taotao.com/${item.itemId }.html">${item.title }</a>
																		</div>
																	</div>
																</li>
																<li class="fore2"><span class="ftx03"> <fmt:formatDate
																			value="${order.createTime}" pattern="yyy-MM-dd" />
																</span></li>



																<li class="fore3 forem"><a href="#none"
																	voucherstatus="0" class="pj" alt="975788"
																	title="发评价拿京豆" catefirst="670" catesecond="716"
																	catethird="720">发表评价<b class="icon-show"></b></a></li>
															</ul>
															<div class="clr"></div>
															<div id="hidden" class="comment-box prompt01"
																oid="3122336930" pid="975788" style="display: block;">
																<div class="box-t"></div>
																<div class="form" tagflag="true" isconspros="0"
																	pid="975788">
																	<!-- <div class="item item01 titleEl" style="display: none;">
																<span class="label"><em>*</em>标题：</span>
																<div class="tit">
																	<input autocomplete="off" id="title" name="title"
																		style="width: 340px;" type="text"
																		class="title text area01" value="">
																		<div class="clr"></div>
																		<div class="msg-text ftx-03">4-20字</div>
																</div>
																<span class="msg-error-01 ml10 hide">麻烦填写4-20个字呦</span>
																<div class="clr"></div>
															</div> -->

																	<div class="item">
																		<span class="label"><em>*</em>评分：</span>
																		<div class="fl">
																			<div id="starStyle" data-score="1"></div>
																			<input type="hidden" name="commentStar" />
																			<div class="clr"></div>
																		</div>
																		<span class="msg-error-01 ml10 hide">你的评分是偶们前进的动力</span>
																		<div class="clr"></div>
																	</div>

																	<div class="item tagEl" style="" id="commentTags">
																		<span class="label"><em>*</em>标签：</span>
																		<div class="fl">
																			<ul class="tips-list">
																				<c:forEach items="${tags}" var="tag">
																					<li vid="${tag.id}"
																						onclick="checkbox_checked(this);"><input
																						type="checkbox" /></s>${tag.name}</li>
																				</c:forEach>
																				<li vid="keydown" class="hide"><span class=""><font
																						color="red" size="4">按回车提交</font></span></li>
																				<li class="list-last">
																					<div class="fore1">
																						<s class="f-input"></s>自定义
																					</div>
																					<div id="input_entry" class="fore2 hide">
																						<input type="text" maxlength="12" class="itxt"
																							onkeydown="enterPress();" onblur="hide();"/>
																					</div>
																				</li>
																			</ul>
																			<span class="msg-error-01 hide"></span>
																			<div class="clr"></div>
																		</div>
																		<div class="clr"></div>
																	</div>

																	<div class="item item01 xindeEl">
																		<span class="label"><em>*</em>心得：</span>
																		<div class="cont">
																			<textarea name="content" cols="" rows=""
																				class="area area01">商品是否给力？快分享你的购买心得吧~</textarea>
																			<div class="clr"></div>
																			<span class="msg-error-01 hide">麻烦填写10-500个字呦</span>
																			<div class="msg-text ftx-03">10-500字</div>
																		</div>
																		<div class="clr"></div>
																	</div>

																	<div class="item imgEl"
																		id="imgContainer_3122336930_975788"
																		style="position: relative;">
																		<span class="label">晒单：</span> <input type="button"
																			id="J_selectImage" value="批量上传" />
																		<div id="J_imageView"></div>
																	</div>

																	<div class="isnL hide" style="text-align: center;">
																		登录后才能发表评价，请<a href="#none" class="loginBut">登录</a>
																	</div>

																	<div class="isL item">
																		<span class="label"><em>*</em>验证码：</span>
																		<div class="fl">
																			<input id="seccode" acid="ZGgxODk8439" maxlength="7"
																				type="text" autocomplete="off"
																				class="text text02 itxt" name=""
																				onblur="checkNumber();" onkeyup="checkNumber();" />&nbsp;&nbsp;<img
																				class="seccodeimg" height="28" alt=""
																				src="http://comment.taotao.com/image.html"
																				onclick="checkNumberImage()" /> <span id="app"></span>
																		</div>
																		<div class="clr"></div>
																	</div>

																	<div class="item item02">
																		<span class="label">&nbsp;</span>
																		<div class="fl">
																			<a id="evaluate" href="javascript:void(0);"
																				class="btn btn-5 mr20"> <s></s> <span
																				class="pingjiaEl">评价</span>
																			</a>
																			<div class="msg-text"></div>
																		</div>
																		<div class="clr"></div>
																	</div>
																</div>
															</div>

														</td>
													</tr>
												</tbody>
									</table>
								</div>
							</div>
						</div>

					</div>
				</div>
				<div id="left" class="g-3-5 c-0">
					<!--js 加载异步加载的左侧菜单 -->
					<div id="menu">
						<h3>我的交易</h3>
						<dl class="fore1">
							<dt>
								<a target="_blank" clstag="homepage|keycount|home2013|hdd"
									id="_MYJD_ordercenter"
									href="http://order.jd.com/center/list.action" class="curr">我的订单</a>
							</dt>
						</dl>
						<dl class="fore2">
							<dt>
								<a target="_blank" clstag="homepage|keycount|home2013|hyushou"
									id="_MYJD_yushou"
									href="http://yushou.jd.com/member/qualificationList.action">我的预售</a>
							</dt>
						</dl>
						<dl class="fore3">
							<dt>
								<a target="_blank" clstag="homepage|keycount|home2013|hbdsh"
									id="_MYJD_locallife"
									href="http://life.jd.com/localOrder/iniOrder.do">我的本地生活</a>
							</dt>
						</dl>
						<dl class="fore4">
							<dt>
								<a target="_blank" clstag="homepage|keycount|home2013|hdqs"
									id="_MYJD_ding" href="http://ding.jd.com/plan/showPlans.action">我的定期送</a>
							</dt>
						</dl>
						<dl class="fore5">
							<dt>
								<a target="_blank" clstag="homepage|keycount|home2013|htg"
									id="_MYJD_tuan" href="http://tuan.jd.com/order/index.php">我的团购</a>
							</dt>
						</dl>
						<dl class="fore6">
							<dt>
								<a target="_blank" clstag="homepage|keycount|home2013|hjg"
									id="_MYJD_protection" href="http://jiabao.jd.com/protecting">价格保护</a>
							</dt>
						</dl>
						<dl class="fore7">
							<dt class="hc">
								<b></b><a target="_blank" id="_MYJD_gz" href="#none">我的关注</a>
							</dt>
							<dd class="fore1">
								<div class="item" id="_MYJD_product">
									<a target="_blank" clstag="homepage|keycount|home2013|hgz"
										href="http://t.jd.com/home/follow">关注的商品</a>
								</div>
							</dd>
							<dd class="fore2">
								<div class="item" id="_MYJD_vender">
									<a target="_blank" clstag="homepage|keycount|home2013|hdp"
										href="http://t.jd.com/vender/followVenderList.action">关注的店铺</a>
								</div>
							</dd>
							<dd class="fore3">
								<div class="item" id="_MYJD_activity">
									<a target="_blank" clstag="homepage|keycount|home2013|hhd"
										href="http://t.jd.com/activity/followActivityList.action">关注的活动</a>
								</div>
							</dd>
							<dd class="fore4 last ">
								<div class="item" id="_MYJD_history">
									<a target="_blank" clstag="homepage|keycount|home2013|hll"
										href="http://my.jd.com/history/list.html">浏览历史&nbsp;<img
										width="24" height="11" src="/images/myjd-new-ico.png"></a>
								</div>
							</dd>
						</dl>
						<dl class="fore8">
							<dt class="hc">
								<b></b><a target="_blank" id="_MYJD_zc" href="#none">我的资产</a>
							</dt>
							<dd class="fore1">
								<div class="item" id="_MYJD_cashbox">
									<a target="_blank" clstag="homepage|keycount|home2013|hjk"
										href="http://jinku.pay.jd.com/xjk/income.action">我的小金库</a>
								</div>
							</dd>
							<dd class="fore2">
								<div class="item" id="_MYJD_credit">
									<a clstag="homepage|keycount|home2013|hbt" tag="213"
										href="http://baitiao.jd.com/creditUser/record">京东白条</a>&nbsp;
								</div>
							</dd>
							<dd class="fore3">
								<div class="item" id="_MYJD_tx">
									<a target="_blank" clstag="homepage|keycount|home2013|htx"
										href="http://mobile.jd.com/yyswt/myjd.do">京东通信</a>
								</div>
							</dd>
							<dd class="fore4">
								<div class="item" id="_MYJD_balance">
									<a target="_blank" clstag="homepage|keycount|home2013|hye"
										href="http://mymoney.jd.com/finance/recently.action">余额</a>
								</div>
							</dd>
							<dd class="fore5">
								<div class="item" id="_MYJD_ticket">
									<a target="_blank" clstag="homepage|keycount|home2013|hyh"
										href="http://quan.jd.com/user_quan.action">优惠券</a>
								</div>
							</dd>
							<dd class="fore6">
								<div class="item" id="_MYJD_card">
									<a target="_blank" clstag="homepage|keycount|home2013|he"
										href="http://giftcard.jd.com/giftcard/index.action">京东卡/E卡</a>
								</div>
							</dd>
							<dd class="fore7 last">
								<div class="item" id="_MYJD_bean">
									<a target="_blank" clstag="homepage|keycount|home2013|hjd"
										href="http://bean.jd.com/myJingBean/list">京豆</a>
								</div>
							</dd>
						</dl>
						<dl class="fore9 last ">
							<dt class="hc">
								<b></b><a target="_blank" id="_MYJD_fw" href="#none">客户服务</a>
							</dt>
							<dd class="fore1">
								<div class="item" id="_MYJD_repair">
									<a target="_blank" clstag="homepage|keycount|home2013|hfx"
										href="http://myjd.jd.com/repair/orderlist.action">返修退换货</a>
								</div>
							</dd>
							<dd class="fore2">
								<div class="item" id="_MYJD_refundment">
									<a target="_blank" clstag="homepage|keycount|home2013|hqx"
										href="http://rps.fm.jd.com/rest/refund/refundList">取消订单记录</a>
								</div>
							</dd>
							<dd class="fore3 last ">
								<div class="item" id="_MYJD_complaint">
									<a target="_blank" clstag="homepage|keycount|home2013|htx"
										href="http://myjd.jd.com/opinion/orderList.action">我的投诉</a>
								</div>
							</dd>
						</dl>
					</div>
					<div id="da-game" class="da-box m">
						<a
							href="http://c.nfa.jd.com/adclick?sid=14&amp;cid=720&amp;aid=4497&amp;bid=0&amp;unit=85943&amp;advid=131939&amp;guv=&amp;url=http://wan.jd.com/yeyou/play.html?gameId=86&amp;gateWayId=s40"><img
							width="100%" src="/images/547e6a57N75c2f016.gif" alt=""></a>
					</div>
					<div id="da-home" class="da-box">
						<a
							href="http://c.nfa.jd.com/adclick?sid=2&amp;cid=102&amp;aid=413&amp;bid=8305&amp;unit=65429&amp;advid=166662&amp;guv=&amp;url=http://vivoshop.jd.com"
							target="_blank"><img width="100%" height="100%" alt=""
							app="image:poster" src="/images/549d03d0N59b1f026.jpg"></a>
					</div>
				</div>
				<span class="clr"></span>
			</div>
		</div>
	</div>

	<!-- footer start -->
	<jsp:include page="../commons/footer.jsp" />
	<!-- footer end -->
	<script type="text/javascript" src="/js/jquery.min.js"></script>
	<script type="text/javascript" src="/js/jquery.raty.min.js"></script>
	<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
	<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/lang/zh_CN.js"></script>

	<script type="text/javascript">
		$(function() {
	
			$.fn.raty.defaults.path = '/img';
			$('#starStyle').raty({
				score : function() {
					return $(this).attr('data-score');
				}
			});
		});

		function checkNumberImage() {
			$(".seccodeimg").attr(
					"src",
					"http://comment.taotao.com/image.html?timestamp="
							+ new Date());
		}

		function checkNumber() {
			var data = $("#seccode").val();
			$
					.ajax({
						url : "/service/image/check",
						type : "POST",
						data : {
							"data" : data
						},
						statusCode : {
							200 : function() {
								$("#app")
										.html(
												"&nbsp;&nbsp;&nbsp;<font color='green'>正确<font>")
							},
							400 : function() {
								$("#app")
										.html(
												"&nbsp;&nbsp;&nbsp;<font color='red'>验证码错误<font>")
							},
							500 : function() {
								$("#app")
										.html(
												"&nbsp;&nbsp;&nbsp;<font color='red'>验证码错误<font>")
							}
						}
					});
		}
		var kingEditorParams = {
			filePostName  : "uploadFile", //上传的文件输入框的名称
			uploadJson : '/service/pic/upload', //上传地址
			dir : "image" //类型
		};
		
		KindEditor.ready(function(K) {
			var editor = K.editor(kingEditorParams);
			K('#J_selectImage').click(function() {
				editor.loadPlugin('multiimage', function() {
					editor.plugin.multiImageDialog({
						clickFn : function(urlList) {
							var div = K('#J_imageView');
							div.html('');
							K.each(urlList, function(i, data) {
								div.append('<img name="upload" src="' + data.url + '" width="80" heigth="130">');
							});
							editor.hideDialog();
						}
					});
				});
			});
		});
	</script>

</body>
</html>