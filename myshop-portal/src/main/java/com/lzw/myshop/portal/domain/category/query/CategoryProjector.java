package com.lzw.myshop.portal.domain.category.query;

import com.lzw.myshop.portal.domain.category.CategoryEntity;
import com.lzw.myshop.portal.domain.category.CategoryEntityRepository;
import com.lzw.myshop.portal.domain.category.event.CategoryCreatedEvent;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryProjector {

	@Autowired
	private CategoryEntityRepository categoryEntityRepository;

	@EventHandler
	public void on(CategoryCreatedEvent event) {
		CategoryEntity categoryEntity = new CategoryEntity(event.getId(), event.getParentId(), event.getName(),
				event.getStatus(), event.getSortOrder(), event.getUpdateTime(), event.getUpdateTime());
		categoryEntityRepository.save(categoryEntity);
	}

}
