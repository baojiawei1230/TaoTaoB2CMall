<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<table class="tb-void">
	<colgroup>
		<col width="290">
		<col width="90">
		<col width="110">
		<col width="100">
		<col width="100">
		<col width="130">
	</colgroup>
	<c:if test="${orderResult.data!=null && orderResult.data.size()!=0}">
		<c:forEach items="${orderResult.data}" var="order">
			<tbody id="tb-${order.orderId}">
				<tr class="tr-th">
					<td colspan="6"><span class="tcol1"> 订单编号: <a
							name="orderIdLinks" id="idUr${order.orderId}" target="_blank"
							href="${pageContext.request.contextPath}/order/orderDetails?orderid=${order.orderId}"
							clstag="click|keycount|orderinfo|order_num">${order.orderId}</a>
					</span> <span class="tcol2"> 淘淘 </span> <span class="tcol3"> <a
							class="btn-im" onclick="getPamsForChat()" href="#none"
							title="联系客服"></a>
					</span></td>
				</tr>

				<tr id="track${order.orderId}" oty="0,1,70" class="tr-td">
					<td>
						<div class="img-list">
							<c:forEach items="${order.orderItems}" var="oitem">
								<a href="${oitem.picPath}" class="img-box"
									clstag="click|keycount|orderinfo|order_product" target="_blank">
									<img title="${oitem.title}" width="50"
									height="50"
									src="${oitem.picPath}"
									class="err-product">
								</a>
							</c:forEach>
						</div>
					</td>
					<td>
						<div class="u-name">
							<c:if test="${order.orderShipping.receiverName !=null}">${order.orderShipping.receiverName}</c:if>
						</div>
					</td>

					<td>￥<fmt:formatNumber groupingUsed="false"
							value="${order.payment}" maxFractionDigits="2"
							minFractionDigits="2" /><br /> <c:choose>
							<c:when test="${order.paymentType == 1}">在线支付</c:when>
							<c:when test="${order.paymentType == 2}">货到付款</c:when>
							<c:otherwise>未知状态</c:otherwise>
						</c:choose>
					</td>

					<td><span class="ftx-03"><fmt:formatDate
								value="${order.createTime}" type="date" pattern="yyyy-MM-dd" /><br />
							<fmt:formatDate value="${order.createTime}" type="date"
								pattern="HH:mm:ss" /></td>

					<td><span class="ftx-03"> <c:choose>
								<c:when test="${order.status == 1}">未付款</c:when>
								<c:when test="${order.status == 2}">已付款</c:when>
								<c:when test="${order.status == 3}">未发货</c:when>
								<c:when test="${order.status == 4}">已发货</c:when>
								<c:when test="${order.status == 5}">已成功</c:when>
								<c:when test="${order.status == 6}">关闭</c:when>
								<c:otherwise>未知状态</c:otherwise>
							</c:choose>
					</span><br /> <a target="_blank"
						href="${pageContext.request.contextPath}/order/orderDetails?orderid=${order.orderId}"
						clstag="click|keycount|orderinfo|order_check">订单详情</a></td>
					<td id="operate${order.orderId}" class="order-doi" width="100"><span
						id="pay-button-${order.orderId}" state=""></span> <span
						id="order_comment"></span><span class="pop-recycle-a">|<a
							href="javascript:void(0)"
							clstag="click|keycount|orderinfo|order_del"
							onclick="ensureMoveOrderToRecycle(${order.orderId},'397FF574E089D5409E6CC8EF67129D65');">删除</a></span><span
						id="doi${order.orderId}"><br></span> <a class="btn-again"
						clstag="click|keycount|orderlist|buy"
						href="http://cart.jd.com/cart/dynamic/reBuyForOrderCenter.action?wids=1113410,1222567&amp;nums=1,1&amp;rid=1419846299535"
						target="_blank">还要买</a></td>
				</tr>
			</tbody>
		</c:forEach>
	</c:if>
</table>