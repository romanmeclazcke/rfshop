package org.example.rfshop.Post.Infrastructure.Repository;

import org.example.rfshop.Post.Infrastructure.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
    @Query("SELECT p FROM Post P WHERE p.barberShop.id = :barberId")
    List<Post> findAllByBarberShopId(Long barberId);
}
