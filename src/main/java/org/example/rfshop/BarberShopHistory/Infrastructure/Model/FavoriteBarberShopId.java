package org.example.rfshop.BarberShopHistory.Infrastructure.Model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteBarberShopId implements Serializable {

    private Long userId;
    private Long barberShopId;


}
