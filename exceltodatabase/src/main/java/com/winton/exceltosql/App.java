package com.winton.exceltosql;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;

/**
 * @author winton
 * @since 2017-11-16
 */
@SpringBootApplication
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
	
	/**
	 * error page定义
	 * @author winton
	 * @since 2017年11月17日
	 */
	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer(){
	   return (container -> {
	        ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/error/404");
	        ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error");
	        container.addErrorPages(error404Page, error500Page);
	   });
	}
	
}
