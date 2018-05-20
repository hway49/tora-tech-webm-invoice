package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The Class Hoge.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hoge {

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
