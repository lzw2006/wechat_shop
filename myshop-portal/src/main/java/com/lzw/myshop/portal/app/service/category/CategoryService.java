package com.lzw.myshop.portal.app.service.category;

import com.lzw.myshop.portal.domain.category.CategoryEntity;
import com.lzw.myshop.portal.domain.category.CategoryEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

	@Autowired
	private CategoryEntityRepository categoryEntityRepository;

	public List<CategoryEntity> getChildParallelCategoryByParentId(Integer parentId) {
		return categoryEntityRepository.findCategoryChildrenByParentIdOrderBySortOrder(parentId);
	}
}
