package com.winton.exceltosql.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.winton.exceltosql.common.R;
import com.winton.exceltosql.common.exception.WrongFileTypeException;
import com.winton.exceltosql.service.ExcelToSqlService;
import com.xiaoleilu.hutool.io.FileUtil;
import com.xiaoleilu.hutool.util.RandomUtil;

/**
 * excel->sql controller
 * @author winton
 * @since 
 */
@Controller
@RequestMapping(value = "/excel")
public class ExcelToSqlController {
	
	public static final String SQLFILEPATH_SESSION_KEY = "sqlFilePath";
	
	@Value("${sql.file.root.path}")
	public String rootPath;
	
	@Autowired
	StringRedisTemplate redis;
	
	@Autowired
	protected ExcelToSqlService excelToSqlService;

	@PostMapping(value = "/upload-to-sql")
	@ResponseBody
	public R uploadToSql(@RequestParam(required=true) MultipartFile file, HttpServletRequest request) throws IOException, WrongFileTypeException {
		Object obj = request.getSession().getAttribute(SQLFILEPATH_SESSION_KEY);
		if (obj != null) {
			return R.error("您有正在处理的文件。");
		}
		
		String sqlName = rootPath + RandomUtil.randomUUID() + ".txt";
		request.getSession().setAttribute(SQLFILEPATH_SESSION_KEY, sqlName);
		
		System.out.println(request.getSession().getAttribute(SQLFILEPATH_SESSION_KEY));
		
		excelToSqlService.readDataFromExcel(file,sqlName);
		request.getSession().removeAttribute(SQLFILEPATH_SESSION_KEY);
		return R.ok("上传生成成功，请点击下载");
	}

	@GetMapping(value="/download-sql-txt")
	public ResponseEntity<byte[]> download(HttpServletRequest request) throws IOException {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		headers.setContentDispositionFormData("attachment", "sql.txt");
		
		File file = new File(String.valueOf(request.getSession().getAttribute(SQLFILEPATH_SESSION_KEY)));
		return new ResponseEntity<byte[]>(FileUtil.readBytes(file), headers, HttpStatus.CREATED);
	}

}
