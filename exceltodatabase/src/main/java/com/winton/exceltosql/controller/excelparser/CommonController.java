package com.winton.exceltosql.controller.excelparser;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.winton.exceltosql.common.R;
import com.winton.exceltosql.service.ExcelToSqlService;

/**
 * 公用controller
 * @author winton
 * @since 2017-11-16
 */
@Controller
@RequestMapping(value="/common")
public class CommonController {
	
	@Autowired
	StringRedisTemplate redis;
	
	/**
	 * 获取业务逻辑执行进度
	 * @param key redis对应的名
	 * @return 返回1以内的小数作为进度
	 * @author winton
	 * @since 2017年11月17日
	 */
	@GetMapping(value="/get-rate")
	@ResponseBody
	public R getRate(HttpServletRequest request) {
		String key = String.valueOf(request.getSession().getAttribute(ExcelToSqlService.SQLFILEPATH_SESSION_KEY));
		String rate = Optional.ofNullable(redis.opsForValue().get(key)).orElse("0");
		return R.ok(rate,"获取进度成功");
	}
	
}
