package com.lzw.myshop.portal.ui.rest.category.dto;

public class CategoryUpdateDto {

	private Integer id;

	private Integer parentId;

	private String name;

	private Integer status;

	private Integer sortOrder;

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

}
