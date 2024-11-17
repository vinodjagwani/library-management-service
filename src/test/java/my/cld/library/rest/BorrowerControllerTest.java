package my.cld.library.rest;

import lombok.AccessLevel;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import my.cld.library.rest.dto.BorrowerCreateRequest;
import my.cld.library.rest.dto.BorrowerCreateResponse;
import my.cld.library.service.BorrowerService;
import my.cld.library.utils.MockUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.MongoReactiveAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Mono;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@FieldDefaults(level = AccessLevel.PRIVATE)
@WebFluxTest(value = BorrowerController.class, excludeAutoConfiguration = {MongoReactiveAutoConfiguration.class})
class BorrowerControllerTest {

    @Autowired
    WebTestClient webTestClient;

    @MockBean
    BorrowerService borrowerService;

    @Test
    @SneakyThrows
    void testRegisterBorrower() {
        final BorrowerCreateRequest request = Optional.ofNullable(MockUtils.getResource("mock/borrower-create-request.json", BorrowerCreateRequest.class))
                .orElse(new BorrowerCreateRequest("", ""));
        final BorrowerCreateResponse response = Optional.ofNullable(MockUtils.getResource("mock/borrower-create-response.json", BorrowerCreateResponse.class))
                .orElse(new BorrowerCreateResponse("", "", ""));
        when(borrowerService.createBorrower(any(Mono.class))).thenReturn(Mono.just(response));
        webTestClient.post().uri("/api/register-borrower")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(request))
                .exchange()
                .expectStatus()
                .isOk()
                .expectHeader()
                .contentType(MediaType.APPLICATION_JSON)
                .expectBody(BorrowerCreateResponse.class)
                .value(res -> {
                    assertEquals("673a13f4ece39e323ee9617d", res.id());
                    assertEquals("test2", res.name());
                });
        verify(borrowerService, times(1)).createBorrower(any(Mono.class));
    }


}
