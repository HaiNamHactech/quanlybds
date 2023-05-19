package com.bkap.repositories;

import com.bkap.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room,Integer>, JpaSpecificationExecutor<Room> {
    Optional<Room> findByTitle(String categoryName);
}
