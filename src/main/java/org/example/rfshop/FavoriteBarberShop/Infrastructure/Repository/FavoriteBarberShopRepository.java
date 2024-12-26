package org.example.rfshop.FavoriteBarberShop.Infrastructure.Repository;

import org.example.rfshop.FavoriteBarberShop.Domain.Dto.Response.FavoriteBarberShopResponseDto;
import org.example.rfshop.FavoriteBarberShop.Infrastructure.Model.FavoriteBarberShop;
import org.example.rfshop.FavoriteBarberShop.Infrastructure.Model.FavoriteBarberShopId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteBarberShopRepository extends JpaRepository<FavoriteBarberShop, FavoriteBarberShopId> {

    @Query("SELECT favoriteBarberShop FROM FavoriteBarberShop favoriteBarberShop WHERE favoriteBarberShop.id.userId=:userId")
    List<FavoriteBarberShop> findAllFavoriteBarberShopByUserId(@Param("userId") Long userId);
}
