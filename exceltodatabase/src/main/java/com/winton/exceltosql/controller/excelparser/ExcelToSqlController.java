package com.winton.exceltosql.controller.excelparser;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
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

/**
 * excel->sql controller
 * @author winton
 * @since 
 */
@Controller
@RequestMapping(value = "/excel")
public class ExcelToSqlController {
	
	@Autowired
	protected ExcelToSqlService excelToSqlService;

	@PostMapping(value = "/upload-to-sql")
	@ResponseBody
	public R uploadToSql(@RequestParam(required=true) MultipartFile file, HttpServletRequest request) throws IOException, WrongFileTypeException {
		return excelToSqlService.readDataFromExcel(file, request);
	}

	@GetMapping(value="/has-file")
	@ResponseBody
	public R hasFile(HttpServletRequest request) {
		File file = new File(String.valueOf(request.getSession().getAttribute(ExcelToSqlService.SQLFILEPATH_SESSION_KEY)));
		return Optional.ofNullable(file).isPresent() ? R.ok() : R.error("文件不存在"); 
	}
	
	@GetMapping(value="/download-sql-txt")
	public ResponseEntity<byte[]> download(HttpServletRequest request) throws IOException {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		headers.setContentDispositionFormData("attachment", "sql.txt");
		
		File file = new File(String.valueOf(request.getSession().getAttribute(ExcelToSqlService.SQLFILEPATH_SESSION_KEY)));
		return new ResponseEntity<byte[]>(FileUtil.readBytes(file), headers, HttpStatus.CREATED);
	}

}
