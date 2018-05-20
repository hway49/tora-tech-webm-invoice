package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InvoiceController {

	/**
	 * Home.
	 */
	@PostMapping("/invoice")
	// @RequestMapping(value = "/invoice", method = { RequestMethod.POST }, produces
	// = MediaType.APPLICATION_JSON_VALUE)
	// public List<Hoge> top(@Validated @RequestParam("client_no") String client_no,
	public void setResultvalue(@Validated @RequestParam("client_no") String client_no,
			@Validated @RequestParam("invoice_start_date") String invoice_start_date,
			@Validated @RequestParam("invoice_end_date") String invoice_end_date,
			@Validated @RequestParam("create_user") String create_user,
			@Validated @RequestParam("invoice_create_date") String invoice_create_date) {
		// Hoge aa = new Hoge();
		// List<Hoge> list = new ArrayList<Hoge>();

		Hoge aa = new Hoge();
		if (checkItem(client_no)) {
			aa.setClientNo(client_no);
		}
		aa.setInvoiceStartDate(invoice_start_date);
		aa.setInvoiceEndDate(invoice_end_date);
		aa.setCreateUser(create_user);
		aa.setInvoiceCreateDate(invoice_create_date);

		// list.add(aa);
		returnResponse(aa, null);

		// Response response = new Response();
		// response.setResult(aa);
		// return response;
	}

	@GetMapping("/invoice")
	public List<Hoge> hoge() {

		List<Hoge> hoge = new ArrayList<Hoge>();
		hoge.add(new Hoge());

		return hoge;
	}

	public Response returnResponse(Hoge hoge, ErrorResponse er) {
		Response response = new Response();
		response.setResult(hoge);
		response.setErrors(er);

		return response;
	}

	public boolean checkItem(String checkItem) {
		ErrorResponse errorResponse = new ErrorResponse();
		// List<ErrorResponse> errorList = new ArrayList<ErrorResponse>();

		if (checkItem.equals("")) {
			errorResponse.setErrorCode("40001");
			errorResponse.setErrorMessage("顧客管理番号は必須入力です。");
			errorResponse.setErrorDetail("client_no");
			returnResponse(null, errorResponse);
		} else {
			if (isNum(checkItem)) {
				return true;
			} else if (!isNum(checkItem)) {
				errorResponse.setErrorCode("40002");
				errorResponse.setErrorMessage("顧客管理番号のフォーマットが不正です。");
				errorResponse.setErrorDetail("client_no");
				returnResponse(null, errorResponse);
			} else {
				errorResponse.setErrorCode("50001");
				errorResponse.setErrorMessage("予期しないエラーが発生しました。");
				errorResponse.setErrorDetail("client_no");
				returnResponse(null, errorResponse);
			}
		}
		return false;
	}

	static boolean isNum(String number) {
		try {
			Integer.parseInt(number);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}