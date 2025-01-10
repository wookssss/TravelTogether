package me.jeongwook.jplan.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SignupType {
    JPALN("JPLAN", "이메일 회원가입"),
    KAKAO("KAKAO", "카카오 회원가입");

    private String type;
    private String description;
}
