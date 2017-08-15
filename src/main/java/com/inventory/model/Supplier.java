package com.inventory.model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

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

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.Proxy;
import org.json.JSONObject;

import com.inventory.DTO.AdminDTO;

@Entity
@Table(name = "supplier")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Supplier implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue
	@Column(name = "id")
	private long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "contactNo")
	private long contactNo;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "tin_number")
	private String tinNumber;
	
	@Column(name = "gst_number")
	private String gstNumber;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="adminId")
	private Admin admin;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="checkerId")
	private Checker checker;

	@OneToMany(mappedBy="supplier")
	private List<SupplierReport> supplierReport;
	
	@OneToMany(mappedBy="supplier")
	private Set<PurchaseInvoice> purchaseInvoices;
	
	 public Supplier(){
		
	}
	
	public Supplier(AdminDTO adminDTO){
		this.name = adminDTO.getName();
		this.address = adminDTO.getAddress();
		this.contactNo = adminDTO.getContactNo();
		this.email = adminDTO.getEmail();
	}


	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public long getContactNo() {
		return contactNo;
	}

	public void setContactNo(long contactNo) {
		this.contactNo = contactNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Admin getAdmin() {
		return admin;
	}
	
	

	public String getTinNumber() {
		return tinNumber;
	}

	public void setTinNumber(String tinNumber) {
		this.tinNumber = tinNumber;
	}

	public String getGstNumber() {
		return gstNumber;
	}

	public void setGstNumber(String gstNumber) {
		this.gstNumber = gstNumber;
	}

	public Checker getChecker() {
		return checker;
	}

	public void setChecker(Checker checker) {
		this.checker = checker;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	
	public List<SupplierReport> getSupplierReport() {
		return supplierReport;
	}

	public void setSupplierReport(List<SupplierReport> supplierReport) {
		this.supplierReport = supplierReport;
	}

	public Set<PurchaseInvoice> getPurchaseInvoices() {
		return purchaseInvoices;
	}

	public void setPurchaseInvoices(Set<PurchaseInvoice> purchaseInvoices) {
		this.purchaseInvoices = purchaseInvoices;
	}

	public String toJSON() throws Exception{
		JSONObject jsonObj=new JSONObject();
		jsonObj.put("id",id).put("address",address).put("contact", contactNo).put("email",email);
		return jsonObj.toString();
	}
	
}