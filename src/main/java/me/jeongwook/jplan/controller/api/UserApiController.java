package me.jeongwook.jplan.controller.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.jeongwook.jplan.domain.User;
import me.jeongwook.jplan.dto.Header;
import me.jeongwook.jplan.dto.request.SignupRequest;
import me.jeongwook.jplan.dto.response.SignupResponse;
import me.jeongwook.jplan.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserApiController {

    private final UserService userService;

    @PostMapping("/check-email")
    public Header<Boolean> checkEmail(@RequestBody Header<SignupRequest> request){
            User user = request.getData().toEntity();
            try {
                if (user.getEmail() != null)
                    return Header.OK(userService.checkEmail(user.getEmail()));
                throw new RuntimeException("Email is NULL");
            } catch (Exception e){
                return Header.ERROR(e.getClass().getSimpleName()+e.getMessage());
            }
    }
    @PostMapping("/check-contact-number")
    public Header<Boolean> checkContactNumber(@RequestBody Header<SignupRequest> request){
        User user = request.getData().toEntity();
        try{
            if(user.getContactNumber() != null)
                return Header.OK(userService.checkContactNumber(user.getContactNumber()));
            throw new RuntimeException("Contact Number Data is NULL");
        } catch (Exception e){
            return Header.ERROR(e.getClass().getSimpleName()+e.getMessage());
        }
    }
    @PostMapping("/signup")
    public Header<SignupResponse> signup(@RequestBody Header<SignupRequest> request){
        return Header.OK(userService.signupUser(request.getData()));
    }
}

