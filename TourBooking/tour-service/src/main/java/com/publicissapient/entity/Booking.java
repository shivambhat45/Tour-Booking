package com.publicissapient.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="BOOKING")
public class Booking {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer bookingId;
    
    @Column(name="BOOKING_STATUS")
    private String bookingStatus;
    
    @Column(name="TOTAL_PEOPLE")
    private Integer totalPeople;
    
    @Column(name="PACKAGE_TYPE")
    private String packageStandard;
    
    @Column(name="TOTAL_PRICE")
    private Double totalPrice;
    
    @Column(name="PAYMENT_STATUS")
    private String paymentStatus;
    
    @Column(name="STAFF_ASSIGNED")
    private Integer staffId;
    
    @Column(name="FROM_DATE")
    private String fromDate;
    
    @Column(name="TO_DATE")
    private String toDate;
    
    @Column(name="TOTAL_DAYS")
    private Integer totalDays;
    
    @Column(name="CUSTOMER_ID")
    private Integer customerId;

    @Column(name="PACKAGE_ID")
    private Integer packageId;
    
    @Column(name="BOOKING_ENABLED")
    private Boolean bookingEnabled;

	public Booking() {

	}

   	public Booking(String bookingStatus, Integer totalPeople, String packageStandard,
			Double totalPrice, String paymentStatus, Integer staffId, String fromDate, String toDate, Integer totalDays,
			Integer customerId, Integer packageId,Boolean bookingEnabled) {
		super();
		this.bookingStatus = bookingStatus;
		this.totalPeople = totalPeople;
		this.packageStandard = packageStandard;
		this.totalPrice = totalPrice;
		this.paymentStatus = paymentStatus;
		this.staffId = staffId;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.totalDays = totalDays;
		this.customerId = customerId;
		this.packageId = packageId;
		this.bookingEnabled=bookingEnabled;
	}


	public Integer getBookingId() {
		return bookingId;
	}

	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}

	public String getBookingStatus() {
		return bookingStatus;
	}

	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}

	public Integer getTotalPeople() {
		return totalPeople;
	}

	public void setTotalPeople(Integer totalPeople) {
		this.totalPeople = totalPeople;
	}

	public String getPackageStandard() {
		return packageStandard;
	}

	public void setPackageStandard(String packageStandard) {
		this.packageStandard = packageStandard;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public Integer getStaffId() {
		return staffId;
	}

	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public Integer getTotalDays() {
		return totalDays;
	}

	public void setTotalDays(Integer totalDays) {
		this.totalDays = totalDays;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public Integer getPackageId() {
		return packageId;
	}

	public void setPackageId(Integer packageId) {
		this.packageId = packageId;
	}

	public Boolean getBookingEnabled() {
		return bookingEnabled;
	}

	public void setBookingEnabled(Boolean bookingEnabled) {
		this.bookingEnabled = bookingEnabled;
	}


	
	
    
	
    
    


}
