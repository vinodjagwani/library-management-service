package my.cld.library.service;

import my.cld.library.repository.entity.Borrower;
import my.cld.library.rest.dto.BorrowerCreateRequest;
import my.cld.library.rest.dto.BorrowerCreateResponse;
import reactor.core.publisher.Mono;

public interface IBorrowerService {

    Mono<Borrower> findBorrowerById(final String borrowerId);

    Mono<BorrowerCreateResponse> createBorrower(final Mono<BorrowerCreateRequest> request);
}
