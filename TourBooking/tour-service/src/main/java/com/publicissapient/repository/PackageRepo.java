package com.publicissapient.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.publicissapient.entity.Package;

@Repository
public interface PackageRepo extends JpaRepository<Package,Integer> {
	
	public Boolean existsByPackageName(String packageName);
	
	@Query("UPDATE Package SET packageDetails = :packageDetails,packageFees = :packageFees,packageFacilities =:packageFacilities WHERE packageName = :packageName")
	@Transactional
	@Modifying
	Integer updatePackage(String packageName, String packageDetails, Double packageFees,
			String packageFacilities);
	
	@Query("delete from Package p where p.packageName=:packageName")
	@Transactional
	@Modifying
	void deletePackageByName(String packageName);

}
