package me.jeongwook.jplan.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SignupResponse {
    private Long id;
    private String email;
    private String contactNumber;
}
