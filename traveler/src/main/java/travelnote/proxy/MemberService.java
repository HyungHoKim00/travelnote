package travelnote.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import travelnote.dto.MembersRequest;
import travelnote.dto.MembersResponse;

@FeignClient(name = "member-service")
public interface MemberService {

    @GetMapping("/members")
    MembersResponse getMembersById(@RequestBody MembersRequest request);
}

