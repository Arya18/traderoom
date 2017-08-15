package com.inventory.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Entity
@Table(name = "firms")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Firms implements Serializable{
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;
	
	private String name;
	
	private String tinNumber;
	
	private String shortName;
	
	private String email;
	
	private String gstNumber;
	
	private String contactNumber;
	
	private String addresLine1;
	
	private String addressLine2;
	
	private String city;
	
	private String state;
	
	@OneToMany(mappedBy = "firm")
	private List<ProductSaleInvoice> productSaleInvoices;
	
	@OneToMany(mappedBy="firm")	
	private List<ProductPurchaseInvoice> productPurchaseInvoice;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTinNumber() {
		return tinNumber;
	}

	public void setTinNumber(String tinNumber) {
		this.tinNumber = tinNumber;
	}

	public String getGstNumber() {
		return gstNumber;
	}

	public void setGstNumber(String gstNumber) {
		this.gstNumber = gstNumber;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getAddresLine1() {
		return addresLine1;
	}

	public void setAddresLine1(String addresLine1) {
		this.addresLine1 = addresLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<ProductSaleInvoice> getProductSaleInvoices() {
		return productSaleInvoices;
	}

	public void setProductSaleInvoices(List<ProductSaleInvoice> productSaleInvoices) {
		this.productSaleInvoices = productSaleInvoices;
	}

	public List<ProductPurchaseInvoice> getProductPurchaseInvoice() {
		return productPurchaseInvoice;
	}

	public void setProductPurchaseInvoice(List<ProductPurchaseInvoice> productPurchaseInvoice) {
		this.productPurchaseInvoice = productPurchaseInvoice;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
