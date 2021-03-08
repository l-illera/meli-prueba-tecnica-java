package co.edu.meli.domain.usecase;

import co.edu.meli.domain.model.Point;
import co.edu.meli.domain.model.SpaceshipReference;
import co.edu.meli.domain.provider.SatelliteProvider;
import co.edu.meli.domain.utilities.AngleCalculatorUtil;
import co.edu.meli.domain.utilities.CardinalCalculatorUtil;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@Slf4j
@ApplicationScoped
public class CalculatePositionUseCase {

    private final SatelliteProvider satellites;

    @Inject
    public CalculatePositionUseCase(SatelliteProvider satellites) {
        this.satellites = satellites;
    }

    public Point process(final SpaceshipReference spaceship) {
        try {
            final var positionAlpha = findAlpha(spaceship);
            final var positionBravo = findBravo(spaceship);
            assertPositions(positionAlpha, positionBravo);
            return positionAlpha;
        } catch (AssertionError | Exception ex) {
            log.error(ex.getLocalizedMessage(), ex);
            return null;
        }
    }

    private void assertPositions(Point positionAlpha, Point positionBravo) {
        assert positionAlpha.getNorth() == positionBravo.getNorth();
        assert positionAlpha.getEast() == positionBravo.getEast();
    }

    private Point findBravo(SpaceshipReference spaceship) {
        final var beta = AngleCalculatorUtil.beta(satellites.getSato().getSkywalker(), spaceship.getToSkywalker(), spaceship.getToSato());
        final var azimut = AngleCalculatorUtil.azimuts(satellites.getSato().getCoordinates(), satellites.getSkywalker().getCoordinates()) + beta;
        final var north = CardinalCalculatorUtil.north(azimut, spaceship.getToSato(), satellites.getSato().getCoordinates());
        final var east = CardinalCalculatorUtil.east(azimut, spaceship.getToSato(), satellites.getSato().getCoordinates());
        return new Point(east, north);
    }

    private Point findAlpha(final SpaceshipReference spaceship) {
        final var beta = AngleCalculatorUtil.beta(satellites.getKenobi().getSkywalker(), spaceship.getToSkywalker(), spaceship.getToKenobi());
        final var azimut = AngleCalculatorUtil.azimuts(satellites.getKenobi().getCoordinates(), satellites.getSkywalker().getCoordinates()) - beta;
        final var north = CardinalCalculatorUtil.north(azimut, spaceship.getToKenobi(), satellites.getKenobi().getCoordinates());
        final var east = CardinalCalculatorUtil.east(azimut, spaceship.getToKenobi(), satellites.getKenobi().getCoordinates());
        return new Point(east, north);
    }

}
