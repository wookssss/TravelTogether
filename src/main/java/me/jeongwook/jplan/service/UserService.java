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
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Not Found User: "+email));
    }

    public Boolean checkEmail(String email){
        return userRepository.existsByEmail(email);
    }

    public Boolean checkContactNumber(String contactNumber){
        return userRepository.existsByContactNumber(contactNumber);
    }

    public SignupResponse signup(SignupRequest request) throws DuplicateRequestException{
        if(!userRepository.existsByEmail(request.getEmail())){
            User user = request.toEntity();
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return SignupResponse.builder()
                    .id(user.getId())
                    .email(user.getEmail())
                    .contactNumber(user.getContactNumber())
                    .build();
        }
        throw new DuplicateRequestException("이미 존재하는 ID 입니다.");
    }
}
