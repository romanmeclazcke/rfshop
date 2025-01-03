package org.example.rfshop.Booking.Infrastructure.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.rfshop.BarberShop.Infrastructure.Model.BarberShop;
import org.example.rfshop.User.Infrastructure.Model.User;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime bookingStart;
    @Column(nullable = false)
    private Boolean state;

    @ManyToOne
    private User user;

    @ManyToOne
    private BarberShop barberShop;
}
