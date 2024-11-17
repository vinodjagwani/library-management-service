package my.cld.library.rest;

import lombok.AccessLevel;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import my.cld.library.rest.dto.BorrowBookCreateRequest;
import my.cld.library.rest.dto.BorrowBookCreateResponse;
import my.cld.library.rest.dto.ReturnBookCreateRequest;
import my.cld.library.service.BorrowBookService;
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
@WebFluxTest(value = BorrowBookController.class, excludeAutoConfiguration = {MongoReactiveAutoConfiguration.class})
class BorrowBookControllerTest {

    @Autowired
    WebTestClient webTestClient;

    @MockBean
    BorrowBookService borrowBookService;


    @Test
    @SneakyThrows
    void testBorrowBook() {
        final BorrowBookCreateRequest request = Optional.ofNullable(MockUtils.getResource("mock/borrow-book-create-request.json", BorrowBookCreateRequest.class))
                .orElse(new BorrowBookCreateRequest("", ""));
        final BorrowBookCreateResponse response = Optional.ofNullable(MockUtils.getResource("mock/borrow-book-create-response.json", BorrowBookCreateResponse.class))
                .orElse(new BorrowBookCreateResponse("", "", ""));
        when(borrowBookService.borrowBook(any(Mono.class))).thenReturn(Mono.just(response));
        webTestClient.post().uri("/api/borrow-book")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(request))
                .exchange()
                .expectStatus()
                .isOk()
                .expectHeader()
                .contentType(MediaType.APPLICATION_JSON)
                .expectBody(BorrowBookCreateResponse.class)
                .value(res -> {
                    assertEquals("673a13e7ece39e323ee9617c", res.bookId());
                    assertEquals("673a13f4ece39e323ee9617d", res.borrowerId());
                });
        verify(borrowBookService, times(1)).borrowBook(any(Mono.class));
    }

    @Test
    @SneakyThrows
    void testReturnBook() {
        final ReturnBookCreateRequest request = Optional.ofNullable(MockUtils.getResource("mock/borrow-book-create-request.json", ReturnBookCreateRequest.class))
                .orElse(new ReturnBookCreateRequest("", ""));
        when(borrowBookService.returnBorrowedBook(any(Mono.class))).thenReturn(Mono.empty().then());
        webTestClient.post().uri("/api/return-book")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(request))
                .exchange()
                .expectStatus()
                .isOk();
        verify(borrowBookService, times(1)).returnBorrowedBook(any(Mono.class));
    }
}
