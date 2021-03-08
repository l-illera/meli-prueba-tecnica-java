package co.edu.meli.domain.usecase;

import co.edu.meli.domain.model.Point;
import co.edu.meli.domain.model.SpaceshipReference;
import co.edu.meli.domain.provider.SatelliteProvider;
import data.SatellitesDataProvider;
import groovy.util.logging.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;

@Slf4j
public class CalculatePositionUseCaseTest {
    @Mock
    SatelliteProvider satellites;
    @InjectMocks
    CalculatePositionUseCase calculatePositionUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testProcess() {
        final SpaceshipReference spaceship = SpaceshipReference.builder()
                .toKenobi(485.696d)
                .toSkywalker(266.083d)
                .toSato(600.5d)
                .build();
        when(satellites.getKenobi()).thenReturn(SatellitesDataProvider.kenobi());
        when(satellites.getSkywalker()).thenReturn(SatellitesDataProvider.skywalker());
        when(satellites.getSato()).thenReturn(SatellitesDataProvider.sato());
        Point result = calculatePositionUseCase.process(spaceship);
        Assertions.assertEquals(-100d, result.getEast(), "X position not compliant.");
        Assertions.assertEquals(75.5d, result.getNorth(), "Y position not compliant.");
    }
}