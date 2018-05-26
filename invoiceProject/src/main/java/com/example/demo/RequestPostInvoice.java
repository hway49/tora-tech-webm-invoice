package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The Class RequestPostInvoice.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
//@Entity
//@Table(name = "invoice")
public class RequestPostInvoice {

//	@Id
//	@GeneratedValue
	private Integer invoiceNo;

	/** The client no. */
	private String clientNo;

	/** The invoice start date. */
	private String invoiceStartDate;

	/** The invoice end date. */
	private String invoiceEndDate;

	/** The create user. */
	private String createUser;

	/** The invoice create date. */
	private String invoiceCreateDate;

}
