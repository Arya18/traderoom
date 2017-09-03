package com.inventory.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="product_saleinvoice")
public class ProductSaleInvoice {

	@Id
	@GeneratedValue
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	
	@ManyToOne(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name = "saleinvoice_id")
	private SaleInvoice saleinvoice;
	private String comment;
	
	@Column(name = "quantity")
	private long quantity;
	@Column(name = "unitPrice")
	private double unitPrice;
	
	@Column(name="discountRate")
	private double discountRate;
	
	@Column(name="cmpyPurchaseInvoiceNo")
	private String cmpyPurchaseInvoiceNo;
	
	@Column(name="purchaseInvoiceDate")
	private String purchaseInvoiceDate;
	
	@Column(name="serialNumber")
	private String serialNumber;
	
	@Column(name="indoorSerialNo")
	private String indoorSerialNo;
	
	@Column(name="cmpyPurchaseinvoiceNoForIndoor")
	private String cmpyPurchaseinvoiceNoForIndoor;
	
	@Column(name = "trade_discount")
	private Double tradeDiscount;
	
	@Column(name="bill_amount")
	private Double billAmount;
	
	@Column(name="singleUnitTax")
	private Double singleUnitTax;
	
	@ManyToOne
	@JoinColumn(name = "firm_id")
	private Firms firm;
	
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
	
	
	public SaleInvoice getSaleinvoice() {
		return saleinvoice;
	}
	public void setSaleinvoice(SaleInvoice saleinvoice) {
		this.saleinvoice = saleinvoice;
	}
	public String getComment() {
		return comment;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
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
	public double getDiscountRate() {
		return discountRate;
	}
	public void setDiscountRate(double discountRate) {
		this.discountRate = discountRate;
	}
	public String getCmpyPurchaseInvoiceNo() {
		return cmpyPurchaseInvoiceNo;
	}
	public void setCmpyPurchaseInvoiceNo(String cmpyPurchaseInvoiceNo) {
		this.cmpyPurchaseInvoiceNo = cmpyPurchaseInvoiceNo;
	}
	public String getPurchaseInvoiceDate() {
		return purchaseInvoiceDate;
	}
	public void setPurchaseInvoiceDate(String purchaseInvoiceDate) {
		this.purchaseInvoiceDate = purchaseInvoiceDate;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getIndoorSerialNo() {
		return indoorSerialNo;
	}
	public void setIndoorSerialNo(String indoorSerialNo) {
		this.indoorSerialNo = indoorSerialNo;
	}
	
	public String getCmpyPurchaseinvoiceNoForIndoor() {
		return cmpyPurchaseinvoiceNoForIndoor;
	}
	
	public void setCmpyPurchaseinvoiceNoForIndoor(
			String cmpyPurchaseinvoiceNoForIndoor) {
		this.cmpyPurchaseinvoiceNoForIndoor = cmpyPurchaseinvoiceNoForIndoor;
	}
	public Firms getFirm() {
		return firm;
	}
	public void setFirm(Firms firm) {
		this.firm = firm;
	}
	public Double getTradeDiscount() {
		return tradeDiscount;
	}
	public void setTradeDiscount(Double tradeDiscount) {
		this.tradeDiscount = tradeDiscount;
	}
	public Double getBillAmount() {
		return billAmount;
	}
	public void setBillAmount(Double billAmount) {
		this.billAmount = billAmount;
	}
	public Double getSingleUnitTax() {
		return singleUnitTax;
	}
	public void setSingleUnitTax(Double singleUnitTax) {
		this.singleUnitTax = singleUnitTax;
	}
	
}
