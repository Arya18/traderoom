package com.inventory.services;

import java.util.List;

import com.inventory.model.Customer;

public interface CustomerServices {
	boolean addOrUpdateCustomer(Customer customer);
	boolean deleteCustomer(long customerId);
	List<Customer> customerList(long adminId);
	Customer getCustomerById(long id);
	List<Customer> getAllCustomers();
	public Customer getCustomerByEmail(String email);
	public Customer getCustomerByMobile(long contactNo);
}
