package travelnote;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class Traveler {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "traveler_id")
    private Long travelerId;

    private Long memberId;

    private Long tripId;

    public Traveler(Long memberId, Long tripId) {
        this.memberId = memberId;
        this.tripId = tripId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Traveler traveler = (Traveler) o;
        return Objects.equals(travelerId, traveler.travelerId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(travelerId);
    }

    @Override
    public String toString() {
        return "Traveler{" +
                "travelerId=" + travelerId +
                ", memberId=" + memberId +
                ", tripId=" + tripId +
                '}';
    }
}
