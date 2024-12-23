package my.cld.library.service.impl;

import com.querydsl.core.types.Predicate;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import my.cld.library.repository.BorrowBookRepository;
import my.cld.library.repository.entity.Book;
import my.cld.library.repository.entity.BorrowBook;
import my.cld.library.repository.entity.Borrower;
import my.cld.library.rest.v1.dto.BorrowBookCreateRequest;
import my.cld.library.rest.v1.dto.ReturnBookCreateRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BorrowBookServiceImplTest {

    @Mock
    BookServiceImpl bookService;

    @Mock
    BorrowerServiceImpl borrowerService;

    @Mock
    BorrowBookRepository borrowBookRepository;

    @InjectMocks
    BorrowBookServiceImpl borrowBookService;

    @Test
    void testBorrowBook() {
        when(bookService.findOne(any(Predicate.class))).thenReturn(Mono.just(new Book()));
        when(bookService.save(any(Book.class))).thenReturn(Mono.just(new Book()));
        when(borrowerService.findBorrowerById(any(String.class))).thenReturn(Mono.just(new Borrower()));
        when(borrowBookRepository.save(any(BorrowBook.class))).thenReturn(Mono.just(new BorrowBook()));
        borrowBookService.borrowBook(Mono.just(new BorrowBookCreateRequest("134", "14"))).block();
        verify(bookService, atLeastOnce()).findOne(any(Predicate.class));
        verify(bookService, atLeastOnce()).save(any(Book.class));
        verify(borrowerService, atLeastOnce()).findBorrowerById((any(String.class)));
        verify(borrowBookRepository, atLeastOnce()).save(any(BorrowBook.class));
    }

    @Test
    void testReturnBorrowedBook() {
        final BorrowBook borrowBook = new BorrowBook();
        borrowBook.setBookId("234");
        when(borrowBookRepository.findOne(any(Predicate.class))).thenReturn(Mono.just(borrowBook));
        when(bookService.findOne(any(Predicate.class))).thenReturn(Mono.just(new Book()));
        when(bookService.save(any(Book.class))).thenReturn(Mono.just(new Book()));
        when(borrowBookRepository.delete(any(BorrowBook.class))).thenReturn(Mono.empty().then());
        borrowBookService.returnBorrowedBook(Mono.just(new ReturnBookCreateRequest("", ""))).block();
        verify(borrowBookRepository, atLeastOnce()).findOne(any(Predicate.class));
        verify(bookService, atLeastOnce()).findOne(any(Predicate.class));
        verify(bookService, atLeastOnce()).save(any(Book.class));
        verify(borrowBookRepository, atLeastOnce()).delete(any(BorrowBook.class));
    }
}
