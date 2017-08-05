package com.inventory.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;



import com.inventory.model.Product;
import com.inventory.model.Supplier;

@Transactional(propagation = Propagation.SUPPORTS, readOnly = true) 
public class SupplierDaoImpl implements SupplierDao {

	@Autowired
	SessionFactory sessionFactory;

	Session session = null;
	Transaction tx = null;
	
	@Override
	public boolean addOrUpdateSupplier(Supplier supplier) {
		boolean flag = false;
	    try{    
	    	session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.saveOrUpdate(supplier);
			tx.commit();
			session.close();
			flag = true;
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
		return flag;	
	}

	@Override
	public boolean deleteSupplier(long supplierId) {
		boolean flag = true;
		try{
		session = sessionFactory.openSession();
		Object o = session.load(Supplier.class, supplierId);
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
	public List<Supplier> supplierList(long adminId) {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		
		Criteria c = session.createCriteria(Supplier.class);
		c.createAlias("admin", "a");
		c.add(Restrictions.eq("a.id", adminId));
		
		List<Supplier> supplierList = c.list();
		tx.commit();
		session.close();
		return supplierList;
	}

	@Override
	public Supplier getSupplierById(long id) {
		Supplier  supplier = null;
		try{
			session = sessionFactory.openSession();
			List<Supplier> suppliers = session.createQuery("SELECT s FROM Supplier s WHERE s.id=:id").setParameter("id", id).list();
			if(!suppliers.isEmpty()){
			supplier=suppliers.get(0);
			session.close();
			return supplier;
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
			
		}
		session.close();
		return supplier;
	}

	@Override
	public List<Supplier> getAllSupplier() {
		
	session = sessionFactory.openSession();
	@SuppressWarnings("unchecked")
	List<Supplier> suppliers = session.createQuery("SELECT s FROM Supplier s").list();
		
	session.close();
	return suppliers;
	}

	@Override
	public Supplier getSupplierByMobileNumber(long supplierPhone) {
		Supplier supplier=null;
		try{
			session = sessionFactory.openSession();
			Criteria criteria=session.createCriteria(Supplier.class);
			criteria.add(Restrictions.eq("contactNo",supplierPhone));
			Object result=criteria.uniqueResult();
			supplier=(Supplier)result;
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		session.close();
		return supplier;
	}

}
