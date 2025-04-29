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

    public SignupResponse create(SignupRequest request) {
        validateEmailExist(request.email());

        String encodedPassword = authService.encode(request.password());
        Member member = new Member(request.email(), encodedPassword);
        Member savedMember = memberRepository.save(member);
        return new SignupResponse(savedMember.getId());
    }

    private void validateEmailExist(String email) {
        if (memberRepository.existsByEmail(email)) {
            throw new IllegalArgumentException();
        }
    }

    public String login(LoginRequest request) {
        Member member = memberRepository.findByEmail(request.email());
        authService.validatePassword(request.password(), member.getPassword());
        return authService.createToken(member);
    }
}
