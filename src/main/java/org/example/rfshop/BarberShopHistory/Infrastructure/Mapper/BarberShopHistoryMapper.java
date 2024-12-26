package org.example.rfshop.BarberShopHistory.Infrastructure.Mapper;

import org.example.rfshop.BarberShopHistory.Domain.Dto.Response.BarberShopHistoryResponseDto;
import org.example.rfshop.BarberShopHistory.Infrastructure.Model.BarberShopHistory;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BarberShopHistoryMapper {

    BarberShopHistoryResponseDto toDto(BarberShopHistory barberShopHistory);
}
