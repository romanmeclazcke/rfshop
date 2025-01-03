package org.example.rfshop.Email.Application.SendEmailUseCase;

import org.example.rfshop.Email.Domain.EmailTypes.Email;
import org.example.rfshop.Email.Infrastructure.Config.EmailConfig;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Service
public class SendEmailUseCaseImpl implements SendEmailUseCase {

    private final Session emailSession;
    private final EmailConfig emailConfig;

    public SendEmailUseCaseImpl(Session emailSession, EmailConfig emailConfig) {
        this.emailSession = emailSession;
        this.emailConfig = emailConfig;
    }

    @Override
    public boolean execute(Email email) throws MessagingException {
        Message message = new MimeMessage(emailSession);
        message.setFrom(new InternetAddress(emailConfig.getUser()));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email.getTo()));
        message.setSubject(email.getSubject());
        message.setContent(email.generateEmail(), "text/html");
        Transport.send(message);
        return true;
    }
}
