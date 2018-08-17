package com.lzw.myshop.portal.domain.category.command;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

import java.util.Date;

public class CategoryDisableCommand {

	@TargetAggregateIdentifier
	private Integer id;

	private Date updateTime;

	public CategoryDisableCommand(Integer id, Date updateTime) {
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
