package org.example.rfshop.Post.Infrastructure.Controller;

import org.example.rfshop.Post.Domain.Dto.Request.CreatePostDto;
import org.example.rfshop.Post.Domain.Dto.Request.UpdatePostDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
public class PostController {

    @GetMapping("/barber-shop/{barberId}")
    public ResponseEntity<?> getAllPostByBarberShopId(@PathVariable Long barberId) {
      return null;
    }

    @GetMapping("/{postId}")
    public ResponseEntity<?> getPostById(@PathVariable Long postId) {
        return null;
    }

    @PostMapping("/barber-shop/{barberId}")
    public ResponseEntity<?> createPost(@PathVariable Long barberId, @RequestBody CreatePostDto createPostDto) {
        return null;
    }

    @PatchMapping("/{postId}")
    public ResponseEntity<?> updatePost(@PathVariable Long postId, @RequestBody UpdatePostDto updatePostDto) {
        return null;
    }


    @DeleteMapping("/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Long postId) {
        return null;
    }

}
