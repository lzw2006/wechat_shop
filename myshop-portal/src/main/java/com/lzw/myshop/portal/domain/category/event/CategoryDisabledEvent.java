package com.lzw.myshop.portal.domain.category.event;

import java.util.Date;

public class CategoryDisabledEvent {

	private Integer id;

	private Date updateTime;

	public CategoryDisabledEvent(Integer id, Date updateTime) {
		this.id = id;
		this.updateTime = updateTime;
	}

	public Integer getId() {
		return id;
	}

	public Date getUpdateTime() {
		return updateTime;
	}
}
