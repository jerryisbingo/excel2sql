package com.winton.exceltosql.common;

/**
 * 结果集
 * @author winton
 *
 */
public class R {
	
	public R (int code, Object data, String msg) {
		this.code = code;
		this.data = data;
		this.msg = msg;
	}
	
	private int code;
	
	private Object data;
	
	private String msg;
	
	/**
	 * 通用R
	 */
	public static R result(int code, Object data, String msg) {
		return new R(code, data, msg);
	}

	/**
	 * 只包含code的成功返回
	 */
	public static R ok() {
		return new R(1,null,"");
	}
	
	/**
	 * 包含数据和消息的成功返回
	 */
	public static R ok(Object data,String msg) {
		return new R(1,data,msg);
	}
	
	/**
	 * 只包含消息的成功返回
	 */
	public static R ok(String msg) {
		return new R(1,null,msg);
	}
	
	/**
	 * 只包含code的失败返回
	 */
	public static R error() {
		return new R(-1,null,"");
	}
	
	/**
	 * 包含数据和消息的失败返回
	 */
	public static R error(Object data,String msg) {
		return new R(-1,data,msg);
	}
	
	/**
	 * 只包含消息的失败返回
	 */
	public static R error(String msg) {
		return new R(-1,null,msg);
	}
	
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
