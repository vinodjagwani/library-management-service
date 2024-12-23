package my.cld.library.rest.v1;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import my.cld.library.rest.v1.dto.BookCreateRequest;
import my.cld.library.rest.v1.dto.BookCreateResponse;
import my.cld.library.rest.v1.dto.BookQueryResponse;
import my.cld.library.service.IBookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class BookController {

    IBookService bookService;

    @GetMapping(value = "/books", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Page<BookQueryResponse>> getAllBooks(final Pageable pageable) {
        return bookService.findAllBooks(pageable);
    }

    @PostMapping(value = "/create-book", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<BookCreateResponse> createBook(@Valid @RequestBody final Mono<BookCreateRequest> request) {
        return bookService.createBook(request);
    }
}
