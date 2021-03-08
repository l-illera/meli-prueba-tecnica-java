package co.edu.meli.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AllSatelliteRequestDTO implements Serializable {
    private List<SatelliteDTO> satellites;

    public Double findDistance(final String satelliteName) {
        return this.getSatellites().stream()
                .filter(sat -> sat.getName().equalsIgnoreCase(satelliteName))
                .map(SatelliteDTO::getDistance)
                .findAny().orElseThrow();
    }

    public List<String[]> messagesList() {
        return this.getSatellites().stream().map(SatelliteDTO::getMessage).collect(Collectors.toList());
    }
}
