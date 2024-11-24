package my.cld.library.service;

import com.querydsl.core.types.Predicate;
import my.cld.library.repository.entity.Book;
import my.cld.library.rest.v1.dto.BookCreateRequest;
import my.cld.library.rest.v1.dto.BookCreateResponse;
import my.cld.library.rest.v1.dto.BookQueryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Mono;

public interface IBookService {

    Mono<Book> save(final Book book);

    Mono<Book> findOne(final Predicate predicate);

    Mono<Page<BookQueryResponse>> findAllBooks(final Pageable pageable);

    Mono<BookCreateResponse> createBook(final Mono<BookCreateRequest> request);

}
