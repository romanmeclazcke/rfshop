package org.example.rfshop.Post.Application.DeletePostUseCase;

import jakarta.persistence.EntityNotFoundException;
import org.example.rfshop.Cloudinary.Application.DeleteImageUseCase.DeleteImageUseCase;
import org.example.rfshop.Post.Infrastructure.Model.Post;
import org.example.rfshop.Post.Infrastructure.Repository.PostRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class DeletePostUseCaseImpl implements DeletePostUseCase {

    private final PostRepository postRepository;
    private final DeleteImageUseCase   deleteImageUseCase;

    public DeletePostUseCaseImpl(PostRepository postRepository, DeleteImageUseCase deleteImageUseCase) {
        this.postRepository = postRepository;
        this.deleteImageUseCase = deleteImageUseCase;
    }
    @Override
    public void execute(Long postId) throws IOException {
        Post post = postRepository.findById(postId).orElseThrow(() -> new EntityNotFoundException("Post with id "+ postId + " not found"));
        if(!post.getSecureUrl().isEmpty()){
           this.deleteImageUseCase.execute(post.getSecureUrl());
        }
        postRepository.delete(post);
    }
}
