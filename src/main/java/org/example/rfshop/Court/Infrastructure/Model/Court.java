package org.example.rfshop.Court.Infrastructure.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.rfshop.BarberShop.Infrastructure.Model.BarberShop;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Court {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Date startDate;

    @ManyToOne
    private BarberShop barberShop;
}
