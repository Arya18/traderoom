package com.inventory.services;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.inventory.dao.SaleInvoiceDao;
import com.inventory.model.SaleInvoice;


@Transactional(propagation = Propagation.SUPPORTS, readOnly = true) 
@Service("saleInvoiceServices")
public class SaleInvoiceServicesImpl implements SaleInvoiceServices{

	@Autowired
	SaleInvoiceDao saleInvoiceDao;

	@Override
	public boolean addOrUpdateSaleInvoice(SaleInvoice saleInvoice) {
		return saleInvoiceDao.addOrUpdateSaleInvoice(saleInvoice);
	}

	@Override
	public List<SaleInvoice> getSaleInvoiceList() {
		return saleInvoiceDao.getSaleInvoiceList(); 
	}

	@Override
	public SaleInvoice getSaleInvoiceById(long saleinvoiceNo) {
		return saleInvoiceDao.getSaleInvoiceById(saleinvoiceNo);
	}

	@Override
	public SaleInvoice getSaleInvoiceByinvoiceNumber(String saleInvoiceNumber) {
		return saleInvoiceDao.getSaleInvoiceByinvoiceNumber(saleInvoiceNumber);
	}

	@Override
	public BigInteger paymentDueCount() {
		return saleInvoiceDao.paymentDueCount();
	}

	@Override
	public List<SaleInvoice> getAllDueSaleInvoice() {
		return saleInvoiceDao.getAllDueSaleInvoice();
	}
	public SaleInvoice getLastSaleInvoice(long customerId){
		return saleInvoiceDao.getLastSaleInvoice(customerId);
	}
}
