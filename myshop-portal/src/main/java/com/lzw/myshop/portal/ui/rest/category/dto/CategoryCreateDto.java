package com.lzw.myshop.portal.ui.rest.category.dto;

import java.util.Date;

public class CategoryCreateDto {

	private Integer id;

	private Integer parentId;

	private String name;

	private Integer status;

	private Integer sortOrder;

	private Date updateTime;

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
