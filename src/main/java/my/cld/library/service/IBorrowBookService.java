package my.cld.library.service;

import my.cld.library.rest.v1.dto.BorrowBookCreateRequest;
import my.cld.library.rest.v1.dto.BorrowBookCreateResponse;
import my.cld.library.rest.v1.dto.ReturnBookCreateRequest;
import reactor.core.publisher.Mono;

public interface IBorrowBookService {

    Mono<Void> returnBorrowedBook(final Mono<ReturnBookCreateRequest> request);

    Mono<BorrowBookCreateResponse> borrowBook(final Mono<BorrowBookCreateRequest> request);
}
