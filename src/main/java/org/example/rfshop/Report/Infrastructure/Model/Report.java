package org.example.rfshop.Report.Infrastructure.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.rfshop.Report.Domain.Dto.ReportStatus.ReportStatus;
import org.example.rfshop.Review.Infrastructure.Model.Review;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private Date createAt;

    private String status;

    @ManyToOne
    private Review review;


    @PrePersist
    protected void onCreate() {
        createAt = new Date();
        status = ReportStatus.PENDING.toString();
    }

}
