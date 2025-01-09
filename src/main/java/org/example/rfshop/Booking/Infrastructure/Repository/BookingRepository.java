package org.example.rfshop.Booking.Infrastructure.Repository;

import org.example.rfshop.Booking.Infrastructure.Model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query("SELECT b FROM Booking b WHERE b.barberShop.id = :barberShopId AND b.bookingStart BETWEEN :startDate AND :endDate")
    List<Booking> findBookingsByBarberShopAndMonth(
            @Param("barberShopId") Long barberShopId,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate
    );
}

