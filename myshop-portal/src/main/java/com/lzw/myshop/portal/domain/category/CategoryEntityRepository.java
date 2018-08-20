package com.lzw.myshop.portal.domain.category;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryEntityRepository extends JpaRepository<CategoryEntity, Integer> {

	List<CategoryEntity> findCategoryChildrenByParentIdOrderBySortOrder(Integer parentId);

}
