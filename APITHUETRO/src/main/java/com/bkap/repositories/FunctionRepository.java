package com.bkap.repositories;

import com.bkap.entities.Function;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FunctionRepository  extends JpaRepository<Function,Integer>, JpaSpecificationExecutor<Function> {
    Optional<Function> findByFunctionName(String functionName);
}
