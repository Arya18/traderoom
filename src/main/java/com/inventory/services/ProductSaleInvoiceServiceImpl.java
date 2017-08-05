package com.inventory.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inventory.dao.ProductSaleInvoiceDao;
import com.inventory.model.ProductSaleInvoice;

@Service
@Transactional(rollbackFor=Exception.class)
public class ProductSaleInvoiceServiceImpl implements ProductSaleInvoiceService{

	@Autowired ProductSaleInvoiceDao productSaleInvoiceDao;
	@Override
	public ProductSaleInvoice getProductSaleInvoiceBySerialNo(String serialNo) {
		return productSaleInvoiceDao.getProductSaleInvoiceBySerialNo(serialNo);
	}

	@Override
	public ProductSaleInvoice getProductSaleInvoiceByIndoorSerialNo(
			String indoorSerialNo) {
		return productSaleInvoiceDao.getProductSaleInvoiceByIndoorSerialNo(indoorSerialNo);
	}

}
