package com.publicissapient.dto;

public class PackageDto {

	private Integer packageId;
	
	private String packageName;

	private String packageDetails;

	private Double packageFees;

	private String packageFacilities;
	
	public PackageDto() {

	}

	public PackageDto(Integer packageId,String packageName, String packageDetails, Double packageFees, String packageFacilities) {
		super();
		this.packageId=packageId;
		this.packageName = packageName;
		this.packageDetails = packageDetails;
		this.packageFees = packageFees;
		this.packageFacilities = packageFacilities;
	}
	
	

	public Integer getPackageId() {
		return packageId;
	}

	public void setPackageId(Integer packageId) {
		this.packageId = packageId;
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

	@Override
	public String toString() {
		return "PackageDto [packageId=" + packageId + ", packageName=" + packageName + ", packageDetails="
				+ packageDetails + ", packageFees=" + packageFees + ", packageFacilities=" + packageFacilities + "]";
	}
	
	
}
