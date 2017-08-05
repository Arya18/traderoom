package com.inventory.model;

import java.io.Serializable;
import java.util.List;
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
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.Proxy;

import com.inventory.DTO.AdminDTO;


@Entity
@Table(name = "customer")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Customer implements Serializable{

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

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

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
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="checkerId")
	private Checker checker;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="adminId")
	private Admin admin;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="salesPersonId")
	private SalesPerson salesPerson;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="makerId")
	private Maker maker;
	


	@OneToMany(mappedBy = "customer", fetch=FetchType.LAZY)
	private Set<SaleInvoice> saleInvoice;
	
	@OneToMany(mappedBy = "customer")
	private List<CustomerReport> customerReport;
	
	public List<CustomerReport> getCustomerReport() {
		return customerReport;
	}

	public void setCustomerReport(List<CustomerReport> customerReport) {
		this.customerReport = customerReport;
	}

	public Set<SaleInvoice> getSaleInvoice() {
		return saleInvoice;
	}

	public void setSaleInvoice(Set<SaleInvoice> saleInvoice) {
		this.saleInvoice = saleInvoice;
	}

	public Customer(){
		
	}
	
	

	public SalesPerson getSalesPerson() {
		return salesPerson;
	}

	public void setSalesPerson(SalesPerson salesPerson) {
		this.salesPerson = salesPerson;
	}

	public Maker getMaker() {
		return maker;
	}

	public void setMaker(Maker maker) {
		this.maker = maker;
	}

	public Checker getChecker() {
		return checker;
	}

	public void setChecker(Checker checker) {
		this.checker = checker;
	}

	public Customer(AdminDTO adminDTO){
		this.name = adminDTO.getName();
		this.address = adminDTO.getAddress();
		this.contactNo = adminDTO.getContactNo();
		this.email = adminDTO.getEmail();
	}
}
