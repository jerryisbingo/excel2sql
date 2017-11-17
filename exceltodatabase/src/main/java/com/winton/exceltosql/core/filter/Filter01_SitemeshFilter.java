package com.winton.exceltosql.core.filter;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;

/**
 * sitemesh过滤器配置
 * @author winton
 * @since 2017年11月16日
 */
public class Filter01_SitemeshFilter extends ConfigurableSiteMeshFilter {
	
	@Override
    protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
        builder.setIncludeErrorPages(true).addDecoratorPath("/*", "/sitemesh-base.html");
    }
}
