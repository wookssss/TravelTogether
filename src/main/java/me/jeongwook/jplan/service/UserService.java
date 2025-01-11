package me.jeongwook.jplan.service;

import com.sun.jdi.request.DuplicateRequestException;
import lombok.*;
import me.jeongwook.jplan.domain.User;
import me.jeongwook.jplan.dto.Header;
import me.jeongwook.jplan.dto.request.SignupRequest;
import me.jeongwook.jplan.dto.response.SignupResponse;
import me.jeongwook.jplan.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Boolean checkEmail(String email){
        return userRepository.existsByEmail(email);
    }

    public Boolean checkContactNumber(String contactNumber){
        return userRepository.existsByContactNumber(contactNumber);
    }

    public SignupResponse signupUser(SignupRequest request){
        User user = request.toEntity();
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return SignupResponse.of(user);
    }
}
