package org.example.rfshop.BarberShop.Domain.Dto.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BarberShopResponseDto {
    private Long id;
    private String name;
    private String city;
    private String street;
    private Integer streetNumber;
    private String phone;
    private Integer chair;
}
