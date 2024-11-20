package my.cld.library.service.impl;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import my.cld.library.repository.BookRepository;
import my.cld.library.repository.entity.Book;
import my.cld.library.rest.v1.dto.BookCreateRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
class BookServiceImplTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    @Test
    void testFindByBookIdAndiSAvailable() {
        when(bookRepository.findByBookIdAndiSAvailable(any(String.class), anyBoolean())).thenReturn(Mono.just(new Book()));
        bookService.findByBookIdAndiSAvailable("1234", true);
        verify(bookRepository, atLeastOnce()).findByBookIdAndiSAvailable(any(String.class), anyBoolean());
    }

    @Test
    void testSave() {
        when(bookRepository.save(any(Book.class))).thenReturn(Mono.just(new Book()));
        bookService.save(new Book());
        verify(bookRepository, atLeastOnce()).save(any(Book.class));
    }

    @Test
    void testCreateBook() {
        when(bookRepository.save(any(Book.class))).thenReturn(Mono.just(new Book()));
        bookService.createBook(Mono.just(new BookCreateRequest("132", "test", "test"))).block();
        verify(bookRepository, atLeastOnce()).save(any(Book.class));
    }

    @Test
    void testFindAllBooks() {
        when(bookRepository.count()).thenReturn(Mono.just(1L));
        when(bookRepository.findAll(any(Sort.class))).thenReturn(Flux.just(new Book()));
        bookService.findAllBooks(PageRequest.of(1, 1)).block();
        verify(bookRepository, atLeastOnce()).count();
        verify(bookRepository, atLeastOnce()).findAll(any(Sort.class));
    }
}
