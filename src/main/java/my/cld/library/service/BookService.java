package my.cld.library.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import my.cld.library.repository.BookRepository;
import my.cld.library.repository.entity.Book;
import my.cld.library.rest.dto.BookCreateRequest;
import my.cld.library.rest.dto.BookCreateResponse;
import my.cld.library.rest.dto.BookQueryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class BookService {

    BookRepository bookRepository;

    public Mono<Book> findByBookIdAndiSAvailable(final String bookId, final boolean isAvailable) {
        return bookRepository.findByBookIdAndiSAvailable(bookId, isAvailable);
    }

    public Mono<Book> save(final Book book) {
        return bookRepository.save(book);
    }

    public Mono<BookCreateResponse> createBook(final Mono<BookCreateRequest> request) {
        return request.map(req -> bookRepository.save(buildBook(req))).flatMap(this::buildBookCreateResponse);
    }

    public Mono<Page<BookQueryResponse>> findAllBooks(final Pageable pageable) {
        return bookRepository.count()
                .flatMap(bookCount -> this.bookRepository.findAll(pageable.getSort())
                        .buffer(pageable.getPageSize(), (pageable.getPageNumber() + 1))
                        .elementAt(pageable.getPageNumber(), new ArrayList<>())
                        .map(books -> new PageImpl<>(books.stream().map(this::buildBookQueryResponse)
                                .collect(Collectors.toList()), pageable, bookCount)));
    }

    private Book buildBook(final BookCreateRequest request) {
        final Book book = new Book();
        book.setTitle(request.title());
        book.setIsbn(request.isbn());
        book.setTitle(request.title());
        book.setAuthor(request.author());
        book.setAvailable(true);
        return book;
    }

    private Mono<BookCreateResponse> buildBookCreateResponse(final Mono<Book> book) {
        return book.map(b -> new BookCreateResponse(b.getId(), b.getIsbn(), b.getTitle(), b.getAuthor()));
    }

    private BookQueryResponse buildBookQueryResponse(final Book book) {
        return new BookQueryResponse(book.getId(), book.getIsbn(), book.getTitle(), book.getAuthor(), book.isAvailable());
    }

}
