package co.edu.meli.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;
import java.util.Arrays;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class SatelliteDTO implements Serializable {

    @EqualsAndHashCode.Include
    private String name;
    private double distance;
    private String[] message;
}
