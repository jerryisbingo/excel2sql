package com.winton.exceltosql.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.winton.exceltosql.common.R;
import com.winton.exceltosql.common.exception.WrongFileTypeException;
import com.winton.exceltosql.core.common.ExcelParserProxy;
import com.winton.exceltosql.core.sql.MySqlStr;
import com.xiaoleilu.hutool.poi.excel.ExcelReader;
import com.xiaoleilu.hutool.poi.excel.ExcelUtil;
import com.xiaoleilu.hutool.util.RandomUtil;
import com.xiaoleilu.hutool.util.StrUtil;

/**
 * excel->sql service
 * @author winton
 * @since 2017-11-16
 */
@Service
public class ExcelToSqlService {
	
	public static final String SQLFILEPATH_SESSION_KEY = "sqlFilePath";
	
	@Value("${sql.file.root.path}")
	public String rootPath;
	
	@Value("${excel.file.suffix}")
	String excelFilneSuffix;
	
	@Autowired
	ExcelParserProxy mySqlStrProxy;
	
	@Autowired
	StringRedisTemplate redis;
	
	
	public R readDataFromExcel(MultipartFile file,HttpServletRequest request) throws IOException, WrongFileTypeException {
		String rate = redis.opsForValue().get(Optional.ofNullable(request.getSession().getAttribute(SQLFILEPATH_SESSION_KEY)).orElse(""));
		if (rate != null && (!StrUtil.equals(rate, "1")) && (!StrUtil.equals(rate, "0"))) {
			return R.error("您有正在处理的文件。");
		}
		
		String sqlName = rootPath + RandomUtil.randomUUID() + ".txt";
		request.getSession().setAttribute(SQLFILEPATH_SESSION_KEY, sqlName);
		
		String fileName = file.getOriginalFilename();
		String tableName = StrUtil.subPre(fileName, StrUtil.indexOf(fileName, '.'));
		if (!excelFilneSuffix.contains(StringUtils.substringAfter(fileName, "."))) {
			throw new WrongFileTypeException();
		}
		
		ExcelReader reader = ExcelUtil.getReader(file.getInputStream());
		List<List<Object>> readAll = reader.read();
		mySqlStrProxy.create(tableName, readAll, sqlName);
		
		return R.ok("上传生成成功，请点击下载");
	}
	
}
