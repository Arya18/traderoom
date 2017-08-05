package com.inventory.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Proxy;

import com.inventory.DTO.ProductDTO;

@Entity
@Table(name = "product")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product implements Serializable{
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	private long id;
	
	@Column (name = "brand")
	private String brand;
	
	@Column (name = "modelNumber")
	private String modelNumber;
	
	@Column (name = "productType")
	private String productType;
	
	@Column(name = "quantity")
	private long quantity;
		
	@Column(name = "discountRate")
	private double discountRate;
	
	@Column(name="reorderpoint")
	private  long reorderPoint;
	
	@Column(name ="size")
	private String size;
	
	@Column(name="starRating")
	private String star;
	
	@Temporal(TemporalType.DATE)
	private Date created;
	
	@Temporal(TemporalType.DATE)
	private Date updated;
	
	private long createdby;
	
	private long updatedby;
	
	@OneToMany(mappedBy = "product")
	private List<ProductSaleInvoice> productSaleInvoices=new ArrayList<ProductSaleInvoice>();
	
	@OneToMany(mappedBy="product")	
	private Set<ProductPurchaseInvoice> productPurchaseInvoice=new HashSet<ProductPurchaseInvoice>();
	
	@OneToOne(mappedBy="product" , fetch = FetchType.LAZY)
	private StockReport stockReport; 
	
	
	public Product(){
		
	}
	
	public Product(ProductDTO productDTO){
		this.brand = productDTO.getBrand();
		this.modelNumber = productDTO.getModelNumber();
		this.productType = productDTO.getProductType();
		this.quantity = productDTO.getQuantity();
		this.discountRate = productDTO.getDiscountRate();
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	

	public long getReorderPoint() {
		return reorderPoint;
	}

	public void setReorderPoint(long reorderPoint) {
		this.reorderPoint = reorderPoint;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getModelNumber() {
		return modelNumber;
	}

	public void setModelNumber(String modelNumber) {
		this.modelNumber = modelNumber;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	public double getDiscountRate() {
		return discountRate;
	}

	public void setDiscountRate(double discountRate) {
		this.discountRate = discountRate;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public long getCreatedby() {
		return createdby;
	}

	public void setCreatedby(long createdby) {
		this.createdby = createdby;
	}

	public long getUpdatedby() {
		return updatedby;
	}

	public void setUpdatedby(long updatedby) {
		this.updatedby = updatedby;
	}

	public List<ProductSaleInvoice> getProductSaleInvoices() {
		return productSaleInvoices;
	}

	public void setProductSaleInvoices(List<ProductSaleInvoice> productSaleInvoices) {
		this.productSaleInvoices = productSaleInvoices;
	}

	public Set<ProductPurchaseInvoice> getProductPurchaseInvoice() {
		return productPurchaseInvoice;
	}

	public void setProductPurchaseInvoice(
			Set<ProductPurchaseInvoice> productPurchaseInvoice) {
		this.productPurchaseInvoice = productPurchaseInvoice;
	}

	public String getStar() {
		return star;
	}

	public void setStar(String star) {
		this.star = star;
	}
	
	
}
