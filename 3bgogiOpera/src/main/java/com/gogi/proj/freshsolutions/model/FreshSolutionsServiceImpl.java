package com.gogi.proj.freshsolutions.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FreshSolutionsServiceImpl implements FreshSolutionsService{

	@Autowired
	private FreshSolutionsDAO freshSolutionsDao;
}
