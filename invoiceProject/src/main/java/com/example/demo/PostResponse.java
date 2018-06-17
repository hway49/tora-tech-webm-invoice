package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

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

    /** The errors. */
    @JsonProperty("errors")
    private List<ErrorResponse> invoiceErrorList = new ArrayList<ErrorResponse>();

    /** The result. */
    public RequestPostInvoice result;

}
