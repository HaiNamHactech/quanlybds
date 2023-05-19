package com.bkap.repositories;

import com.bkap.entities.PriceArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceAreaRepository extends JpaRepository<PriceArea,Integer>, JpaSpecificationExecutor<PriceArea> {

}
