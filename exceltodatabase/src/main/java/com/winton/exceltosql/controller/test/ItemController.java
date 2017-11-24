package com.winton.exceltosql.controller.test;


import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.winton.exceltosql.common.R;
import com.winton.exceltosql.model.entity.test.Item;
import com.winton.exceltosql.model.mapper.test.ItemMapper;
import com.xiaoleilu.hutool.json.JSONUtil;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Yanghu
 * @since 2017-11-24
 */
@Controller
@RequestMapping("/test/item")
public class ItemController {
	
	@Autowired
	ItemMapper dao;
	
	@GetMapping
	@ResponseBody
	public R test() {
		return R.ok(dao.selectById(1),"");
	}
	
	@GetMapping(value={"page"})
	@ResponseBody
	public R page() {
		return R.ok(dao.selectPage(RowBounds.DEFAULT, null),"");
	}
	
	@GetMapping(value={"xml"})
	@ResponseBody
	public R mapperxml() {
		return R.ok(JSONUtil.toJsonStr(dao.testmapper()),"");
	}
	
}
