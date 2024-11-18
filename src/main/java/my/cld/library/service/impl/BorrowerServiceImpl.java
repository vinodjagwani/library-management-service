package my.cld.library.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import my.cld.library.repository.BorrowerRepository;
import my.cld.library.repository.entity.Borrower;
import my.cld.library.rest.dto.BorrowerCreateRequest;
import my.cld.library.rest.dto.BorrowerCreateResponse;
import my.cld.library.service.IBorrowerService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class BorrowerServiceImpl implements IBorrowerService {

    BorrowerRepository borrowerRepository;

    public Mono<Borrower> findBorrowerById(final String borrowerId) {
        return borrowerRepository.findById(borrowerId);
    }

    public Mono<BorrowerCreateResponse> createBorrower(final Mono<BorrowerCreateRequest> request) {
        log.debug("Start creating borrower with request [{}]", request);
        return request.map(borrower -> borrowerRepository.save(buildBorrower(borrower)))
                .flatMap(this::buildBorrowerCreateResponse);
    }

    private Borrower buildBorrower(final BorrowerCreateRequest request) {
        final Borrower borrower = new Borrower();
        borrower.setName(request.name());
        borrower.setEmail(request.email());
        return borrower;
    }

    private Mono<BorrowerCreateResponse> buildBorrowerCreateResponse(final Mono<Borrower> borrower) {
        return borrower.map(br -> new BorrowerCreateResponse(br.getId(), br.getName(), br.getEmail()));
    }
}
