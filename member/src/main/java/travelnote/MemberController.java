package travelnote;

import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import travelnote.dto.SignupRequest;
import travelnote.dto.SignupResponse;

@RestController
@RequestMapping("/members")
public class MemberController {

    private MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<Void> signup(@RequestBody SignupRequest request) {
        SignupResponse response = memberService.create(request);
        return ResponseEntity.created(URI.create("/members/" + response.memberId()))
                .build();
    }
}
