package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HellowController {

	@RequestMapping("/hello2")
	public String index() {
		return "hello2";
	}
}