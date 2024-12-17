package org.example.rfshop.BarberShop.Domain.Dto.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBarberShopDto {
    private String name;

    private String city;

    private String street;

    private Integer streetNumber;

    private Integer chair;
}
