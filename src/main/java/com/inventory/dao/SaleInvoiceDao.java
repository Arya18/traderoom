package com.inventory.dao;

import java.math.BigInteger;
import java.util.List;

import com.inventory.model.SaleInvoice;

public interface SaleInvoiceDao {

	boolean addOrUpdateSaleInvoice(SaleInvoice saleInvoice);
	List<SaleInvoice> getSaleInvoiceList();
	SaleInvoice getSaleInvoiceById(long saleinvoiceNo);
	SaleInvoice getSaleInvoiceByinvoiceNumber(String saleInvoiceNumber);
	BigInteger paymentDueCount();
	List<SaleInvoice> getAllDueSaleInvoice();
	SaleInvoice getLastSaleInvoice(long customerId);
}
