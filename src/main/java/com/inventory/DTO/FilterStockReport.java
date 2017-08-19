package com.inventory.DTO;

public class FilterStockReport {
	private String brandName;
	
	private Long productId;
	
	private Integer quantity;
	
	private String modelNumber;
	
	private String star;
	
	private String size;
	
	private String firmName;
	
	private String serialNo;
	
	private String indoorSerialNo;
	
	private String indoorLocation;
	
	private String location;
	
	private Double unitPrice;

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getModelNumber() {
		return modelNumber;
	}

	public void setModelNumber(String modelNumber) {
		this.modelNumber = modelNumber;
	}

	public String getStar() {
		return star;
	}

	public void setStar(String star) {
		this.star = star;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getFirmName() {
		return firmName;
	}

	public void setFirmName(String firmName) {
		this.firmName = firmName;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public String getIndoorSerialNo() {
		return indoorSerialNo;
	}

	public void setIndoorSerialNo(String indoorSerialNo) {
		this.indoorSerialNo = indoorSerialNo;
	}

	public String getIndoorLocation() {
		return indoorLocation;
	}

	public void setIndoorLocation(String indoorLocation) {
		this.indoorLocation = indoorLocation;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}
	
	
	@Override
	public boolean  equals (Object object) {
	    boolean result = false;
	    if (object == null || object.getClass() != getClass()) {
	        result = false;
	    } else {
	    	FilterStockReport filterStockReport = (FilterStockReport) object;
	        if (this.productId.equals(filterStockReport.getProductId()) && this.firmName.equals(filterStockReport.getFirmName())) {
	            result = true;
	        }
	    }
	    return result;
	}
	
}
