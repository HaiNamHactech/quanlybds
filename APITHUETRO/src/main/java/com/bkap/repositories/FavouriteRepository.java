package com.bkap.repositories;


import com.bkap.entities.Favourite;
import com.bkap.entities.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FavouriteRepository extends JpaRepository<Favourite,Integer>, JpaSpecificationExecutor<Favourite> {

    @Query(value = "Select b.id from Room a " +
            " left join  Favourite b on a.id = b.room.id " +
            " left join User f on f.id = b.user.id " +
            " where a.id = ?1 and f.id = ?2 ")

    Object findByUserIdAndRoomId(int roomId, int userId);
}
