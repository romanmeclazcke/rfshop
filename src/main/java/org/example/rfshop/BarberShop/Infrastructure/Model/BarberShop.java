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

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)

    private String city;

    @Column(nullable = false)

    private String street;

    @Column(nullable = false)
    private String number;

    @Column(nullable = true)
    private String phone;

    @Column(nullable = false)
    private Integer chair; //number of avalible chairs

    @OneToOne()
    @JoinColumn(nullable = false)
    private User owner;

}
