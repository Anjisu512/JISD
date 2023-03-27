package kr.co.an.jsd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kr.co.an.jsd.domain.Customer;
import kr.co.an.jsd.repository.search.CustomerSearch;

public interface CustomerRepository extends JpaRepository<Customer, Long>, CustomerSearch {
	
	@Query(value= "select now()", nativeQuery = true)
	String getTime();

}
