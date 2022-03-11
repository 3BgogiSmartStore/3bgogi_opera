package com.gogi.proj.freshsolutions.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.gogi.proj.freshsolutions.model.FreshSolutionsService;

@Controller
public class FreshSolutionsController {

	private static final Logger logger = LoggerFactory.getLogger(FreshSolutionsController.class);
	
	@Autowired
	private FreshSolutionsService freshSolutionsService;
}
