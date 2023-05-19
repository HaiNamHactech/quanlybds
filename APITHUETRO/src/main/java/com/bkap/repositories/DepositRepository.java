package com.bkap.repositories;

import com.bkap.entities.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DepositRepository extends JpaRepository<Deposit,Integer>, JpaSpecificationExecutor<Deposit> {
}
