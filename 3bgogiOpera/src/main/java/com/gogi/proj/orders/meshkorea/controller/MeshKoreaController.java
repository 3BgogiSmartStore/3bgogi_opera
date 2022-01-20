package com.gogi.proj.orders.meshkorea.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.gogi.proj.orders.meshkorea.model.MeshKoreaService;

@Controller
public class MeshKoreaController {

	private static final Logger logger = LoggerFactory.getLogger(MeshKoreaController.class);
	
	@Autowired
	private MeshKoreaService meshKoreaService;
	
	
}
