package my.cld.library.rest;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import my.cld.library.rest.dto.BookCreateRequest;
import my.cld.library.rest.dto.BookCreateResponse;
import my.cld.library.rest.dto.BookQueryResponse;
import my.cld.library.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class BookController {

    BookService bookService;

    @GetMapping(value = "/books", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Page<BookQueryResponse>> getAllBooks(final Pageable pageable) {
        return bookService.findAllBooks(pageable);
    }

    @PostMapping(value = "/create-book", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<BookCreateResponse> createBook(@Valid @RequestBody final Mono<BookCreateRequest> request) {
        return bookService.createBook(request);
    }
}
