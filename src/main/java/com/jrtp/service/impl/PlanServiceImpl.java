package com.jrtp.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jrtp.entity.PlanCategory;
import com.jrtp.entity.PlanMaster;
import com.jrtp.repository.PlanCategoryRepo;
import com.jrtp.repository.PlanMasterRepo;
import com.jrtp.service.PlanService;

@Service
public class PlanServiceImpl implements PlanService {

	
	private final static Logger log = LoggerFactory.getLogger(PlanServiceImpl.class);
	@Autowired
	private PlanCategoryRepo planCategoryRepo;
	@Autowired
	private PlanMasterRepo planMasterRepo;
	@Override
	public Map<Integer, String> getPlancategory() {
		List<PlanCategory> category = planCategoryRepo.findAll();
		Map<Integer,String> cateogryMap = new HashMap<>();
		category.forEach(catgeory1 ->{
			cateogryMap.put(catgeory1.getPlancatId(),catgeory1.getPlancatName());
		});
		return cateogryMap;
	}
	@Override
	@Transactional(readOnly = false)
	public boolean savePlan(PlanMaster plan) {
     log.info("  Inserting the plan record");
     PlanMaster addRecord = planMasterRepo.save(plan);
//     if(addRecord.getPlanId() != null)
//     {
//    	 return true;
//     }else
//     {
//    	 return false;
//     }
     
     log.info(" Record added {}"+addRecord.toString());
	 //return addRecord.getPlanId()!=null ? true :false;
	 return addRecord.getPlanId()!=null;
	}
	@Override
	public List<PlanMaster> getAllPlans() {
		
		return planMasterRepo.findAll();
	}
	
	@Override
	public PlanMaster getPlanById(Integer planId) {
		
		Optional<PlanMaster> findById = planMasterRepo.findById(planId);
		if (findById.isPresent()) {
			return findById.get();
		}
		return null;
	}
	@Override
	public boolean updatePlan(PlanMaster plan) {
		 planMasterRepo.save(plan); //upsert method
		return plan.getPlanId()!=null;
	}
	@Override
	public boolean deletePlanById(Integer plainId) {
		boolean status = false;
		try {
			planMasterRepo.deleteById(plainId);
			return true;
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}
	@Override
	public boolean planStausChange(Integer plainId, String activeSw) {
//		PlanMaster plan = new PlanMaster();
//		plan.setPlanId(plainId);
//		plan.setPlanActiveInd(activeSw);
		Optional<PlanMaster> findById = planMasterRepo.findById(plainId);
		if(findById.isPresent())
		{
  			PlanMaster plan = new PlanMaster();
   			plan.setPlanId(plainId);
   			plan.setPlanActiveInd(activeSw);

		}
	   return false;	
	}
		
	
	
}
