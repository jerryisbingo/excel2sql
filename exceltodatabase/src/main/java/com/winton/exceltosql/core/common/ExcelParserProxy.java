package com.winton.exceltosql.core.common;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import com.winton.exceltosql.core.sql.MySqlStr;
import com.xiaoleilu.hutool.log.Log;
import com.xiaoleilu.hutool.log.LogFactory;
import com.xiaoleilu.hutool.util.NumberUtil;

/**
 * 代理执行转换类，获取进度，用于前端进度条
 * 
 * @author winton
 * @since 2017年11月17日
 */
@Component
public class ExcelParserProxy extends MySqlStr {

	Log log = LogFactory.get();

	@Autowired
	StringRedisTemplate redis;

	@Override
	protected void getSqlSuffixAndSetIntoFile(List<List<Object>> dataList, List<Integer> isStringColumIndex,
			File sqlTxt, StringBuilder sqlPrefix, int j, String sqlPath) {
		log.info(String.valueOf(j));
		redis.opsForValue().set(sqlPath, String.valueOf(NumberUtil.roundStr(NumberUtil.div(j, rowSize), 2)));
		super.getSqlSuffixAndSetIntoFile(dataList, isStringColumIndex, sqlTxt, sqlPrefix, j, sqlPath);
	}

	@Override
	public void create(String tableName, List<List<Object>> dataList, String sqlPath) {
		super.create(tableName, dataList, sqlPath);
		log.info("处理完毕");
		redis.delete(sqlPath);
	}

}
