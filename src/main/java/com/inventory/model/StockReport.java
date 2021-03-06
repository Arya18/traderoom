package com.inventory.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.Proxy;

@Entity
@Table(name = "stockreport")
@JsonIgnoreProperties(ignoreUnknown = true)
public class StockReport {


	@Id
	@GeneratedValue
	@Column(name = "id")
	private long id;
	
	@OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="productId")
	private Product product;
	
	@Column(name = "units")
	private long units;
	
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public long getUnits() {
		return units;
	}

	public void setUnits(long units) {
		this.units = units;
	}


	
	
	
}
