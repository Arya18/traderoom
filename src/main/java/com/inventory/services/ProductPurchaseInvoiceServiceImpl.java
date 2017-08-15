package com.inventory.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inventory.DTO.AdminDTO;
import com.inventory.dao.ProductPurchaseInvoiceDao;
import com.inventory.model.ProductPurchaseInvoice;

@Service
@Transactional(rollbackFor=Exception.class)
public class ProductPurchaseInvoiceServiceImpl implements ProductPurchaseInvoiceService{
@Autowired ProductPurchaseInvoiceDao productPurchaseInvoiceDao;

@Override
public ProductPurchaseInvoice getProductPurchaseInvoiceBySerialNo(String serialNo){
		return productPurchaseInvoiceDao.getProductPurchaseInvoiceBySerialNo(serialNo);
}

@Override
public List<ProductPurchaseInvoice> getProductByProductId(long id) {
	return productPurchaseInvoiceDao.getProductByProductId(id);
}

@Override
public boolean addOrUpdateProductPurchaseinvoice(ProductPurchaseInvoice ppi) {
	return productPurchaseInvoiceDao.addOrUpdateProductPurchaseinvoice(ppi);
}

@Override
public List<ProductPurchaseInvoice> getIndoorSerialNoByProductId(long id) {
	return productPurchaseInvoiceDao.getIndoorSerialNoByProductId(id);
}

@Override
public ProductPurchaseInvoice getProductPurchaseInvoiceByIndoorSerialNo(
		String indoorserialNo) {
	return productPurchaseInvoiceDao.getProductPurchaseInvoiceByIndoorSerialNo(indoorserialNo);
}

@Override
public ProductPurchaseInvoice findProductPurchaseInvoiceBySerialNo(
		String serialNumber) {
	return productPurchaseInvoiceDao.findProductPurchaseInvoiceBySerialNo(serialNumber);
}

@Override
public ProductPurchaseInvoice findProductPurchaseInvoiceByIndoorSerialNo(
		String indoorSerialNumber) {
	// TODO Auto-generated method stub
	return productPurchaseInvoiceDao.findProductPurchaseInvoiceByIndoorSerialNo(indoorSerialNumber);
}

@Override
public ProductPurchaseInvoice findProductPurchaseInvoiceById(
		long productPurchaseInvoiceId) {
	return productPurchaseInvoiceDao.findProductPurchaseInvoiceById(productPurchaseInvoiceId);
}

@Override
public List<ProductPurchaseInvoice> getAllProductByProductId(long productId){
	return productPurchaseInvoiceDao.getAllProductByProductId(productId);
}

@Override
public List<ProductPurchaseInvoice> getAllIndoorProductByProductId(
		long productid,int indoorSaleStatus) {
	// TODO Auto-generated method stub
	return productPurchaseInvoiceDao.getAllIndoorProductByProductId(productid,indoorSaleStatus);
}

@Override
public List<ProductPurchaseInvoice> getAllOutdoorProductByProductid(
		long productid, int status) {
	return productPurchaseInvoiceDao.getAllOutdoorProductByProductid(productid,status);
}

@Override
public List<ProductPurchaseInvoice> getProductByIndoorOutdoorStatus(
		long productid, int indoorStatus, int outdoorStatus) {
	return productPurchaseInvoiceDao.getProductByIndoorOutdoorStatus(productid,indoorStatus,outdoorStatus);
}

@Override
public List<ProductPurchaseInvoice> getAllProductPurchaseInvoice() {
	return productPurchaseInvoiceDao.getAllProductPurchaseInvoice();
}

@Override
public List<Object[]> getRecordByFilter(String firmName, String unit, String brandName, String modelnumber, String size,
		String starName, String location) {
	return productPurchaseInvoiceDao.getRecordByFilter(firmName,unit,brandName,modelnumber, size,
		starName,location);
}

}
