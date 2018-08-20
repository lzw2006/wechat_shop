package com.lzw.myshop.portal.ui.rest.category;

import com.lzw.myshop.portal.app.service.category.CategoryService;
import com.lzw.myshop.portal.domain.category.CategoryEntity;
import com.lzw.myshop.portal.domain.category.CategoryEntityRepository;
import com.lzw.myshop.portal.domain.category.command.CategoryCreateCommand;
import com.lzw.myshop.portal.domain.category.command.CategoryDisableCommand;
import com.lzw.myshop.portal.domain.category.command.CategoryUpdateCommand;
import com.lzw.myshop.portal.ui.rest.category.dto.CategoryCreateDto;
import com.lzw.myshop.portal.ui.rest.category.dto.CategoryUpdateDto;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author Ray Leung
 * @date 2018/4/22
 * @desctption
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

	private static Logger logger = LoggerFactory.getLogger(CategoryController.class);

	@Autowired
	private CommandGateway commandGateway;

	@Autowired
	private CategoryEntityRepository categoryEntityRepository;

	@Autowired
	private CategoryService categoryService;

	@GetMapping("/{categoryId}")
	public CategoryEntity getCategoryById(@PathVariable Integer categoryId) {
		return categoryEntityRepository.findOne(categoryId);
	}

	@PostMapping("")
	public CompletableFuture<Object> createCategory(@RequestBody CategoryCreateDto categoryCreateDto) {
		Date dateNow = new Date();
		CategoryCreateCommand categoryCreateCommand = new CategoryCreateCommand(categoryCreateDto.getId(),
				categoryCreateDto.getParentId(), categoryCreateDto.getName(), categoryCreateDto.getStatus(),
				categoryCreateDto.getSortOrder(), dateNow, dateNow);
		return commandGateway.send(categoryCreateCommand);
	}

	@PutMapping("")
	public CompletableFuture<Object> updateCategory(@RequestBody CategoryUpdateDto categoryUpdateDto) {
		Date dateNow = new Date();
		CategoryUpdateCommand categoryUpdateCommand = new CategoryUpdateCommand(categoryUpdateDto.getId(),
				categoryUpdateDto.getParentId(), categoryUpdateDto.getName(), categoryUpdateDto.getStatus(),
				categoryUpdateDto.getSortOrder(), dateNow);
		return commandGateway.send(categoryUpdateCommand);
	}

	@DeleteMapping("/{categoryId}")
	public CompletableFuture<Object> deleteCategory(@PathVariable Integer categoryId) {
		Date dateNow = new Date();
		CategoryDisableCommand categoryDisableCommand = new CategoryDisableCommand(categoryId, dateNow);
		return commandGateway.send(categoryDisableCommand);
	}

	@GetMapping("/childParallelCategory/{categoryId}")
	public ResponseEntity getChildrenParallelCategory(@PathVariable Integer categoryId) {
		List<CategoryEntity> categoryEntityList = categoryService.getChildParallelCategoryByParentId(categoryId);
		return ResponseEntity.ok(categoryEntityList);
	}
}
