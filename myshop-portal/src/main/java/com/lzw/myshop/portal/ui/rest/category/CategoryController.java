package com.lzw.myshop.portal.ui.rest.category;

import com.lzw.myshop.portal.domain.category.CategoryEntityRepository;
import com.lzw.myshop.portal.domain.category.command.CategoryCreateCommand;
import com.lzw.myshop.portal.ui.rest.category.dto.CategoryCreateDto;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

	@PostMapping("")
	public CompletableFuture<Object> createCategory(@RequestBody CategoryCreateDto categoryCreateDto) {
		CategoryCreateCommand categoryCreateCommand = new CategoryCreateCommand(categoryCreateDto.getId(),
				categoryCreateDto.getParentId(), categoryCreateDto.getName(), categoryCreateDto.getStatus(),
				categoryCreateDto.getSortOrder(), categoryCreateDto.getUpdateTime(), categoryCreateDto.getUpdateTime());
		return commandGateway.send(categoryCreateCommand);
	}
}
