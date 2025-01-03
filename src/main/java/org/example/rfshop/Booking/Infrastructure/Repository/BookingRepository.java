package org.example.rfshop.Booking.Infrastructure.Repository;

import org.example.rfshop.Booking.Infrastructure.Model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
}
