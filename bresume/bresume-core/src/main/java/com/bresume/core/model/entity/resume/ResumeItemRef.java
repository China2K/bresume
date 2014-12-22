package com.bresume.core.model.entity.resume;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.bresume.core.model.base.BaseEntity;

@Entity
@Table(name = "br_resume_ref_item")
public class ResumeItemRef extends BaseEntity {

	private static final long serialVersionUID = 1832428897252111730L;
	private Resume resume;
	private String itemSn;
	private Integer order;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "RESUME_ID")
	public Resume getResume() {
		return resume;
	}

	public void setResume(Resume resume) {
		this.resume = resume;
	}

	//@ManyToOne(fetch=FetchType.LAZY)
	//@PrimaryKeyJoinColumn(name="ITEM_SN",referencedColumnName="sn")
	//@JoinColumn(name="ITEM_SN",referencedColumnName="SN")
	
	@Column(name="ITEM_SN")
	public String getItemSn() {
		return itemSn;
	}

	public void setItemSn(String itemSn) {
		this.itemSn = itemSn;
	}


	
	@Column(name = "`ORDER`")
	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}
	
	
//	@ManyToMany(targetEntity = ProductInstance.class, fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
//	@Fetch(FetchMode.SUBSELECT) 
//	@JoinTable(
//		name = "v_app_product_instance_rls", 
//		joinColumns = { @JoinColumn(name = "APP_ID") }, 
//		inverseJoinColumns = { @JoinColumn(name = "PROD_INST_ID") }
//	)
//	@Filter(condition="USER_ID=:userId and (status='0' or status ='1')", name="applicationOrderedFilter")

}
