package kr.co.an.jsd.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import kr.co.an.jsd.domain.Customer;
import kr.co.an.jsd.dto.CustomerDTO;
import kr.co.an.jsd.dto.PageRequestDTO;
import kr.co.an.jsd.dto.PageResponseDTO;
import kr.co.an.jsd.repository.CustomerRepository;
 
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class CustomerServiceImpl implements CustomerService{
	
	   private final ModelMapper modelMapper;
	   private final CustomerRepository customerRepository;
	   
	//등록
	@Override
	public Long register(CustomerDTO customerDTO, HttpServletRequest request) {
	 	 
       Customer customer = modelMapper.map(customerDTO, Customer.class);

       Long cno = customerRepository.save(customer).getCno();

       return cno;
       
	}
	
	//조회
	@Override
	public CustomerDTO readOne(Long cno) {
		Optional<Customer> result = customerRepository.findById(cno);
		Customer customer = result.orElseThrow();
		CustomerDTO customerDTO = modelMapper.map(customer, CustomerDTO.class);
		return customerDTO;
	}
	
	//수정
	@Override
	public void modify(CustomerDTO customerDTO, HttpServletRequest request) {
		Optional<Customer> result = customerRepository.findById(customerDTO.getCno());
		
		Customer customer = result.orElseThrow();
		
		customer.change(customerDTO.getTitle(), customerDTO.getContent());
		
		customerRepository.save(customer);
	}
	
	//삭제
	@Override
	public void remove(Long cno) {
		customerRepository.deleteById(cno);	
	}
	
	//리스트 / 검색 / 페이징처리
	@Override
	public PageResponseDTO<CustomerDTO> list(PageRequestDTO pageRequestDTO) {
		 
		String[] types = pageRequestDTO.getTypes(); // type가져와 담기
		String keyword = pageRequestDTO.getKeyword(); // keyword가져와담기
		Pageable pageable = pageRequestDTO.getPageable("cno"); // cno으로 페이징처리
		
		Page<Customer> result = customerRepository.csearchAll(types, keyword, pageable);
		
		List<CustomerDTO> dtoList = result.getContent().stream().map(customer ->
		modelMapper.map(customer, CustomerDTO.class)).collect(Collectors.toList());
				
		
		return PageResponseDTO.<CustomerDTO>withAll()
				.pageRequestDTO(pageRequestDTO)
				.dtoList(dtoList)
				.total((int)result.getTotalElements())
				.build();
		
	}

}
