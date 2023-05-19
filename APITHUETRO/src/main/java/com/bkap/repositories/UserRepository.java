package com.bkap.repositories;

import com.bkap.entities.Room;
import com.bkap.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>, JpaSpecificationExecutor<User> {
    Optional<User> findByName(String name);
    @Query(value = "Select u.accountBalance from User u where u.id = ?1")
    Float accountBalance(int userId);
}
