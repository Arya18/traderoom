package com.inventory.dao;

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

import com.inventory.model.StockReport;

@Transactional(propagation = Propagation.SUPPORTS, readOnly = true) 
public class StockReportDaoImpl implements StockReportDao{

	@Autowired
	SessionFactory sessionFactory;

	Session session = null;
	Transaction tx = null;
	
	@Override
	public boolean createOrUpdateStockReport(StockReport stockReport) {
		boolean flag = false;
	    try{    
	    	session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.saveOrUpdate(stockReport);
			tx.commit();
			session.close();
			flag = true;
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
		return flag;	
	}

	@Override
	public StockReport getStockReportByProductId(long productId) {
		StockReport stockReport = new StockReport();
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		
		Criteria c = session.createCriteria(StockReport.class);
		c.createAlias("product", "p");
		c.add(Restrictions.eq("p.id", productId));
		
		Object result=c.uniqueResult();
		stockReport = (StockReport)result;
		
		return stockReport;
	}

	@Override
	public List<Object[]> getStockReportList() {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		
		SQLQuery query = (SQLQuery) session.createSQLQuery("select p.brand,p.productType,p.modelNumber,sr.units,p.reorderpoint from stockreport sr,product p where sr.productId=p.id");
		@SuppressWarnings("unchecked")
		List<Object[]> rows = query.list();
		tx.commit();
		session.close();
		return rows;
		}
		
}
