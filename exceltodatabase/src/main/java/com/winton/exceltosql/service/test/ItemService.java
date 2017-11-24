package com.winton.exceltosql.service.test;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.winton.exceltosql.model.entity.test.Item;
import com.winton.exceltosql.model.mapper.test.ItemMapper;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Yanghu
 * @since 2017-11-24
 */
@Service
public class ItemService extends ServiceImpl<ItemMapper, Item>  {
	
}
