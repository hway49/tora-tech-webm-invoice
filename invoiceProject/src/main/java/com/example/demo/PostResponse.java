package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

/**
 * Instantiates a new response.
 *
 * @param error the error
 * @param result the result
 */
@AllArgsConstructor
public class PostResponse {

    /** The error. */
    public ErrorResponse errors;

    /** The result. */
    public RequestPostInvoice result;
}
