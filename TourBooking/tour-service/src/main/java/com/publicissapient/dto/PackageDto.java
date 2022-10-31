package com.publicissapient.dto;

public class PackageDto {

	private String packageName;

	private String packageDetails;

	private Double packageFees;

	private String packageFacilities;
	
	public PackageDto() {

	}

	public PackageDto(String packageName, String packageDetails, Double packageFees, String packageFacilities) {
		super();
		this.packageName = packageName;
		this.packageDetails = packageDetails;
		this.packageFees = packageFees;
		this.packageFacilities = packageFacilities;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getPackageDetails() {
		return packageDetails;
	}

	public void setPackageDetails(String packageDetails) {
		this.packageDetails = packageDetails;
	}

	public Double getPackageFees() {
		return packageFees;
	}

	public void setPackageFees(Double packageFees) {
		this.packageFees = packageFees;
	}

	public String getPackageFacilities() {
		return packageFacilities;
	}

	public void setPackageFacilities(String packageFacilities) {
		this.packageFacilities = packageFacilities;
	}
	
	
}
