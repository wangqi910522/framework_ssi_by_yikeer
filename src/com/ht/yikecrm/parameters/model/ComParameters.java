package com.ht.yikecrm.parameters.model;

import com.ht.yikecrm.model.IModel;

public class ComParameters implements IModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2397209454812243844L;
	private String pkey;
	private String pvalue;
	
	
	
	public String getPkey() {
		return pkey;
	}
	public void setPkey(String pkey) {
		this.pkey = pkey;
	}
	public String getPvalue() {
		return pvalue;
	}
	public void setPvalue(String pvalue) {
		this.pvalue = pvalue;
	}
	
	
}
