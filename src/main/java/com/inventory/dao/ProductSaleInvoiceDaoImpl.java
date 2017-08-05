package com.inventory.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.inventory.model.ProductSaleInvoice;

@Repository
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true) 
public class ProductSaleInvoiceDaoImpl implements ProductSaleInvoiceDao {

	@Autowired SessionFactory sessionFactory;
	Session session = null;
	
	@Override
	public ProductSaleInvoice getProductSaleInvoiceBySerialNo(String serialNo) {
		ProductSaleInvoice productSaleInvoice=null;
		session = sessionFactory.openSession();
		
		@SuppressWarnings("unchecked")
		List<ProductSaleInvoice> productSaleInvoies=session.createQuery("FROM ProductSaleInvoice p WHERE p.serialNumber=:sn").setParameter("sn",serialNo).list();
		if(productSaleInvoies!=null){
			productSaleInvoice=productSaleInvoies.get(0);
		}
		session.close();
		return productSaleInvoice;
	}

	@Override
	public ProductSaleInvoice getProductSaleInvoiceByIndoorSerialNo(
			String indoorSerialNo) {
			ProductSaleInvoice productSaleInvoice=null;
			session = sessionFactory.openSession();
		
		@SuppressWarnings("unchecked")
		List<ProductSaleInvoice> productSaleInvoies=session.createQuery("FROM ProductSaleInvoice p WHERE p.indoorSerialNo=:sn").setParameter("sn",indoorSerialNo).list();
		if(productSaleInvoies!=null){
			productSaleInvoice=productSaleInvoies.get(0);
		}
		session.close();
		return productSaleInvoice;
	}

	
}
