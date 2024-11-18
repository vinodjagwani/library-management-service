package my.cld.library.service;

import my.cld.library.rest.dto.BorrowBookCreateRequest;
import my.cld.library.rest.dto.BorrowBookCreateResponse;
import my.cld.library.rest.dto.ReturnBookCreateRequest;
import reactor.core.publisher.Mono;

public interface IBorrowBookService {

    Mono<Void> returnBorrowedBook(final Mono<ReturnBookCreateRequest> request);

    Mono<BorrowBookCreateResponse> borrowBook(final Mono<BorrowBookCreateRequest> request);
}
