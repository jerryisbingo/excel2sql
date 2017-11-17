package com.winton.exceltodatabase.nomal;

import org.junit.Test;

import com.xiaoleilu.hutool.poi.excel.ExcelReader;
import com.xiaoleilu.hutool.poi.excel.ExcelUtil;

/**
 * @author winton
 * @since 2017-11-16
 */
public class TestExcelUtil {
	
	@Test
	public void testUtil() {
		ExcelReader reader = ExcelUtil.getReader("C:/Users/Administrator/Desktop/test.xls");
		System.out.println(reader);
	}
	
}
