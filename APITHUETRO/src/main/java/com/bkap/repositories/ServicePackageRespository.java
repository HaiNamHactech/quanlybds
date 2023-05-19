package com.bkap.repositories;

import com.bkap.entities.Category;
import com.bkap.entities.Room;
import com.bkap.entities.ServicePackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ServicePackageRespository extends JpaRepository<ServicePackage,Integer>, JpaSpecificationExecutor<ServicePackage> {
    Optional<ServicePackage> findByTitle(String categoryName);
}
