package com.winton.exceltosql.core.springboot;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.winton.exceltosql.core.filter.Filter01_SitemeshFilter;

/**
 * 过滤器注册配置类
 * @author winton
 * @since 2017-11-17
 */
@Configuration
public class MyWebAppConfigurer extends WebMvcConfigurerAdapter {
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
	}

	@Bean
	public FilterRegistrationBean siteMeshFilter() {
		FilterRegistrationBean fitler = new FilterRegistrationBean();
		fitler.setFilter(new Filter01_SitemeshFilter());
		return fitler;
	}

}