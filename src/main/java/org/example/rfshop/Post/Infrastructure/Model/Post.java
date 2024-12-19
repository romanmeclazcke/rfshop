package org.example.rfshop.Post.Infrastructure.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.example.rfshop.BarberShop.Infrastructure.Model.BarberShop;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private String urlImage;

    private Date createdAt;

    @ManyToOne
    private BarberShop barberShop;
}
