package org.example.rfshop.BarberShop.Domain.Dto.Request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
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

    @NotNull(message = "the rol cannot be null")
    private Integer streetNumber;

    private String phone;

    @NotNull(message = "The number of chairs cannot be null")
    private Integer chair;

}
