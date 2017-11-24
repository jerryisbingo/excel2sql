package com.winton.exceltosql.controller.test;


import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.winton.exceltosql.common.R;
import com.winton.exceltosql.model.entity.test.Item;
import com.winton.exceltosql.model.mapper.test.ItemMapper;
import com.xiaoleilu.hutool.json.JSONUtil;

/**
 * <p>
 *  测试用例
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
	
	/**
	 * 普通调用
	 * @return
	 * @author winton
	 * @since 2017年11月24日
	 */
	@GetMapping
	@ResponseBody
	public R test() {
		return R.ok(dao.selectById(1),"");
	}
	
	/**
	 * 分页插件测试
	 * @return
	 * @author winton
	 * @since 2017年11月24日
	 */
	@GetMapping(value={"page"})
	@ResponseBody
	public R page() {
		PageInfo<Item> doSelectPageInfo = PageHelper.startPage(1, 2).doSelectPageInfo(() -> dao.selectList(null));
		return R.ok(doSelectPageInfo,"");
	}
	
	/**
	 * mapper自定义
	 * @return
	 * @author winton
	 * @since 2017年11月24日
	 */
	@GetMapping(value={"xml"})
	@ResponseBody
	public R mapperxml() {
		return R.ok(JSONUtil.toJsonStr(dao.testmapper()),"");
	}
	
}
