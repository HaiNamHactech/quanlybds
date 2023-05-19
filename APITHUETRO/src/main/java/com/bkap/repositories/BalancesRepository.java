package com.bkap.repositories;

import com.bkap.entities.Balances;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BalancesRepository extends JpaRepository<Balances,Integer>, JpaSpecificationExecutor<Balances> {

}
