package com.jrtp.controller;

import java.util.List;
import java.util.Map;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import com.jrtp.Props.ApplicationProps;
import com.jrtp.constants.AppConstants;
import com.jrtp.entity.PlanMaster;
import com.jrtp.service.PlanService;


@RestController
public class PlanController {
	private static final Logger log = LoggerFactory.getLogger(PlanController.class);
	
	private PlanService planService;
	
	private Map<String, String> messages;
	
	public PlanController(PlanService planService,ApplicationProps applicationProps) {
		this.planService = planService;
	
		this.messages=applicationProps.getMessages();
		log.info("Application Yml properties:{}",this.messages);
	}
	 
	 
	@GetMapping("/categories")
	public ResponseEntity<Map<Integer, String>> getPlanCategory() {
		
		Map<Integer, String> plancategory = planService.getPlancategory();
		int size = plancategory.size();
		log.info("categories called all plans are :{} and total count :{}",plancategory,size);
		return new ResponseEntity<>(plancategory, HttpStatus.OK);
	}

//response entity is used to send data to client
	@PostMapping("/plan")
	public ResponseEntity<String> savePlan(@RequestBody PlanMaster plan) {
		String resmsg = AppConstants.EMPTY_STR;
		
		boolean savePlan = planService.savePlan(plan);
		if (savePlan) {
			
			resmsg = messages.get(AppConstants.PLAN_SAVED);
			log.info("Plan saved successfully "+plan.toString());
		} else {
			resmsg = messages.get(AppConstants.PLAN_SAVE_FAIL);
		}
		return new ResponseEntity<>(resmsg, HttpStatus.CREATED);
	}

	@GetMapping("/plans")
	public ResponseEntity<List<PlanMaster>> plans() {
		List<PlanMaster> allPlans = planService.getAllPlans();
		if (allPlans.isEmpty())
		{
			log.info("No plans found");
		}

		return new ResponseEntity<>(allPlans, HttpStatus.OK);
	}

	@GetMapping("/plan/{planId}")
	public ResponseEntity<PlanMaster> editPlanId(@PathVariable Integer planId) {
		PlanMaster planById = planService.getPlanById(planId);
		if(planById.getPlanId()!= null)
		{
			log.info("Plan found "+planId);
		}else
		{
			log.info("Plan not found "+planId);
		}
		return new ResponseEntity<>(planById, HttpStatus.OK);
	}

	@DeleteMapping("/plan/{planId}")
	public ResponseEntity<String> deletePlanId(@PathVariable Integer planId) {
		String msg = AppConstants.EMPTY_STR;
		boolean deletePlanById = planService.deletePlanById(planId);
		if (deletePlanById) {
			log.info("Plan deleted " +planId);
			msg = messages.get(AppConstants.PLAN_DELETE);
			
		} else {
			log.info("Plan not deleted " +planId);
			msg = messages.get(AppConstants.PLAN_DELETE_FAIL);
		}
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}

	@PutMapping("/plan")
	public ResponseEntity<String> updatePlan(@RequestBody PlanMaster plan) {
		String msg = AppConstants.EMPTY_STR;
		boolean updatePlan = planService.updatePlan(plan);
		if (updatePlan) {
			log.info("Plan updated "+plan.toString());
			msg = messages.get(AppConstants.PLAN_UPDATED);
		} else {
			log.info("Plan not updated "+plan.toString());
			msg = messages.get(AppConstants.PLAN_UPDATE_FAIL);
		}
		return new ResponseEntity<>(msg, HttpStatus.OK);

	}

	@PutMapping("/status-chnage /{planId}/{status}")
	public ResponseEntity<String> statusChange(@PathVariable Integer planId, @PathVariable String status) {
		// string literals not allowed instead use the constants.
		String msg = AppConstants.EMPTY_STR;
		boolean planStausChange = planService.planStausChange(planId, status);
		if (planStausChange) {
			msg = messages.get(AppConstants.PLAN_STATUS_CHANGED);
		} else {
			msg = messages.get(AppConstants.PLAN_STATUS_FAIL);
		}
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}
}
