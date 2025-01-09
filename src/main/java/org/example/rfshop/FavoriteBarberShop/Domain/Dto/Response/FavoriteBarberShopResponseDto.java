package org.example.rfshop.FavoriteBarberShop.Domain.Dto.Response;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.rfshop.BarberShop.Domain.Dto.Response.BarberShopResponseDto;
import org.example.rfshop.FavoriteBarberShop.Infrastructure.Model.FavoriteBarberShopId;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteBarberShopResponseDto {
    private FavoriteBarberShopId id;
    private BarberShopResponseDto barberShop;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date createdAt;
}
