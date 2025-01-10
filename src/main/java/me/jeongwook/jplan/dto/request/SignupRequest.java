package me.jeongwook.jplan.dto.request;

import lombok.Getter;
import lombok.Setter;
import me.jeongwook.jplan.domain.User;
import me.jeongwook.jplan.enumclass.SignupType;

@Getter
@Setter
public class SignupRequest {
    private Long id;
    private String email;
    private String password;
    private String name;
    private String contactNumber;
    private SignupType signupType;

    public User toEntity(){
        return User.builder()
                .id(this.id)
                .email(this.email)
                .password(this.password)
                .name(this.name)
                .contactNumber(this.contactNumber)
                .signupType(this.signupType)
                .build();
    }
}
