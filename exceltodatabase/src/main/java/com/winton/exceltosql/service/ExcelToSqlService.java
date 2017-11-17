package com.winton.exceltosql.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.winton.exceltosql.common.exception.WrongFileTypeException;
import com.winton.exceltosql.core.common.ExcelParserProxy;
import com.winton.exceltosql.core.sql.MySqlStr;
import com.xiaoleilu.hutool.poi.excel.ExcelReader;
import com.xiaoleilu.hutool.poi.excel.ExcelUtil;
import com.xiaoleilu.hutool.util.StrUtil;

/**
 * excel->sql service
 * @author winton
 * @since 2017-11-16
 */
@Service
public class ExcelToSqlService {
	
	@Autowired
	ExcelParserProxy mySqlStrProxy;
	
	@Value("${excel.file.suffix}")
	String excelFilneSuffix;
	
	public void readDataFromExcel(MultipartFile file, String sqlName) throws IOException, WrongFileTypeException {
		String fileName = file.getOriginalFilename();
		String tableName = StrUtil.subPre(fileName, StrUtil.indexOf(fileName, '.'));
		if (!excelFilneSuffix.contains(StringUtils.substringAfter(fileName, "."))) {
			throw new WrongFileTypeException();
		}
		
		ExcelReader reader = ExcelUtil.getReader(file.getInputStream());
		List<List<Object>> readAll = reader.read();
		mySqlStrProxy.create(tableName, readAll, sqlName);
	}
	
}
