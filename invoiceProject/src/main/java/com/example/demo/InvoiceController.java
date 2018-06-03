/*
 *
 */
package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * The Class InvoiceController.
 */
@CrossOrigin
@RestController
public class InvoiceController {

	@Autowired
	JdbcTemplate jdbcTemplate;

	/**
	 * Home.
	 *
	 * @param clientNo
	 *            the client no
	 * @param invoiceStartDate
	 *            the invoice start date
	 * @param invoiceEndDate
	 *            the invoice end date
	 * @param createUser
	 *            the create user
	 * @param invoiceCreateDate
	 *            the invoice create date
	 * @return the response
	 */
	@PostMapping("/invoice")
	// @ResponseStatus(HttpStatus.BAD_REQUEST)
	public PostResponse Regist(@Validated @RequestParam("client_no") String clientNo,
			@Validated @RequestParam("invoice_start_date") String invoiceStartDate,
			@Validated @RequestParam("invoice_end_date") String invoiceEndDate,
			@Validated @RequestParam("create_user") String createUser,
			@Validated @RequestParam("invoice_create_date") String invoiceCreateDate) {

		RequestPostInvoice rpi = new RequestPostInvoice();
		ErrorResponse er = new ErrorResponse();

		// 実装中～
		if (checkItem(clientNo, er)) {
			String sql = "INSERT INTO invoice("
					+ "client_no,"
					+ "invoice_status,"
					+ "invoice_create_date,"
					+ "invoice_title,"
					+ "invoice_amt,"
					+ "invoice_start_date,"
					+ "invoice_end_date,"
					+ "invoice_note,"
					+ "create_user,"
//					+ "create_datetime,"
					+ "update_user,"
//					+ "update_datetime,"
					+ "del_flg"
					+ ")VALUES("
					+ "'" + clientNo + "',"
					+ "'10',"
					+ "'" + invoiceCreateDate + "',"
					+ "'請求書ですー',"
					+ "100,"
					+ "'" + invoiceStartDate + "',"
					+ "'" + invoiceEndDate + "',"
					+ "'備考備考備考',"
					+ "'" + createUser + "',"
//					+ "create_datetime,"
					+ "'hase',"
//					+ "update_datetime,"
					+ "0"
					+ ")"
					+ ";";

			RowMapper<InvoiceDao> mapper = new BeanPropertyRowMapper<>(InvoiceDao.class);
			int regist = jdbcTemplate.update(sql);

			//登録後にinvoiceNoを取得
			RowMapper<InvoiceDao> responseMapper = new BeanPropertyRowMapper<>(InvoiceDao.class);
			String responseSql = "select invoice_no from invoice where update_datetime=(select max(update_datetime) from invoice);";

			List<InvoiceDao> rs2 = jdbcTemplate.query(responseSql, responseMapper);
			InvoiceDao resonseInvoice = rs2.get(0);
			rpi.setInvoiceNo(resonseInvoice.getInvoiceNo());
		}

		PostResponse postResponse = new PostResponse();
		postResponse.setResult(rpi);
		postResponse.setErrors(er);
		return postResponse;
	}

	@GetMapping("/invoice/{id}")
	// public List<InvoiceDao> getInvoice(@PathVariable("id") String id) {
	public Response Serch(@PathVariable("id") String id) {

		InvoiceResult ir = new InvoiceResult();
		ErrorResponse er = new ErrorResponse();

		if (checkItem(id, er)) {
			ir.setInvoiceNo(id);

			RowMapper<InvoiceDao> mapper = new BeanPropertyRowMapper<>(InvoiceDao.class);
			String sql = "SELECT " + "invoice.invoice_no," + "invoice.client_no," + "client_charge_last_name," +
					"client_charge_first_name," +
					"client_name," + "client_address," + "client_tel," + "client_fax," + "invoice_status,"
					+ "invoice_create_date," + "invoice_title," + "invoice_amt," +
					// "tax_amt," +
					"invoice_start_date," + "invoice_end_date," + "invoice_note," + "create_user,"
					+ "invoice.create_datetime," + "update_user," + "invoice.update_datetime"
					+ " FROM invoice join client on invoice.client_no=client.client_no" + " where invoice_no=" + id
					+ ";";

			List<InvoiceDao> rs = jdbcTemplate.query(sql, mapper);
			InvoiceDao invoice = rs.get(0);

			ir.setClientName(invoice.getClientName());
			ir.setClientNo(invoice.getClientNo());
			ir.setClientAddress(invoice.getClientAddress());
			ir.setClientTel(invoice.getClientTel());
			ir.setClientFax(invoice.getClientFax());
			ir.setClientChargeName(invoice.getClientChargeLastName() + invoice.getClientChargeFirstName());
			ir.setInvoiceStatus(invoice.getInvoiceStatus());
			ir.setInvoiceCreateDate(invoice.getInvoiceCreateDate());
			ir.setInvoiceTitle(invoice.getInvoiceTitle());
			ir.setInvoiceAmt(invoice.getInvoiceAmt());
			ir.setTaxAmt(invoice.getTaxAmt());
			ir.setInvoiceStartDate(invoice.getInvoiceStartDate());
			ir.setInvoiceEndDate(invoice.getInvoiceEndDate());
			ir.setInvoiceNote(invoice.getInvoiceNote());
			ir.setCreateUser(invoice.getCreateUser());
			ir.setCreateDatetime(invoice.getCreateDatetime());
			ir.setUpdateUser(invoice.getUpdateUser());
			ir.setUpdateDatetime(invoice.getUpdateDatetime());
		}

		Response response = new Response();
		response.setResult(ir);
		response.setErrors(er);
		return response;
	}


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