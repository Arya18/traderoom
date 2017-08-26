package com.inventory.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.Proxy;

import com.inventory.DTO.PurchaseInvoiceDTO;


@Entity
@Table(name = "purchaseinvoice")
@JsonIgnoreProperties(ignoreUnknown = true)
public class PurchaseInvoice implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "invoiceNo")
	private long invoiceNo;
	
	@Column(name="cmpyPurchaseInvoiceNo")
	private String cmpyPurchaseInvoiceNo;
	
	@Column(name = "invoiceDate")
	private String inoviceDate;
	
	@Column(name = "quantity")
	private long quantity;

	@Column(name = "unitPrice")
	private double unitPrice;
	
	@Column(name = "discountAmount")
	private double discountAmount;
	
	@Column(name = "finalAmount")
	private double finalAmount;
		
	@Column(name = "paymentMode")
	private String paymentMode;
	
	@Column(name = "chequeNumber")
	private String chequeNumber;
	
	@Column(name = "bankName")
	private String bankName;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "chequeDate")
	private Date chequeDate;
	
	
	private double amountPaid;
	
	private double balanceLeft;
	
	private String comments;
	
	@Temporal(TemporalType.DATE)
	private Date date;

	@ManyToOne
	@JoinColumn(name="supplierId")
	private Supplier supplier;
	
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="makerId")
	private Maker maker;
	
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="checkerId")
	private Checker checker;
	
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="adminId")
	private Admin admin;
	
	@OneToOne(mappedBy = "purchaseInvoice", fetch=FetchType.LAZY)
	private SupplierReport supplierReport;
	
	@OneToMany(mappedBy="purchaseInvoice",fetch=FetchType.EAGER)
	private Set<ProductPurchaseInvoice> productPurchaseInvoice=new HashSet<ProductPurchaseInvoice>();
	
	@Column(name = "taxAmount")
	private Double taxAmount;
	
	
	public PurchaseInvoice(){
		
	}
	
	public PurchaseInvoice(PurchaseInvoiceDTO purchaseInvoiceDTO){
		this.inoviceDate = purchaseInvoiceDTO.getInvoiceDate();
		this.quantity = purchaseInvoiceDTO.getQuantity();
		this.unitPrice = purchaseInvoiceDTO.getUnitPrice();
	}

	public Maker getMaker() {
		return maker;
	}

	public void setMaker(Maker maker) {
		this.maker = maker;
	}
		


	public long getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(long invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public String getInoviceDate() {
		return inoviceDate;
	}

	public void setInoviceDate(String inoviceDate) {
		this.inoviceDate = inoviceDate;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public double getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(double discountAmount) {
		this.discountAmount = discountAmount;
	}

	public double getFinalAmount() {
		return finalAmount;
	}

	public void setFinalAmount(double finalAmount) {
		this.finalAmount = finalAmount;
	}

	public Set<ProductPurchaseInvoice> getProductPurchaseInvoice() {
		return productPurchaseInvoice;
	}

	public void setProductPurchaseInvoice(
			Set<ProductPurchaseInvoice> productPurchaseInvoice) {
		this.productPurchaseInvoice = productPurchaseInvoice;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
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

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getCmpyPurchaseInvoiceNo() {
		return cmpyPurchaseInvoiceNo;
	}

	public void setCmpyPurchaseInvoiceNo(String cmpyPurchaseInvoiceNo) {
		this.cmpyPurchaseInvoiceNo = cmpyPurchaseInvoiceNo;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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
