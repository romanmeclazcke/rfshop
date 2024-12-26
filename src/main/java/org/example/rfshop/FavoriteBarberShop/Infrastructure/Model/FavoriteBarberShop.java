package org.example.rfshop.FavoriteBarberShop.Infrastructure.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.rfshop.BarberShop.Infrastructure.Model.BarberShop;
import org.example.rfshop.User.Infrastructure.Model.User;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "favorite_barber_shop")
public class FavoriteBarberShop {

    @EmbeddedId
    private FavoriteBarberShopId id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("barberShopId")
    @JoinColumn(name = "barber_shop_id")
    private BarberShop barberShop;

    @Column
    private Date createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
    }
}