package co.edu.meli.domain.model.sat;

import co.edu.meli.domain.model.Point;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Skywalker {

    private final Point coordinates;
    private final double sato;
    private final double kenobi;

}
