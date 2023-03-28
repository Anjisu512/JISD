package kr.co.an.jsd.repository.search;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;

import kr.co.an.jsd.domain.Customer;
import kr.co.an.jsd.domain.QCustomer;

public class CustomerSearchImpl extends QuerydslRepositorySupport implements CustomerSearch{
	
	public CustomerSearchImpl() {
		super(Customer.class);
	}

	@Override
	public Page<Customer> csearchAll(String[] types, String keyword, Pageable pageable) {
		 	
			QCustomer customer = QCustomer.customer;
			JPQLQuery<Customer> query = from(customer);
			
			if( (types != null && types.length > 0) && keyword != null ){ //검색 조건과 키워드가 있다면
				
				BooleanBuilder booleanBuilder = new BooleanBuilder(); // (

		        for(String type: types){
		        	switch (type) {
					case "t": 
						booleanBuilder.or(customer.title.contains(keyword));
						break;
					case "c":
						booleanBuilder.or(customer.content.contains(keyword));
						break;
					case "w":
						booleanBuilder.or(customer.writer.contains(keyword));
						break;
		               }
	            }//end for
	            query.where(booleanBuilder);
	        }//end if
			
			//bno > 0
	        query.where(customer.cno.gt(0L));
	        
	        //paging
	        this.getQuerydsl().applyPagination(pageable, query);
	        
	        List<Customer> list = query.fetch();
	        long count = query.fetchCount();
	        return new PageImpl<>(list, pageable, count);
	}
}
