package com.winton.exceltosql.model.entity.test;

import java.io.Serializable;

import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Yanghu
 * @since 2017-11-24
 */
public class Item extends Model<Item> {

    private static final long serialVersionUID = 1L;

	private Integer id;
	private Integer pid;
	private Integer uid;
	private Integer oid;
	private String remarks;
	private Integer flag;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getOid() {
		return oid;
	}

	public void setOid(Integer oid) {
		this.oid = oid;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Item{" +
			", id=" + id +
			", pid=" + pid +
			", uid=" + uid +
			", oid=" + oid +
			", remarks=" + remarks +
			", flag=" + flag +
			"}";
	}
}
