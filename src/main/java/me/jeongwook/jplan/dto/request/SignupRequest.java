package me.jeongwook.jplan.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import me.jeongwook.jplan.domain.User;
import me.jeongwook.jplan.enumclass.SignupType;

@Getter
@Setter
public class SignupRequest {
    private Long id;

    @NotBlank
    @Email(message = "유효한 이메일 주소를 입력하세요.")
    private String email;

    @NotBlank
    @Size(min = 8, max = 16)
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%^&*]).{8,16}$", message = "비밀번호는 최소 8자 이상, 숫자, 대소문자, 특수문자를 포함해야 합니다.")
    private String password;

    @NotBlank
    @Pattern(regexp = "^[가-힣]+$", message = "이름은 한글만 입력 가능합니다.")
    private String name;

    @NotBlank
    @Pattern(regexp = "^(01[0-9])-(\\d{3,4})-(\\d{4})$", message = "유효한 한국 핸드폰 번호를 입력하세요.")
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
