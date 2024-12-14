package org.example.rfshop.User.Infrastructure.Repository;

import org.example.rfshop.User.Infrastructure.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
}
