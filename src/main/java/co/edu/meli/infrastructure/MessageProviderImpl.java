package co.edu.meli.infrastructure;

import co.edu.meli.domain.dto.AllSatelliteRequestDTO;
import co.edu.meli.domain.dto.SatelliteDTO;
import co.edu.meli.domain.provider.MessageProvider;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Singleton
public class MessageProviderImpl implements MessageProvider {

    private final Set<SatelliteDTO> messages;

    public MessageProviderImpl() {
        this.messages = new HashSet<>();
    }

    public void addMessage(final SatelliteDTO satelliteDTO) {
        messages.remove(satelliteDTO);
        messages.add(satelliteDTO);
    }

    public AllSatelliteRequestDTO getMessages() {
        return new AllSatelliteRequestDTO(new ArrayList<>(messages));
    }
}
