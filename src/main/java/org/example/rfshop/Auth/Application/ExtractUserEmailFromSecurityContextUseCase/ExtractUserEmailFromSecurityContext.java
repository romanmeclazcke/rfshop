package org.example.rfshop.Auth.Application.ExtractUserEmailFromSecurityContextUseCase;

import org.springframework.security.core.context.SecurityContext;

public interface ExtractUserEmailFromSecurityContext {
    String execute(SecurityContext securityContext);
}
