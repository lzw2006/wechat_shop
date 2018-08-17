package com.lzw.myshop.portal.ui.rest.category;

import com.lzw.myshop.portal.domain.category.CategoryEntity;
import com.lzw.myshop.portal.domain.category.CategoryEntityRepository;
import com.lzw.myshop.portal.domain.category.command.CategoryCreateCommand;
import com.lzw.myshop.portal.domain.category.command.CategoryUpdateCommand;
import com.lzw.myshop.portal.ui.rest.category.dto.CategoryCreateDto;
import com.lzw.myshop.portal.ui.rest.category.dto.CategoryUpdateDto;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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
}
