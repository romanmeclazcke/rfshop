package org.example.rfshop.Court.Infrastructure.Repository;

import org.example.rfshop.Court.Infrastructure.Model.Court;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourtRepository extends JpaRepository<Court,Long> {
}
