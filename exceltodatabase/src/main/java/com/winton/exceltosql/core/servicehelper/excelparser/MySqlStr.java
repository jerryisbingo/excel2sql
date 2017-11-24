package com.winton.exceltosql.core.servicehelper.excelparser;

import java.io.File;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.xiaoleilu.hutool.io.FileUtil;
import com.xiaoleilu.hutool.log.Log;
import com.xiaoleilu.hutool.log.LogFactory;
import com.xiaoleilu.hutool.util.CollectionUtil;
import com.xiaoleilu.hutool.util.RandomUtil;
import com.xiaoleilu.hutool.util.StrUtil;

import sun.util.logging.resources.logging;

/**
 * excel->sql执行类
 * @author winton
 * @since 2017-11-16
 */
@Component
public class MySqlStr {
	
	Log log = LogFactory.get();
	
	protected int rowSize;
	
	public void create(String tableName, List<List<Object>> dataList, String sqlPath) {
		setRowSize(dataList.size());
		
		List<Integer> isStringColumIndex = CollectionUtil.newArrayList();
		File sqlTxt = new File(sqlPath);
		
		StringBuilder sqlPrefix = getSqlPrefix(tableName, dataList, isStringColumIndex);
		for (int j=0; j<rowSize-1; j++) {
			getSqlSuffixAndSetIntoFile(dataList, isStringColumIndex, sqlTxt, sqlPrefix, j, sqlPath);
		}
		
	}

	protected void getSqlSuffixAndSetIntoFile(List<List<Object>> dataList, List<Integer> isStringColumIndex, File sqlTxt,
			StringBuilder sqlPrefix, int j,String sqlPath) {
		List<Object> objs = dataList.get(j);
		StringBuilder rowSql = new StringBuilder(sqlPrefix);
		rowSql.append("VALUES (");
		
		int rowCellSize = objs.size();
		for (int i=0; i<rowCellSize; i++) {
			Object cell = Optional.ofNullable(objs.get(i)).orElse("");
			String cellValue = cell.toString().trim();
			// begin 处理引号
			if (isStringColumIndex.contains(i)) {
				cellValue = StrUtil.addPrefixIfNot(cellValue, "'");
				cellValue = StrUtil.addSuffixIfNot(cellValue, "'");
			}
			// end
			rowSql.append(cellValue).append(",");
		}
		rowSql = new StringBuilder(StrUtil.removeSuffix(rowSql, ","));
		rowSql.append(");");
		rowSql.append(System.getProperty("line.separator"));
		FileUtil.appendUtf8String(rowSql.toString(), sqlTxt);
	}

	protected StringBuilder getSqlPrefix(String tableName, List<List<Object>> dataList,
			List<Integer> isStringColumIndex) {
		StringBuilder sqlPrefix = new StringBuilder();
		sqlPrefix.append("INSERT INTO ");
		sqlPrefix.append(tableName);
		sqlPrefix.append("(");
		List<Object> columList = dataList.get(0);
		
		int columSize = columList.size();
		for (int i=0; i<columSize; i++) {
			Object colum = columList.get(i);
			String columName = colum.toString().trim();
			// begin 处理引号
			if (columName.trim().endsWith("%s")) {
				isStringColumIndex.add(i);
			}
			// end
			
			sqlPrefix.append(StrUtil.removeAll(columName, "%s")).append(",");
			
		}
		sqlPrefix = new StringBuilder(StrUtil.removeSuffix(sqlPrefix, ","));
		sqlPrefix.append(")");
		dataList.remove(0);
		return sqlPrefix;
	}
	
	public int getRowSize() {
		return rowSize;
	}

	private void setRowSize(int rowSize) {
		this.rowSize = rowSize;
	}
	
}
