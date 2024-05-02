package com.example.demo.model;

import java.util.Date;

public class ClientsModel {
	
		private String sharedKey;
	    private String name;
	    private String phone;
	    private String email;
	    private Date startDate;
	    private Date endDate;
	    private Date dateAdded;		
		
		
		public String getSharedKey() {
			return sharedKey;
		}
		public void setSharedKey(String sharedKey) {
			this.sharedKey = sharedKey;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getPhone() {
			return phone;
		}
		public void setPhone(String phone) {
			this.phone = phone;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
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
		public Date getDateAdded() {
			return dateAdded;
		}
		public void setDateAdded(Date dateAdded) {
			this.dateAdded = dateAdded;
		}
		
		@Override
		public String toString() {
		    return "ClientsModel{" +
		           "sharedKey='" + sharedKey + '\'' +
		           ", name='" + name + '\'' +
		           ", phone='" + phone + '\'' +
		           ", email='" + email + '\'' +
		           ", startDate='" + startDate + '\'' +
		           ", endDate='" + endDate + '\'' +
		           ", dateAdded='" + dateAdded + '\'' +
		           
		           '}';
		}
}
