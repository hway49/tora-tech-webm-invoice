package com.example.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository<RequestPostInvoice, Integer> {
  public List<RequestPostInvoice> findById(int invoiceNo);
}