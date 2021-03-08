package co.edu.meli.infrastructure;

import co.edu.meli.domain.model.Point;
import co.edu.meli.domain.model.sat.Kenobi;
import co.edu.meli.domain.model.sat.Sato;
import co.edu.meli.domain.model.sat.Skywalker;
import co.edu.meli.domain.provider.SatelliteProvider;
import co.edu.meli.domain.utilities.DistanceCalculatorUtil;
import lombok.Getter;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SatelliteProviderImpl implements SatelliteProvider {

    @Getter
    private final Kenobi kenobi;
    @Getter
    private final Skywalker skywalker;
    @Getter
    private final Sato sato;


    public SatelliteProviderImpl(@ConfigProperty(name = "kenobi.x") final double keX,
                                 @ConfigProperty(name = "kenobi.y") final double keY,
                                 @ConfigProperty(name = "skywalker.x") final double skX,
                                 @ConfigProperty(name = "skywalker.y") final double skY,
                                 @ConfigProperty(name = "sato.x") final double saX,
                                 @ConfigProperty(name = "sato.y") final double saY) {
        final var kenobi = new Point(keX, keY);
        final var skywalker = new Point(skX, skY);
        final var sato = new Point(saX, saY);
        this.kenobi = buildKenobi(kenobi, skywalker, sato);
        this.skywalker = buildSkywalker(kenobi, skywalker, sato);
        this.sato = buildSato(kenobi, skywalker, sato);
    }

    private Sato buildSato(Point kenobi, Point skywalker, Point sato) {
        return Sato.builder().coordinates(sato)
                .skywalker(DistanceCalculatorUtil.calculate(skywalker, sato))
                .kenobi(DistanceCalculatorUtil.calculate(kenobi, sato)).build();
    }

    private Skywalker buildSkywalker(Point kenobi, Point skywalker, Point sato) {
        return Skywalker.builder().coordinates(skywalker)
                .sato(DistanceCalculatorUtil.calculate(skywalker, sato))
                .kenobi(DistanceCalculatorUtil.calculate(kenobi, skywalker)).build();
    }

    private Kenobi buildKenobi(Point kenobi, Point skywalker, Point sato) {
        return Kenobi.builder().coordinates(kenobi)
                .skywalker(DistanceCalculatorUtil.calculate(kenobi, skywalker))
                .sato(DistanceCalculatorUtil.calculate(kenobi, sato)).build();
    }
}
