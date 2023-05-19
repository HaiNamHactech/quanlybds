package com.bkap.repositories;

import com.bkap.entities.GroupRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GroupRoleRepository extends JpaRepository<GroupRole,Integer>, JpaSpecificationExecutor<GroupRole> {
    Optional<GroupRole> findByRoleName(String name);
}
