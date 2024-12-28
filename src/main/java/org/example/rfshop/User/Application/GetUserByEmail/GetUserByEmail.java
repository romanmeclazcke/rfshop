package org.example.rfshop.User.Application.GetUserByEmail;

import org.example.rfshop.User.Infrastructure.Model.User;

public interface GetUserByEmail {
    User execute(String email);
}
