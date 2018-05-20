package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

/**
 * Instantiates a new error response.
 *
 * @param errorCode the error code
 * @param errorMessage the error message
 * @param errorDetail the error detail
 */
@AllArgsConstructor
public class ErrorResponse {

	/** The error code. */
	private String errorCode;

	/** The error message. */
	private String errorMessage;

	/** The error detail. */
	private String errorDetail;

}
