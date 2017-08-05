package com.inventory.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inventory.common.dao.DashboardDao;
import com.inventory.model.Product;
import com.inventory.model.ProductPurchaseInvoice;
import com.inventory.model.ProductSaleInvoice;

@Service
@Transactional(rollbackFor=Exception.class)
public class DashboardService {
	@Autowired DashboardDao dashboardDao;

	public List<Product> findProductTypeByBrand(String brandName) {
		
		
		return dashboardDao.findProductTypeByBrand(brandName);
	}

	public List<Product> findModelNumber(String brandName, String productType) {
		return dashboardDao.findModelNumber(brandName,productType);	
		}

	public List<Product> findSize(String brandName, String productType,
			String modelNumber) {
		return dashboardDao.findSize(brandName,productType,modelNumber);	}

	public Product findproductInfo(String brandName, String productType,
			String modelNumber, String size,String star) {
		List<Product> products=dashboardDao.findproductInfo(brandName,productType,modelNumber,size,star);	
		if(!products.isEmpty()){
			return products.get(0);
		}
		return null;
		}

	public void saveSID(ProductSaleInvoice sip) {
		dashboardDao.saveSID(sip);
	}
	
	public void savePurchaseInvoiceDetails(ProductPurchaseInvoice ppi) {
		dashboardDao.savePurchaseInvoiceDetails(ppi);
	}
	
	public List<ProductSaleInvoice> findAllProductSaleInvoiceBySaleInvoiceid(long saleInvoiceNo){
		return dashboardDao.findAllProductSaleInvoiceBySaleInvoiceid(saleInvoiceNo);
		
	}

	public List<Product> findStar(String brandName, String productType,
			String modelNumber) {
		return dashboardDao.findStar(brandName,productType,modelNumber);
	}

	public Product findproductInfo(String brandName, String modelNumber) {
		return dashboardDao.findproductInfo(brandName,modelNumber);
	}

	public Product findproductInfoExcludeStarRating(String brandName,
			String productType, String modelNumber, String size) {
		List<Product> products=dashboardDao.findproductInfoExcludeStarRating(brandName,productType,modelNumber,size);	
		if(!products.isEmpty()){
			return products.get(0);
		}
		return null;
		}

/*	public Product findproductInfoByModelAndSerialNo(String brandName,
			String modelNumber, String serialNo) {
		return dashboardDao.findproductInfoByModelAndSerialNo(brandName,modelNumber,serialNo);
	}*/
	

}
