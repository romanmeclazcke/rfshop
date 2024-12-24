package org.example.rfshop.BarberShop.Infrastructure.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.rfshop.Court.Infrastructure.Model.Court;
import org.example.rfshop.Post.Infrastructure.Model.Post;
import org.example.rfshop.User.Infrastructure.Model.User;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BarberShop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private Integer streetNumber;

    @Column(nullable = true)
    private String phone;

    @Column(nullable = false)
    private Integer chair; //number of avalible chairs

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private User owner;

    @OneToMany(mappedBy = "barberShop")
    private List<Court> courts;

    @OneToMany (mappedBy = "barberShop")
    private List<Post> post;

}
