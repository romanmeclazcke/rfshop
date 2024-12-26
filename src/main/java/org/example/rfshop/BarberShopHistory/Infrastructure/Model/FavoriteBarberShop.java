package org.example.rfshop.BarberShopHistory.Infrastructure.Model;

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
public class FavoriteBarberShop {

    @EmbeddedId
    private FavoriteBarberShopId id;

    @ManyToOne
    private User user;

    @ManyToOne
    private BarberShop barberShop;

    @Column
    private Date date;

    @PrePersist
    protected void onCreate() {
        date = new Date();
    }
}
