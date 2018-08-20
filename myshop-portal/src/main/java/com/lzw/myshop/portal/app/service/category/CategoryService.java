package com.lzw.myshop.portal.app.service.category;

import com.lzw.myshop.portal.domain.category.CategoryEntity;
import com.lzw.myshop.portal.domain.category.CategoryEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

	@Autowired
	private CategoryEntityRepository categoryEntityRepository;

	public List<CategoryEntity> getChildParallelCategoryByParentId(Integer categoryId) {
		return categoryEntityRepository.findCategoryChildrenByParentIdOrderBySortOrder(categoryId);
	}

	public List<CategoryItem> getChildrenDeepParallelCategoryByParentId(Integer parentId) {
		List<CategoryEntity> categoryEntities = categoryEntityRepository.findAll();
		List<CategoryItem> categoryItems = new ArrayList<>();
		for (CategoryEntity categoryEntity : categoryEntities) {
			CategoryItem categoryItem = new CategoryItem();
			categoryItem.setId(categoryEntity.getId());
			categoryItem.setName(categoryEntity.getName());
			categoryItem.setParentId(categoryEntity.getParentId());
			categoryItems.add(categoryItem);
		}
		if (categoryItems != null && categoryItems.size() > 0) {
			categoryItems = getChildrenCategory(categoryItems, parentId);
		}
		return categoryItems;
	}

	private List<CategoryItem> getChildrenCategory(List<CategoryItem> categoryEntities, Integer categoryId) {
		List<CategoryItem> innerCategory = new ArrayList<>();
		for (CategoryItem item : categoryEntities) {
			if (item.parentId.equals(categoryId)) {
				CategoryItem categoryItem = new CategoryItem();
				categoryItem.setId(item.getId());
				categoryItem.setParentId(categoryId);
				categoryItem.setName(item.getName());
				List<CategoryItem> innerItem = getChildrenCategory(categoryEntities, item.getId());
				categoryItem.setCategoryItems(innerItem);
				innerCategory.add(categoryItem);
			}
		}
		return innerCategory;
	}

	public class CategoryItem {

		private Integer id;
		private Integer parentId;
		private String name;

		private List<CategoryItem> categoryItems;

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

		public List<CategoryItem> getCategoryItems() {
			return categoryItems;
		}

		public void setCategoryItems(List<CategoryItem> categoryItems) {
			this.categoryItems = categoryItems;
		}
	}
}
