package co.edu.meli.domain.provider;

import co.edu.meli.domain.dto.AllSatelliteRequestDTO;
import co.edu.meli.domain.dto.SatelliteDTO;

public interface MessageProvider {

    AllSatelliteRequestDTO getMessages();

    void addMessage(final SatelliteDTO satelliteDTO);
}
