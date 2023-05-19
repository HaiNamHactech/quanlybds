package com.bkap.repositories;


import com.bkap.entities.Instruct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructRepository extends JpaRepository<Instruct,Integer>, JpaSpecificationExecutor<Instruct> {

}
