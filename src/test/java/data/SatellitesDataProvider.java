package data;

import co.edu.meli.domain.dto.AllSatelliteRequestDTO;
import co.edu.meli.domain.dto.SatelliteDTO;
import co.edu.meli.domain.model.Point;
import co.edu.meli.domain.model.sat.Kenobi;
import co.edu.meli.domain.model.sat.Sato;
import co.edu.meli.domain.model.sat.Skywalker;
import co.edu.meli.domain.utilities.DistanceCalculatorUtil;

import java.util.Arrays;

public class SatellitesDataProvider {

    private static final Point kenobi;
    private static final Point skywalker;
    private static final Point sato;

    static {
        kenobi = new Point(-500d, -200d);
        skywalker = new Point(100d, -100d);
        sato = new Point(500d, 100d);
    }

    public static Kenobi kenobi() {
        return Kenobi.builder().coordinates(kenobi)
                .skywalker(DistanceCalculatorUtil.calculate(kenobi, skywalker))
                .sato(DistanceCalculatorUtil.calculate(kenobi, sato)).build();
    }

    public static Skywalker skywalker() {
        return Skywalker.builder().coordinates(skywalker)
                .sato(DistanceCalculatorUtil.calculate(skywalker, sato))
                .kenobi(DistanceCalculatorUtil.calculate(kenobi, skywalker)).build();
    }

    public static Sato sato() {
        return Sato.builder().coordinates(sato)
                .skywalker(DistanceCalculatorUtil.calculate(skywalker, sato))
                .kenobi(DistanceCalculatorUtil.calculate(kenobi, sato)).build();
    }

    public static AllSatelliteRequestDTO requestDTO() {
        final SatelliteDTO kenobi = new SatelliteDTO("kenobi", 485.696d, new String[]{"este", "", "", "mensaje", ""});
        final SatelliteDTO skywalker = new SatelliteDTO("skywalker", 266.083d, new String[]{"", "es", "", "", "secreto"});
        final SatelliteDTO sato = new SatelliteDTO("sato", 600.5d, new String[]{"este", "", "un", "", ""});
        return new AllSatelliteRequestDTO(Arrays.asList(kenobi,skywalker,sato));
    }
}
