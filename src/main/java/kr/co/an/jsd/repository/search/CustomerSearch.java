package kr.co.an.jsd.repository.search;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import kr.co.an.jsd.domain.Customer;


public interface CustomerSearch {
    Page<Customer> csearchAll(String[] types, String keyword, Pageable pageable);
}
