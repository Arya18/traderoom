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
	
	private double unitPriceBeforeDiscount;
	
	private String starRating;
	
	private String indoorPurchaseInvoiceNo;
	
	private String indoorModelNumber;
	
	private double unitPrice;
	
	private double rebateDiscount;
	
	private double tradeDiscount;
	
	private double billAmount;
	
	private String indoorLocation;
	
	private double discountRate;
	
	private double discountedAmount;
	
	private String size;
	
	private String sessionId;
	
	private String purchaseInvoiceNo;
	
	private Date purchaseInvoiceDate;
	
	private String serialNumber;
	
	private String location;

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

	public double getDiscountRate() {
		return discountRate;
	}

	public void setDiscountRate(double discountRate) {
		this.discountRate = discountRate;
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

	public double getUnitPriceBeforeDiscount() {
		return unitPriceBeforeDiscount;
	}

	public void setUnitPriceBeforeDiscount(double unitPriceBeforeDiscount) {
		this.unitPriceBeforeDiscount = unitPriceBeforeDiscount;
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

	public double getRebateDiscount() {
		return rebateDiscount;
	}

	public void setRebateDiscount(double rebateDiscount) {
		this.rebateDiscount = rebateDiscount;
	}

	public double getTradeDiscount() {
		return tradeDiscount;
	}

	public void setTradeDiscount(double tradeDiscount) {
		this.tradeDiscount = tradeDiscount;
	}

	public double getBillAmount() {
		return billAmount;
	}

	public void setBillAmount(double billAmount) {
		this.billAmount = billAmount;
	}

	public String getIndoorLocation() {
		return indoorLocation;
	}

	public void setIndoorLocation(String indoorLocation) {
		this.indoorLocation = indoorLocation;
	}
	
}
