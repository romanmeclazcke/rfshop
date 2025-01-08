package org.example.rfshop.Auth.Application.ChangePassword;

import org.example.rfshop.Auth.Domain.Dto.Request.ChangePasswordDto;

public interface ChangePasswordUseCase {
    void execute(ChangePasswordDto changePasswordDto);
}
