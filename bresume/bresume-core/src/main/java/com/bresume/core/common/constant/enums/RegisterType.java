package com.bresume.core.common.constant.enums;

public enum RegisterType {
	PORTAL_REGISTER(1),PORTAL_ADD(2),ADMIN_ADD(3);
	
	private int type;
	RegisterType(int type	){
		this.type=type;
	}
	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}
	

}
