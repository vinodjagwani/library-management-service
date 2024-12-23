package my.cld.library.rest.v1;

import lombok.AccessLevel;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import my.cld.library.rest.v1.dto.BookCreateRequest;
import my.cld.library.rest.v1.dto.BookCreateResponse;
import my.cld.library.rest.v1.dto.BookQueryResponse;
import my.cld.library.service.impl.BookServiceImpl;
import my.cld.library.utils.MockUtils;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.MongoReactiveAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Mono;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@FieldDefaults(level = AccessLevel.PRIVATE)
@WebFluxTest(value = BookController.class, excludeAutoConfiguration = {MongoReactiveAutoConfiguration.class})
class BookControllerTest {

    @Autowired
    WebTestClient webTestClient;

    @MockBean
    BookServiceImpl bookService;

    @Test
    @SneakyThrows
    void testGetAllBooks() {
        final BookQueryResponse response = Optional.ofNullable(MockUtils.getResource("mock/book-query-response.json", BookQueryResponse.class)).orElse(new BookQueryResponse("", "", "", "", true));
        when(bookService.findAllBooks(PageRequest.of(0, 10))).thenReturn(Mono.just(new PageImpl<>(Lists.list(response))));
        webTestClient.get()
                .uri("/api/v1/books")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader()
                .contentType(MediaType.APPLICATION_JSON);
        verify(bookService, times(1)).findAllBooks(any());
    }


    @Test
    @SneakyThrows
    void testCreateBook() {
        final BookCreateRequest request = Optional.ofNullable(MockUtils.getResource("mock/book-create-request.json", BookCreateRequest.class)).orElse(new BookCreateRequest("", "", ""));
        when(bookService.createBook(any(Mono.class))).thenReturn(Mono.just(new BookCreateResponse("", "", "", "")));
        webTestClient.post().uri("/api/v1/create-book")
                .body(BodyInserters.fromValue(request))
                .exchange()
                .expectStatus().isOk()
                .expectBody();
    }

}
