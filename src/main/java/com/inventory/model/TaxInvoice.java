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
@Table(name = "taxinvoice")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaxInvoice {


	@Id
	@GeneratedValue
	@Column(name = "taxInvoiceNo")
	private long taxInvoiceNo;
	
	@OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="saleInvoiceNo")
	private SaleInvoice saleInvoice;

	public long getTaxInvoiceNo() {
		return taxInvoiceNo;
	}

	public void setTaxInvoiceNo(long taxInvoiceNo) {
		this.taxInvoiceNo = taxInvoiceNo;
	}

	public SaleInvoice getSaleInvoice() {
		return saleInvoice;
	}

	public void setSaleInvoice(SaleInvoice saleInvoice) {
		this.saleInvoice = saleInvoice;
	}
}
