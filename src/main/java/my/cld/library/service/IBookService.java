package my.cld.library.service;

import my.cld.library.repository.entity.Book;
import my.cld.library.rest.dto.BookCreateRequest;
import my.cld.library.rest.dto.BookCreateResponse;
import my.cld.library.rest.dto.BookQueryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Mono;

public interface IBookService {

    Mono<Book> save(final Book book);

    Mono<Page<BookQueryResponse>> findAllBooks(final Pageable pageable);

    Mono<BookCreateResponse> createBook(final Mono<BookCreateRequest> request);

    Mono<Book> findByBookIdAndiSAvailable(final String bookId, final boolean isAvailable);
}
