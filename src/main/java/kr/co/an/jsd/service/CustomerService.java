package kr.co.an.jsd.service;

import javax.servlet.http.HttpServletRequest;

import kr.co.an.jsd.dto.CustomerDTO;
import kr.co.an.jsd.dto.PageRequestDTO;
import kr.co.an.jsd.dto.PageResponseDTO;

 
public interface CustomerService {
	//등록
    Long register(CustomerDTO customerDTO, HttpServletRequest request);    
    //조회
    CustomerDTO readOne(Long cno);    
    //수정
    void modify(CustomerDTO customerDTO, HttpServletRequest request);    
    //삭제
    void remove(Long cno);    
    //검색 / 리스트처리
    PageResponseDTO<CustomerDTO> list(PageRequestDTO pageRequestDTO);
    
}
