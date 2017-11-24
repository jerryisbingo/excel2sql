package com.winton.exceltosql.model.mapper.test;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.winton.exceltosql.model.entity.test.Item;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author Yanghu
 * @since 2017-11-24
 */
public interface ItemMapper extends BaseMapper<Item> {
	
	List<Item> testmapper();
	
}