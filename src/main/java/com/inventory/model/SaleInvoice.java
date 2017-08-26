package com.inventory.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;


@Entity
@Table(name = "saleinvoice")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SaleInvoice implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "saleInvoiceNo")
	private long saleInvoiceNo;
	
	@Column(name="cmpySaleInvoiceNo")
	private String cmpySaleInvoiceNo;
	
	@Column(name = "invoiceDate")
	@Temporal(TemporalType.DATE)
	private Date invoiceDate;
	
	
	
	@Column(name = "totaldiscountedAmount")
	private double totalDiscountedAmount;
	
	@Column(name = "finalAmount")
	private double finalAmount;
	
	@Column(name = "paymentMode")
	private String paymentMode;
	
	
	private double amountPaid;
	
	private double balanceLeft;
	
	@Column(name = "taxPercent")
	private Double taxPercent=0.0;
	
	@Column(name = "taxAmount")
	private Double taxAmount=0.0;
	
	@Column(name = "chequeNumber")
	private String chequeNumber;
	
	@Column(name = "bankName")
	private String bankName;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "chequeDate")
	private Date chequeDate;
	
	@OneToMany(mappedBy = "saleinvoice",fetch=FetchType.EAGER)
	private List<ProductSaleInvoice> productSaleInvoices=new ArrayList<ProductSaleInvoice>();
	
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="checkerId")
	private Checker checker;
	
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="adminId")
	private Admin admin;
	
	public Checker getChecker() {
		return checker;
	}

	public void setChecker(Checker checker) {
		this.checker = checker;
	}
	
	
	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}


	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="salesPersonId")
	private SalesPerson salesPerson;
	
	
	public SalesPerson getSalesPerson() {
		return salesPerson;
	}

	public void setSalesPerson(SalesPerson salesPerson) {
		this.salesPerson = salesPerson;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	@ManyToOne
	@JoinColumn(name = "customerId")
	private Customer customer;

	@OneToOne(mappedBy = "saleInvoice", fetch=FetchType.LAZY)
	private TaxInvoice taxInvoice;
	
	@OneToOne(mappedBy = "saleInvoice")
	private CustomerReport customerReport;
	

	public CustomerReport getCustomerReport() {
		return customerReport;
	}

	public void setCustomerReport(CustomerReport customerReport) {
		this.customerReport = customerReport;
	}
	
	public TaxInvoice getTaxInvoice() {
		return taxInvoice;
	}

	public void setTaxInvoice(TaxInvoice taxInvoice) {
		this.taxInvoice = taxInvoice;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public long getSaleInvoiceNo() {
		return saleInvoiceNo;
	}

	public void setSaleInvoiceNo(long saleInvoiceNo) {
		this.saleInvoiceNo = saleInvoiceNo;
	}



	public Date getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public double getFinalAmount() {
		return finalAmount;
	}

	public void setFinalAmount(double finalAmount) {
		this.finalAmount = finalAmount;
	}

	public String getCmpySaleInvoiceNo() {
		return cmpySaleInvoiceNo;
	}

	public void setCmpySaleInvoiceNo(String cmpySaleInvoiceNo) {
		this.cmpySaleInvoiceNo = cmpySaleInvoiceNo;
	}

	
	public double getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(double amountPaid) {
		this.amountPaid = amountPaid;
	}

	public double getBalanceLeft() {
		return balanceLeft;
	}

	public void setBalanceLeft(double balanceLeft) {
		this.balanceLeft = balanceLeft;
	}

	public List<ProductSaleInvoice> getProductSaleInvoices() {
		return productSaleInvoices;
	}

	public void setProductSaleInvoices(List<ProductSaleInvoice> productSaleInvoices) {
		this.productSaleInvoices = productSaleInvoices;
	}

	public double getTotalDiscountedAmount() {
		return totalDiscountedAmount;
	}

	public void setTotalDiscountedAmount(double totalDiscountedAmount) {
		this.totalDiscountedAmount = totalDiscountedAmount;
	}

	public Double getTaxPercent() {
		return taxPercent;
	}

	public void setTaxPercent(Double taxPercent) {
		this.taxPercent = taxPercent;
	}

	public Double getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(Double taxAmount) {
		this.taxAmount = taxAmount;
	}

	public String getChequeNumber() {
		return chequeNumber;
	}

	public void setChequeNumber(String chequeNumber) {
		this.chequeNumber = chequeNumber;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public Date getChequeDate() {
		return chequeDate;
	}

	public void setChequeDate(Date chequeDate) {
		this.chequeDate = chequeDate;
	}

	


	
}
