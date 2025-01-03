package org.example.rfshop.User.Infrastructure.Model;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;
    // Return the email as the username
    @Getter
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String password;

    @ManyToOne()
    private Role role;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.getName()));
    }

    @Override
    public String getUsername() {
        return this.email; // Return the email as the username
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Return true if the account is not expired
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Return true if the account is not locked
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Return true if the credentials are not expired
    }

    @Override
    public boolean isEnabled() {
        return true; // Return true if the account is enabled
    }
}
