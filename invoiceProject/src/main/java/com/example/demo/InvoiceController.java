package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
//import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * The Class InvoiceController.
 */
@RestController
public class InvoiceController {

	@Autowired
    JdbcTemplate jdbcTemplate;

	/**
	 * Home.
	 *
	 * @param client_no
	 *            the client no
	 * @param invoice_start_date
	 *            the invoice start date
	 * @param invoice_end_date
	 *            the invoice end date
	 * @param create_user
	 *            the create user
	 * @param invoice_create_date
	 *            the invoice create date
	 * @return the response
	 */
	@PostMapping("/invoice")
	// @ResponseStatus(HttpStatus.BAD_REQUEST)
	// public List<RequestPostInvoice> top(@Validated @RequestParam("client_no")
	// String client_no,
	public Response setResultvalue(@Validated @RequestParam("client_no") String client_no,
			@Validated @RequestParam("invoice_start_date") String invoice_start_date,
			@Validated @RequestParam("invoice_end_date") String invoice_end_date,
			@Validated @RequestParam("create_user") String create_user,
			@Validated @RequestParam("invoice_create_date") String invoice_create_date) {
		// RequestPostInvoice rpi = new RequestPostInvoice();
		// List<RequestPostInvoice> list = new ArrayList<RequestPostInvoice>();

		RequestPostInvoice rpi = new RequestPostInvoice();
		ErrorResponse er = new ErrorResponse();

		if (checkItem(client_no, er)) {
			rpi.setClientNo(client_no);
		}
		rpi.setInvoiceStartDate(invoice_start_date);
		rpi.setInvoiceEndDate(invoice_end_date);
		rpi.setCreateUser(create_user);
		rpi.setInvoiceCreateDate(invoice_create_date);

		// list.add(rpi);
		// returnResponse(rpi, null);

		Response response = new Response();
		response.setResult(rpi);
		response.setErrors(er);
		return response;
	}

	@GetMapping("/invoice")
	public List<InvoiceDao> getInvoice() {

		RowMapper<InvoiceDao> mapper = new BeanPropertyRowMapper<>(InvoiceDao.class);

		List<InvoiceDao> rs = jdbcTemplate.query("select invoice_no from invoice", mapper);

		return rs;
	}

	/** The repository. */
	// @Autowired
	// private InvoiceService invoiceService;

	/**
	 * RequestPostInvoice.
	 *
	 * @param model
	 *            the model
	 * @param id
	 *            the id
	 * @return the list
	 */
	// @GetMapping("/invoice/{id}")
	// public List<InvoiceResult> get(Model model, @PathVariable("id") int id) {
	//
	//// List<RequestPostInvoice> requestPostInvoice = new
	// ArrayList<RequestPostInvoice>();
	//// requestPostInvoice.add(new RequestPostInvoice());
	////
	//// return requestPostInvoice;
	// List<InvoiceResult> rp = invoiceService.findById(id);
	// return rp;
	// }

	/**
	 * Check item.
	 *
	 * @param checkItem
	 *            the check item
	 * @param errorResponse
	 *            the error response
	 * @return true, if successful
	 */
	public boolean checkItem(String checkItem, ErrorResponse errorResponse) {
		// ErrorResponse errorResponse = new ErrorResponse();
		// List<ErrorResponse> errorList = new ArrayList<ErrorResponse>();

		if (checkItem.equals("")) {
			errorResponse.setErrorCode("40001");
			errorResponse.setErrorMessage("顧客管理番号は必須入力です。");
			errorResponse.setErrorDetail("client_no");
		} else {
			if (isNum(checkItem)) {
				return true;
			} else if (!isNum(checkItem)) {
				errorResponse.setErrorCode("40002");
				errorResponse.setErrorMessage("顧客管理番号のフォーマットが不正です。");
				errorResponse.setErrorDetail("client_no");
			} else {
				errorResponse.setErrorCode("50001");
				errorResponse.setErrorMessage("予期しないエラーが発生しました。");
				errorResponse.setErrorDetail("client_no");
			}
		}
		return false;
	}

	/**
	 * Checks if is num.
	 *
	 * @param number
	 *            the number
	 * @return true, if is num
	 */
	static boolean isNum(String number) {
		try {
			Integer.parseInt(number);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}