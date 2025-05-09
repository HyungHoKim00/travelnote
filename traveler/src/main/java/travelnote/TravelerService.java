package travelnote;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import travelnote.dto.MembersRequest;
import travelnote.dto.MembersResponse;
import travelnote.proxy.MemberService;

@Service
@RequiredArgsConstructor
public class TravelerService {

    private final TravelerRepository travelerRepository;
    private final MemberService memberService;

    public MembersResponse getTravelersByTripId(long tripId) {
        List<Traveler> travelers = travelerRepository.findAllByTripId(tripId);
        List<Long> memberIds = travelers.stream()
                .map(Traveler::getMemberId)
                .toList();
        return memberService.getMembersById(new MembersRequest(memberIds));
    }
}
