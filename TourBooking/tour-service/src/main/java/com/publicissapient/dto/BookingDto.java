package com.publicissapient.dto;

public class BookingDto {

	private String bookingStatus;

	private Integer totalPeople;

	private String packageStandard;

	private Double totalPrice;

	private String paymentStatus;

	private Integer staffId;

	private String fromDate;

	private String toDate;

	private Integer totalDays;

	private Integer customerId;

	private Integer packageId;

	private Boolean bookingEnabled;

	public BookingDto(String bookingStatus, Integer totalPeople, String packageStandard, Double totalPrice,
			String paymentStatus, Integer staffId, String fromDate, String toDate, Integer totalDays,
			Integer customerId, Integer packageId, Boolean bookingEnabled) {
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
		this.bookingEnabled = bookingEnabled;
	}

	public String getBookingStatus() {
		return bookingStatus;
	}

	public Integer getTotalPeople() {
		return totalPeople;
	}

	public String getPackageStandard() {
		return packageStandard;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public Integer getStaffId() {
		return staffId;
	}

	public String getFromDate() {
		return fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public Integer getTotalDays() {
		return totalDays;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public Integer getPackageId() {
		return packageId;
	}

	public Boolean getBookingEnabled() {
		return bookingEnabled;
	}

}
