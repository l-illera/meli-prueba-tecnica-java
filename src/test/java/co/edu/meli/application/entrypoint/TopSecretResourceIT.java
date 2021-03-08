package co.edu.meli.application.entrypoint;

import co.edu.meli.domain.dto.AllSatelliteRequestDTO;
import data.SatellitesDataProvider;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.MediaType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@Tag("integration")
@QuarkusTest
public class TopSecretResourceIT {

    @Test
    public void testExtractEndpointOK() {
        given()
                .with()
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(SatellitesDataProvider.requestDTO())
                .when()
                .request("POST", "/topsecret")
                .then()
                .statusCode(200)
                .body(is("{\"position\":{\"x\":-100.0,\"y\":75.5},\"message\":\"este es un mensaje secreto\"}"));
    }
    @Test
    public void testExtractEndpointError() {
        var requestDTO = SatellitesDataProvider.requestDTO();

        given()
                .with()
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(new AllSatelliteRequestDTO(requestDTO.getSatellites().subList(0,1)))
                .when()
                .request("POST", "/topsecret")
                .then()
                .statusCode(404);
    }

}