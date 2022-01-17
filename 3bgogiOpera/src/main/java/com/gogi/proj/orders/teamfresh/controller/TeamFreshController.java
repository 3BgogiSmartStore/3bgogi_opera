package com.gogi.proj.orders.teamfresh.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.gogi.proj.orders.teamfresh.model.TeamFreshService;

@Controller
public class TeamFreshController {

	private static final Logger logger = LoggerFactory.getLogger(TeamFreshController.class);
	
	@Autowired
	private TeamFreshService teamFreshService;
	
}
