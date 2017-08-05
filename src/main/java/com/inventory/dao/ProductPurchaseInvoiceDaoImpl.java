package com.inventory.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.inventory.model.ProductPurchaseInvoice;

@Repository
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true) 
public class ProductPurchaseInvoiceDaoImpl implements ProductPurchaseInvoiceDao{
	
	@Autowired SessionFactory sessionFactory;
	Session session = null;
	@Override
	public ProductPurchaseInvoice getProductPurchaseInvoiceBySerialNo(
			String serialNo) {
	
		ProductPurchaseInvoice productPurchaseInvoice=null;
		session = sessionFactory.openSession();
		@SuppressWarnings("unchecked")
		List<ProductPurchaseInvoice> productPurchaseInvoices = session.createQuery("FROM ProductPurchaseInvoice p WHERE p.serialNo=:sn AND p.sale=0").setParameter("sn",serialNo).list();
		
		if(!productPurchaseInvoices.isEmpty()){
		productPurchaseInvoice=productPurchaseInvoices.get(0);
		}
		session.close();
		return productPurchaseInvoice;
	}
	
	@Override
	public List<ProductPurchaseInvoice> getProductByProductId(long id) {
		session = sessionFactory.openSession();
		
		@SuppressWarnings("unchecked")
		List<ProductPurchaseInvoice> ppis=session.createQuery("FROM ProductPurchaseInvoice WHERE product_id=:id AND sale=0").setParameter("id",id).list();
		return ppis;
	}
	
	@Override
	public List<ProductPurchaseInvoice> getIndoorSerialNoByProductId(long id){
		@SuppressWarnings("unchecked")
		List<ProductPurchaseInvoice> ppis=session.createQuery("FROM ProductPurchaseInvoice WHERE product_id=:id AND indoorsale=0").setParameter("id",id).list();
		return ppis;
	}

	@Override
		public boolean addOrUpdateProductPurchaseinvoice(ProductPurchaseInvoice ppi){
			boolean flag = false;
		    try{    
		    	session = sessionFactory.openSession();
				Transaction tx = session.beginTransaction();
				session.saveOrUpdate(ppi);
				tx.commit();
				session.close();
				flag = true;
		    }catch(Exception e){
		    	e.printStackTrace();
		    }
			return flag;	
		}

	@Override
	public ProductPurchaseInvoice getProductPurchaseInvoiceByIndoorSerialNo(
			String indoorserialNo) {
		ProductPurchaseInvoice productPurchaseInvoice=null;
		session = sessionFactory.openSession();
		@SuppressWarnings("unchecked")
		List<ProductPurchaseInvoice> productPurchaseInvoices = session.createQuery("FROM ProductPurchaseInvoice p WHERE p.indoorSerialNo=:sn").setParameter("sn",indoorserialNo).list();
		
		if(!productPurchaseInvoices.isEmpty()){
		productPurchaseInvoice=productPurchaseInvoices.get(0);
		}
		session.close();
		return productPurchaseInvoice;
	}

	@Override
	public ProductPurchaseInvoice findProductPurchaseInvoiceBySerialNo(
			String serialNumber) {
		ProductPurchaseInvoice productPurchaseInvoice=null;
		session = sessionFactory.openSession();
		@SuppressWarnings("unchecked")
		List<ProductPurchaseInvoice> productPurchaseInvoices = session.createQuery("FROM ProductPurchaseInvoice p WHERE p.serialNo=:sn").setParameter("sn",serialNumber).list();
		
		if(!productPurchaseInvoices.isEmpty()){
		productPurchaseInvoice=productPurchaseInvoices.get(0);
		}
		session.close();
		return productPurchaseInvoice;
	}

	@Override
	public ProductPurchaseInvoice findProductPurchaseInvoiceByIndoorSerialNo(
			String indoorSerialNumber) {
		
		ProductPurchaseInvoice productPurchaseInvoice=null;
		session = sessionFactory.openSession();
		@SuppressWarnings("unchecked")
		List<ProductPurchaseInvoice> productPurchaseInvoices = session.createQuery("FROM ProductPurchaseInvoice p WHERE p.indoorSerialNo=:sn").setParameter("sn",indoorSerialNumber).list();
		
		if(!productPurchaseInvoices.isEmpty()){
		productPurchaseInvoice=productPurchaseInvoices.get(0);
		}
		session.close();
		return productPurchaseInvoice;
	}

	@Override
	public ProductPurchaseInvoice findProductPurchaseInvoiceById(
			long productPurchaseInvoiceId) {
		session = sessionFactory.openSession();
		ProductPurchaseInvoice productPurchaseInvoice=(ProductPurchaseInvoice)session.get(ProductPurchaseInvoice.class, productPurchaseInvoiceId);
		session.close();
		return productPurchaseInvoice;
	}

	@Override
	public List<ProductPurchaseInvoice> getAllProductByProductId(long id) {
	session = sessionFactory.openSession();
		
		@SuppressWarnings("unchecked")
		List<ProductPurchaseInvoice> ppis=session.createQuery("FROM ProductPurchaseInvoice WHERE product_id=:id").setParameter("id",id).list();
		return ppis;	
		}

	@Override
	public List<ProductPurchaseInvoice> getAllIndoorProductByProductId(
			long productid,int indoorSaleStatus) {
		@SuppressWarnings("unchecked")
		List<ProductPurchaseInvoice> ppis=session.createQuery("FROM ProductPurchaseInvoice WHERE product_id=:id AND indoorsale=:indoorStatus").setParameter("id",productid).setParameter("indoorStatus", indoorSaleStatus).list();
		return ppis;
	}

	

	@Override
	public List<ProductPurchaseInvoice> getAllOutdoorProductByProductid(
			long productid, int status) {
		@SuppressWarnings("unchecked")
		List<ProductPurchaseInvoice> ppis=session.createQuery("FROM ProductPurchaseInvoice WHERE product_id=:id AND sale=:status").setParameter("id",productid).setParameter("status", status).list();
		return ppis;
	}

	@Override
	public List<ProductPurchaseInvoice> getProductByIndoorOutdoorStatus(
			long productid, int indoorStatus, int outdoorStatus) {
		@SuppressWarnings("unchecked")
		List<ProductPurchaseInvoice> ppis=session.createQuery("FROM ProductPurchaseInvoice WHERE product_id=:id AND sale=:status AND indoorsale=:indoorStatus").setParameter("id",productid).setParameter("status", outdoorStatus).setParameter("indoorStatus", indoorStatus).list();
		return ppis;
	}
	
	}


