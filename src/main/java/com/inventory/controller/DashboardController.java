package com.inventory.controller;

import static com.inventory.utility.ConversionUtils.getSafeDouble;
import static com.inventory.utility.ConversionUtils.getSafeInteger;
import static com.inventory.utility.ConversionUtils.getSafeLong;
import static com.inventory.utility.ConversionUtils.getSafeString;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.inventory.DTO.FilterStockReport;
import com.inventory.DTO.ProductDTO;
import com.inventory.DTO.PurchaseinvoiceProductDTO;
import com.inventory.DTO.SaleInvoiceProductDTO;
import com.inventory.common.service.DashboardService;
import com.inventory.model.Admin;
import com.inventory.model.Checker;
import com.inventory.model.Customer;
import com.inventory.model.CustomerReport;
import com.inventory.model.Firms;
import com.inventory.model.Maker;
import com.inventory.model.Product;
import com.inventory.model.ProductPurchaseInvoice;
import com.inventory.model.ProductSaleInvoice;
import com.inventory.model.PurchaseInvoice;
import com.inventory.model.SaleInvoice;
import com.inventory.model.SalesPerson;
import com.inventory.model.StockReport;
import com.inventory.model.Supplier;
import com.inventory.model.SupplierReport;
import com.inventory.model.TaxInvoice;
import com.inventory.services.AdminServices;
import com.inventory.services.CheckerServices;
import com.inventory.services.CustomerReportServices;
import com.inventory.services.CustomerServices;
import com.inventory.services.MakerServices;
import com.inventory.services.ProductPurchaseInvoiceService;
import com.inventory.services.ProductSaleInvoiceService;
import com.inventory.services.ProductServices;
import com.inventory.services.PurchaseInvoiceServices;
import com.inventory.services.SaleInvoiceServices;
import com.inventory.services.SalesPersonServices;
import com.inventory.services.StockReportServices;
import com.inventory.services.SupplierReportServices;
import com.inventory.services.SupplierServices;
import com.inventory.services.TaxInvoiceServices;
import com.inventory.utility.CommonUtils;
import com.inventory.utility.SessionUser;

import au.com.bytecode.opencsv.CSVReader;

@Controller
@RequestMapping(value = "/dashboard")
public class DashboardController extends BaseController {
	@Autowired
	DashboardService dashboardService;
	@Autowired
	SaleInvoiceServices saleInvoiceServices;
	@Autowired
	ProductServices productServices;
	@Autowired
	CheckerServices checkerServices;
	@Autowired
	AdminServices adminServices;
	@Autowired
	CustomerServices customerService;
	@Autowired
	TaxInvoiceServices taxInvoiceServices;
	@Autowired
	CustomerReportServices customerReportServices;
	@Autowired
	SalesPersonServices salesPersonServices;
	@Autowired
	MakerServices makerServices;
	@Autowired
	SupplierServices supplierServices;
	@Autowired
	StockReportServices stockReportServices;
	@Autowired
	PurchaseInvoiceServices purchaseInvoiceServices;
	@Autowired
	SupplierReportServices supplierReportServices;
	@Autowired
	ProductPurchaseInvoiceService productPurchaseInvoiceService;
	@Autowired
	ProductSaleInvoiceService productSaleInvoiceService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String rendorDashboard(Model model) {
		BigInteger reorderCount = productServices.reorderProductCount();
		model.addAttribute("reorderCount", reorderCount);
		BigInteger payDueCustomerCount = saleInvoiceServices.paymentDueCount();
		model.addAttribute("payDueCustomerCount", payDueCustomerCount);
		BigInteger payDueSupplierCount = purchaseInvoiceServices.paymentDueCountOfSuppliers();
		model.addAttribute("payDueSupplierCount", payDueSupplierCount);
		return "dashboard";
	}

	@RequestMapping(value = "/createUsers", method = RequestMethod.GET)
	public String createUsers() {
		return "create-users";
	}

	@RequestMapping(value = "/createUsers", method = RequestMethod.POST)
	public String saveuser(Model model, HttpSession httpSession, @RequestParam(value = "email") String email,
			@RequestParam(value = "password") String password, @RequestParam(value = "role") String role,
			@RequestParam(value = "username") String username, @RequestParam(value = "status") String status) {

		if (email == null || username == null || role == null || status == null || password == null
				|| email.trim().length() == 0 || password.trim().length() == 0 || email.trim().length() == 0
				|| status.trim().length() == 0 || role.trim().length() == 0) {
			setError(model, "Inputs fields email or password or username or role or status cannot be null");
			return "create-users";
		}
		boolean emailExist = true;
		if (role.equalsIgnoreCase("Checker")) {
			emailExist = checkerServices.checkUniqueEmail(email);
		}
		if (role.equalsIgnoreCase("Maker")) {
			emailExist = makerServices.checkUniqueEmail(email);
		}
		if (role.equalsIgnoreCase("Sales Person")) {
			emailExist = salesPersonServices.checkUniqueEmail(email);
		}

		if (!emailExist) {
			setError(model, "Email Already exists");
			return "create-users";
		}
		System.out.println("status" + status);
		try {
			SessionUser sessionUser = SessionUser.getHttpSessionUser(httpSession);
			Admin admin = adminServices.getAdminById(sessionUser.getId());

			String passwordinMD5 = CommonUtils.getMD5(password);

			if (role.equalsIgnoreCase("Maker")) {
				Maker maker = new Maker();
				maker.setEmail(email);
				maker.setPassword(passwordinMD5);
				maker.setUsername(username);
				maker.setName(username);
				maker.setAdmin(admin);
				maker.setUserRole(role);

				maker.setStatus(0);
				if (status.equalsIgnoreCase("active")) {
					maker.setStatus(1);
				}
				makerServices.addOrUpdateMaker(maker);
			}

			if (role.equalsIgnoreCase("Checker")) {
				Checker checker = new Checker();
				checker.setEmail(email);
				checker.setPassword(passwordinMD5);
				checker.setUsername(username);
				checker.setName(username);
				checker.setAdmin(admin);
				checker.setUserRole(role);
				checker.setStatus(0);
				if (status.equalsIgnoreCase("active")) {
					checker.setStatus(1);
				}
				checkerServices.addOrUpdateChecker(checker);
			}
			if (role.equalsIgnoreCase("Sales Person")) {
				SalesPerson salePerson = new SalesPerson();
				salePerson.setEmail(email);
				salePerson.setPassword(passwordinMD5);
				salePerson.setUsername(username);
				salePerson.setName(username);
				salePerson.setAdmin(admin);
				salePerson.setUserRole(role);

				salePerson.setStatus(0);
				if (status.equalsIgnoreCase("active")) {
					salePerson.setStatus(1);
				}
				salesPersonServices.addOrUpdateSalesPerson(salePerson);
			}
		} catch (Exception e) {
			setUnknownError(model);
		}
		model.addAttribute("Update_Msg", true);
		return "create-users";
	}

	@RequestMapping(value = "/customerReport", method = RequestMethod.GET)
	public String customerReport(Model model) {
		List<CustomerReport> customerReps = customerReportServices.getCustomerReportList();
		System.out.println("Size:: " + customerReps.size());

		if (!customerReps.isEmpty())
			model.addAttribute("customerReports", customerReps);
		return "customer-report";
	}

	@RequestMapping(value = "/salesinvoice", method = RequestMethod.GET)
	public String salesInvoice(Model model) {
		List<Product> products = productServices.getAllProducts();
		Set<String> productBrands = new TreeSet<String>();
		for (Product pro : products) {
			productBrands.add(pro.getBrand());
		}
		model.addAttribute("products", productBrands);
		List<Firms> firms = dashboardService.findallFirms();
		model.addAttribute("firms", firms);
		return "sales-invoice";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(Model model, HttpServletRequest request) {
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("HTTP_SESSION_USER", null);
		httpSession.invalidate();
		return "login";
	}

	@RequestMapping(value = "/getproductType/{brandName}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> getProductType(@PathVariable(value = "brandName") String brandName) {

		Map<String, Object> responseMap = new HashMap<String, Object>(2);
		responseMap.put("exists", false);
		responseMap.put("brandName", null);

		try {
			List<Product> products = dashboardService.findProductTypeByBrand(brandName);
			if (!products.isEmpty()) {
				responseMap.put("exists", true);
				Set<String> productType = new HashSet<String>();
				for (Product p : products) {
					productType.add(p.getProductType());
				}
				responseMap.put("brandName", productType);

			}
		} catch (Exception e) {
			e.printStackTrace();
			responseMap.remove("exists");
			responseMap.put("brandName", "Some error occured.");
			return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
	}

	@RequestMapping(value = "/getModelNumber/{brandName}/{productType}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> getModelNumber(@PathVariable(value = "brandName") String brandName,
			@PathVariable(value = "productType") String productType) {

		Map<String, Object> responseMap = new HashMap<String, Object>(2);
		responseMap.put("exists", false);
		responseMap.put("modelNumber", null);

		try {
			List<Product> products = dashboardService.findModelNumber(brandName, productType);
			if (!products.isEmpty()) {
				responseMap.put("exists", true);
				List<String> modelNumber = new ArrayList<String>();
				for (Product p : products) {
					modelNumber.add(p.getModelNumber());
				}
				responseMap.put("modelNumber", modelNumber);

			}
		} catch (Exception e) {
			e.printStackTrace();
			responseMap.remove("exists");
			responseMap.put("modelNumber", "Some error occured.");
			return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
	}

	@RequestMapping(value = "/getsize/{brandName}/{productType}/{modelNumber}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> getSize(@PathVariable(value = "brandName") String brandName,
			@PathVariable(value = "productType") String productType,
			@PathVariable(value = "modelNumber") String modelNumber) {

		Map<String, Object> responseMap = new HashMap<String, Object>(2);
		responseMap.put("exists", false);
		responseMap.put("size", null);

		try {
			List<Product> products = dashboardService.findSize(brandName, productType, modelNumber);
			if (!products.isEmpty()) {
				responseMap.put("exists", true);
				List<String> size = new ArrayList<String>();
				for (Product p : products) {
					size.add(p.getSize());
				}
				responseMap.put("size", size);

			}
		} catch (Exception e) {
			e.printStackTrace();
			responseMap.remove("exists");
			responseMap.put("size", "Some error occured.");
			return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
	}

	@RequestMapping(value = "/getStar/{brandName}/{productType}/{modelNumber}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> getStar(@PathVariable(value = "brandName") String brandName,
			@PathVariable(value = "productType") String productType,
			@PathVariable(value = "modelNumber") String modelNumber) {

		Map<String, Object> responseMap = new HashMap<String, Object>(2);
		responseMap.put("exists", false);
		responseMap.put("star", null);

		try {
			List<Product> products = dashboardService.findStar(brandName, productType, modelNumber);
			if (!products.isEmpty()) {
				responseMap.put("exists", true);
				List<String> star = new ArrayList<String>();
				for (Product p : products) {
					star.add(p.getStar());
				}
				responseMap.put("star", star);

			}
		} catch (Exception e) {
			e.printStackTrace();
			responseMap.remove("exists");
			responseMap.put("star", "Some error occured.");
			return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
	}

	/*
	 * @RequestMapping(value=
	 * "/getproductInfoBySerialNo/{brandName}/{modelNumber}/{serialNo}/",method=
	 * RequestMethod.GET)
	 * 
	 * @ResponseBody public ResponseEntity<Map<String, Object>>
	 * getproductInfoByModelAndSerialNo(@PathVariable(value="brandName") String
	 * brandName,
	 * 
	 * @PathVariable(value="serialNo") String
	 * serialNo,@PathVariable(value="modelNumber") String modelNumber){
	 * 
	 * Map<String, Object> responseMap = new HashMap<String, Object>(2);
	 * responseMap.put("exists", false);
	 * 
	 * 
	 * try{
	 * 
	 * Product product=
	 * dashboardService.findproductInfoByModelAndSerialNo(brandName,modelNumber,
	 * serialNo); if(product!=null){ responseMap.put("exists", true);
	 * responseMap.put("productId", product.getId());
	 * responseMap.put("discountRate",product.getDiscountRate());
	 * responseMap.put("unitPrice", product.getUnitPrice());
	 * responseMap.put("availableQuantity", product.getQuantity());
	 * responseMap.put("purchaseInvoiceid", "");
	 * responseMap.put("purchaseInvoiceDate","");
	 * 
	 * ProductPurchaseInvoice
	 * ppi=productPurchaseInvoiceService.getProductPurchaseInvoiceBySerialNo(
	 * serialNo); if(ppi!=null){ PurchaseInvoice
	 * purchaseInvoice=purchaseInvoiceServices.getPurchaseInvoiceById(ppi.
	 * getPurchaseInvoice().getInvoiceNo());
	 * 
	 * if(purchaseInvoice!=null){ responseMap.put("purchaseInvoiceid",
	 * purchaseInvoice.getCmpyPurchaseInvoiceNo());
	 * responseMap.put("purchaseInvoiceDate",purchaseInvoice.getDate()); } } } }
	 * catch(Exception e){ e.printStackTrace(); responseMap.remove("exists");
	 * responseMap.put("discountRate","Some Error Occured");
	 * responseMap.put("unitPrice", "Some Error Occured");
	 * responseMap.put("availableQuantity","Some Error Occured");
	 * responseMap.put("purchaseInvoiceid", "Some Error Occured");
	 * responseMap.put("purchaseInvoiceDate","Some Error Occured"); return new
	 * ResponseEntity<Map<String,Object>>(responseMap, HttpStatus.BAD_REQUEST);
	 * } return new ResponseEntity<Map<String,Object>>(responseMap,
	 * HttpStatus.OK); }
	 */

	@RequestMapping(value = "/getpurchaseInvoiceNoBySerialNo/{serialNo}/", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> getproductInfoByModelAndSerialNo(
			@PathVariable(value = "serialNo") String serialNo) {

		Map<String, Object> responseMap = new HashMap<String, Object>(2);
		responseMap.put("exists", false);
		try {
			responseMap.put("purchaseInvoiceid", "");
			responseMap.put("purchaseInvoiceDate", "");

			ProductPurchaseInvoice ppi = productPurchaseInvoiceService.getProductPurchaseInvoiceBySerialNo(serialNo);
			if (ppi != null) {
				PurchaseInvoice purchaseInvoice = purchaseInvoiceServices
						.getPurchaseInvoiceById(ppi.getPurchaseInvoice().getInvoiceNo());

				if (purchaseInvoice != null) {
					responseMap.put("exists", true);
					responseMap.put("purchaseInvoiceid", purchaseInvoice.getCmpyPurchaseInvoiceNo());
					responseMap.put("purchaseInvoiceDate", purchaseInvoice.getDate());
					responseMap.put("purchaseInvoiceUnitPrice", ppi.getUnitPrice());
					responseMap.put("purchaseInvoiceDiscountedAmount", ppi.getDiscountedAmount());
				}
			}
		}

		catch (Exception e) {
			e.printStackTrace();
			responseMap.remove("exists");
			responseMap.put("purchaseInvoiceid", "Some Error Occured");
			responseMap.put("purchaseInvoiceDate", "Some Error Occured");
			responseMap.put("purchaseInvoiceUnitPrice", "Some Error Occured");
			responseMap.put("purchaseInvoiceDiscountedAmount", "Some Error Occured");
			return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
	}

	@RequestMapping(value = "/getindoorPurchaseInvoiceNoBySerialNo/{indoorserialNo}/", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> getindoorPurchaseInvoiceNoBySerialNo(
			@PathVariable(value = "indoorserialNo") String indoorserialNo) {

		Map<String, Object> responseMap = new HashMap<String, Object>(2);
		responseMap.put("exists", false);
		try {
			responseMap.put("indoorPurchaseInvoiceid", "");
			responseMap.put("indoorPurchaseInvoiceDate", "");

			ProductPurchaseInvoice ppi = productPurchaseInvoiceService
					.getProductPurchaseInvoiceByIndoorSerialNo(indoorserialNo);
			if (ppi != null) {
				PurchaseInvoice purchaseInvoice = purchaseInvoiceServices
						.getPurchaseInvoiceById(ppi.getPurchaseInvoice().getInvoiceNo());

				if (purchaseInvoice != null) {
					responseMap.put("exists", true);
					responseMap.put("indoorPurchaseInvoiceid", purchaseInvoice.getCmpyPurchaseInvoiceNo());
					responseMap.put("indoorPurchaseInvoiceDate", purchaseInvoice.getDate());
				}
			}
		}

		catch (Exception e) {
			e.printStackTrace();
			responseMap.remove("exists");
			responseMap.put("indoorPurchaseInvoiceid", "Some Error Occured");
			responseMap.put("indoorPurchaseInvoiceDate", "Some Error Occured");
			return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
	}

	@RequestMapping(value = "/getproduct-info/{brandName}/{productType}/{modelNumber}/{size}/{star}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> getproductinfo(@PathVariable(value = "brandName") String brandName,
			@PathVariable(value = "productType") String productType,
			@PathVariable(value = "modelNumber") String modelNumber, @PathVariable(value = "size") String size,
			@PathVariable(value = "star") String star) {

		Map<String, Object> responseMap = new HashMap<String, Object>(2);
		responseMap.put("exists", false);

		Product product = null;
		try {
			if (star.equalsIgnoreCase("NA") || star.trim().length() == 0) {
				product = dashboardService.findproductInfoExcludeStarRating(brandName, productType, modelNumber, size);
			} else {
				product = dashboardService.findproductInfo(brandName, productType, modelNumber, size, star);
			}

			if (product != null) {
				responseMap.put("exists", true);
				responseMap.put("productId", product.getId());
				responseMap.put("availableQuantity", product.getQuantity());
				responseMap.put("hsnCode", product.getHsnCode());
				responseMap.put("cgst", product.getCgstValue());
				responseMap.put("igst", product.getIgstValue());
				responseMap.put("sgst", product.getSgstValue());

				Set<String> serialNo = new HashSet<String>();
				List<ProductPurchaseInvoice> ppi = productPurchaseInvoiceService.getProductByProductId(product.getId());
				if (!ppi.isEmpty()) {
					for (ProductPurchaseInvoice productPurchaseInvoice : ppi) {
						serialNo.add(productPurchaseInvoice.getSerialNo());
					}
				}
				responseMap.put("productSerialNo", serialNo);
				Set<String> indoorSerialNo = null;
				String protype = productType.replace(" ", "").trim();
				System.out.println("productType" + protype);

				if (protype.equalsIgnoreCase("splitac")) {
					indoorSerialNo = new HashSet<String>();
					List<ProductPurchaseInvoice> ppIndoorInvoice = productPurchaseInvoiceService
							.getIndoorSerialNoByProductId(product.getId());
					if (!ppIndoorInvoice.isEmpty()) {
						for (ProductPurchaseInvoice productPurchaseInvoice : ppIndoorInvoice) {
							indoorSerialNo.add(productPurchaseInvoice.getIndoorSerialNo());
						}
					}
				}
				responseMap.put("productIndoorSerialNo", indoorSerialNo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			responseMap.remove("exists");
			responseMap.put("unitPrice", "Some Error Occured");
			responseMap.put("availableQuantity", "Some Error Occured");
			responseMap.put("productSerialNo", "Some Error Occured");
			return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
	}
	/*
	 * @RequestMapping(value="/salesinvoice",method=RequestMethod.POST) public
	 * String saveSalesInvoiceDetails(Model model,@ModelAttribute SaleInvoice
	 * saleInvoice,@ModelAttribute Customer customer, HttpSession
	 * httpSession,@RequestParam(value="productId") long productId){ try{
	 * 
	 * 
	 * customerService.addOrUpdateCustomer(customer);
	 * 
	 * saleInvoice.setCustomer(customer); Product
	 * product=productServices.getProductById(productId); if(product!=null){
	 * if(product.getQuantity()<saleInvoice.getQuantity()){ setError(model,
	 * "Sorry we have only "+product.getQuantity()+" units of inventory");
	 * return "redirect:/dashboard/salesinvoice"; }
	 * product.setQuantity(product.getQuantity()-saleInvoice.getQuantity());
	 * productServices.addOrUpdateProduct(product);
	 * //saleInvoice.setProduct(product); }
	 * 
	 * SessionUser sessionUser=SessionUser.getHttpSessionUser(httpSession);
	 * 
	 * if(sessionUser.getRole().equalsIgnoreCase("Checker")){ Checker
	 * checker=checkerServices.getCheckerById(sessionUser.getId());
	 * saleInvoice.setChecker(checker);
	 * 
	 * }
	 * 
	 * if(sessionUser.getRole().equalsIgnoreCase("Admin")){ Admin
	 * admin=adminServices.getAdminById(sessionUser.getId());
	 * saleInvoice.setAdmin(admin); }
	 * 
	 * if(sessionUser.getRole().equalsIgnoreCase("Sales Person")){ SalesPerson
	 * salesPerson=salesPersonServices.getSalesPersonById(sessionUser.getId());
	 * saleInvoice.setSalesPerson(salesPerson); }
	 * saleInvoiceServices.addOrUpdateSaleInvoice(saleInvoice); TaxInvoice ti =
	 * new TaxInvoice(); ti.setSaleInvoice(saleInvoice);
	 * taxInvoiceServices.addOrUpdateTaxInvoice(ti);
	 * 
	 * CustomerReport cr = new CustomerReport(); cr.setCustomer(customer);
	 * cr.setSaleInvoice(saleInvoice);
	 * customerReportServices.addOrUpdateCustomerReport(cr);
	 * model.addAttribute("Save_Success", true);
	 * 
	 * System.out.println("success"); } catch(Exception e){ e.printStackTrace();
	 * setUnknownError(model); } return "redirect:/dashboard/salesinvoice"; }
	 */

	@RequestMapping(value = "/checkUniqueEmail/{role}/{email}/", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> checkUniqueExistance(@PathVariable(value = "role") String role,
			@PathVariable(value = "email") String email) {

		Map<String, Object> responseMap = new HashMap<String, Object>(2);
		responseMap.put("exists", false);
		responseMap.put("message", email + " does not exists.");

		try {
			boolean emailExist = true;
			if (role.equalsIgnoreCase("Checker")) {
				emailExist = checkerServices.checkUniqueEmail(email);
			}
			if (role.equalsIgnoreCase("Maker")) {
				emailExist = makerServices.checkUniqueEmail(email);
			}
			if (role.equalsIgnoreCase("Sales Person")) {
				emailExist = salesPersonServices.checkUniqueEmail(email);
			}

			if (!emailExist) {
				responseMap.put("exists", true);
				responseMap.put("message", email + " already exists.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			responseMap.remove("exists");
			responseMap.put("message", "Some error occured.");
			return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
	}

	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public String showProductList(Model model) {
		List<Product> products = productServices.getAllProducts();
		model.addAttribute("products", products);
		return "product-list";
	}

	@RequestMapping(value = "/addproduct", method = RequestMethod.GET)
	public String addProduct() {
		return "add-product";
	}

	@RequestMapping(value = "/addproduct", method = RequestMethod.POST)
	public String saveProduct(Model model, @ModelAttribute Product product, HttpSession httpSession) {

		if (product.getBrand() == null || product.getBrand().trim().length() == 0) {
			setError(model, "Brand name cannot be empty");
			return "add-product";
		}

		if (product.getProductType() == null || product.getProductType().trim().length() == 0) {
			setError(model, "Product Type name cannot be empty");
			return "add-product";
		}

		if (product.getSize() == null || product.getSize().trim().length() == 0) {
			setError(model, "Size cannot be empty");
			return "add-product";
		}
		if (product.getHsnCode() == null) {
			setError(model, "HSN code cannot be empty");
			return "add-product";
		}

		if (product.getModelNumber() == null || product.getModelNumber().trim().length() == 0) {
			setError(model, "Model number cannot be empty");
			return "add-product";
		}

		Product pro = null;
		if (product.getStar() != null || product.getStar().trim().length() > 0)
			pro = dashboardService.findproductInfo(product.getBrand(), product.getProductType(),
					product.getModelNumber(), product.getSize(), product.getStar());

		else
			pro = dashboardService.findproductInfoExcludeStarRating(product.getBrand(), product.getProductType(),
					product.getModelNumber(), product.getSize());

		if (pro != null) {
			setError(model, "Product with this configuration already exist");
			return "add-product";
		}

		SessionUser currentUser = SessionUser.getHttpSessionUser(httpSession);
		product.setCreated(new Date());
		product.setUpdated(new Date());
		product.setCreatedby(currentUser.getId());
		product.setUpdatedby(currentUser.getId());
		productServices.addOrUpdateProduct(product);
		StockReport sr = new StockReport();
		sr.setProduct(product);
		sr.setUnits(product.getQuantity());
		stockReportServices.createOrUpdateStockReport(sr);
		model.addAttribute("Success", true);
		return "redirect:/dashboard/products";
	}

	@RequestMapping(value = "/update-product/{productId}", method = RequestMethod.GET)
	public String updateProduct(Model model, @PathVariable(value = "productId") long productId) {
		Product product = productServices.getProductById(productId);

		model.addAttribute("product", product);
		return "update-product";
	}

	@RequestMapping(value = "/update-product/{productId}", method = RequestMethod.POST)
	public String SaveUpdatedProduct(Model model, HttpSession httpSession, @ModelAttribute Product product,
			@PathVariable(value = "productId") long productId) {

		Product productFromDb = productServices.getProductById(productId);

		if (productFromDb == null) {
			setError(model, "Sorry no product found for updation");
			return "update-product";
		}

		if (product.getBrand() == null || product.getBrand().trim().length() == 0) {
			setError(model, "Brand name cannot be empty");
			return "update-product";
		}

		if (product.getProductType() == null || product.getProductType().trim().length() == 0) {
			setError(model, "Product Type name cannot be empty");
			return "update-product";
		}

		if (product.getSize() == null || product.getSize().trim().length() == 0) {
			setError(model, "Size cannot be empty");
			return "update-product";
		}

		if (product.getHsnCode() == null) {
			setError(model, "HSN code cannot be empty");
			return "add-product";
		}

		if (product.getModelNumber() == null || product.getModelNumber().trim().length() == 0) {
			setError(model, "Model number cannot be empty");
			return "update-product";
		}
		
		
		SessionUser currentUser = SessionUser.getHttpSessionUser(httpSession);

		productFromDb.setBrand(product.getBrand());
		productFromDb.setProductType(getSafeString(product.getProductType()));
		productFromDb.setModelNumber(getSafeString(product.getModelNumber()));
		productFromDb.setSize(getSafeString(product.getSize()));
		productFromDb.setQuantity(getSafeLong(product.getQuantity()));
		productFromDb.setStar(getSafeString(product.getStar()));
		productFromDb.setReorderPoint(getSafeLong(product.getReorderPoint()));
		productFromDb.setUpdated(new Date());
		productFromDb.setCgstValue(getSafeDouble(product.getCgstValue()));
		productFromDb.setSgstValue(getSafeDouble(product.getSgstValue()));
		productFromDb.setIgstValue(getSafeDouble(product.getIgstValue()));
		productFromDb.setHsnCode(getSafeString(product.getHsnCode()));
		productFromDb.setUpdatedby(currentUser.getId());
		productServices.addOrUpdateProduct(productFromDb);
		StockReport stockReport = stockReportServices.getStockReportByProductId(productFromDb.getId());
		if (stockReport != null) {
			stockReport.setUnits(productFromDb.getQuantity());
			stockReportServices.createOrUpdateStockReport(stockReport);
		}

		model.addAttribute("Update_Msg", true);
		return "redirect:/dashboard/products";
	}

	@RequestMapping(value = "/suppliers", method = RequestMethod.GET)
	public String showSupplierList(Model model) {
		List<Supplier> suppliers = supplierServices.getAllSupplier();
		model.addAttribute("suppliers", suppliers);
		return "supplier-list";
	}

	@RequestMapping(value = "/addsupplier", method = RequestMethod.GET)
	public String addSupplier() {
		return "add-supplier";
	}

	@RequestMapping(value = "/addsupplier", method = RequestMethod.POST)
	public String saveProduct(Model model, @ModelAttribute Supplier supplier, HttpSession httpSession) {

		if (supplier.getName().trim().length() == 0 || supplier.getAddress().length() == 0) {
			setError(model, "name or Address of supplier cannot be empty");
			return "add-supplier";
		}

		Supplier supplierContactNo = supplierServices.getSupplierByMobileNumber(supplier.getContactNo());
		if (supplierContactNo != null) {
			setError(model, " supplier contact number " + supplier.getContactNo() + " already exist");
			return "add-supplier";
		}

		SessionUser currentUser = SessionUser.getHttpSessionUser(httpSession);
		if (currentUser.getRole().equalsIgnoreCase("Admin")) {
			Admin admin = adminServices.getAdminById(currentUser.getId());
			supplier.setAdmin(admin);
		}

		if (currentUser.getRole().equalsIgnoreCase("Checker")) {
			Checker checker = checkerServices.getCheckerById(currentUser.getId());
			supplier.setChecker(checker);
		}
		supplierServices.addOrUpdateSupplier(supplier);
		model.addAttribute("Success", true);
		return "redirect:/dashboard/suppliers";
	}

	@RequestMapping(value = "/update-supplier/{supplierId}", method = RequestMethod.GET)
	public String updateSupplier(Model model, @PathVariable(value = "supplierId") long supplierId) {
		Supplier supplier = supplierServices.getSupplierById(supplierId);
		model.addAttribute("supplier", supplier);
		return "update-supplier";
	}

	@RequestMapping(value = "/update-supplier/{supplierId}", method = RequestMethod.POST)
	public String SaveUpdatedSupplier(Model model, HttpSession httpSession, @ModelAttribute Supplier supplier,
			@PathVariable(value = "supplierId") long supplierId) {

		Supplier supplierFromDb = supplierServices.getSupplierById(supplierId);

		if (supplierFromDb == null) {
			setError(model, "Sorry no supplier found for updation");
			return "update-supplier";
		}

		if (supplier.getName().trim().length() == 0 || supplier.getAddress().length() == 0) {
			model.addAttribute("supplier", supplierFromDb);
			setError(model, "name or Address of supplier cannot be empty");
			return "update-supplier";
		}

		if (supplierFromDb.getContactNo() != supplier.getContactNo()) {
			Supplier supplierContactNo = supplierServices.getSupplierByMobileNumber(supplier.getContactNo());
			if (supplierContactNo != null) {
				model.addAttribute("supplier", supplierFromDb);
				setError(model, " supplier contact number " + supplier.getContactNo() + " already exist");
				return "update-supplier";
			}

		}

		supplierFromDb.setAddress(supplier.getAddress());
		supplierFromDb.setName(supplier.getName());
		supplierFromDb.setContactNo(supplier.getContactNo());
		supplierFromDb.setEmail(supplier.getEmail());
		supplierFromDb.setTinNumber(supplier.getTinNumber());
		supplierFromDb.setGstNumber(supplier.getGstNumber());

		supplierServices.addOrUpdateSupplier(supplierFromDb);
		model.addAttribute("Update_Msg", true);
		return "redirect:/dashboard/suppliers";
	}

	@RequestMapping(value = "/customers", method = RequestMethod.GET)
	public String showCustomerList(Model model) {
		List<Customer> customers = customerService.getAllCustomers();
		model.addAttribute("customers", customers);
		return "customer-list";
	}

	@RequestMapping(value = "/addcustomer", method = RequestMethod.GET)
	public String addCustomer() {
		return "add-customer";
	}

	@RequestMapping(value = "/addcustomer", method = RequestMethod.POST)
	public String saveCustomer(Model model, @ModelAttribute Customer customer, HttpSession httpSession) {

		if (customer.getName().trim().length() == 0 || customer.getAddress().length() == 0) {
			setError(model, "name or Address of customer cannot be empty");
			return "add-customer";
		}

		Customer cust = customerService.getCustomerByEmail(customer.getEmail());
		if (cust != null) {
			setError(model, "Customer email " + customer.getEmail() + "already exist");
			return "update-customer";
		}

		Customer custMob = customerService.getCustomerByMobile(customer.getContactNo());
		if (custMob != null) {
			setError(model, "Customer Mobile " + customer.getContactNo() + "already exist");
			return "update-customer";
		}

		SessionUser currentUser = SessionUser.getHttpSessionUser(httpSession);
		if (currentUser.getRole().equalsIgnoreCase("Admin")) {
			Admin admin = adminServices.getAdminById(currentUser.getId());
			customer.setAdmin(admin);
		}

		if (currentUser.getRole().equalsIgnoreCase("Checker")) {
			Checker checker = checkerServices.getCheckerById(currentUser.getId());
			customer.setChecker(checker);
		}

		if (currentUser.getRole().equalsIgnoreCase("Maker")) {
			Maker maker = makerServices.getMakerById(currentUser.getId());
			customer.setMaker(maker);
		}
		if (currentUser.getRole().equalsIgnoreCase("Sales Person")) {
			SalesPerson salesPerson = salesPersonServices.getSalesPersonById(currentUser.getId());
			customer.setSalesPerson(salesPerson);
		}
		customerService.addOrUpdateCustomer(customer);
		model.addAttribute("Success", true);
		return "redirect:/dashboard/customers";
	}

	@RequestMapping(value = "/update-customer/{customerId}", method = RequestMethod.GET)
	public String updateCustomer(Model model, @PathVariable(value = "customerId") long customerId) {
		Customer customer = customerService.getCustomerById(customerId);
		model.addAttribute("customer", customer);
		return "update-customer";
	}

	@RequestMapping(value = "/update-customer/{customerId}", method = RequestMethod.POST)
	public String SaveUpdatedCustomer(Model model, HttpSession httpSession, @ModelAttribute Customer customer,
			@PathVariable(value = "customerId") long customerId) {

		Customer customerFromDb = customerService.getCustomerById(customerId);

		if (customerFromDb == null) {
			setError(model, "Sorry no customer found for updation");
			return "update-customer";
		}

		if (customer.getName().trim().length() == 0 || customer.getAddress().length() == 0) {
			setError(model, "name or Address of customer cannot be empty");
			return "update-customer";
		}

		if (customerFromDb.getEmail() != null && !customerFromDb.getEmail().equalsIgnoreCase(customer.getEmail())) {
			Customer cust = customerService.getCustomerByEmail(customer.getEmail());
			if (cust != null) {
				setError(model, "Customer email " + customer.getEmail() + "already exist");
				return "update-customer";
			}
		}

		if (customerFromDb.getContactNo() != customer.getContactNo()) {
			Customer custMob = customerService.getCustomerByMobile(customer.getContactNo());
			if (custMob != null) {
				setError(model, "Customer Mobile " + customer.getContactNo() + "already exist");
				return "update-customer";
			}
		}

		customerFromDb.setAddress(customer.getAddress());
		customerFromDb.setName(customer.getName());
		customerFromDb.setContactNo(customer.getContactNo());
		customerFromDb.setEmail(customer.getEmail());

		customerService.addOrUpdateCustomer(customerFromDb);
		model.addAttribute("Update_Msg", true);
		return "redirect:/dashboard/customers";
	}

	/*
	 * @RequestMapping(value="/justcheck",method=RequestMethod.GET) public void
	 * checkSaveSaleinvoice(){
	 * 
	 * Admin admin=adminServices.getAdminById(6);
	 * 
	 * SaleInvoice saleinvoice=new SaleInvoice();
	 * saleinvoice.setCmpySaleInvoiceNo("Adf4563");
	 * saleinvoice.setAmountPaid(20550.5); saleinvoice.setBalanceLeft(100.5);
	 * saleinvoice.setAdmin(admin);
	 * 
	 * ProductSaleInvoice sip=new ProductSaleInvoice(); Product
	 * p1=productServices.getProductById(1); Product
	 * p2=productServices.getProductById(2); sip.setProduct(p1);
	 * sip.setSaleinvoice(saleinvoice); sip.setComment("ookk");
	 * 
	 * dashboardService.saveSID(sip); System.out.println("success");
	 * 
	 * }
	 */

	@RequestMapping(value = "/save-saleinvoice", method = RequestMethod.POST)
	public ResponseEntity<String> saveSaleinvoice(@RequestBody SaleInvoiceProductDTO sipd, HttpServletRequest request,
			HttpServletResponse response, HttpSession httpSession) throws IOException {

		try {
			List<ProductDTO> productss = sipd.getProductsArray();

			Map<String, Integer> mapModelNumberCount = new HashMap<String, Integer>();
			List<String> modelNumber = new ArrayList<String>();

			for (ProductDTO product : productss) {
				Product pro = productServices.getProductById(product.getId());
				modelNumber.add(pro.getModelNumber());
			}
			for (String str : modelNumber) {
				Integer count = mapModelNumberCount.get(str);
				mapModelNumberCount.put(str, (count == null) ? 1 : count + 1);
			}

			for (Map.Entry<String, Integer> map : mapModelNumberCount.entrySet()) {
				Product p = productServices.getProductByModelNumber(map.getKey());
				if (map.getValue() > p.getQuantity()) {
					return new ResponseEntity<String>("Available Inventory of Product with model Number " + map.getKey()
							+ " is " + p.getQuantity() + " which is less than your resquested quantity "
							+ map.getValue(), HttpStatus.BAD_REQUEST);
				}
			}

			SaleInvoice saleInv = saleInvoiceServices.getSaleInvoiceByinvoiceNumber(sipd.getInvoiceNumber());
			if (saleInv != null) {
				return new ResponseEntity<String>(sipd.getInvoiceNumber() + "Sale Invoice number already exist",
						HttpStatus.BAD_REQUEST);
			}

			if (sipd.getFirmId() == null) {
				return new ResponseEntity<String>("No Firm name found", HttpStatus.BAD_REQUEST);
			}
			Firms firm = dashboardService.getFirmById(sipd.getFirmId());
			if (firm == null) {
				return new ResponseEntity<String>("No Firm name found", HttpStatus.BAD_REQUEST);
			}
			SaleInvoice saleinvoice = new SaleInvoice();

			saleinvoice.setCmpySaleInvoiceNo(sipd.getInvoiceNumber());
			saleinvoice.setTotalDiscountedAmount(sipd.getTotalDiscount());
			saleinvoice.setFinalAmount(sipd.getFinalAmount());
			if (sipd.getDate() == null)
				saleinvoice.setInvoiceDate(new Date());
			else
				saleinvoice.setInvoiceDate(sipd.getDate());
			saleinvoice.setAmountPaid(sipd.getAmountPaid());
			saleinvoice.setPaymentMode(sipd.getPaymentMode());
			if(sipd.getPaymentMode().equalsIgnoreCase("Cheque")){
				saleinvoice.setBankName(sipd.getBankName());
				saleinvoice.setChequeNumber(sipd.getChequeNumber());
				saleinvoice.setChequeDate(sipd.getChequeDate());
			}
			
			saleinvoice.setBalanceLeft(sipd.getBalanceLeft());
			saleinvoice.setTaxAmount(sipd.getTaxAmount());
			saleinvoice.setTaxPercent(sipd.getTaxPercent());

			Customer customer = null;
			if (sipd.getCustomerContact() != null && sipd.getCustomerContact().trim().length() != 0
					&& sipd.getCustomerContact().trim().length() == 10) {
				customer = customerService.getCustomerByMobile(Long.parseLong(sipd.getCustomerContact()));
			}

			if (customer == null) {
				customer = new Customer();
				customer.setName(sipd.getCustomerName());
				customer.setAddress(sipd.getCustomerAddress());
				customer.setContactNo(Long.parseLong(sipd.getCustomerContact()));

			}

			SessionUser sessionUser = SessionUser.getHttpSessionUser(httpSession);

			if (sessionUser.getRole().equalsIgnoreCase("Checker")) {
				Checker checker = checkerServices.getCheckerById(sessionUser.getId());
				saleinvoice.setChecker(checker);
				customer.setChecker(checker);

			}

			if (sessionUser.getRole().equalsIgnoreCase("Admin")) {
				Admin admin = adminServices.getAdminById(sessionUser.getId());
				saleinvoice.setAdmin(admin);
				customer.setAdmin(admin);
			}

			if (sessionUser.getRole().equalsIgnoreCase("Sales Person")) {
				SalesPerson salesPerson = salesPersonServices.getSalesPersonById(sessionUser.getId());
				saleinvoice.setSalesPerson(salesPerson);
				customer.setSalesPerson(salesPerson);
			}

			if (sessionUser.getRole().equalsIgnoreCase("Maker")) {
				Maker maker = makerServices.getMakerById(sessionUser.getId());
				customer.setMaker(maker);
			}

			customerService.addOrUpdateCustomer(customer);
			saleinvoice.setCustomer(customer);

			saleInvoiceServices.addOrUpdateSaleInvoice(saleinvoice);

			List<ProductDTO> products = sipd.getProductsArray();
			for (ProductDTO product : products) {

				ProductSaleInvoice psi = new ProductSaleInvoice();
				Product pro = productServices.getProductById(product.getId());
				if (pro.getQuantity() <= 0) {
					continue;
				}
				pro.setQuantity((pro.getQuantity() - product.getQuantity()));
				productServices.addOrUpdateProduct(pro);

				StockReport stockReport = stockReportServices.getStockReportByProductId(pro.getId());
				stockReport.setUnits(pro.getQuantity());
				stockReportServices.createOrUpdateStockReport(stockReport);

				psi.setProduct(pro);
				psi.setSaleinvoice(saleinvoice);
				psi.setComment(sipd.getComments());
				psi.setQuantity(product.getQuantity());
				psi.setUnitPrice(product.getUnitPrice());
				psi.setCmpyPurchaseInvoiceNo(product.getPurchaseInvoiceNo());
				psi.setSerialNumber(product.getSerialNumber());
				psi.setTradeDiscount(product.getTradeDiscount());
				psi.setBillAmount(product.getBillAmount());
				psi.setFirm(firm);
				

				psi.setIndoorSerialNo("");
				if (product.getIndoorModelNumber() != null || product.getIndoorModelNumber().trim().length() > 0) {
					psi.setIndoorSerialNo(product.getIndoorModelNumber());
				}

				psi.setCmpyPurchaseinvoiceNoForIndoor("");
				if (product.getIndoorPurchaseInvoiceNo() != null
						|| product.getIndoorPurchaseInvoiceNo().trim().length() > 0) {
					psi.setCmpyPurchaseinvoiceNoForIndoor(product.getIndoorPurchaseInvoiceNo());
				}

				dashboardService.saveSID(psi);
				ProductPurchaseInvoice ppi = productPurchaseInvoiceService
						.getProductPurchaseInvoiceBySerialNo(product.getSerialNumber());
				if (ppi != null) {
					ppi.setSale(1);
					productPurchaseInvoiceService.addOrUpdateProductPurchaseinvoice(ppi);
				}

				if (product.getIndoorModelNumber() != null || product.getIndoorModelNumber().trim().length() > 0) {
					ProductPurchaseInvoice ppiForIndoor = productPurchaseInvoiceService
							.getProductPurchaseInvoiceByIndoorSerialNo(product.getIndoorModelNumber());
					if (ppiForIndoor != null) {
						ppiForIndoor.setIndoorsale(1);
						productPurchaseInvoiceService.addOrUpdateProductPurchaseinvoice(ppiForIndoor);
					}
				}
			}

			TaxInvoice ti = new TaxInvoice();
			ti.setSaleInvoice(saleinvoice);
			taxInvoiceServices.addOrUpdateTaxInvoice(ti);

			CustomerReport cr = new CustomerReport();
			cr.setCustomer(customer);
			cr.setSaleInvoice(saleinvoice);
			customerReportServices.addOrUpdateCustomerReport(cr);

			return new ResponseEntity<String>(String.valueOf(saleinvoice.getSaleInvoiceNo()), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("some error", HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}

	@RequestMapping(value = "/finalSaleInvoice", method = RequestMethod.GET)
	public String finalSaleInvoice(Model model, @RequestParam(value = "si") String si) {

		if (si != null || si.trim().length() != 0) {
			SaleInvoice saleInvoice = saleInvoiceServices.getSaleInvoiceById(Long.parseLong(si));
			List<ProductSaleInvoice> productSaleInvoices = saleInvoice.getProductSaleInvoices();
			List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
			if (!productSaleInvoices.isEmpty()) {

				// model.addAttribute("listProductSaleInvoices",
				// productSaleInvoices);

				for (ProductSaleInvoice productSaleInvoice : productSaleInvoices) {
					Product product = productServices.getProductById(productSaleInvoice.getProduct().getId());
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("brand", product.getBrand());
					map.put("productType", product.getProductType());
					map.put("model", product.getModelNumber());
					map.put("size", product.getSize());
					map.put("serialNo", productSaleInvoice.getSerialNumber());
					map.put("purchaseInvoiceNo", productSaleInvoice.getCmpyPurchaseInvoiceNo());
					map.put("quantity", productSaleInvoice.getQuantity());
					map.put("unitPrice", productSaleInvoice.getUnitPrice());
					map.put("indoorSerialNo", productSaleInvoice.getIndoorSerialNo());
					listmap.add(map);
				}
				Firms firm = productSaleInvoices.iterator().next().getFirm();
				model.addAttribute("firm", firm);
				model.addAttribute("productList", listmap);
				model.addAttribute("saleInvoice", saleInvoice);
				// SaleInvoice
				// previousSaleInvoice=saleInvoiceServices.getLastSaleInvoice(saleInvoice.getCustomer().getId());
				return "final-saleInvoice";
			}
		}
		model.addAttribute("error", "something went wrong! Try again");
		return "sales-invoice";
	}

	@RequestMapping(value = "/finalPurchaseInvoice", method = RequestMethod.GET)
	public String finalPurchaseInvoice(Model model, @RequestParam(value = "pi") String pi) {

		if (pi != null || pi.trim().length() != 0) {
			PurchaseInvoice purchaseInvoice = purchaseInvoiceServices.getPurchaseInvoiceById(Long.parseLong(pi));
			Set<ProductPurchaseInvoice> productPurchaseInvoices = purchaseInvoice.getProductPurchaseInvoice();
			List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
			if (!productPurchaseInvoices.isEmpty()) {

				for (ProductPurchaseInvoice productPurchsaseInvoice : productPurchaseInvoices) {
					Product product = productServices.getProductById(productPurchsaseInvoice.getProduct().getId());
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("brand", product.getBrand());
					map.put("productType", product.getProductType());
					map.put("model", product.getModelNumber());
					map.put("cgst", product.getCgstValue());
					map.put("sgst", product.getSgstValue());
					map.put("igst", product.getIgstValue());
					map.put("size", product.getSize());
					map.put("serialNo", productPurchsaseInvoice.getSerialNo());
					map.put("quantity", productPurchsaseInvoice.getQuantity());
					map.put("unitPrice", productPurchsaseInvoice.getUnitPrice());
					map.put("rebateDiscount", productPurchsaseInvoice.getRebateDiscount());
					map.put("tradeDiscount", productPurchsaseInvoice.getTradeDiscount());
					map.put("billAmount", productPurchsaseInvoice.getBillAmount());
					map.put("singleUnitTax", productPurchsaseInvoice.getSingleUnitTax());
					map.put("hsnCode", product.getHsnCode());
					listmap.add(map);
				}
				Firms firm = productPurchaseInvoices.iterator().next().getFirm();
				model.addAttribute("firm", firm);
				model.addAttribute("productList", listmap);
				model.addAttribute("purchaseInvoice", purchaseInvoice);
				return "final-purchaseInvoice";
			}
		}
		model.addAttribute("error", "something went wrong! Try again");
		return "purchaseInvoice";
	}

	@RequestMapping(value = "/purchaseInvoice", method = RequestMethod.GET)
	public String rendorPurchaseInvoice(Model model) throws Exception {
		List<Supplier> suppliers = supplierServices.getAllSupplier();
		Map<String, String> map = new HashMap<String, String>();
		for (Supplier supplier : suppliers) {
			map.put(supplier.getName(), supplier.toJSON());
		}
		model.addAttribute("suppliers", map);

		List<Firms> firms = dashboardService.findallFirms();
		model.addAttribute("firms", firms);
		List<Product> products = productServices.getAllProducts();
		Set<String> productBrands = new TreeSet<String>();
		for (Product pro : products) {
			productBrands.add(pro.getBrand());
		}
		model.addAttribute("products", productBrands);

		return "purchaseInvoice";
	}

	@RequestMapping(value = "/save-purchaseinvoice", method = RequestMethod.POST)
	public ResponseEntity<String> savePurchaseinvoice(@RequestBody PurchaseinvoiceProductDTO purchaseInvoiceProductDTO,
			HttpServletRequest request, HttpServletResponse response, HttpSession httpSession) throws IOException {

		try {
			PurchaseInvoice purchaseInvoice = null;
			Supplier supplier = supplierServices.getSupplierById(purchaseInvoiceProductDTO.getSupplierId());

			if (supplier == null)
				return new ResponseEntity<String>("Supplier does not exits", HttpStatus.BAD_REQUEST);

			List<ProductDTO> prods = purchaseInvoiceProductDTO.getProductsArray();

			for (ProductDTO productDto : prods) {

				Product prodtl = productServices.getProductById(productDto.getId());
				if (prodtl == null) {
					return new ResponseEntity<String>("Product detals not available", HttpStatus.BAD_REQUEST);
				}
				ProductPurchaseInvoice productPurchaseInv = productPurchaseInvoiceService
						.findProductPurchaseInvoiceBySerialNo(productDto.getSerialNumber());
				if (productPurchaseInv != null)
					return new ResponseEntity<String>(productDto.getSerialNumber() + " serial number already exists",
							HttpStatus.BAD_REQUEST);

				if (productDto.getIndoorModelNumber() != null
						&& productDto.getIndoorModelNumber().trim().length() > 0) {
					ProductPurchaseInvoice productPurchaseInvForIndoor = productPurchaseInvoiceService
							.findProductPurchaseInvoiceByIndoorSerialNo(productDto.getIndoorModelNumber());
					if (productPurchaseInvForIndoor != null)
						return new ResponseEntity<String>(
								productDto.getIndoorModelNumber() + " indoor serial number already exists",
								HttpStatus.BAD_REQUEST);
				}
			}

			PurchaseInvoice purchaseInv = purchaseInvoiceServices
					.getpurchaseInvoiceByInvoiceNumber(purchaseInvoiceProductDTO.getInvoiceNumber());
			if (purchaseInv != null)
				return new ResponseEntity<String>(
						purchaseInvoiceProductDTO.getInvoiceNumber() + " purchase invoice number already exists",
						HttpStatus.BAD_REQUEST);

			if (purchaseInvoiceProductDTO.getFirmId() == null) {
				return new ResponseEntity<String>("No Firm name found", HttpStatus.BAD_REQUEST);
			}
			Firms firm = dashboardService.getFirmById(purchaseInvoiceProductDTO.getFirmId());
			if (firm == null) {
				return new ResponseEntity<String>("No Firm name found", HttpStatus.BAD_REQUEST);
			}

			purchaseInvoice = new PurchaseInvoice();
			purchaseInvoice.setCmpyPurchaseInvoiceNo(purchaseInvoiceProductDTO.getInvoiceNumber());
			purchaseInvoice.setAmountPaid(purchaseInvoiceProductDTO.getAmountPaid());
			purchaseInvoice.setBalanceLeft(purchaseInvoiceProductDTO.getBalanceLeft());
			purchaseInvoice.setComments(purchaseInvoiceProductDTO.getComments());
			purchaseInvoice.setDiscountAmount(purchaseInvoiceProductDTO.getTotalDiscount());
			purchaseInvoice.setFinalAmount(purchaseInvoiceProductDTO.getFinalAmount());
			purchaseInvoice.setSupplier(supplier);
			purchaseInvoice.setPaymentMode(purchaseInvoiceProductDTO.getPaymentMode());
			if(purchaseInvoiceProductDTO.getPaymentMode().equalsIgnoreCase("Cheque")){
				purchaseInvoice.setChequeNumber(purchaseInvoiceProductDTO.getChequeNumber());
				purchaseInvoice.setChequeDate(purchaseInvoiceProductDTO.getChequeDate());
				purchaseInvoice.setBankName(purchaseInvoiceProductDTO.getBankName());
			}
			purchaseInvoice.setTaxAmount(purchaseInvoiceProductDTO.getTaxAmount());
			if (purchaseInvoiceProductDTO.getDate() == null)
				purchaseInvoice.setDate(new Date());
			else
				purchaseInvoice.setDate(purchaseInvoiceProductDTO.getDate());

			SessionUser sessionUser = SessionUser.getHttpSessionUser(httpSession);

			if (sessionUser.getRole().equalsIgnoreCase("Checker")) {
				Checker checker = checkerServices.getCheckerById(sessionUser.getId());
				purchaseInvoice.setChecker(checker);

			}

			if (sessionUser.getRole().equalsIgnoreCase("Admin")) {
				Admin admin = adminServices.getAdminById(sessionUser.getId());
				purchaseInvoice.setAdmin(admin);
			}

			if (sessionUser.getRole().equalsIgnoreCase("Maker")) {
				Maker maker = makerServices.getMakerById(sessionUser.getId());
				purchaseInvoice.setMaker(maker);
			}

			purchaseInvoiceServices.addOrUpdatePurchaseInvoice(purchaseInvoice);

			List<ProductDTO> products = purchaseInvoiceProductDTO.getProductsArray();

			System.out.println("products size::" + products.size());
			for (ProductDTO product : products) {

				ProductPurchaseInvoice ppi = new ProductPurchaseInvoice();
				Product pro = productServices.getProductById(product.getId());
				if (pro == null) {
					return new ResponseEntity<String>("Product detals not available", HttpStatus.BAD_REQUEST);
				}

				pro.setQuantity(product.getQuantity() + pro.getQuantity());
				productServices.addOrUpdateProduct(pro);

				StockReport stockReport = stockReportServices.getStockReportByProductId(pro.getId());
				stockReport.setUnits(pro.getQuantity());
				stockReportServices.createOrUpdateStockReport(stockReport);

				ppi.setQuantity(product.getQuantity());
				ppi.setPurchaseInvoice(purchaseInvoice);
				ppi.setProduct(pro);
				ppi.setUnitPrice(product.getUnitPrice());
				ppi.setDiscountedAmount(product.getDiscountedAmount());
				ppi.setLocation(product.getLocation());
				ppi.setRebateDiscount(product.getRebateDiscount());
				ppi.setTradeDiscount(product.getTradeDiscount());
				ppi.setSingleUnitTax(product.getSingleUnitTax());
				ppi.setBillAmount(product.getBillAmount());
				ppi.setSale(0);
				ppi.setSerialNo(product.getSerialNumber());
				ppi.setFirm(firm);

				ppi.setIndoorSerialNo("");
				if (product.getIndoorModelNumber().trim().length() > 0) {
					ppi.setIndoorSerialNo(product.getIndoorModelNumber());
					ppi.setIndoorsale(0);
					ppi.setIndoorLocation(product.getIndoorLocation());
				}
				// ppi.setUnitPriceBeforeDiscount(product.getUnitPriceBeforeDiscount());

				dashboardService.savePurchaseInvoiceDetails(ppi);
			}
			SupplierReport supplierReport = new SupplierReport();
			supplierReport.setSupplier(supplier);
			supplierReport.setPurchaseInvoice(purchaseInvoice);
			supplierReportServices.createOrUpdateSupplierReport(supplierReport);
			return new ResponseEntity<String>(String.valueOf(purchaseInvoice.getInvoiceNo()), HttpStatus.OK);
		}

		catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("some error", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@RequestMapping(value = "/myprofile", method = RequestMethod.GET)
	public String myProfile(Model model, HttpSession httpSession) {

		SessionUser currentUser = SessionUser.getHttpSessionUser(httpSession);
		if (currentUser.getRole().equalsIgnoreCase("Admin")) {
			Admin admin = adminServices.getAdminById(currentUser.getId());
			model.addAttribute("currentUser", admin);
		}

		if (currentUser.getRole().equalsIgnoreCase("Checker")) {
			Checker checker = checkerServices.getCheckerById(currentUser.getId());
			model.addAttribute("currentUser", checker);
		}

		if (currentUser.getRole().equalsIgnoreCase("Maker")) {
			Maker maker = makerServices.getMakerById(currentUser.getId());
			model.addAttribute("currentUser", maker);
		}
		if (currentUser.getRole().equalsIgnoreCase("Sales Person")) {
			SalesPerson salesPerson = salesPersonServices.getSalesPersonById(currentUser.getId());
			model.addAttribute("currentUser", salesPerson);
		}
		return "myprofile";
	}

	@RequestMapping(value = "/myprofile", method = RequestMethod.POST)
	public String submitMyProfile(Model model, HttpSession httpSession, @RequestParam(value = "name") String name,
			@RequestParam(value = "address") String address, @RequestParam(value = "email") String email,
			@RequestParam(value = "username") String username) {

		if (name == null || address == null || email == null || username == null || name.trim().length() == 0) {
			setError(model, "name cannot be blank");
			return "redirect:/dashboard/myprofile";
		}
		if (address == null || address.trim().length() == 0) {
			setError(model, "address cannot be blank");
			return "redirect:/dashboard/myprofile";
		}
		if (email == null || email.trim().length() == 0) {
			setError(model, "email cannot be blank");
			return "redirect:/dashboard/myprofile";
		}
		if (username == null || username.trim().length() == 0) {
			setError(model, "username cannot be blank");
			return "redirect:/dashboard/myprofile";
		}

		SessionUser currentUser = SessionUser.getHttpSessionUser(httpSession);
		if (currentUser.getRole().equalsIgnoreCase("Admin")) {
			Admin admin = adminServices.getAdminById(currentUser.getId());
			admin.setAddress(address);
			admin.setName(name);
			admin.setUsername(username);
			admin.setEmail(email);
			adminServices.addOrUpdateAdmin(admin);
		}

		else if (currentUser.getRole().equalsIgnoreCase("Checker")) {
			Checker checker = checkerServices.getCheckerById(currentUser.getId());
			checker.setAddress(address);
			checker.setName(name);
			checker.setUsername(username);
			checker.setEmail(email);
			checkerServices.addOrUpdateChecker(checker);
		}

		else if (currentUser.getRole().equalsIgnoreCase("Maker")) {
			Maker maker = makerServices.getMakerById(currentUser.getId());
			maker.setAddress(address);
			maker.setName(name);
			maker.setUsername(username);
			maker.setEmail(email);
			makerServices.addOrUpdateMaker(maker);
		} else {
			SalesPerson salesPerson = salesPersonServices.getSalesPersonById(currentUser.getId());
			salesPerson.setAddress(address);
			salesPerson.setName(name);
			salesPerson.setUsername(username);
			salesPerson.setEmail(email);
			salesPersonServices.addOrUpdateSalesPerson(salesPerson);
		}
		model.addAttribute("Update_Msg", true);
		return "redirect:/dashboard/myprofile";
	}

	@RequestMapping(value = "/stockReport", method = RequestMethod.GET)
	public String stockReport(Model model) {
		List<Product> products = productServices.getAllProducts();
		model.addAttribute("products", products);
		return "stock-report";
	}

	@RequestMapping(value = "/supplierReport", method = RequestMethod.GET)
	public String supplierReport(Model model) {
		List<PurchaseInvoice> purchaseInvoice = purchaseInvoiceServices.getPurchaseList();
		System.out.println("Size:: " + purchaseInvoice.size());

		if (!purchaseInvoice.isEmpty())
			model.addAttribute("purchaseInvoice", purchaseInvoice);
		return "supplier-report";
	}

	@RequestMapping(value = "/downloadStockReport", method = RequestMethod.GET)
	public String downloadStockReport(HttpServletResponse response) {
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Stock Report");

		// create style for header cells
		CellStyle style = workbook.createCellStyle();
		Font font = workbook.createFont();
		font.setFontName("Arial");
		style.setFillForegroundColor(HSSFColor.BLUE.index);
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setColor(HSSFColor.WHITE.index);
		style.setFont(font);

		// create header row
		Row rowheader = sheet.createRow(0);

		rowheader.createCell(1).setCellValue("Brand");
		rowheader.getCell(1).setCellStyle(style);

		rowheader.createCell(2).setCellValue("product Type");
		rowheader.getCell(2).setCellStyle(style);

		rowheader.createCell(3).setCellValue("Model");
		rowheader.getCell(3).setCellStyle(style);

		rowheader.createCell(4).setCellValue("Units");
		rowheader.getCell(4).setCellStyle(style);

		rowheader.createCell(5).setCellValue("Reorder point");
		rowheader.getCell(5).setCellStyle(style);

		List<Object[]> rows = stockReportServices.getStockReportList();

		int rowCount = 0;
		for (Object[] stockObject : rows) {
			Row row = sheet.createRow(++rowCount);

			int columnCount = 0;

			for (Object field : stockObject) {
				Cell cell = row.createCell(++columnCount);
				if (field instanceof String) {
					cell.setCellValue((String) field);
				} else if (field instanceof Integer) {
					cell.setCellValue((Integer) field);
				} else {
					cell.setCellValue(field.toString());
				}
			}
		}

		try {
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename=StockReport.xlsx");

			workbook.write(response.getOutputStream()); // Write workbook to
														// response.
			response.getOutputStream().close();
			workbook.close();

			System.out.println("Excel written successfully..");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "stock-report";
	}

	@RequestMapping(value = "uploadSaleinvoice", method = RequestMethod.GET)
	public String rendorUploadSaleinvoice() {
		return "redirect:/dashboard/salesinvoice";
	}

	@RequestMapping(produces = "text/html", value = "uploadSaleinvoice", method = RequestMethod.POST)
	public String uploadProducts(@RequestParam(value = "uploadInvoice", required = false) MultipartFile uploadInvoice,
			Model uiModel, HttpServletRequest request, Model model, HttpSession httpSession)
			throws IOException, ParseException {

		@SuppressWarnings("resource")
		CSVReader reader = new CSVReader(new InputStreamReader(uploadInvoice.getInputStream()), ',', '"', 1);
		int count = 0;
		// Read CSV line by line and use the string array as you want
		String[] record;
		List<String> errorMsg = new ArrayList<String>();
		while ((record = reader.readNext()) != null) {
			count++;
			String brandName = record[0].trim();
			String modelNumber = record[1].trim();
			String productType = record[2].trim();
			String size = record[3].trim();
			String star = record[4].trim();
			String customerName = record[5].trim();
			String customerAddress = record[6].trim();
			String customerPhone = record[7].trim();
			String serialNumber = record[8].trim();
			String purchaseInvoiceNumber = record[9].trim();
			String purchaseInvoiceDate = record[10].trim();
			String unitPrice = record[11].trim();
			String saleInvoiceNumber = record[12].trim();
			String saleInvoiceDate = record[13].trim();
			String amount = record[14].trim();
			String indoorSerialNumber = record[15].trim();

			if (!saleInvoiceDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
				errorMsg.add("sale invoice date " + saleInvoiceDate + " of record " + count
						+ " should be in format yyyy-mm-dd");
				continue;
			}
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date siDate;
			siDate = df.parse(saleInvoiceDate);

			if (brandName.trim().length() == 0) {
				errorMsg.add("Brand name of record " + count + " cannot be empty");
				continue;
			}

			if (modelNumber.trim().length() == 0) {
				errorMsg.add("Model number of record " + count + " cannot be empty");
				continue;
			}

			if (productType.trim().length() == 0) {
				errorMsg.add("Product Type of record " + count + " cannot be empty");
				continue;
			}

			if (size.trim().length() == 0) {
				errorMsg.add("Size of record " + count + " cannot be empty");
				continue;
			}

			if (amount.trim().length() == 0) {
				errorMsg.add("amount of record " + count + " cannot be empty");
				continue;
			}

			if (purchaseInvoiceNumber.trim().length() == 0) {
				errorMsg.add("purchase invoice number cannot be blank " + count + " cannot be empty");
				continue;
			}

			PurchaseInvoice purchaseInvoice = purchaseInvoiceServices
					.getpurchaseInvoiceByInvoiceNumber(purchaseInvoiceNumber);
			if (purchaseInvoice == null) {
				errorMsg.add("Invalid purchase invoice" + purchaseInvoice + "of record " + count);
				continue;
			}

			Product product = null;
			if (star == null || star.trim().length() == 0)
				product = dashboardService.findproductInfoExcludeStarRating(brandName, productType, modelNumber, size);

			else
				product = dashboardService.findproductInfo(brandName, productType, modelNumber, size, star);

			if (product == null) {
				errorMsg.add(
						"No product found corresponding to brand " + brandName + " and model number " + modelNumber);
				continue;
			}

			ProductPurchaseInvoice productPurchaseInvoiceForIndoor = null;
			if (indoorSerialNumber.trim().length() > 0) {
				productPurchaseInvoiceForIndoor = productPurchaseInvoiceService
						.findProductPurchaseInvoiceByIndoorSerialNo(indoorSerialNumber);
				if (productPurchaseInvoiceForIndoor == null) {
					errorMsg.add(
							"indoorSerialNumber " + indoorSerialNumber + " of record " + count + " does not exist");
					continue;
				}
			}

			if (customerPhone.trim().length() <= 0) {
				errorMsg.add("Phone Number Cannot be empty");
				continue;
			}

			Customer customer = null;

			customer = customerService.getCustomerByMobile(Long.parseLong(customerPhone));

			if (customer == null) {
				customer = new Customer();
				if (customerName.trim().length() > 0)
					customer.setName(customerName);
				if (customerAddress.trim().length() > 0)
					customer.setAddress(customerAddress);
				customer.setContactNo(Long.parseLong(customerPhone));
			}

			ProductPurchaseInvoice productPurchaseInvoice = productPurchaseInvoiceService
					.getProductPurchaseInvoiceBySerialNo(serialNumber);

			if (productPurchaseInvoice == null) {
				errorMsg.add("Product with serial number " + serialNumber + " does not exist in database");
				continue;
			}

			SaleInvoice saleInvoice = null;

			if (saleInvoiceNumber.trim().length() > 0) {
				saleInvoice = saleInvoiceServices.getSaleInvoiceByinvoiceNumber(saleInvoiceNumber);
			}

			if (saleInvoice == null) {
				saleInvoice = new SaleInvoice();

				saleInvoice.setCmpySaleInvoiceNo(saleInvoiceNumber);
				saleInvoice.setTotalDiscountedAmount(0.0);
				saleInvoice.setFinalAmount(Double.parseDouble(amount));

				saleInvoice.setInvoiceDate(siDate);
				saleInvoice.setAmountPaid(Double.parseDouble(amount));
				saleInvoice.setPaymentMode("Cash");
				saleInvoice.setBalanceLeft(0.0);

				SessionUser sessionUser = SessionUser.getHttpSessionUser(httpSession);

				if (sessionUser.getRole().equalsIgnoreCase("Checker")) {
					Checker checker = checkerServices.getCheckerById(sessionUser.getId());
					saleInvoice.setChecker(checker);
					customer.setChecker(checker);

				}

				if (sessionUser.getRole().equalsIgnoreCase("Admin")) {
					Admin admin = adminServices.getAdminById(sessionUser.getId());
					saleInvoice.setAdmin(admin);
					customer.setAdmin(admin);
				}

				if (sessionUser.getRole().equalsIgnoreCase("Sales Person")) {
					SalesPerson salesPerson = salesPersonServices.getSalesPersonById(sessionUser.getId());
					saleInvoice.setSalesPerson(salesPerson);
					customer.setSalesPerson(salesPerson);
				}

				if (sessionUser.getRole().equalsIgnoreCase("Maker")) {
					Maker maker = makerServices.getMakerById(sessionUser.getId());
					customer.setMaker(maker);
				}

				customerService.addOrUpdateCustomer(customer);
				saleInvoice.setCustomer(customer);

				saleInvoiceServices.addOrUpdateSaleInvoice(saleInvoice);

				TaxInvoice ti = new TaxInvoice();
				ti.setSaleInvoice(saleInvoice);
				taxInvoiceServices.addOrUpdateTaxInvoice(ti);

				CustomerReport cr = new CustomerReport();
				cr.setCustomer(customer);
				cr.setSaleInvoice(saleInvoice);
				customerReportServices.addOrUpdateCustomerReport(cr);

			}

			Product product1 = productServices.getProductById(productPurchaseInvoice.getProduct().getId());
			product1.setQuantity((product1.getQuantity() - 1));
			productServices.addOrUpdateProduct(product1);

			StockReport stockReport = stockReportServices.getStockReportByProductId(product1.getId());
			stockReport.setUnits(product1.getQuantity());
			stockReportServices.createOrUpdateStockReport(stockReport);

			ProductSaleInvoice psi = new ProductSaleInvoice();
			psi.setProduct(product1);
			psi.setSaleinvoice(saleInvoice);
			psi.setComment("not given");
			psi.setQuantity(1);
			psi.setUnitPrice(Double.parseDouble(unitPrice));

			psi.setSerialNumber(serialNumber);
			psi.setCmpyPurchaseInvoiceNo(purchaseInvoiceNumber);
			// psi.setPurchaseInvoiceDate(purchaseInvoiceDate);
			dashboardService.saveSID(psi);

			productPurchaseInvoice.setSale(1);
			productPurchaseInvoiceService.addOrUpdateProductPurchaseinvoice(productPurchaseInvoice);

			if (productPurchaseInvoiceForIndoor != null) {
				productPurchaseInvoiceForIndoor.setIndoorsale(1);
				productPurchaseInvoiceService.addOrUpdateProductPurchaseinvoice(productPurchaseInvoiceForIndoor);
			}

		}
		if (!errorMsg.isEmpty()) {
			setError(model, errorMsg);
			model.addAttribute("errorMsg", errorMsg);
		} else {
			model.addAttribute("uploadSuccess", true);
		}
		return "sales-invoice";
	}

	@RequestMapping(produces = "text/csv", value = "downloadforSaleInvoice")
	public void downloadforSaleInvoice(Model uiModel, HttpServletResponse response) {
		String output = "brandName,modelNumber,Product Type,size,star(Not Manditory),customerName,customerAddress,customerPhone,serialNumber,purchaseInvoiceNumber,purchaseInvoiceDate,Unit Price,saleInvoiceNumber,saleInvoiceDate,amount,Indoor Serial Number";
		try {

			response.addHeader("Content-Disposition", "attachment; filename=SaleInvoice.csv");
			OutputStream out = response.getOutputStream();
			out.write(output.getBytes());
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@RequestMapping(produces = "text/csv", value = "downloadforPurchaseInvoice")
	public void downloadforPurchaseinvoice(Model uiModel, HttpServletResponse response) {
		String output = "Brand Name,Model Number,Product Type,size,star(Not Manditory),Serial Number,Supplier Name,Supplier Address,Supplier Phone,Purchase InvoiceNumber,Purchase InvoiceDate,Unit Price,Amount,Indoor Serial Number,Location";
		try {

			response.addHeader("Content-Disposition", "attachment; filename=PurchaseInvoice.csv");
			OutputStream out = response.getOutputStream();
			out.write(output.getBytes());
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "uploadPurchaseinvoice", method = RequestMethod.GET)
	public String rendorUploadPurchaseInvoice() {
		return "redirect:/dashboard/salesinvoice";
	}

	@RequestMapping(produces = "text/html", value = "uploadPurchaseinvoice", method = RequestMethod.POST)
	public String uploadPurchaseinvoice(
			@RequestParam(value = "uploadPurchaseInvoice", required = false) MultipartFile uploadInvoice, Model uiModel,
			HttpServletRequest request, Model model, HttpSession httpSession) throws IOException, ParseException {
		List<String> errorMsg = new ArrayList<String>();
		if (uploadInvoice.getSize() == 0) {
			model.addAttribute("errorMsg", errorMsg);
			setError(model, errorMsg);
			return "purchaseInvoice";
		}
		CSVReader reader = new CSVReader(new InputStreamReader(uploadInvoice.getInputStream()), ',', '"', 1);
		int count = 0;
		// Read CSV line by line and use the string array as you want
		String[] record;

		try {

			while ((record = reader.readNext()) != null) {
				count++;
				if (count > 100) {
					model.addAttribute("errorMsg", "Record couning cannot be more than 100");
					setError(model, errorMsg);
					return "purchaseInvoice";
				}
				String brandName = record[0].trim();
				String modelNumber = record[1].trim();
				String productType = record[2].trim();
				String size = record[3].trim();
				String star = record[4].trim();
				String serialNumber = record[5].trim();
				String supplierName = record[6].trim();
				String supplierAddress = record[7].trim();
				String supplierPhone = record[8].trim();
				String purchaseInvoiceNumber = record[9].trim();
				String purchaseInvoiceDate = record[10].trim();
				String unitPrice = record[11].trim();
				String amount = record[12].trim();
				String indoorSerialNumber = record[13].trim();
				String location = record[14].trim();

				if (serialNumber.trim().length() == 0) {
					errorMsg.add("serialNumber " + serialNumber + " of record " + count + " cannot be empty");
					continue;
				}

				if (!purchaseInvoiceDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
					errorMsg.add("purchase invoice date " + purchaseInvoiceDate + " of record " + count
							+ " should be in format yyyy-mm-dd");
					continue;
				}

				ProductPurchaseInvoice productPurchaseInvoiceForSerialNo = productPurchaseInvoiceService
						.findProductPurchaseInvoiceBySerialNo(serialNumber);
				if (productPurchaseInvoiceForSerialNo != null) {
					errorMsg.add("serialNumber " + serialNumber + " of record " + count + " already exist");
					continue;
				}

				if (indoorSerialNumber.trim().length() > 0) {
					ProductPurchaseInvoice productPurchaseInvoiceForIndoor = productPurchaseInvoiceService
							.findProductPurchaseInvoiceByIndoorSerialNo(indoorSerialNumber);
					if (productPurchaseInvoiceForIndoor != null) {
						errorMsg.add(
								"indoorSerialNumber " + indoorSerialNumber + " of record " + count + " already exist");
						continue;
					}
				}
				if (amount.trim().length() == 0) {
					errorMsg.add("amount of record " + count + " cannot be empty");
					continue;
				}

				if (purchaseInvoiceNumber.trim().length() == 0) {
					errorMsg.add("Purchase invoice number " + purchaseInvoiceNumber + " of record " + count
							+ " cannot be empty");
					continue;
				}

				if (brandName.trim().length() == 0 || modelNumber.trim().length() == 0) {
					errorMsg.add("Brand name or model number of record " + count + " cannot be empty");
					continue;
				}

				if (productType.trim().length() == 0 || size.trim().length() == 0) {
					errorMsg.add("Product type or size of record " + count + " cannot be empty");
					continue;
				}

				if (unitPrice.trim().length() == 0) {
					errorMsg.add("Unit Price of record " + count + " cannot be empty");
					continue;
				}

				Product product = null;
				if (star == null || star.trim().length() == 0)
					product = dashboardService.findproductInfoExcludeStarRating(brandName, productType, modelNumber,
							size);

				else
					product = dashboardService.findproductInfo(brandName, productType, modelNumber, size, star);

				if (product == null) {
					errorMsg.add("No product found corresponding to brand " + brandName + " and model number "
							+ modelNumber);
					continue;
				}

				/*
				 * DateFormat srcDf = new SimpleDateFormat("MM/dd/yyyy");
				 * 
				 * Date date = srcDf.parse(purchaseInvoiceDate);
				 * purchaseInvoiceDate=srcDf.format(date);
				 */

				DateFormat destDf = new SimpleDateFormat("yyyy-MM-dd");

				Date piDate = destDf.parse(purchaseInvoiceDate);

				if (supplierPhone.trim().length() == 0) {
					errorMsg.add("Phone Number Cannot be empty");
					continue;
				}
				Supplier supplier = null;
				supplier = supplierServices.getSupplierByMobileNumber(Long.parseLong(supplierPhone));
				if (supplier == null) {
					supplier = new Supplier();

					if (supplierName.trim().length() > 0) {
						supplier.setName(supplierName);
					}
					if (supplierAddress.trim().length() > 0) {
						supplier.setAddress(supplierAddress);
					}
					supplier.setContactNo(Long.parseLong(supplierPhone));

				}

				PurchaseInvoice purchaseInvoice = null;
				if (purchaseInvoiceNumber.trim().length() > 0) {
					purchaseInvoice = purchaseInvoiceServices.getpurchaseInvoiceByInvoiceNumber(purchaseInvoiceNumber);
				}

				if (purchaseInvoice == null) {
					purchaseInvoice = new PurchaseInvoice();
					purchaseInvoice.setCmpyPurchaseInvoiceNo(purchaseInvoiceNumber);
					purchaseInvoice.setAmountPaid(Double.parseDouble(amount));
					purchaseInvoice.setBalanceLeft(0);
					purchaseInvoice.setDate(piDate);
					purchaseInvoice.setPaymentMode("Cash");
					purchaseInvoice.setSupplier(supplier);
				}

				SessionUser sessionUser = SessionUser.getHttpSessionUser(httpSession);

				if (sessionUser.getRole().equalsIgnoreCase("Checker")) {
					Checker checker = checkerServices.getCheckerById(sessionUser.getId());
					purchaseInvoice.setChecker(checker);
					supplier.setChecker(checker);
				}

				if (sessionUser.getRole().equalsIgnoreCase("Admin")) {
					Admin admin = adminServices.getAdminById(sessionUser.getId());
					purchaseInvoice.setAdmin(admin);
					supplier.setAdmin(admin);
				}

				supplierServices.addOrUpdateSupplier(supplier);
				purchaseInvoice.setSupplier(supplier);
				purchaseInvoiceServices.addOrUpdatePurchaseInvoice(purchaseInvoice);

				product.setQuantity((product.getQuantity() + 1));
				productServices.addOrUpdateProduct(product);

				StockReport stockReport = stockReportServices.getStockReportByProductId(product.getId());
				if (stockReport != null) {
					stockReport.setUnits(product.getQuantity());
					stockReportServices.createOrUpdateStockReport(stockReport);
				}

				ProductPurchaseInvoice productPurchaseInvoice = new ProductPurchaseInvoice();
				productPurchaseInvoice.setQuantity(1);
				productPurchaseInvoice.setPurchaseInvoice(purchaseInvoice);
				productPurchaseInvoice.setProduct(product);
				productPurchaseInvoice.setUnitPrice(Double.parseDouble(unitPrice));
				productPurchaseInvoice.setLocation("Godown");// location by
																// default
																// godown,otherwise
																// shop
				if (location != null && location.trim().length() > 0) {
					productPurchaseInvoice.setLocation("Shop");
				}
				productPurchaseInvoice.setSale(0);
				productPurchaseInvoice.setSerialNo(serialNumber);
				productPurchaseInvoice.setIndoorsale(0);

				if (indoorSerialNumber.trim().length() > 0) {
					productPurchaseInvoice.setIndoorSerialNo(indoorSerialNumber);
					productPurchaseInvoice.setIndoorsale(0);
				}

				dashboardService.savePurchaseInvoiceDetails(productPurchaseInvoice);
			}

			if (!errorMsg.isEmpty()) {
				model.addAttribute("errorMsg", errorMsg);
				setError(model, errorMsg);
			} else {
				model.addAttribute("uploadSuccess", true);
				System.out.println("success");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "purchaseInvoice";

	}

	@RequestMapping(value = "/checkUniqueSaleInvoice/{invoiceId}/", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> checkUniqueSaleInvoice(
			@PathVariable(value = "invoiceId") String invoiceId) {

		Map<String, Object> responseMap = new HashMap<String, Object>(2);
		responseMap.put("exists", false);
		responseMap.put("message", invoiceId + " does not exists.");

		try {
			SaleInvoice saleInvoice = saleInvoiceServices.getSaleInvoiceByinvoiceNumber(invoiceId);
			if (saleInvoice != null) {
				responseMap.put("exists", true);
				responseMap.put("message", invoiceId + " already exists.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			responseMap.remove("exists");
			responseMap.put("message", "Some error occured.");
			return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
	}

	@RequestMapping(value = "/checkUniquePurchaseInvoice/{invoiceId}/", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> checkUniquePurchaseInvoice(
			@PathVariable(value = "invoiceId") String invoiceId) {

		Map<String, Object> responseMap = new HashMap<String, Object>(2);
		responseMap.put("exists", false);
		responseMap.put("message", invoiceId + " does not exists.");

		try {
			PurchaseInvoice purchaseInvoice = purchaseInvoiceServices.getpurchaseInvoiceByInvoiceNumber(invoiceId);
			if (purchaseInvoice != null) {
				responseMap.put("exists", true);
				responseMap.put("message", invoiceId + " already exists.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			responseMap.remove("exists");
			responseMap.put("message", "Some error occured.");
			return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
	}

	@RequestMapping(value = "/checkUniqueSerilaNo/{serialNo}/", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> checkUniqueSerilaNo(@PathVariable(value = "serialNo") String serialNo) {

		Map<String, Object> responseMap = new HashMap<String, Object>(2);
		responseMap.put("exists", false);
		responseMap.put("message", serialNo + " does not exists.");

		try {
			ProductPurchaseInvoice productPurchaseInvoiceForSerialNo = productPurchaseInvoiceService
					.findProductPurchaseInvoiceBySerialNo(serialNo);
			if (productPurchaseInvoiceForSerialNo != null) {
				responseMap.put("exists", true);
				responseMap.put("message", serialNo + " already exists.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			responseMap.remove("exists");
			responseMap.put("message", "Some error occured.");
			return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
	}

	@RequestMapping(value = "/checkUniqueIndoorSerilaNo/{indoorSerialNo}/", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> checkUniqueIndoorSerilaNo(
			@PathVariable(value = "indoorSerialNo") String indoorSerialNo) {

		Map<String, Object> responseMap = new HashMap<String, Object>(2);
		responseMap.put("exists", false);
		responseMap.put("message", indoorSerialNo + " does not exists.");

		try {
			ProductPurchaseInvoice productPurchaseInvoiceForIndoorSerialNo = productPurchaseInvoiceService
					.findProductPurchaseInvoiceByIndoorSerialNo(indoorSerialNo);
			if (productPurchaseInvoiceForIndoorSerialNo != null) {
				responseMap.put("exists", true);
				responseMap.put("message", indoorSerialNo + " already exists.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			responseMap.remove("exists");
			responseMap.put("message", "Some error occured.");
			return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
	}

	@RequestMapping(value = "/checkUniqueProductBrand/{brand}/", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> checkUniqueProductBrand(@PathVariable(value = "brand") String brand) {

		Map<String, Object> responseMap = new HashMap<String, Object>(2);
		responseMap.put("exists", false);
		responseMap.put("message", brand + " does not exists.");

		try {
			Product product = productServices.getProductByBrand(brand);
			if (product != null) {
				responseMap.put("exists", true);
				responseMap.put("message", brand + " already exists.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			responseMap.remove("exists");
			responseMap.put("message", "Some error occured.");
			return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
	}

	@RequestMapping(value = "/stockReportReorderProduct", method = RequestMethod.GET)
	public String stockReportForReorderProduct(Model model) {
		List<Product> products = productServices.getAllReorderProducts();
		model.addAttribute("products", products);
		return "reorder-product-report";
	}

	@RequestMapping(value = "/paymentDueSalesInvoice", method = RequestMethod.GET)
	public String paymentDueSalesInvoice(Model model) {
		List<SaleInvoice> saleInvoices = saleInvoiceServices.getAllDueSaleInvoice();
		model.addAttribute("saleInvoices", saleInvoices);
		return "due-saleInvoice";
	}

	@RequestMapping(value = "/paymentDuePurchaseInvoice", method = RequestMethod.GET)
	public String paymentDuepurchaseInvoice(Model model) {
		List<PurchaseInvoice> purchaseInvoices = purchaseInvoiceServices.getAllDuePurchaseInvoice();
		model.addAttribute("purchaseInvoices", purchaseInvoices);
		return "due-purchaseInvoice";
	}

	@RequestMapping(value = "/singleProductUnitList/{productId}", method = RequestMethod.GET)
	public String updateProductLocation(Model model, @PathVariable(value = "productId") long productId) {
		List<ProductPurchaseInvoice> productPurchaseInvoices = productPurchaseInvoiceService
				.getAllProductByProductId(productId);

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		for (ProductPurchaseInvoice productPurchaseInvoice : productPurchaseInvoices) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", productPurchaseInvoice.getId());
			map.put("cmpyPurchaseInvoiceNo", productPurchaseInvoice.getPurchaseInvoice().getCmpyPurchaseInvoiceNo());
			map.put("unitPrice", productPurchaseInvoice.getUnitPrice());
			map.put("rebateDiscount", productPurchaseInvoice.getRebateDiscount());
			map.put("singleUnitTax", productPurchaseInvoice.getSingleUnitTax());
			map.put("tradeDiscount", productPurchaseInvoice.getTradeDiscount());
			map.put("serialNo", productPurchaseInvoice.getSerialNo());
			map.put("sale", productPurchaseInvoice.getSale());
			map.put("indoorSerialNo", productPurchaseInvoice.getIndoorSerialNo());
			map.put("indoorsale", productPurchaseInvoice.getIndoorsale());
			map.put("location", productPurchaseInvoice.getLocation());
			map.put("indoorLocation", productPurchaseInvoice.getIndoorLocation());
			map.put("firmName", productPurchaseInvoice.getFirm().getName());
			map.put("saleInvoiceNo", null);
			map.put("saleInvoiceId", null);
			map.put("saleInvoiceNoForIndoor", null);
			map.put("saleInvoiceIdForIndoor", null);

			try {
				if (productPurchaseInvoice.getSale() == 1) {
					ProductSaleInvoice productSaleInvoice = productSaleInvoiceService
							.getProductSaleInvoiceBySerialNo(productPurchaseInvoice.getSerialNo());
					if (productSaleInvoice != null) {
						System.out.println("Serial No" + productSaleInvoice.getSerialNumber());
						map.put("saleInvoiceNo", productSaleInvoice.getSaleinvoice().getCmpySaleInvoiceNo());
						map.put("saleInvoiceId", productSaleInvoice.getSaleinvoice().getSaleInvoiceNo());
					}
				}

				if (productPurchaseInvoice.getIndoorsale() == 1) {
					ProductSaleInvoice productSaleInvoiceForIndoor = productSaleInvoiceService
							.getProductSaleInvoiceByIndoorSerialNo(productPurchaseInvoice.getIndoorSerialNo());

					if (productSaleInvoiceForIndoor != null) {
						System.out.println("Indoor Serial No" + productSaleInvoiceForIndoor.getIndoorSerialNo());
						map.put("saleInvoiceNoForIndoor",
								productSaleInvoiceForIndoor.getSaleinvoice().getCmpySaleInvoiceNo());
						map.put("saleInvoiceIdForIndoor",
								productSaleInvoiceForIndoor.getSaleinvoice().getSaleInvoiceNo());
					}
				}

				list.add(map);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		model.addAttribute("productPurchaseInvoiceList", list);
		model.addAttribute("productid", productId);
		return "product-singleUnitList";
	}

	@RequestMapping(value = "/update-unitLocation/{productPurchaseInvoiceId}", method = RequestMethod.GET)
	public String rendorProductSingleUnitLocation(Model model,
			@PathVariable(value = "productPurchaseInvoiceId") long productPurchaseInvoiceId) {

		ProductPurchaseInvoice productPurchaseInvoice = productPurchaseInvoiceService
				.findProductPurchaseInvoiceById(productPurchaseInvoiceId);

		model.addAttribute("productPurchaseInvoice", productPurchaseInvoice);
		return "update-unit-location";
	}

	@RequestMapping(value = "/update-unitLocation/{productPurchaseInvoiceId}", method = RequestMethod.POST)
	public String updateProductSingleUnitLocation(Model model, HttpSession httpSession,
			@ModelAttribute ProductPurchaseInvoice productPurchaseInvoice,
			@PathVariable(value = "productPurchaseInvoiceId") long productPurchaseInvoiceId) {

		ProductPurchaseInvoice productPurchaseInvoiceFromDb = productPurchaseInvoiceService
				.findProductPurchaseInvoiceById(productPurchaseInvoiceId);

		if (productPurchaseInvoiceFromDb == null) {
			setError(model, "Sorry no product found for updation");
			return "update-unit-location";

		}

		// SessionUser currentUser=SessionUser.getHttpSessionUser(httpSession);

		productPurchaseInvoiceFromDb.setUnitPrice(productPurchaseInvoice.getUnitPrice());
		productPurchaseInvoiceFromDb.setSerialNo(productPurchaseInvoice.getSerialNo());
		// productPurchaseInvoiceFromDb.setSale(productPurchaseInvoice.getSale());
		if (productPurchaseInvoice.getIndoorSerialNo() != null || productPurchaseInvoice.getIndoorSerialNo() == "") {
			productPurchaseInvoiceFromDb.setIndoorSerialNo(productPurchaseInvoice.getIndoorSerialNo());
			// productPurchaseInvoiceFromDb.setIndoorsale(productPurchaseInvoice.getIndoorsale());
		}
		productPurchaseInvoiceFromDb.setLocation(productPurchaseInvoice.getLocation());
		productPurchaseInvoiceService.addOrUpdateProductPurchaseinvoice(productPurchaseInvoiceFromDb);

		model.addAttribute("Update_Msg", true);
		return "redirect:/dashboard/singleProductUnitList/" + productPurchaseInvoiceFromDb.getProduct().getId();
	}

	@RequestMapping(value = "/update-saleInvoice/{saleinvoiceId}", method = RequestMethod.GET)
	public String rendorSaleInvoice(Model model, @PathVariable(value = "saleinvoiceId") long saleinvoiceId) {
		SaleInvoice saleInvoice = saleInvoiceServices.getSaleInvoiceById(saleinvoiceId);
		if (saleInvoice == null) {
			setError(model, "Sorry no sale invoice found for updation");
			return "update-saleInvoice";
		}
		model.addAttribute("saleInvoiceId", saleInvoice.getSaleInvoiceNo());
		model.addAttribute("balanceLeft", saleInvoice.getBalanceLeft());
		model.addAttribute("amountPaid", saleInvoice.getAmountPaid());
		model.addAttribute("finalAmount", saleInvoice.getFinalAmount());

		return "update-saleInvoice";
	}

	@RequestMapping(value = "/update-saleInvoice/{saleinvoiceId}", method = RequestMethod.POST)
	public String SaveSaleInvoice(Model model, @PathVariable(value = "saleinvoiceId") long saleinvoiceId,
			@RequestParam(value = "balanceLeft") double balanceLeft,
			@RequestParam(value = "amountPaid") double amountPaid) {

		SaleInvoice saleInvoice = saleInvoiceServices.getSaleInvoiceById(saleinvoiceId);
		if (saleInvoice == null) {
			setError(model, "Sorry no sale invoice found for updation");
			return "update-saleInvoice";
		}

		saleInvoice.setBalanceLeft(balanceLeft);
		saleInvoice.setAmountPaid(amountPaid);

		saleInvoiceServices.addOrUpdateSaleInvoice(saleInvoice);
		model.addAttribute("Update_Msg", true);
		return "redirect:/dashboard/customerReport";
	}

	@RequestMapping(value = "/update-purchaseInvoice/{purchaseId}", method = RequestMethod.GET)
	public String rendorPurchaseInvoice(Model model, @PathVariable(value = "purchaseId") long purchaseinvoiceId) {
		PurchaseInvoice purchaseInvoice = purchaseInvoiceServices.getPurchaseInvoiceById(purchaseinvoiceId);
		if (purchaseInvoice == null) {
			setError(model, "Sorry no purchase invoice found for updation");
			return "update-purchaseInvoice";
		}
		model.addAttribute("purchaseInvoiceId", purchaseInvoice.getInvoiceNo());
		model.addAttribute("balanceLeft", purchaseInvoice.getBalanceLeft());
		model.addAttribute("amountPaid", purchaseInvoice.getAmountPaid());
		model.addAttribute("finalAmount", purchaseInvoice.getFinalAmount());

		return "update-purchaseInvoice";
	}

	@RequestMapping(value = "/update-purchaseInvoice/{purchaseId}", method = RequestMethod.POST)
	public String SavePurchaseInvoice(Model model, @PathVariable(value = "purchaseId") long purchaseId,
			@RequestParam(value = "balanceLeft") double balanceLeft,
			@RequestParam(value = "amountPaid") double amountPaid) {

		PurchaseInvoice purchaseInvoice = purchaseInvoiceServices.getPurchaseInvoiceById(purchaseId);
		if (purchaseInvoice == null) {
			setError(model, "Sorry no purchase invoice found for updation");
			return "update-purchaseInvoice";
		}

		purchaseInvoice.setBalanceLeft(balanceLeft);
		purchaseInvoice.setAmountPaid(amountPaid);

		purchaseInvoiceServices.addOrUpdatePurchaseInvoice(purchaseInvoice);
		model.addAttribute("Update_Msg", true);
		return "redirect:/dashboard/supplierReport";
	}

	@RequestMapping(value = "/getSingleProductUnitByFilter", method = RequestMethod.GET)
	public String getSingleProductUnitByFilter(Model model, @RequestParam(value = "serialNo") String serialNoStauts,
			@RequestParam(value = "indoorSerialNo") String indoorSerialNoStatus,
			@RequestParam(value = "productid") long productid) {

		if (serialNoStauts == null || indoorSerialNoStatus == null
				|| (serialNoStauts.equals("") && indoorSerialNoStatus.equals(""))) {
			return "redirect:/dashborad/singleProductUnitList/" + productid;

		}

		List<ProductPurchaseInvoice> productPurchaseInvoices = null;
		int status = 0;
		if (serialNoStauts.equals("") && !indoorSerialNoStatus.equals("")) {

			if (indoorSerialNoStatus.equalsIgnoreCase("Yes")) {
				status = 1;
			}
			productPurchaseInvoices = productPurchaseInvoiceService.getAllIndoorProductByProductId(productid, status);

		}

		else if (!serialNoStauts.equals("") && indoorSerialNoStatus.equals("")) {
			if (serialNoStauts.equalsIgnoreCase("Yes")) {
				status = 1;
			}
			productPurchaseInvoices = productPurchaseInvoiceService.getAllOutdoorProductByProductid(productid, status);
		}

		else {

			int indoorStatus = 0, outdoorStatus = 0;
			if (serialNoStauts.equalsIgnoreCase("Yes") && indoorSerialNoStatus.equalsIgnoreCase("No")) {
				outdoorStatus = 1;
				productPurchaseInvoices = productPurchaseInvoiceService.getProductByIndoorOutdoorStatus(productid,
						indoorStatus, outdoorStatus);
			}

			else if (serialNoStauts.equalsIgnoreCase("No") && indoorSerialNoStatus.equalsIgnoreCase("Yes")) {
				indoorStatus = 1;
				productPurchaseInvoices = productPurchaseInvoiceService.getProductByIndoorOutdoorStatus(productid,
						indoorStatus, outdoorStatus);
			}

			else if (serialNoStauts.equalsIgnoreCase("Yes") && indoorSerialNoStatus.equalsIgnoreCase("Yes")) {
				outdoorStatus = 1;
				indoorStatus = 1;
				productPurchaseInvoices = productPurchaseInvoiceService.getProductByIndoorOutdoorStatus(productid,
						indoorStatus, outdoorStatus);
			}

			else {
				productPurchaseInvoices = productPurchaseInvoiceService.getProductByIndoorOutdoorStatus(productid,
						indoorStatus, outdoorStatus);
			}
		}

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		for (ProductPurchaseInvoice productPurchaseInvoice : productPurchaseInvoices) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", productPurchaseInvoice.getId());
			map.put("cmpyPurchaseInvoiceNo", productPurchaseInvoice.getPurchaseInvoice().getCmpyPurchaseInvoiceNo());
			map.put("unitPrice", productPurchaseInvoice.getUnitPrice());
			map.put("rebateDiscount", productPurchaseInvoice.getRebateDiscount());
			map.put("singleUnitTax", productPurchaseInvoice.getTradeDiscount());
			map.put("tradeDiscount", productPurchaseInvoice.getTradeDiscount());
			map.put("serialNo", productPurchaseInvoice.getSerialNo());
			map.put("sale", productPurchaseInvoice.getSale());
			map.put("indoorSerialNo", productPurchaseInvoice.getIndoorSerialNo());
			map.put("indoorsale", productPurchaseInvoice.getIndoorsale());
			map.put("location", productPurchaseInvoice.getLocation());
			map.put("indoorLocation", productPurchaseInvoice.getIndoorLocation());
			map.put("firmName", productPurchaseInvoice.getFirm().getName());
			map.put("saleInvoiceNo", null);
			map.put("saleInvoiceId", null);
			map.put("saleInvoiceNoForIndoor", null);
			map.put("saleInvoiceIdForIndoor", null);

			try {
				if (productPurchaseInvoice.getSale() == 1) {
					ProductSaleInvoice productSaleInvoice = productSaleInvoiceService
							.getProductSaleInvoiceBySerialNo(productPurchaseInvoice.getSerialNo());
					if (productSaleInvoice != null) {
						System.out.println("Serial No" + productSaleInvoice.getSerialNumber());
						map.put("saleInvoiceNo", productSaleInvoice.getSaleinvoice().getCmpySaleInvoiceNo());
						map.put("saleInvoiceId", productSaleInvoice.getSaleinvoice().getSaleInvoiceNo());
					}
				}

				if (productPurchaseInvoice.getIndoorsale() == 1) {
					ProductSaleInvoice productSaleInvoiceForIndoor = productSaleInvoiceService
							.getProductSaleInvoiceByIndoorSerialNo(productPurchaseInvoice.getIndoorSerialNo());

					if (productSaleInvoiceForIndoor != null) {
						System.out.println("Indoor Serial No" + productSaleInvoiceForIndoor.getIndoorSerialNo());
						map.put("saleInvoiceNoForIndoor",
								productSaleInvoiceForIndoor.getSaleinvoice().getCmpySaleInvoiceNo());
						map.put("saleInvoiceIdForIndoor",
								productSaleInvoiceForIndoor.getSaleinvoice().getSaleInvoiceNo());
					}
				}

				list.add(map);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		model.addAttribute("productPurchaseInvoiceList", list);
		model.addAttribute("productid", productid);
		return "product-singleUnitList";
	}

	@RequestMapping(value = "/firms", method = RequestMethod.GET)
	public String getallFirms(Model model) {
		List<Firms> firms = dashboardService.findallFirms();
		model.addAttribute("firms", firms);
		return "firms";
	}

	@RequestMapping(value = "/addfirm", method = RequestMethod.GET)
	public String addFirm() {
		return "add-firm";
	}

	@RequestMapping(value = "/addfirm", method = RequestMethod.POST)
	public String saveFirm(Model model, @ModelAttribute Firms firm, HttpSession httpSession) {

		if (firm.getName().trim().length() == 0 && firm.getAddresLine1().length() == 0
				&& firm.getShortName().length() == 0) {
			setError(model, "name or Address or short name of firm cannot be empty");
			return "add-firm";
		}

		Firms firmName = dashboardService.getFirmByName(firm.getName());
		if (firmName != null) {
			setError(model, " firm " + firm.getName() + " already exist");
			return "add-firm";
		}

		dashboardService.addOrUpdateFirm(firm);
		model.addAttribute("Success", true);
		return "redirect:/dashboard/firms";
	}

	@RequestMapping(value = "/update-firm/{firmId}", method = RequestMethod.GET)
	public String updateFirm(Model model, @PathVariable(value = "firmId") long firmId) {
		Firms firm = dashboardService.getFirmById(firmId);
		if (firm == null) {
			setError(model, " firm does not exit");
			return "redirect:/dashboard/firms";
		}
		model.addAttribute("firm", firm);
		return "update-firm";
	}

	@RequestMapping(value = "/update-firm/{firmId}", method = RequestMethod.POST)
	public String SaveUpdatedFirm(Model model, HttpSession httpSession, @ModelAttribute Firms firm,
			@PathVariable(value = "firmId") long firmId) {
		Firms firmFromDb = dashboardService.getFirmById(firmId);

		if (firmFromDb == null) {
			setError(model, "Sorry no firm found for updation");
			return "update-firm";
		}

		if (firm.getName().trim().length() == 0 || firm.getAddresLine1().length() == 0) {
			model.addAttribute("firm", firmFromDb);
			setError(model, "Name or Address of firm cannot be empty");
			return "update-firm";
		}

		firmFromDb.setAddresLine1(firm.getAddresLine1());
		firmFromDb.setAddressLine2(firm.getAddressLine2());
		firmFromDb.setCity(firm.getCity());
		firmFromDb.setState(firm.getState());
		firmFromDb.setGstNumber(firm.getGstNumber());
		firmFromDb.setTinNumber(firm.getTinNumber());
		firmFromDb.setContactNumber(firm.getContactNumber());
		firmFromDb.setEmail(firm.getEmail());
		firmFromDb.setShortName(firm.getShortName());

		dashboardService.addOrUpdateFirm(firmFromDb);
		model.addAttribute("Update_Msg", true);
		return "redirect:/dashboard/firms";
	}

	@RequestMapping(value = "/getFilterRecord", method = RequestMethod.GET)
	public String getFilterRecord(Model model) {
		List<ProductPurchaseInvoice> ppi = new ArrayList<ProductPurchaseInvoice>();
		List<Object[]> objects = productPurchaseInvoiceService.getAllStockProductPurchaseInvoice();
		List<FilterStockReport> filterStockReportList = new ArrayList<>();

		if (objects != null && !objects.isEmpty()) {
			filterStockReportList = transformProductPurchaseinvoice(objects);
		}
		model.addAttribute("finalStock", filterStockReportList);

		List<Firms> firms = dashboardService.findallFirms();
		Set<String> firmName = new HashSet<>();
		for (Firms firm : firms) {
			firmName.add(firm.getName());
		}
		model.addAttribute("firmName", firmName);
		List<Product> products = productServices.getAllProducts();
		Set<String> brandName = new HashSet<>();
		Set<String> modelnumber = new HashSet<>();
		Set<String> size = new HashSet<>();
		Set<String> star = new HashSet<>();
		for (Product product : products) {
			brandName.add(product.getBrand());
			modelnumber.add(product.getModelNumber());
			size.add(product.getSize());
			star.add(product.getStar());

		}
		model.addAttribute("brandName", brandName);
		model.addAttribute("modelnumber", modelnumber);
		model.addAttribute("size", size);
		model.addAttribute("star", star);
		return "get-filter-record";
	}

	@RequestMapping(value = "/getStockByFilterRecord", method = RequestMethod.GET)
	public String getStockByFilterRecord(Model model, @RequestParam(value = "firmName") String firmName,
			@RequestParam(value = "unit") String unit, @RequestParam(value = "brandName") String brandName,
			@RequestParam(value = "modelnumber") String modelnumber, @RequestParam(value = "size") String size,
			@RequestParam(value = "starName") String starName, @RequestParam(value = "location") String location) {

		List<Firms> firms = dashboardService.findallFirms();
		Set<String> firmsName = new HashSet<>();
		for (Firms firm : firms) {
			firmsName.add(firm.getName());
		}
		model.addAttribute("firmName", firmsName);
		List<Product> products = productServices.getAllProducts();
		Set<String> brandsName = new HashSet<>();
		Set<String> modelnumbers = new HashSet<>();
		Set<String> sizes = new HashSet<>();
		Set<String> stars = new HashSet<>();
		for (Product product : products) {
			brandsName.add(product.getBrand());
			modelnumbers.add(product.getModelNumber());
			sizes.add(product.getSize());
			stars.add(product.getStar());

		}
		stars.removeAll(Arrays.asList(null, ""));
		brandsName.removeAll(Arrays.asList(null, ""));
		modelnumbers.removeAll(Arrays.asList(null, ""));
		sizes.removeAll(Arrays.asList(null, ""));
		model.addAttribute("brandName", brandsName);
		model.addAttribute("modelnumber", modelnumbers);
		model.addAttribute("size", sizes);
		model.addAttribute("star", stars);

		List<Object[]> objects = productPurchaseInvoiceService.getRecordByFilter(firmName, unit, brandName, modelnumber,
				size, starName, location);
		if (objects == null || objects.size() == 0 || objects.isEmpty()) {
			setError(model, "No Record Found for corressponding criteria");
			return "get-filter-record";
		}
		List<FilterStockReport> finalStockReportList = transformProductPurchaseinvoice(objects);
		model.addAttribute("finalStock", finalStockReportList);

		return "get-filter-record";
	}

	private List<FilterStockReport> transformProductPurchaseinvoice(List<Object[]> objects) {
		List<FilterStockReport> filterList = new ArrayList<>();
		for (Object[] obj : objects) {
			FilterStockReport filterStockReport = new FilterStockReport();
			filterStockReport.setBrandName(getSafeString(obj[0]));
			filterStockReport.setModelNumber(getSafeString(obj[1]));
			filterStockReport.setSize(getSafeString(obj[2]));
			filterStockReport.setStar(getSafeString(obj[3]));
			filterStockReport.setFirmName(getSafeString(obj[4]));
			filterStockReport.setProductId(getSafeLong(obj[5]));
			filterStockReport.setQuantity(getSafeInteger(obj[6]));
			filterStockReport.setUnitPrice(getSafeDouble(obj[7]));
			filterList.add(filterStockReport);
		}
		return filterList;
	}

	@RequestMapping(value = "/downloadFilterStockReport", method = RequestMethod.GET)
	public String downloadFilterStockReport(HttpServletResponse response) {
		return null;
	}
}
