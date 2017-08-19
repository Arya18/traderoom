package com.inventory.DTO;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDTO {

	private long id;
	
	private String brand;
	
	private String modelNumber;
	
	private String productType;
	
	private long quantity;
	
	private String starRating;
	
	private String indoorPurchaseInvoiceNo;
	
	private String indoorModelNumber;
	
	private double unitPrice;
	
	private Double rebateDiscount;
	
	private Double tradeDiscount;
	
	private Double billAmount;
	
	private String indoorLocation;
	
	private double discountedAmount;
	
	private Double singleUnitTax;
	
	private String size;
	
	private String sessionId;
	
	private String purchaseInvoiceNo;
	
	private Date purchaseInvoiceDate;
	
	private String serialNumber;
	
	private String location;
	
	private Double cgstValue;
	
	private Double igstValue;
	
	private Double sgstValue;
	
	private String hsnCode;

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

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public double getDiscountedAmount() {
		return discountedAmount;
	}

	public void setDiscountedAmount(double discountedAmount) {
		this.discountedAmount = discountedAmount;
	}

	public String getPurchaseInvoiceNo() {
		return purchaseInvoiceNo;
	}

	public void setPurchaseInvoiceNo(String purchaseInvoiceNo) {
		this.purchaseInvoiceNo = purchaseInvoiceNo;
	}

	public Date getPurchaseInvoiceDate() {
		return purchaseInvoiceDate;
	}

	public void setPurchaseInvoiceDate(Date purchaseInvoiceDate) {
		this.purchaseInvoiceDate = purchaseInvoiceDate;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getStarRating() {
		return starRating;
	}

	public void setStarRating(String starRating) {
		this.starRating = starRating;
	}

	public String getIndoorPurchaseInvoiceNo() {
		return indoorPurchaseInvoiceNo;
	}

	public void setIndoorPurchaseInvoiceNo(String indoorPurchaseInvoiceNo) {
		this.indoorPurchaseInvoiceNo = indoorPurchaseInvoiceNo;
	}

	public String getIndoorModelNumber() {
		return indoorModelNumber;
	}

	public void setIndoorModelNumber(String indoorModelNumber) {
		this.indoorModelNumber = indoorModelNumber;
	}

	
	public Double getRebateDiscount() {
		return rebateDiscount;
	}

	public void setRebateDiscount(Double rebateDiscount) {
		this.rebateDiscount = rebateDiscount;
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

	public String getIndoorLocation() {
		return indoorLocation;
	}

	public void setIndoorLocation(String indoorLocation) {
		this.indoorLocation = indoorLocation;
	}

	public Double getCgstValue() {
		return cgstValue;
	}

	public void setCgstValue(Double cgstValue) {
		this.cgstValue = cgstValue;
	}

	public Double getIgstValue() {
		return igstValue;
	}

	public void setIgstValue(Double igstValue) {
		this.igstValue = igstValue;
	}

	public Double getSgstValue() {
		return sgstValue;
	}

	public void setSgstValue(Double sgstValue) {
		this.sgstValue = sgstValue;
	}

	public String getHsnCode() {
		return hsnCode;
	}

	public void setHsnCode(String hsnCode) {
		this.hsnCode = hsnCode;
	}

	public Double getSingleUnitTax() {
		return singleUnitTax;
	}

	public void setSingleUnitTax(Double singleUnitTax) {
		this.singleUnitTax = singleUnitTax;
	}
	
	
}
