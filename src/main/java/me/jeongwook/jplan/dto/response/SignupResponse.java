package me.jeongwook.jplan.dto.response;

import lombok.Builder;
import lombok.Getter;
import me.jeongwook.jplan.domain.User;

@Builder
@Getter
public class SignupResponse {
    private Long id;
    private String email;

    public static SignupResponse of(User user){
        return SignupResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .build();
    }
}
