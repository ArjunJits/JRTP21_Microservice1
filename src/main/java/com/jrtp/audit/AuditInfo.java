package com.jrtp.audit;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuditInfo {
    
	private Date createdDate;
	private Date updatedDate;
	private String createdBy;
	private String updatedBy;

}
