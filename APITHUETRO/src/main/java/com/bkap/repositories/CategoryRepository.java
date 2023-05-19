package com.bkap.repositories;

import com.bkap.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer>, JpaSpecificationExecutor<Category> {

    Optional<Category> findByName(String categoryName);
}
