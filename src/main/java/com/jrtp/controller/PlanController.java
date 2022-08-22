package com.jrtp.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jrtp.entity.PlanMaster;
import com.jrtp.exception.PlanNotFoundException;
import com.jrtp.service.PlanService;


@RestController
public class PlanController {
	private static final Logger log = LoggerFactory.getLogger(PlanController.class);
	@Autowired
	private PlanService planService;

	@GetMapping("/categories")
	public ResponseEntity<Map<Integer, String>> getPlanCategory() {
		
		Map<Integer, String> plancategory = planService.getPlancategory();
		log.info("categories called all plans are {}"+plancategory);
		return new ResponseEntity<>(plancategory, HttpStatus.OK);
	}

//response entity is used to send data to client
	@PostMapping("/plan")
	public ResponseEntity<String> savePlan(@RequestBody PlanMaster plan) {
		String resmsg = "";
		boolean savePlan = planService.savePlan(plan);
		if (savePlan) {
			resmsg = "plan saved";
			log.info("Plan saved successfully "+plan.toString());
		} else {
			resmsg = "plan not saved";
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
		String msg = "";
		boolean deletePlanById = planService.deletePlanById(planId);
		if (deletePlanById) {
			log.info("Plan deleted " +planId);
			msg = "plan deleted";
			
		} else {
			log.info("Plan not deleted " +planId);
			msg = "plan not deleted";
			throw new PlanNotFoundException();
		}
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}

	@PutMapping("/plan")
	public ResponseEntity<String> updatePlan(@RequestBody PlanMaster plan) {
		String msg = " ";
		boolean updatePlan = planService.updatePlan(plan);
		if (updatePlan) {
			log.info("Plan updated "+plan.toString());
			msg = "Plan Updated";
		} else {
			log.info("Plan not updated "+plan.toString());
			msg = "Plan not delted";
			throw new PlanNotFoundException();
		}
		return new ResponseEntity<>(msg, HttpStatus.OK);

	}

	@PutMapping("/status-chnage /{planId}/{status}")
	public ResponseEntity<String> statusChange(@PathVariable Integer planId, @PathVariable String status) {
		String msg = "";
		boolean planStausChange = planService.planStausChange(planId, status);
		if (planStausChange) {
			msg = "Switch changed";
		} else {
			msg = "Switch not changed";
		}
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}
}
