package com.lzw.myshop.portal.domain.category;

import com.lzw.myshop.portal.domain.category.command.CategoryCreateCommand;
import com.lzw.myshop.portal.domain.category.event.CategoryCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.Date;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

@Aggregate
public class Category {

	@AggregateIdentifier
	private Integer id;

	private Integer parentId;

	private String name;

	private Integer status;

	private Integer sortOrder;

	private Date createTime;

	private Date updateTime;

	@CommandHandler
	public Category(CategoryCreateCommand command) {
		apply(new CategoryCreatedEvent(command.getId(), command.getParentId(), command.getName(), command.getStatus(),
				command.getSortOrder(), command.getUpdateTime()));
	}

	@EventSourcingHandler
	public void on(CategoryCreatedEvent event) {
		this.id = event.getId();
		this.parentId = event.getParentId();
		this.name = event.getName();
		this.status = event.getStatus();
		this.sortOrder = event.getSortOrder();
		this.createTime = event.getUpdateTime();
		this.updateTime = event.getUpdateTime();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
