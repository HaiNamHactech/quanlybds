package com.bkap.repositories;

import com.bkap.entities.RoleFunction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleFunctionRepository  extends JpaRepository<RoleFunction,Integer>, JpaSpecificationExecutor<RoleFunction> {

    @Query(value = "Select b.id from GroupRole  a " +
                   " left join  RoleFunction b on a.id = b.groupRole.id " +
                   " left  join Function f on f.id = b.function.id " +
                   " where a.id = ?1 and f.id = ?2 ")
    Object findByFunctionIdAndGroupRoleId(int groupRoleId, int functionId);
}
