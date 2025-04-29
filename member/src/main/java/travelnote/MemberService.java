package travelnote;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import travelnote.auth.AuthService;
import travelnote.dto.LoginRequest;
import travelnote.dto.SignupRequest;
import travelnote.dto.SignupResponse;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final AuthService authService;

    public SignupResponse create(SignupRequest signupRequest) {
        validateEmailExist(signupRequest.email());

        String encodedPassword = authService.encode(signupRequest.password());
        Member member = new Member(signupRequest.email(), encodedPassword);
        Member savedMember = memberRepository.save(member);
        return new SignupResponse(savedMember.getId());
    }

    private void validateEmailExist(String email) {
        if (memberRepository.existsByEmail(email)) {
            throw new IllegalArgumentException();
        }
    }

    public String login(LoginRequest loginRequest) {
        Member member = memberRepository.findByEmail(loginRequest.email());
        authService.validatePassword(loginRequest.password(), member.getPassword());
        return authService.createToken(member);
    }
}
