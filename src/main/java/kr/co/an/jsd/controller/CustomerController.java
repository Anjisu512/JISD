package kr.co.an.jsd.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.an.jsd.domain.Customer;
import kr.co.an.jsd.dto.CustomerDTO;
import kr.co.an.jsd.dto.PageRequestDTO;
import kr.co.an.jsd.dto.PageResponseDTO;
import kr.co.an.jsd.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/customer")
@Log4j2
@RequiredArgsConstructor
public class CustomerController {
	
	private final CustomerService customerService;
	
	//등록
	@GetMapping("/register")
	public void registerGET() {
		
	}
	@PostMapping("/register")
	public String registerPOST(@Valid CustomerDTO customerDTO, BindingResult bindingResult,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {
		
		log.info("============customer POST 진입");
		if(bindingResult.hasErrors()) {//오류발생할경우
			log.info("==============customer POST error 발생");
			redirectAttributes.addFlashAttribute("RegisterPostErrors", bindingResult.getAllErrors());
			
			return "redirect:/customer/register";
		}
		
		log.info(customerDTO);
		long cno = customerService.register(customerDTO, request);
		redirectAttributes.addFlashAttribute("CustomerRegister", cno);
				return "redirect:/customer/list";
	}
	
	
	//조회
	//수정
	//삭제
	//검색&리스트
	@GetMapping("/list")
	public void list(PageRequestDTO pageRequestDTO, Model model) {
		
		PageResponseDTO<CustomerDTO> responseDTO = customerService.list(pageRequestDTO);
		
		log.info(responseDTO);
		
		model.addAttribute("dto",responseDTO);
		//pageRequestDTO와 pageResponseDTO객체가 view로 전달됨
	}
	
}
