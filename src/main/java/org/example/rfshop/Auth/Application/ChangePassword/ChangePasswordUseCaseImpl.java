package org.example.rfshop.Auth.Application.ChangePassword;

import org.example.rfshop.Auth.Application.ExtractUserEmailFromSecurityContextUseCase.ExtractUserEmailFromSecurityContext;
import org.example.rfshop.Auth.Domain.Dto.Request.ChangePasswordDto;
import org.example.rfshop.Auth.Infrastructure.Exception.InvalidPassword;
import org.example.rfshop.User.Application.GetUserByEmail.GetUserByEmail;
import org.example.rfshop.User.Infrastructure.Model.User;
import org.example.rfshop.User.Infrastructure.Repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ChangePasswordUseCaseImpl implements ChangePasswordUseCase {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ExtractUserEmailFromSecurityContext extractUserEmailFromSecurityContext;
    private final GetUserByEmail getUserByEmail;

    public ChangePasswordUseCaseImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, ExtractUserEmailFromSecurityContext extractUserEmailFromSecurityContext, GetUserByEmail getUserByEmail) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.extractUserEmailFromSecurityContext = extractUserEmailFromSecurityContext;
        this.getUserByEmail = getUserByEmail;
    }


    @Override
    public void execute(ChangePasswordDto  changePasswordDto) {
        User user = this.getUserByEmail.execute(this.extractUserEmailFromSecurityContext.execute(SecurityContextHolder.getContext()));
        if(!this.passwordEncoder.matches(changePasswordDto.getOldPassword(), user.getPassword())){
            throw new InvalidPassword("Invalid password");
        }
        user.setPassword(this.passwordEncoder.encode(changePasswordDto.getNewPassword()));
        this.userRepository.save(user);
    }
}
