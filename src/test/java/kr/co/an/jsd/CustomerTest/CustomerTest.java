package kr.co.an.jsd.CustomerTest;

import java.util.Optional;
import java.util.stream.IntStream;

import javax.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import kr.co.an.jsd.domain.Customer;
import kr.co.an.jsd.dto.CustomerDTO;
import kr.co.an.jsd.repository.CustomerRepository;
import kr.co.an.jsd.service.CustomerService;
import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class CustomerTest {

	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private CustomerService customerService;
	
	//등록테스트
	@Test
	public void register() {
		IntStream.rangeClosed(1, 240).forEach(i ->{
			Customer customer = Customer.builder()
					.title("title..."+i)
					.content("content..."+i)
					.writer("writer..."+i)
					.cstate(1)
					.build();
			Customer result = customerRepository.save(customer);
			log.info("cno : " + result.getCno());
		});
	}
	
	//조회 테스트
	@Test
	public void selectTest() {
		long cno = 1L; //select번호
		Optional<Customer> result = customerRepository.findById(cno);
		Customer customer = result.orElseThrow();
		log.info(customer);
	}
	
	//수정테스트
	@Test
	public void update() {
		long cno=1L;
		Optional<Customer> result = customerRepository.findById(cno);
		
		Customer customer = result.orElseThrow();
		customer.change("수정1", "수정1");
		customerRepository.save(customer);
	}
	
	//삭제테스트
	@Test
	public void deleteTest() {
		long cno = 1L;
		customerRepository.deleteById(cno);
	}
	
	//검색기능 테스트
	@Test
	public void testSearchAll() {
		String[] types = {"t","c"}; // title / content
		String keyword = "2";
		Pageable pageable = PageRequest.of(0, 10,Sort.by("cno").descending());
		Page<Customer> result = customerRepository.csearchAll(types, keyword, pageable);
		
		//총 페이지
		log.info(result.getTotalPages());
		//페이지 사이즈
		log.info(result.getSize());
		//페이지 넘버
		log.info(result.getNumber());
		//prev next
		log.info(result.hasPrevious()+" : "+result.hasNext());
		
		result.getContent().forEach(customer -> log.info(customer));
	}
	
	@Test
	public void register2(HttpServletRequest request) {
		log.info(customerService.getClass().getName());
		CustomerDTO customerDTO = CustomerDTO.builder()
				.cno(100L)
				.title("title")
				.content("123")
				.writer("writer")
				.cstate(1)
				.build();
		long cno = customerService.register(customerDTO, request);
				log.info("cno :  "+ cno);
		}
	
}
