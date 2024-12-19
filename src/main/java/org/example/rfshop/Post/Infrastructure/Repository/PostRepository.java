package org.example.rfshop.Post.Infrastructure.Repository;

import org.example.rfshop.Post.Infrastructure.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
}
