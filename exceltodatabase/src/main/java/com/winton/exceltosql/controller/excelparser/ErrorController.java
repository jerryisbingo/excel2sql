package com.winton.exceltosql.controller.excelparser;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * error page controller
 * @author winton
 * @since 2017-11-16
 */
@Controller
@RequestMapping(value="/error")
public class ErrorController {
	
	@GetMapping(value="/404")
	public String error404() {
		return "error/404";
	}
	
	@GetMapping(value="/500")
	public String error500() {
		return "error/500";
	}
	
}
