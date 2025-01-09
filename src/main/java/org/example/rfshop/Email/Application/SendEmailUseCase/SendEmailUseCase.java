package org.example.rfshop.Email.Application.SendEmailUseCase;

import org.example.rfshop.Email.Domain.EmailTypes.Email;

import javax.mail.MessagingException;

public interface SendEmailUseCase {
    public boolean execute(Email email) throws MessagingException;
}
