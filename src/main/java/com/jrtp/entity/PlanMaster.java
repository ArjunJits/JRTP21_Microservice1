package com.jrtp.entity;


import java.time.LocalDate;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name="PLAN_MASTER")
public class PlanMaster  {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer planId;
	@Column(name = "plan_name")
	private String planName;
	@Column(name = "plan_start_date")
	private LocalDate planStartDate;
	@Column(name = "plan_end_date")
	private LocalDate planEndDate;

	@Column(name = "plan_category")
	private Integer planCategory;
	
	@Column(name = "plan_active_ind")
	private String planActiveInd;
	 
	@Column(name="created_date",updatable = false)
	@CreationTimestamp
	private LocalDate createdDate;
	
	@Column(name="updated_date",insertable = false)
	@CreationTimestamp
	private LocalDate updatedDate;
	
	@Column(name="created_by")
	private String createdBy;
	
	@Column(name="updated_by")
	private String updatedBy;


}
