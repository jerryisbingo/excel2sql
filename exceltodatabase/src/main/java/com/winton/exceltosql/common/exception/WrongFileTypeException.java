package com.winton.exceltosql.common.exception;
/**
 * @author winton
 * @since 2017年11月17日
 */
public class WrongFileTypeException extends Exception{
	private static final long serialVersionUID = 1L;
	
	public WrongFileTypeException() {
		super("不支持该文件类型");
	}
	public WrongFileTypeException(String message) {
		super(message);
	}
	
}
