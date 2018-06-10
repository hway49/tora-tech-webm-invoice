package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// TODO: 自動生成された Javadoc
/**
 * The Class Hoge.
 */

/* (非 Javadoc)
 * @see java.lang.Object#toString()
 */
@Data
@NoArgsConstructor

/**
 * Instantiates a new response.
 *
 * @param error the error
 * @param result the result
 */
@AllArgsConstructor
public class Response {

    /** The error. */
    public ErrorResponse errors;

    /** The result. */
    public InvoiceResult result;
}
