package travelnote;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import travelnote.dto.SignupRequest;
import travelnote.dto.SignupResponse;

@Service
@RequiredArgsConstructor
public class MemberService {

    private MemberRepository memberRepository;

    public SignupResponse create(SignupRequest signupRequest) {
        Member member = new Member(signupRequest.email(), signupRequest.password());
        Member savedMember = memberRepository.save(member);
        return new SignupResponse(savedMember.getId());
    }
}
