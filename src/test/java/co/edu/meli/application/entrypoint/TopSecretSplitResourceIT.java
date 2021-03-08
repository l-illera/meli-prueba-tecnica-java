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
public class TopSecretSplitResourceIT {

    @Test
    public void testCompletePath() {
        final AllSatelliteRequestDTO requestDTO = SatellitesDataProvider.requestDTO();
        given()
                .with()
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(requestDTO.getSatellites().get(0))
                .when()
                .request("POST", "/topsecret_split/{satellite_name}","kenobi")
                .then()
                .statusCode(200)
                .body(is("{\"message\":\"no hay suficiente informacion.\"}"));
        given()
                .with()
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(requestDTO.getSatellites().get(2))
                .when()
                .request("POST", "/topsecret_split/{satellite_name}","sato")
                .then()
                .statusCode(200)
                .body(is("{\"message\":\"no hay suficiente informacion.\"}"));
        given()
                .with()
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(requestDTO.getSatellites().get(1))
                .when()
                .request("POST", "/topsecret_split/{satellite_name}","skywalker")
                .then()
                .statusCode(200)
                .body(is("{\"position\":{\"x\":-100.0,\"y\":75.5},\"message\":\"este es un mensaje secreto\"}"));

    }
}