package me.jeongwook.jplan.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSignUpRequest {
    private String email;
    private String password;
}
