package com.inventory.services;

import java.math.BigInteger;
import java.util.List;

import com.inventory.model.Product;

public interface ProductServices {
	boolean addOrUpdateProduct(Product product);
	Product getProductByNameAndModel(String productName,String modelNumber);
	boolean deleteProduct(long productId);
	List<Product> productList();
	Product getProductById(long productId);
	List<Product> getAllProducts();
	BigInteger reorderProductCount();
	List<Product> getAllReorderProducts();
	Product getProductByBrand(String brand);
	Product getProductByModelNumber(String modelNumber);
}
