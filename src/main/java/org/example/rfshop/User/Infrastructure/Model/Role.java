package org.example.rfshop.User.Infrastructure.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;


    @Column(nullable = false, unique = true)
    private String name;
}
