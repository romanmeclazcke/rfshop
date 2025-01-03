package org.example.rfshop.Report.Infrastructure.Repository;

import org.example.rfshop.Report.Infrastructure.Model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends JpaRepository<Report,Long> {
}
