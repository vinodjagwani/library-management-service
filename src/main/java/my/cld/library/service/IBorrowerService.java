package my.cld.library.service;

import my.cld.library.rest.v1.dto.BorrowerCreateRequest;
import my.cld.library.rest.v1.dto.BorrowerCreateResponse;
import reactor.core.publisher.Mono;

public interface IBorrowerService {

    Mono<BorrowerCreateResponse> createBorrower(final Mono<BorrowerCreateRequest> request);
}
