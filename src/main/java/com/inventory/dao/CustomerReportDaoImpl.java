package com.inventory.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.inventory.model.CustomerReport;
import com.inventory.model.Product;

@Transactional(propagation = Propagation.SUPPORTS, readOnly = true) 
public class CustomerReportDaoImpl implements CustomerReportDao{

	@Autowired
	SessionFactory sessionFactory;

	Session session = null;
	Transaction tx = null;
	
	@Override
	public boolean addOrUpdateCustomerReport(CustomerReport customerReport) {
		boolean flag = false;
	    try{    
	    	session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.saveOrUpdate(customerReport);
			tx.commit();
			session.close();
			flag = true;
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
		return flag;	
	}

	@Override
	public List<CustomerReport> getCustomerReportList() {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		
		Criteria c = session.createCriteria(CustomerReport.class);

		@SuppressWarnings("unchecked")
		List<CustomerReport> customerReportList = c.list();
		tx.commit();
		session.close();
		return customerReportList;
	}
	
	@Override
	public List<Object[]> getCustomerInvoiceReportList(){
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		
		SQLQuery query = (SQLQuery) session.createSQLQuery("select c.id,c.name,c.contactNo,c.email,c.address from customer c,customerreport cr where c.id=cr.customerId");
		@SuppressWarnings("unchecked")
		List<Object[]> rows = query.list();
		tx.commit();
		session.close();
		return rows;
		
	}
}
