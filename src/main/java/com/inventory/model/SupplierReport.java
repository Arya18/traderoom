package com.inventory.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Entity
@Table(name = "supplierreport")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SupplierReport implements Serializable{

	@Id
	@GeneratedValue
	@Column(name = "supplierReportId")
	private long supplierReportId;
	
	@ManyToOne
    @JoinColumn(name="supplierId")
	private Supplier supplier;
	
	
	@OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="purchaseInvoiceNo")
	private PurchaseInvoice purchaseInvoice;
	

	public long getSupplierReportId() {
		return supplierReportId;
	}

	public void setSupplierReportId(long supplierReportId) {
		this.supplierReportId = supplierReportId;
	}

    
	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public PurchaseInvoice getPurchaseInvoice() {
		return purchaseInvoice;
	}

	public void setPurchaseInvoice(PurchaseInvoice purchaseInvoice) {
		this.purchaseInvoice = purchaseInvoice;
	}

		
	
}
