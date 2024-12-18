package org.example.rfshop.BarberShop.Infrastructure.Repository;

import org.example.rfshop.BarberShop.Infrastructure.Model.BarberShop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BarberShopRepository extends JpaRepository<BarberShop,Long> {
}
