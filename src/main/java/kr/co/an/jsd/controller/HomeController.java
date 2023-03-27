package kr.co.an.jsd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Controller
@Log4j2
@RequiredArgsConstructor
public class HomeController {
	
	@GetMapping({"/","/home"})
	public String home() {
		
		return "/home";
	}
	
}
