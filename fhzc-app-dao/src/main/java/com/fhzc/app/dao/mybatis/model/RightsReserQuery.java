package com.fhzc.app.dao.mybatis.model;

import java.util.Date;

/**
 * Created by Double_J on 2016/9/8.
 */
public class RightsReserQuery {
	
	 	private Integer rightsId;

	    private String rightsName;

	    public Integer getRightsId() {
			return rightsId;
		}

		public void setRightsId(Integer rightsId) {
			this.rightsId = rightsId;
		}

		public String getRightsName() {
			return rightsName;
		}

		public void setRightsName(String rightsName) {
			this.rightsName = rightsName;
		}

		public Date getStartDate() {
			return startDate;
		}

		public void setStartDate(Date startDate) {
			this.startDate = startDate;
		}

		public Date getEndDate() {
			return endDate;
		}

		public void setEndDate(Date endDate) {
			this.endDate = endDate;
		}

		private Date startDate;

	    private Date endDate;
}
