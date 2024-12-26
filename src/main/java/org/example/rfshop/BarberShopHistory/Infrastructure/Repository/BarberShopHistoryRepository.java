package org.example.rfshop.BarberShopHistory.Infrastructure.Repository;

import org.example.rfshop.BarberShopHistory.Infrastructure.Model.BarberShopHistory;
import org.example.rfshop.BarberShopHistory.Infrastructure.Model.BarberShopHistoryId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BarberShopHistoryRepository extends JpaRepository<BarberShopHistory, BarberShopHistoryId> {
}
