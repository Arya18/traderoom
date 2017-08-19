package com.inventory.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.SQLQuery;
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
	Transaction tx = null;
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
		session.close();
		return ppis;	
		}

	@Override
	public List<ProductPurchaseInvoice> getAllIndoorProductByProductId(
			long productid,int indoorSaleStatus) {
		session = sessionFactory.openSession();
		@SuppressWarnings("unchecked")
		List<ProductPurchaseInvoice> ppis=session.createQuery("FROM ProductPurchaseInvoice WHERE product_id=:id AND indoorsale=:indoorStatus").setParameter("id",productid).setParameter("indoorStatus", indoorSaleStatus).list();
		session.close();
		return ppis;
	}

	

	@Override
	public List<ProductPurchaseInvoice> getAllOutdoorProductByProductid(
			long productid, int status) {
		session = sessionFactory.openSession();
		@SuppressWarnings("unchecked")
		List<ProductPurchaseInvoice> ppis=session.createQuery("FROM ProductPurchaseInvoice WHERE product_id=:id AND sale=:status").setParameter("id",productid).setParameter("status", status).list();
		session.close();
		return ppis;
	}

	@Override
	public List<ProductPurchaseInvoice> getProductByIndoorOutdoorStatus(
			long productid, int indoorStatus, int outdoorStatus) {
		session = sessionFactory.openSession();
		@SuppressWarnings("unchecked")
		List<ProductPurchaseInvoice> ppis=session.createQuery("FROM ProductPurchaseInvoice WHERE product_id=:id AND sale=:status AND indoorsale=:indoorStatus").setParameter("id",productid).setParameter("status", outdoorStatus).setParameter("indoorStatus", indoorStatus).list();
		session.close();
		return ppis;
	}

	@Override
	public List<Object[]> getAllStockProductPurchaseInvoice() {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		SQLQuery query = (SQLQuery) session.createSQLQuery("select p.brand,p.modelNumber,p.size,p.starRating,fr.name as frimName,p.id as productId,count(*) as quantity,sum(ps.unit_price) as unitPrice from product p "+
		"inner join product_supplier ps on p.id=ps.product_id inner join firms fr on fr.id=ps.firm_id where ps.sale=0 group by fr.id, p.id");
		@SuppressWarnings("unchecked")
		List<Object[]> rows = query.list();
		tx.commit();
		session.close();
		return rows;
	}

	@Override
	public List<Object[]> getRecordByFilter(String firmName, String unit, String brandName, String modelnumber,
			String size, String starName, String location) {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		
		String joinQuery="select p.brand,p.modelNumber,p.size,p.starRating,fr.name as frimName,p.id as productId,count(*) as quantity,sum(ps.unit_price) as unitPrice from product p "+ 
					"inner join product_supplier ps on p.id=ps.product_id inner join firms fr on fr.id=ps.firm_id";
		StringBuilder queryString=new StringBuilder(joinQuery);
		boolean flag=false;
		if(firmName!=null && !firmName.trim().isEmpty()){
			queryString.append(" where fr.name=:name");
			flag=true;
		}
		
		if(unit!=null && !unit.trim().isEmpty()){
			if(unit.equals("I")){
				if(flag){
					queryString.append(" AND ps.indoorsale=0");
				}
				else{
					queryString.append(" where ps.indoorsale=0");
				}
				flag=true;
		}
		}
		else{
			
			if(flag){
				queryString.append(" AND ps.sale=0");
			}
			else{
				queryString.append(" where ps.sale=0");
			}
			flag=true;
		}
		
		if(brandName!=null && !brandName.trim().isEmpty()){
			if(flag){
				queryString.append(" AND p.brand=:brand");
			}
			else{
				queryString.append(" where p.brand=brand");
			}
			flag=true;
		}
		
		if(modelnumber!=null && !modelnumber.trim().isEmpty()){
			if(flag){
				queryString.append(" AND p.modelNumber=:modelnumber");
			}
			else{
				queryString.append(" where p.modelNumber=modelnumber");
			}
			flag=true;
		}
		
		if(size!=null && !size.trim().isEmpty()){
			if(flag){
				queryString.append(" AND p.size=:size");
			}
			else{
				queryString.append(" where p.size=:size");
			}
			flag=true;
		}
		
		if(starName!=null && !starName.trim().isEmpty()){
			if(flag){
				queryString.append(" AND p.starRating=:star");
			}
			else{
				queryString.append(" where p.starRating=:star");
			}
			flag=true;
		}
		if(location!=null && !location.trim().isEmpty()){
			if(location.equals("IUG")){
				if(flag){
					queryString.append(" AND ps.indoor_location='Godown'");
				}
				else{
					queryString.append(" where ps.indoor_location='Godown'");
				}
				flag=true;
		}
			else if(location.equals("IUS")){
				if(flag){
					queryString.append(" AND ps.indoor_location='Shop'");
				}
				else{
					queryString.append(" where ps.indoor_location='Shop'");
				}
				flag=true;
		}
			else if(location.equals("UG")){
				if(flag){
					queryString.append(" AND ps.outer_location='Godown'");
				}
				else{
					queryString.append(" where ps.outer_location='Godown'");
				}
				flag=true;
		}
			else if(location.equals("US")){
				if(flag){
					queryString.append(" AND ps.outer_location='Shop'");
				}
				else{
					queryString.append(" where ps.outer_location='Shop'");
				}
				flag=true;
		}
		
		}
		queryString.append(" ").append("group by fr.id, p.id");
		SQLQuery query = (SQLQuery) session.createSQLQuery(queryString.toString());
		if(firmName!=null && !firmName.trim().isEmpty()){
			query.setParameter("name", firmName);
		}
		if(brandName!=null && !brandName.trim().isEmpty()){
			query.setParameter("brand", brandName);
		}
		if(modelnumber!=null && !modelnumber.trim().isEmpty()){
			query.setParameter("modelnumber", modelnumber);
		}
		if(size!=null && !size.trim().isEmpty()){
			query.setParameter("size", size);
		}
		if(starName!=null && !starName.trim().isEmpty()){
			query.setParameter("star", starName);
		}
		@SuppressWarnings("unchecked")
		List<Object[]> rows = query.list();
		tx.commit();
		session.close();
		return rows;
	}
	
	}


