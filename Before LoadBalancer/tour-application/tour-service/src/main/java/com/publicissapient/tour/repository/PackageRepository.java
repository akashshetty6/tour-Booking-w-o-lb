package com.publicissapient.tour.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.publicissapient.tour.entity.Packages;

public interface PackageRepository extends JpaRepository<Packages, Integer>{

	boolean existsByPackageDetails(String packageDetails);
	

}
