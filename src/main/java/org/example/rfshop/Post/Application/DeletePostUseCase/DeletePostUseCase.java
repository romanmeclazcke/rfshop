package org.example.rfshop.Post.Application.DeletePostUseCase;

import java.io.IOException;

public interface DeletePostUseCase {
    void execute(Long postId) throws IOException;
}
