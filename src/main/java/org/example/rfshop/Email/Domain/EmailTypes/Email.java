package org.example.rfshop.Email.Domain.EmailTypes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Email {
    private String to;
    private String subject;

    public abstract String generateEmail();

}
