package com.inventory.services;

import java.math.BigInteger;
import java.util.List;

import com.inventory.model.SaleInvoice;

public interface SaleInvoiceServices {
	boolean addOrUpdateSaleInvoice(SaleInvoice saleInvoice);
	List<SaleInvoice> getSaleInvoiceList();
	SaleInvoice getSaleInvoiceById(long parseLong);
	SaleInvoice getSaleInvoiceByinvoiceNumber(String saleInvoiceNumber);
	BigInteger paymentDueCount();
	List<SaleInvoice> getAllDueSaleInvoice();
	SaleInvoice getLastSaleInvoice(long customerId);
	public BigInteger getMaxId();
}
