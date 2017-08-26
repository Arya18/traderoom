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
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.inventory.model.Product;

@Repository
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true) 
public class ProductDaoImpl implements ProductDao{
	
	@Autowired
	SessionFactory sessionFactory;

	Session session = null;
	Transaction tx = null;

	@Override
	public boolean addOrUpdateProduct(Product product) {
		boolean flag = false;
		try{    
	    	session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.saveOrUpdate(product);
			tx.commit();
			session.close();
			flag = true;
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
		return flag;	
	}

	@Override
	public Product getProductByNameAndModel(String productName,
			String modelNumber) {
		Session session;
		Product  product = null;
		try{
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(Product.class);
			 criteria.add(Restrictions.eq("productName", productName));
			 criteria.add(Restrictions.eq("modelNumber", modelNumber));
			 Object result=criteria.uniqueResult();
			 product = (Product)result;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return product;
	}

	@Override
	public boolean deleteProduct(long productId) {
		boolean flag = true;
		try{
		session = sessionFactory.openSession();
		Object o = session.load(Product.class, productId);
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
	public List<Product> productList() {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		
		Criteria c = session.createCriteria(Product.class);

		List<Product> productList = c.list();
		tx.commit();
		session.close();
		return productList;
	}

	@Override
	public Product getProductById(long productId) {
		Session session = null;
		Product  product = null;
		try{
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(Product.class);
			 criteria.add(Restrictions.eq("id", productId));
			 Object result=criteria.uniqueResult();
			 product = (Product)result;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		session.close();
		return product;
	}

	@Override
	public List<Product> getAllProducts() {
		session = sessionFactory.openSession();
		
		@SuppressWarnings("unchecked")
		List<Product> products = session.createQuery("SELECT p FROM Product p").list();
			
		session.close();
		return products;
	}

	@Override
	public BigInteger reorderProductCount() {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		
		SQLQuery query = session.createSQLQuery("select count(*) from product p where p.quantity<p.reorderpoint");
		BigInteger count = (BigInteger)query.uniqueResult();
		tx.commit();
		session.close();
		return count;
	}
	
	@Override
	public List<Product> getAllReorderProducts(){
	session = sessionFactory.openSession();
		
		@SuppressWarnings("unchecked")
		List<Product> products = session.createQuery("SELECT p FROM Product p where p.quantity<p.reorderPoint ORDER BY p.reorderPoint ASC").list();
			
		session.close();
		return products;
	}
	
	@Override
	public Product getProductByBrand(String brand){
		Session session;
		Product  product = null;
		session = sessionFactory.openSession();
		try{
			
			Criteria criteria = session.createCriteria(Product.class);
			 criteria.add(Restrictions.eq("brand", brand));
			 Object result=criteria.uniqueResult();
			 product = (Product)result;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		session.close();
		return product;
	}

	@Override
	public Product getProductByModelNumber(String modelNumber) {
		session = sessionFactory.openSession();
		
		Product product=null;
		@SuppressWarnings("unchecked")
		List<Product> products = session.createQuery("FROM Product where modelNumber=:mn").setParameter("mn", modelNumber).list();
		if(!products.isEmpty()){
			product=products.get(0);
		}
		session.close();
		return product;
	}

	@Override
	public List<Object[]> getTop5SaleProdcut() {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		
		SQLQuery query = (SQLQuery) session.createSQLQuery("select sum(si.unitPrice) as totalPrice,si.product_id,p.brand,p.modelNumber,p.productType,p.size,"+
		"count(*) as quantity from product_saleinvoice si inner join product p on si.product_id=p.id group by  si.product_id order by totalPrice desc limit 5");
		@SuppressWarnings("unchecked")
		List<Object[]> rows = query.list();
		tx.commit();
		session.close();
		return rows;
	}
	
	@Override
	public List<Object[]> getTop5PurchaseProduct() {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		
		SQLQuery query = (SQLQuery) session.createSQLQuery("select sum(ps.unit_price) as totalPrice,ps.product_id,p.brand,p.modelNumber,p.productType,p.size,"+
		"count(*) as quantity from product_supplier ps inner join product p on ps.product_id=p.id where ps.sale=0 group by  ps.product_id order by totalPrice desc limit 5");
		@SuppressWarnings("unchecked")
		List<Object[]> rows = query.list();
		tx.commit();
		session.close();
		return rows;
	}
	
}
