package com.jrtp.service;

import java.util.List;
import java.util.Map;

import com.jrtp.entity.PlanMaster;

public interface PlanService {
//drop down data in key value
	public Map<Integer, String> getPlancategory();

// Insert the plan 
	public boolean savePlan(PlanMaster plan);

// To display all plans 
	public List<PlanMaster> getAllPlans();

// update the plan ( upsert() one method 
// both insert and update )
	public PlanMaster getPlanById(Integer planId);
	public boolean updatePlan(PlanMaster plan);

//  delete plan
	public boolean deletePlanById(Integer plainId);
	
//activate and inactive plan
   public boolean planStausChange(Integer plainId,String activeSw);

}
