package my.cld.library.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import my.cld.library.exception.BusinessServiceException;
import my.cld.library.exception.dto.ErrorCodeEnum;
import my.cld.library.repository.BorrowBookRepository;
import my.cld.library.repository.entity.BorrowBook;
import my.cld.library.repository.entity.QBook;
import my.cld.library.repository.entity.QBorrowBook;
import my.cld.library.rest.v1.dto.BorrowBookCreateRequest;
import my.cld.library.rest.v1.dto.BorrowBookCreateResponse;
import my.cld.library.rest.v1.dto.ReturnBookCreateRequest;
import my.cld.library.service.IBorrowBookService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class BorrowBookServiceImpl implements IBorrowBookService {

    BookServiceImpl bookService;

    BorrowerServiceImpl borrowerService;

    BorrowBookRepository borrowBookRepository;

    public Mono<BorrowBookCreateResponse> borrowBook(final Mono<BorrowBookCreateRequest> request) {
        log.debug("Start calling borrowing book with request [{}]", request);
        return request.map(bbr -> bookService.findOne(QBook.book.id.eq(bbr.bookId()).and(QBook.book.available.eq(true)))
                        .switchIfEmpty(Mono.defer(() -> Mono.error(new BusinessServiceException(ErrorCodeEnum.DATA_NOT_FOUND, "Book not found with [ " + bbr.bookId() + " ]"))))
                        .flatMap(book -> {
                            book.setAvailable(false);
                            return bookService.save(book).flatMap(result -> borrowerService.findBorrowerById(bbr.borrowerId())
                                    .switchIfEmpty(Mono.defer(() -> Mono.error(new BusinessServiceException(ErrorCodeEnum.DATA_NOT_FOUND, "Borrower not found with [ " + bbr.borrowerId() + " ]")))));
                        }).flatMap(borrowerMono -> borrowBookRepository.save(buildBorrowBook(bbr))))
                .flatMap(this::buildBorrowBookCreateResponse);
    }

    public Mono<Void> returnBorrowedBook(final Mono<ReturnBookCreateRequest> request) {
        log.debug("Start calling return borrowed book with request [{}]", request);
        return request.flatMap(borrowedBookReq -> borrowBookRepository.findOne(QBorrowBook.borrowBook.bookId.eq(borrowedBookReq.bookId()).and(QBorrowBook.borrowBook.borrowerId.eq(borrowedBookReq.borrowerId())))
                .switchIfEmpty(Mono.defer(() -> Mono.error(new BusinessServiceException(ErrorCodeEnum.DATA_NOT_FOUND, "No booked borrowed"))))
                .flatMap(borrowBook -> bookService.findOne(QBook.book.id.eq(borrowBook.getBookId()).and(QBook.book.available.eq(false)))
                        .zipWith(Mono.just(borrowBook)))
                .flatMap(book -> {
                    book.getT1().setAvailable(true);
                    return bookService.save(book.getT1()).zipWith(Mono.just(book.getT2()));
                }).flatMap(book -> borrowBookRepository.delete(book.getT2())));
    }

    private BorrowBook buildBorrowBook(final BorrowBookCreateRequest request) {
        final BorrowBook borrowBook = new BorrowBook();
        borrowBook.setBookId(request.bookId());
        borrowBook.setBorrowerId(request.borrowerId());
        borrowBook.setBorrowedAt(LocalDateTime.now());
        return borrowBook;
    }

    private Mono<BorrowBookCreateResponse> buildBorrowBookCreateResponse(final Mono<BorrowBook> borrowBook) {
        return borrowBook.map(bb -> new BorrowBookCreateResponse(bb.getId(), bb.getBookId(), bb.getBorrowerId()));
    }
}
