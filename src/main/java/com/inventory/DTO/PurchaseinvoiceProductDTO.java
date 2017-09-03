package com.inventory.DTO;

import java.util.Date;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PurchaseinvoiceProductDTO {
	private String invoiceNumber;
	private String paymentMode;
	private double amountPaid;
	private double balanceLeft;
	private double totalDiscount;
	private double finalAmount;
	private String comments="ok";
	private Date date;
	private long supplierId;
	private double taxAmount;
	private Long firmId;
	private String chequeNumber;
	private String bankName;
	private Date chequeDate;
	private String invoiceSequence;
	List<ProductDTO> productsArray;
	public String getInvoiceNumber() {
		return invoiceNumber;
	}
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
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
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public long getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(long supplierId) {
		this.supplierId = supplierId;
	}
	
	public List<ProductDTO> getProductsArray() {
		return productsArray;
	}
	public void setProductsArray(List<ProductDTO> productsArray) {
		this.productsArray = productsArray;
	}
	public double getTotalDiscount() {
		return totalDiscount;
	}
	public void setTotalDiscount(double totalDiscount) {
		this.totalDiscount = totalDiscount;
	}
	public double getFinalAmount() {
		return finalAmount;
	}
	public void setFinalAmount(double finalAmount) {
		this.finalAmount = finalAmount;
	}
	public double getTaxAmount() {
		return taxAmount;
	}
	public void setTaxAmount(double taxAmount) {
		this.taxAmount = taxAmount;
	}
	public Long getFirmId() {
		return firmId;
	}
	public void setFirmId(Long firmId) {
		this.firmId = firmId;
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
	public String getInvoiceSequence() {
		return invoiceSequence;
	}
	public void setInvoiceSequence(String invoiceSequence) {
		this.invoiceSequence = invoiceSequence;
	}
	
	
}
