package com.taotao.store.order.pojo;

import org.hibernate.validator.constraints.Length;

//发票
public class Invoice {
	//发票id
	private Long invoiceId;
	//订单id
	private String orderId;
	//发票类型
	private Integer invoiceType;
	//发票抬头
	@Length(min=2)
	private String invoiceTitle;
	//普通发票公司名称
	@Length(min=1)
	private String invoiceCompanyName;
	//普通发票内容
	@Length(min=1)
	private String invoiceCommonContent;
	//电子发票内容
	@Length(min=1)
	private String invoiceBookContent;
	//电子发票标题
	@Length(min=1)
	private String electroInvoiceTitle;
	//电子发票电话.
	@Length(min=1)
	private String electroPhone;
	//电子发票email
	@Length(min=1)
	private String electroEmail;
	//电子发票公司名称
	@Length(min=1)
	private String electroCompanyName;
	
	public Long getInvoiceId() {
		return invoiceId;
	}
	public void setInvoiceId(Long invoiceId) {
		this.invoiceId = invoiceId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public Integer getInvoiceType() {
		return invoiceType;
	}
	public void setInvoiceType(Integer invoiceType) {
		this.invoiceType = invoiceType;
	}
	public String getInvoiceTitle() {
		return invoiceTitle;
	}
	public void setInvoiceTitle(String invoiceTitle) {
		this.invoiceTitle = invoiceTitle;
	}
	public String getInvoiceCompanyName() {
		return invoiceCompanyName;
	}
	public void setInvoiceCompanyName(String invoiceCompanyName) {
		this.invoiceCompanyName = invoiceCompanyName;
	}
	public String getInvoiceCommonContent() {
		return invoiceCommonContent;
	}
	public void setInvoiceCommonContent(String invoiceCommonContent) {
		this.invoiceCommonContent = invoiceCommonContent;
	}
	public String getInvoiceBookContent() {
		return invoiceBookContent;
	}
	public void setInvoiceBookContent(String invoiceBookContent) {
		this.invoiceBookContent = invoiceBookContent;
	}
	public String getElectroInvoiceTitle() {
		return electroInvoiceTitle;
	}
	public void setElectroInvoiceTitle(String electroInvoiceTitle) {
		this.electroInvoiceTitle = electroInvoiceTitle;
	}
	public String getElectroPhone() {
		return electroPhone;
	}
	public void setElectroPhone(String electroPhone) {
		this.electroPhone = electroPhone;
	}
	public String getElectroEmail() {
		return electroEmail;
	}
	public void setElectroEmail(String electroEmail) {
		this.electroEmail = electroEmail;
	}
	public String getElectroCompanyName() {
		return electroCompanyName;
	}
	public void setElectroCompanyName(String electroCompanyName) {
		this.electroCompanyName = electroCompanyName;
	}
	
}
