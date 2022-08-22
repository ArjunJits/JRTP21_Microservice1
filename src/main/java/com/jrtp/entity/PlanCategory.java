package com.jrtp.entity;

import java.time.LocalDate;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "PLAN_CATEGORY")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PlanCategory  {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "plan_cat_id")
	private int plancatId;
	@Column(name = "plan_cat_name")
	private String plancatName;
	@Column(name = "plan_active_ind")
	private String planactiveInd;
	
	@Column(name="created_date",updatable = false)
	@CreationTimestamp
	private LocalDate createdDate;
	
	@Column(name="updated_date" ,insertable = false)
	@CreationTimestamp
	private LocalDate updatedDate;
	
	@Column(name="created_by")
	private String createdBy;
	@Column(name="updated_by")
	private String updatedBy;


}
