package org.example.rfshop.BarberShop.Domain.Dto.Request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBarberShopDto {

    @NotEmpty(message = "The name of barber cannot be empty")
    private String name;

    @NotEmpty(message = "The city cannot be empty")
    private String city;

    @NotEmpty(message = "The street cannot be empty")
    private String street;

    @NotEmpty(message = "The street number cannot be empty")
    private Integer streetNumber;

    @NotEmpty(message = "the number of chair cannot be empty")
    private Integer chair;
}
