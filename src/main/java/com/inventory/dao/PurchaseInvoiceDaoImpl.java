package com.inventory.dao;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.inventory.model.Admin;
import com.inventory.model.Product;
import com.inventory.model.PurchaseInvoice;
import com.inventory.model.SaleInvoice;

@Transactional(propagation = Propagation.SUPPORTS, readOnly = true) 
public class PurchaseInvoiceDaoImpl implements PurchaseInvoiceDao{

	@Autowired
	SessionFactory sessionFactory;

	Session session = null;
	Transaction tx = null;
	
	@Override
	public boolean addOrUpdatePurchaseInvoice(PurchaseInvoice purchaseInvoice) {
		boolean flag = false;
	    try{    
	    	session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.saveOrUpdate(purchaseInvoice);
			tx.commit();
			session.close();
			flag = true;
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
		return flag;	
	}

	@Override
	public PurchaseInvoice getPurchaseInvoiceById(long purchaseInvoiceId) {
		PurchaseInvoice  purchaseInvoice = null;
		try{
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(PurchaseInvoice.class);
			 criteria.add(Restrictions.eq("invoiceNo", purchaseInvoiceId));
			 Object result=criteria.uniqueResult();
			 purchaseInvoice = (PurchaseInvoice)result;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		session.close();
		return purchaseInvoice;
	}

	@Override
	public List<PurchaseInvoice> getPurchaseList() {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		
		Criteria c = session.createCriteria(PurchaseInvoice.class);

		List<PurchaseInvoice> purchaseInvoiceList = c.list();
		tx.commit();
		session.close();
		return purchaseInvoiceList;
	}

	@Override
	public boolean deletePurchaseInvoice(long purchaseInvoiceNo) {
		boolean flag = true;
		try{
		session = sessionFactory.openSession();
		Object o = session.load(PurchaseInvoice.class, purchaseInvoiceNo);
		tx = session.getTransaction();
		session.beginTransaction();
		session.delete(o);
		tx.commit();
		}catch(Exception e){
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public PurchaseInvoice verifyPurchaseInvoice(long purchaseInvoiceNo, boolean verify) {
		Session session;
		PurchaseInvoice  purchaseInvoice = null;
		System.out.println("in Purchase Invoice Dao : "+ verify);
		try{
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(PurchaseInvoice.class);
			 criteria.add(Restrictions.eq("invoiceNo", purchaseInvoiceNo));
			 Object result=criteria.uniqueResult();
			 purchaseInvoice = (PurchaseInvoice)result;
			 session.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		try{    
	    	session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.saveOrUpdate(purchaseInvoice);
			tx.commit();
			session.close();
			
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
		return purchaseInvoice;
	}

	@Override
	public PurchaseInvoice getPurchaseInvoiceBySupplierReportId(
			long supplierReportId) {
		Session session;
		PurchaseInvoice  purchaseInvoice = null;
		try{
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(PurchaseInvoice.class);
			criteria.createAlias("supplierReport", "s");
			criteria.add(Restrictions.eq("s.supplierReportId", supplierReportId));
			 Object result=criteria.uniqueResult();
			 purchaseInvoice = (PurchaseInvoice)result;
			 session.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return purchaseInvoice;
	}

	@Override
	public PurchaseInvoice getpurchaseInvoiceByInvoiceNumber(
			String purchaseInvoiceNumber) {
		Session session;
		PurchaseInvoice purchaseInvoice=null;
		try{
			session = sessionFactory.openSession();
			Criteria criteria=session.createCriteria(PurchaseInvoice.class);
			criteria.add(Restrictions.eq("cmpyPurchaseInvoiceNo",purchaseInvoiceNumber));
			Object result=criteria.uniqueResult();
			purchaseInvoice=(PurchaseInvoice)result;
			session.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return purchaseInvoice;
	}

	@Override
	public BigInteger paymentDueCountOfSuppliers() {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		
		SQLQuery query = session.createSQLQuery("select count(*) from purchaseinvoice p where p.balanceLeft<0");
		BigInteger count = (BigInteger)query.uniqueResult();
		System.out.println(count);
		tx.commit();
		session.close();
		return count;
	}

	@Override
	public List<PurchaseInvoice> getAllDuePurchaseInvoice() {
		session = sessionFactory.openSession();
		@SuppressWarnings("unchecked")
		List<PurchaseInvoice> purchaseInvoices = session.createQuery("SELECT p FROM PurchaseInvoice p WHERE  p.balanceLeft<0 ORDER BY p.balanceLeft ASC").list();
		session.close();
		return purchaseInvoices;
	}
	
	@Override
	public BigInteger getMaxId() {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		
		SQLQuery query = session.createSQLQuery("select max(invoiceNo) from purchaseinvoice");
		BigInteger maxId = (BigInteger)query.uniqueResult();
		tx.commit();
		session.close();
		return maxId;
	}

}
