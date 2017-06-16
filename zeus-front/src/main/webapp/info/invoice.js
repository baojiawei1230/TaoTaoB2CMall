/**
 * 保存发票内容
 */
function save_Invoice(isGeneral) {
	// 发票类型和内容
	var invoice_hasBook = $("#hasBook").val(); // false
	var invoice_hasCommon = $("#hasCommon").val(); // true
	var invoice_type = $('.tab-item-selected').val(); // 1
	// 个人
	var invoice_companyName = $('.invoice-item-selected')
			.children('#invoice-1').children('.fore2').children('input').val();
	var invoice_title = $('.invoice-item-selected').children('#invoice-1')
			.children('.hide').children('input').val();
	var electro_invoiceTitle = $('.invoice-item-selected').children(
			'#invoice-2').children('.hide').children('input').val();
	var electro_phone = $("#e_consignee_mobile").val();
	var electro_email = $("#e_consignee_email").val();
	var electro_companyName = $('.invoice-item').children('#invoice-2')
			.children('.selec').children('input').val();
	var invoice_ceshi1 = $("#invoice_ceshi1").val();
	if (invoice_type == 1) { // 普票
		if ($("li[name='normal-normalContent']").html() != null) {
			invoice_common_content = $(
					"li:[name='normal-normalContent'].invoice-item-selected")
					.val();
		} else {
			invoice_common_content = "";
		}
		if ($("li[name='normal-bookContent']").html() != null) {
			invoice_book_content = $(
					"li:[name='normal-bookContent'].invoice-item-selected")
					.val();
		} else {
			invoice_book_content = "";
		}
	}
	else {
		// 电子发票内容
		if ($("li:[name='electro-bookContent']").html() != null) {
			invoice_book_content = $(
					"li:[name='electro-bookContent'].invoice-item-selected")
					.val();
		} else {
			invoice_book_content = "";
		}
		if ($("li:[name='electro-normalContent']").html() != null) {
			invoice_common_content = $(
					"li:[name='electro-normalContent'].invoice-item-selected")
					.val();
		} else {
			invoice_common_content = "";
		}
	}

	consigineePhone = $("#e_consignee_mobile").val();
	consignee_email = $("#e_consignee_email").val();
	//示例
	$("body",window.parent.document).find("input[name='invoice.invoiceType']").val(invoice_type);
	$("body",window.parent.document).find("input[name='invoice.invoiceTitle']").val(invoice_title);
	$("body",window.parent.document).find("input[name='invoice.invoiceCompanyName']").val(invoice_companyName);
	$("body",window.parent.document).find("input[name='invoice.invoiceCommonContent']").val(invoice_common_content);
	$("body",window.parent.document).find("input[name='invoice.invoiceBookContent']").val(invoice_book_content);
	$("body",window.parent.document).find("input[name='invoice.electroInvoiceTitle']").val(electro_invoiceTitle);
	$("body",window.parent.document).find("input[name='invoice.electroPhone']").val(electro_phone);
	$("body",window.parent.document).find("input[name='invoice.electroEmail']").val(electro_email);
	$("body",window.parent.document).find("input[name='invoice.electroCompanyName']").val(electro_companyName);
	
	//用于修改发票时数据的回显.
	//普通发票 
	if(invoice_type == 1){
		$("body",window.parent.document).find(".in_type").html("普通发票");
		$("body",window.parent.document).find(".in_name").html(invoice_companyName);
		if(invoice_common_content == 1){
			$("body",window.parent.document).find(".in_content").html("明细");
		}else if(invoice_common_content == 2){
			$("body",window.parent.document).find(".in_content").html("办公用品");
		}else if(invoice_common_content == 3){
			$("body",window.parent.document).find(".in_content").html("电脑配件");
		}else if(invoice_common_content == 19){
			$("body",window.parent.document).find(".in_content").html("耗材");
		}
		//电子发票
	}else if(invoice_type == 3){
		$("body",window.parent.document).find(".in_type").html("电子发票");
		$("body",window.parent.document).find(".in_name").html(invoice_companyName);
		if(invoice_common_content == 1){
			$("body",window.parent.document).find(".in_content").html("明细");
		}else if(invoice_common_content == 2){
			$("body",window.parent.document).find(".in_content").html("办公用品");
		}else if(invoice_common_content == 3){
			$("body",window.parent.document).find(".in_content").html("电脑配件");
		}else if(invoice_common_content == 19){
			$("body",window.parent.document).find(".in_content").html("耗材");
		}
	}
	window.parent.jQuery.closeDialog();
}
