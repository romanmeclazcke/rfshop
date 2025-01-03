package org.example.rfshop.Email.Application.SendEmailUseCase;

import org.example.rfshop.Email.Domain.EmailTypes.Email;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public interface SendEmailUseCase {
    public boolean execute(Email email) throws MessagingException;
}
