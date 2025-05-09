package travelnote.dto;

import java.util.List;

public record MembersResponse(
        List<MemberDto> memberDtos
) {

}
