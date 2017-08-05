package com.inventory.dao;

import com.inventory.model.ProductSaleInvoice;

public interface ProductSaleInvoiceDao {
ProductSaleInvoice getProductSaleInvoiceBySerialNo(String serialNo);
	
	ProductSaleInvoice getProductSaleInvoiceByIndoorSerialNo(String indoorSerialNo);
}
