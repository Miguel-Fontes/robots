package br.com.miguelmf.robots.api.rest;

import br.com.miguelmf.robots.port.nasa.data.ComputeRobotCommandResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
class RobotControllerTest {

    @Autowired
    private WebTestClient client;

    @Test
    @DisplayName("should compute a robot final position")
    void shouldComputeARobotFinalPosition() throws Exception {
        EntityExchangeResult<ComputeRobotCommandResponse> result =
                client.post().uri("/rest/mars/MM")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .exchange()
                        .expectStatus().isOk()
                        .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                        .expectBody(ComputeRobotCommandResponse.class)
                        .returnResult();

        assertEquals("(0, 2, N)", result.getResponseBody().getRobotFinalPosition());
    }

    @Test
    @DisplayName("should compute a robot final position with right turns")
    void shouldComputeARobotFinalPositionWithRightTurns() throws Exception {
        EntityExchangeResult<ComputeRobotCommandResponse> result =
                client.post().uri("/rest/mars/MMMRMMRMRM")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .exchange()
                        .expectStatus().isOk()
                        .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                        .expectBody(ComputeRobotCommandResponse.class)
                        .returnResult();

        assertEquals("(1, 2, W)", result.getResponseBody().getRobotFinalPosition());
    }

    @Test
    @DisplayName("should compute a robot final position with right and left turns")
    void shouldComputeARobotFinalPositionWithRightAndLeftTurns() throws Exception {
        EntityExchangeResult<ComputeRobotCommandResponse> result =
                client.post().uri("/rest/mars/MMRMMMLLLMMRRML")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .exchange()
                        .expectStatus().isOk()
                        .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                        .expectBody(ComputeRobotCommandResponse.class)
                        .returnResult();

        assertEquals("(3, 1, W)", result.getResponseBody().getRobotFinalPosition());
    }

    @Test
    @DisplayName("should return error for invalid command")
    void shouldReturnErrorForInvalidCommand() throws Exception {
        EntityExchangeResult<String> result =
                client.post().uri("/rest/mars/MMRMMMLLLAMMRRML")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .exchange()
                        .expectStatus().isBadRequest()
                        .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                        .expectBody(String.class)
                        .returnResult();

        assertTrue(result.getResponseBody().startsWith("The [A] command is invalid! Supported commands are" ));
    }


    @Test
    @DisplayName("should return error for movement outside of zone bounds")
    void shouldRetornErrorForMovementOutsideOfZoneBounds() throws Exception {
        EntityExchangeResult<String> result =
                client.post().uri("/rest/mars/MMMMMMM")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .exchange()
                        .expectStatus().isBadRequest()
                        .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                        .expectBody(String.class)
                        .returnResult();

        assertEquals("The command [MMMMMMM] results on a illegal Robot position!",
                result.getResponseBody());
    }

}