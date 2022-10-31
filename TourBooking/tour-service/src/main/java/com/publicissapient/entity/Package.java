package com.publicissapient.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PACKAGES")
public class Package {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer packageId;

	@Column(name = "PACKAGE_NAME")
	private String packageName;

	@Column(name = "PACKAGE_DETAILS")
	private String packageDetails;

	@Column(name = "PACKAGE_FEES")
	private Double packageFees;

	@Column(name = "PACKAGE_FACILITIES")
	private String packageFacilities;

	public Package() {

	}

	public Package(String packageName, String packageDetails, Double packageFees, String packageFacilities) {
		this.packageName = packageName;
		this.packageDetails = packageDetails;
		this.packageFees = packageFees;
		this.packageFacilities = packageFacilities;
	}

	public Integer getPackageId() {
		return packageId;
	}

	public String getPackageName() {
		return packageName;
	}

	public String getPackageDetails() {
		return packageDetails;
	}

	public Double getPackageFees() {
		return packageFees;
	}

	public String getPackageFacilities() {
		return packageFacilities;
	}

}
