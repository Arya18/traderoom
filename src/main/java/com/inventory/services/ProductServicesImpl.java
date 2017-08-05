package com.inventory.services;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.inventory.dao.ProductDao;
import com.inventory.model.Product;


@Transactional(propagation = Propagation.SUPPORTS, readOnly = true) 
@Service("productServices")
public class ProductServicesImpl implements ProductServices {

	@Autowired
	ProductDao productDao;

	@Override
	public boolean addOrUpdateProduct(Product product) {
		return productDao.addOrUpdateProduct(product);
	}

	@Override
	public Product getProductByNameAndModel(String productName,
			String modelNumber) {
		return productDao.getProductByNameAndModel(productName, modelNumber);
	}

	@Override
	public boolean deleteProduct(long productId) {
		return productDao.deleteProduct(productId);
	}

	@Override
	public List<Product> productList() {
		return productDao.productList();
	}

	@Override
	public Product getProductById(long productId) {
		return productDao.getProductById(productId);
	}

	@Override
	public List<Product> getAllProducts() {
		return productDao.getAllProducts();
	}

	@Override
	public BigInteger reorderProductCount() {
		return productDao.reorderProductCount();
	}

	@Override
	public List<Product> getAllReorderProducts() {
		return productDao.getAllReorderProducts();
	}

	@Override
	public Product getProductByBrand(String brand) {
		return productDao.getProductByBrand(brand);
	}

	@Override
	public Product getProductByModelNumber(String modelNumber) {
		return productDao.getProductByModelNumber(modelNumber);
	}
	
}
