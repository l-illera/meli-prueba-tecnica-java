package co.edu.meli.application.entrypoint;

import co.edu.meli.domain.dto.PositionDTO;
import co.edu.meli.domain.dto.ResponseDTO;
import co.edu.meli.domain.dto.SatelliteDTO;
import co.edu.meli.domain.model.Result;
import co.edu.meli.domain.model.SpaceshipReference;
import co.edu.meli.domain.provider.MessageProvider;
import co.edu.meli.domain.usecase.ExtractInformationUseCase;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Locale;

@Slf4j
@Path("/topsecret_split")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TopSecretSplitResource {

    @Inject
    MessageProvider messageProvider;
    @Inject
    ExtractInformationUseCase extractInformationUseCase;

    @POST
    @Path("/{satellite_name}")
    public ResponseDTO addMessage(@PathParam("satellite_name") final String satelliteName, @Valid SatelliteDTO body) {
        messageProvider.addMessage(new SatelliteDTO(satelliteName.toLowerCase(Locale.ROOT), body.getDistance(), body.getMessage()));
        return generateResponse();
    }

    @GET
    public ResponseDTO locate() {
        return generateResponse();
    }

    private ResponseDTO generateResponse() {
        try {
            final var requestDTO = messageProvider.getMessages();
            final var spaceshipReference = SpaceshipReference.builder()
                    .toKenobi(requestDTO.findDistance("kenobi"))
                    .toSato(requestDTO.findDistance("sato"))
                    .toSkywalker(requestDTO.findDistance("skywalker"))
                    .build();
            var result = extractInformationUseCase.process(spaceshipReference, requestDTO.messagesList());
            return buildResponse(result);
        } catch (Exception ex) {
            return new ResponseDTO(null, "no hay suficiente informacion.");
        }
    }

    private ResponseDTO buildResponse(Result result) {
        final var positionDTO = new PositionDTO(result.getPosition().getEast(), result.getPosition().getNorth());
        return new ResponseDTO(positionDTO, result.getMessage());
    }
}
