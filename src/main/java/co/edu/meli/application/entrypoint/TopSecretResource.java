package co.edu.meli.application.entrypoint;

import co.edu.meli.domain.dto.AllSatelliteRequestDTO;
import co.edu.meli.domain.dto.PositionDTO;
import co.edu.meli.domain.dto.ResponseDTO;
import co.edu.meli.domain.model.Result;
import co.edu.meli.domain.model.SpaceshipReference;
import co.edu.meli.domain.usecase.ExtractInformationUseCase;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.net.HttpURLConnection;

@Slf4j
@Path("/topsecret")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TopSecretResource {

    @Inject
    ExtractInformationUseCase extractInformationUseCase;

    @POST
    public ResponseDTO extract(AllSatelliteRequestDTO requestDTO) {
        try {
            final SpaceshipReference spaceshipReference = SpaceshipReference.builder()
                    .toKenobi(requestDTO.findDistance("kenobi"))
                    .toSato(requestDTO.findDistance("sato"))
                    .toSkywalker(requestDTO.findDistance("skywalker"))
                    .build();
            var result = extractInformationUseCase.process(spaceshipReference, requestDTO.messagesList());
            return buildResponse(result);
        } catch (Exception ex) {
            throw new WebApplicationException(HttpURLConnection.HTTP_NOT_FOUND);
        }
    }

    private ResponseDTO buildResponse(Result result) {
        final PositionDTO positionDTO = new PositionDTO(result.getPosition().getEast(), result.getPosition().getNorth());
        return new ResponseDTO(positionDTO, result.getMessage());
    }
}