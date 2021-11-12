package com.nitor.cognitostarter.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nitor.cognitostarter.entity.BaseEntity;
import com.nitor.cognitostarter.repository.BaseRepositiry;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/base")
public class BaseController {

	@Autowired
	BaseRepositiry baseRepository;
	
	@GetMapping("/data")
	@ApiOperation(value = "Get data", response = BaseEntity.class)
	public List<BaseEntity> getAllDetails(Principal principal) {
		return baseRepository.findAll();
	
	}
}
