package org.example.rfshop.Post.Infrastructure.Controller;

import org.example.rfshop.Post.Application.CreatePostUseCase.CreatePostUseCase;
import org.example.rfshop.Post.Application.CreatePostUseCase.CreatePostUseCaseImpl;
import org.example.rfshop.Post.Application.DeletePostUseCase.DeletePostUseCase;
import org.example.rfshop.Post.Application.GetPostByBarberShopIdUseCase.GetPostByBarberShopIdUseCase;
import org.example.rfshop.Post.Application.GetPostByIdUseCase.GetPostByIdUseCase;
import org.example.rfshop.Post.Application.UpdatePostUseCase.UpdatePostUseCase;
import org.example.rfshop.Post.Domain.Dto.Request.CreatePostDto;
import org.example.rfshop.Post.Domain.Dto.Request.UpdatePostDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final CreatePostUseCase createPostUseCase;
    private final UpdatePostUseCase updatePostUseCase;
    private final GetPostByIdUseCase getPostByIdUseCase;
    private final GetPostByBarberShopIdUseCase getPostByBarberShopIdUseCase;
    private final DeletePostUseCase deletePostUseCase;

    @Autowired
    public PostController(CreatePostUseCase createPostUseCase, UpdatePostUseCase updatePostUseCase, GetPostByIdUseCase getPostByIdUseCase,GetPostByBarberShopIdUseCase getPostByBarberShopIdUseCase, DeletePostUseCase deletePostUseCase) {
        this.createPostUseCase = createPostUseCase;
        this.updatePostUseCase = updatePostUseCase;
        this.getPostByBarberShopIdUseCase = getPostByBarberShopIdUseCase;
        this.getPostByIdUseCase = getPostByIdUseCase;
        this.deletePostUseCase = deletePostUseCase;
    }

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
