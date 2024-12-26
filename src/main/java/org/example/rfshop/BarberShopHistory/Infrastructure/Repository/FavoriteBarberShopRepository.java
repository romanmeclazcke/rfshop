package org.example.rfshop.BarberShopHistory.Infrastructure.Repository;

import org.example.rfshop.BarberShopHistory.Infrastructure.Model.FavoriteBarberShop;
import org.example.rfshop.BarberShopHistory.Infrastructure.Model.FavoriteBarberShopId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteBarberShopRepository extends JpaRepository<FavoriteBarberShop, FavoriteBarberShopId> {
}
