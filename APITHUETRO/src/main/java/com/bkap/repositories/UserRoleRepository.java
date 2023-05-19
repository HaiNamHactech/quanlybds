package com.bkap.repositories;

import com.bkap.entities.User;
import com.bkap.entities.UserRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole,Integer>, JpaSpecificationExecutor<UserRole> {

    @Query(value = "Select b.id from GroupRole  a " +
            " left join  UserRole b on a.id = b.groupRole.id " +
            " left  join User f on f.id = b.user.id " +
            " where a.id = ?1 and f.id = ?2 ")

    Object findByUserIdAndRoleId(int roleId, int userId);
}
