package org.example.rfshop.Report.Infrastructure.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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

    private Date date;

    private String status;

    @ManyToOne
    private Review review;


    @PrePersist
    protected void onCreate() {
        date = new Date();
    }

}
