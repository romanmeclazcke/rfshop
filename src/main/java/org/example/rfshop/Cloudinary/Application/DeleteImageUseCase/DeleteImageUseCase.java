package org.example.rfshop.Cloudinary.Application.DeleteImageUseCase;

import java.io.IOException;

public interface DeleteImageUseCase {

    Boolean execute(String secureUrl) throws IOException;
}
