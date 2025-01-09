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
    private LocalDateTime bookingEnd;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private Boolean reserved=false;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private Boolean state=false;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(optional = false)
    private BarberShop barberShop;
}
