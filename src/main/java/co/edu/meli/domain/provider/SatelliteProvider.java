package co.edu.meli.domain.provider;

import co.edu.meli.domain.model.sat.Kenobi;
import co.edu.meli.domain.model.sat.Sato;
import co.edu.meli.domain.model.sat.Skywalker;

public interface SatelliteProvider {

    Kenobi getKenobi();

    Skywalker getSkywalker();

    Sato getSato();
}
