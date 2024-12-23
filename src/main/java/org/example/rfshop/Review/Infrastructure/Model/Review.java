package org.example.rfshop.Review.Infrastructure.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.rfshop.BarberShop.Infrastructure.Model.BarberShop;
import org.example.rfshop.User.Infrastructure.Model.User;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String review;
    @Column(nullable = false)
    private Long rating; //points from 1 to 5

    @ManyToOne
    private BarberShop barberShop;

    @ManyToOne
    private User user;

    private Date createdAt;

    private Date updateAt;

    @PrePersist
    private void onCreate (){
        createdAt = new Date();
        updateAt = new Date();
    }

    @PreUpdate()
    private void onUpdate(){
        updateAt = new Date();
    }
}
