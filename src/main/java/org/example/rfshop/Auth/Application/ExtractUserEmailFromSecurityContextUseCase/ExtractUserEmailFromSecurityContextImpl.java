package org.example.rfshop.Auth.Application.ExtractUserEmailFromSecurityContextUseCase;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Service;

@Service
public class ExtractUserEmailFromSecurityContextImpl implements ExtractUserEmailFromSecurityContext {

    @Override
    public String execute(SecurityContext securityContext) {
        if (securityContext.getAuthentication().getName().isEmpty()) {
            throw new SecurityException("Invalid format to security context, email must be present");
        }
        return securityContext.getAuthentication().getName();
    }
}
