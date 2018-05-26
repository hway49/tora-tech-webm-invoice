package com.example.demo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
//@Entity
//@Table(name = "invoice")
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceResult{

    /** The invoice no. */
    private String invoiceNo;

    /** The client name. */
    private String clientName;

    /** The client address. */
    private String clientAddress;

    /** The client tel. */
    private String clientTel;

    /** The client fax. */
    private String clientFax;

    /** The client charge name. */
    private String clientChargeName;

    /** The invoice status. */
    private String invoiceStatus;

    /** The invoice create date. */
    private String invoiceCreateDate;

    /** The invoice title. */
    private String invoiceTitle;

    /** The invoice amt. */
    private String invoiceAmt;

    /** The tax amt. */
    private String taxAmt;

    /** The invoice start date. */
    private String invoiceStartDate;

    /** The invoice end date. */
    private String invoiceEndDate;

    /** The invoice note. */
    private String invoiceNote;

    /** The create user. */
    private String createUser;

    /** The create datetime. */
    private String createDatetime;

    /** The update user. */
    private String updateUser;

    /** The update datetime. */
    private String updateDatetime;

}