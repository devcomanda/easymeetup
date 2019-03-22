package com.devcomanda.easymeetup.controller.model.security;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class NewUserRequest {
    public static final int PASSWORD_WITH_MIN_LENGTH = 3;
    public static final int PASSWORD_WITH_MAX_LENGTH = 50;

    @NotBlank
    @Email
    @Size(min = 5, max = 100)
    private String email;

    @Size(min = NewUserRequest.PASSWORD_WITH_MIN_LENGTH, max = NewUserRequest.PASSWORD_WITH_MAX_LENGTH)
    private String password;

    public NewUserRequest(@NotBlank @Email @Size(min = 5, max = 100) String email,
                          @Size(min = NewUserRequest.PASSWORD_WITH_MIN_LENGTH,
                          max = NewUserRequest.PASSWORD_WITH_MAX_LENGTH) String password) {
        this.email = email;
        this.password = password;
    }

    public NewUserRequest(String firstName, String lastName,
                          @NotBlank @Email @Size(min = 5, max = 100) String email,
                          @Size(min = NewUserRequest.PASSWORD_WITH_MIN_LENGTH,
                          max = NewUserRequest.PASSWORD_WITH_MAX_LENGTH) String password) {
        this.email = email;
        this.password = password;
    }
}
