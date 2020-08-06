package org.sid.securityservice.entities;

import lombok.Data;

@Data
public class UserForm {
    String username;
    String password;
    String confirmedPassword;
}
