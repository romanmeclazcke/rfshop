package org.example.rfshop.User.Infrastructure.Repository;

import org.example.rfshop.User.Infrastructure.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
