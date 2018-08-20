package com.lzw.myshop.portal.app.service.category;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertNotNull;

/**
 * @author Ray Leung
 * @date 2018/4/22
 * @desctption
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceTest {

	@Autowired
	private CategoryService categoryService;

	@Test
	public void testGetChildrenDeepParallelCategoryByParentId() {
		List<CategoryService.CategoryItem> categoryItems = categoryService.getChildrenDeepParallelCategoryByParentId(0);
		System.out.println(categoryItems);
		assertNotNull(categoryItems);
	}

}
