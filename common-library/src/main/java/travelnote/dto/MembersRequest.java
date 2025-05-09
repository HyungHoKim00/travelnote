package travelnote.dto;

import java.util.List;

public record MembersRequest(
        List<Long> memberIds
) {

}
