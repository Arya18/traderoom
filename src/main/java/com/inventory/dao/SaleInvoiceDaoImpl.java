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
import com.inventory.model.CustomerReport;
import com.inventory.model.Product;
import com.inventory.model.SaleInvoice;

@Transactional(propagation = Propagation.SUPPORTS, readOnly = true) 
public class SaleInvoiceDaoImpl implements SaleInvoiceDao{

	@Autowired
	SessionFactory sessionFactory;

	Session session = null;
	Transaction tx = null;
	
	@Override
	public boolean addOrUpdateSaleInvoice(SaleInvoice saleInvoice) {
		boolean flag = false;
	    try{    
	    	session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.saveOrUpdate(saleInvoice);
			tx.commit();
			session.close();
			flag = true;
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
		return flag;	
	}

	@Override
	public List<SaleInvoice> getSaleInvoiceList() {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		
		Criteria c = session.createCriteria(SaleInvoice.class);

		List<SaleInvoice> saleInvoiceList = c.list();
		tx.commit();
		session.close();
		return saleInvoiceList;
	}

	@Override
	public SaleInvoice getSaleInvoiceById(long saleinvoiceNo) {
		SaleInvoice  saleInvoice = null;
		try{
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(SaleInvoice.class);
			 criteria.add(Restrictions.eq("saleInvoiceNo", saleinvoiceNo));
			 Object result=criteria.uniqueResult();
			 saleInvoice = (SaleInvoice)result;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		session.close();
		return saleInvoice;
	}

	@Override
	public SaleInvoice getSaleInvoiceByinvoiceNumber(String companySaleInvoiceNo) {
		SaleInvoice  saleInvoice = null;
		try{
			session = sessionFactory.openSession();
			@SuppressWarnings("unchecked")
			List<SaleInvoice> saleInvoices = session.createQuery("SELECT s FROM SaleInvoice s WHERE s.cmpySaleInvoiceNo=:sno AND s.isDisable=0").setParameter("sno", companySaleInvoiceNo).list();
			if(!saleInvoices.isEmpty()){
				saleInvoice= saleInvoices.get(0);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		session.close();
		return saleInvoice;
	}

	@Override
	public BigInteger paymentDueCount() {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		
		SQLQuery query = session.createSQLQuery("select count(*) from saleinvoice s where s.balanceLeft>0 AND s.isDisable=0"); 
		BigInteger count = (BigInteger)query.uniqueResult();
		System.out.println(count);
		tx.commit();
		session.close();
		return count;
	}

	@Override
	public List<SaleInvoice> getAllDueSaleInvoice() {
		session = sessionFactory.openSession();
		@SuppressWarnings("unchecked")
		List<SaleInvoice> saleInvoices = session.createQuery("SELECT s FROM SaleInvoice s WHERE  s.balanceLeft>0 AND s.isDisable=0 ORDER BY s.balanceLeft DESC").list();
		session.close();
		return saleInvoices;
	}
	
	@Override
	public SaleInvoice getLastSaleInvoice(long customerId){
		session = sessionFactory.openSession();
		@SuppressWarnings("unchecked")
		List<SaleInvoice> saleInvoices = session.createQuery("SELECT s FROM SaleInvoice s WHERE  s.customer:=c AND s.isDisable=0").setParameter("c", customerId).list();
		
		session.close();
		return saleInvoices.get(0);
	}
	
	@Override
	public BigInteger getMaxId() {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		
		SQLQuery query = session.createSQLQuery("select max(saleInvoiceNo) from saleinvoice");
		BigInteger maxId = (BigInteger)query.uniqueResult();
		tx.commit();
		session.close();
		return maxId;
	}
}
