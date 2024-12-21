package org.example.rfshop.Cloudinary.Application.DeleteImageUseCase;

import com.cloudinary.Cloudinary;

public class DeleteImageUseCaseImpl implements DeleteImageUseCase{

    private Cloudinary cloudinary;

    public DeleteImageUseCaseImpl(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }


}
