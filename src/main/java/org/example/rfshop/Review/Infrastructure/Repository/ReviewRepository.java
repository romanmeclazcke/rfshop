package org.example.rfshop.Review.Infrastructure.Repository;

import org.example.rfshop.Review.Infrastructure.Model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByBarberShopId(Long barberShopId);


    @Query("SELECT avg(r.rating) FROM Review r where r.barberShop.id =:barberShopId group by r.barberShop")
    Double getRatingByBarberShopId(@Param("barberShopId") Long barberShopId);

}
