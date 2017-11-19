package com.winton.exceltosql.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * index controller
 * @author winton
 * @since 2017-11-16
 */
@Controller
@RequestMapping(value="/")
public class IndexController {
	
	@GetMapping
	public String index() {
		return "index";
	}
	
}
