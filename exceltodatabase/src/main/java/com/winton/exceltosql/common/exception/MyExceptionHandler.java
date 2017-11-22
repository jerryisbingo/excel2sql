package com.winton.exceltosql.common.exception;

import java.io.FileNotFoundException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import com.winton.exceltosql.common.R;

/**
 * @author winton
 * @since 2017-11-16
 */
@ControllerAdvice
public class MyExceptionHandler {

	@ExceptionHandler(value=Exception.class)
	@ResponseBody
	public R handle(HttpServletRequest req, Exception e) {
		e.printStackTrace();
		if (e instanceof WrongFileTypeException) {
			return R.error("文件类型不支持");
		} else if(e instanceof MissingServletRequestPartException) {
			return R.error("参数不能为空");
		} else if(e instanceof FileNotFoundException) {
			return R.error("文件不存在");
		}
		return R.error("系统异常");
	}
	
}