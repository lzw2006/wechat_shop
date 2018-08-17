package com.lzw.myshop.portal.domain.category.event;

import java.util.Date;

public class CategoryCreatedEvent {

	private Integer id;

	private Integer parentId;

	private String name;

	private Integer status;

	private Integer sortOrder;

	private Date updateTime;

	public CategoryCreatedEvent(Integer id, Integer parentId, String name, Integer status, Integer sortOrder,
			Date updateTime) {
		this.id = id;
		this.parentId = parentId;
		this.name = name;
		this.status = status;
		this.sortOrder = sortOrder;
		this.updateTime = updateTime;
	}

	public Integer getId() {
		return id;
	}

	public Integer getParentId() {
		return parentId;
	}

	public String getName() {
		return name;
	}

	public Integer getStatus() {
		return status;
	}

	public Integer getSortOrder() {
		return sortOrder;
	}

	public Date getUpdateTime() {
		return updateTime;
	}
}
