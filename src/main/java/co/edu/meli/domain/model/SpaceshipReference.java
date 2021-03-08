package co.edu.meli.domain.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SpaceshipReference {
    private final double toKenobi;
    private final double toSkywalker;
    private final double toSato;

    @Override
    public String toString() {
        return "{" +
                "toKenobi=" + toKenobi +
                ", toSkywalker=" + toSkywalker +
                ", toSato=" + toSato +
                '}';
    }
}
