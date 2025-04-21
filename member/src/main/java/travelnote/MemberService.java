package travelnote;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import travelnote.auth.PasswordValidator;
import travelnote.dto.SignupRequest;
import travelnote.dto.SignupResponse;

@Service
@RequiredArgsConstructor
public class MemberService {

    private MemberRepository memberRepository;
    private PasswordValidator passwordValidator;

    public SignupResponse create(SignupRequest signupRequest) {
        validateEmailExist(signupRequest.email());

        String encodedPassword = passwordValidator.encode(signupRequest.password());
        Member member = new Member(signupRequest.email(), encodedPassword);
        Member savedMember = memberRepository.save(member);
        return new SignupResponse(savedMember.getId());
    }

    private void validateEmailExist(String email) {
        if(memberRepository.existsByEmail(email)) {
            throw new IllegalArgumentException();
        }
    }
}
