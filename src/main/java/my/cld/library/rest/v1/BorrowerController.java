package my.cld.library.rest.v1;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import my.cld.library.rest.v1.dto.BorrowerCreateRequest;
import my.cld.library.rest.v1.dto.BorrowerCreateResponse;
import my.cld.library.service.IBorrowerService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class BorrowerController {

    IBorrowerService borrowerService;

    @PostMapping(value = "/register-borrower", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<BorrowerCreateResponse> registerBorrower(@Valid @RequestBody final Mono<BorrowerCreateRequest> request) {
        return borrowerService.createBorrower(request);
    }
}
