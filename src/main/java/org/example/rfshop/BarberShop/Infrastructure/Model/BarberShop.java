package org.example.rfshop.BarberShop.Infrastructure.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.rfshop.User.Infrastructure.Model.User;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BarberShop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String city;

    private String street;

    private String number;

    @OneToOne()
    private User owner;
}
