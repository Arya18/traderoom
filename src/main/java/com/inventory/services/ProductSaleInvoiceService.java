package com.inventory.services;

import com.inventory.model.ProductSaleInvoice;

public interface ProductSaleInvoiceService {

	ProductSaleInvoice getProductSaleInvoiceBySerialNo(String serialNo);
	
	ProductSaleInvoice getProductSaleInvoiceByIndoorSerialNo(String indoorSerialNo);
}
