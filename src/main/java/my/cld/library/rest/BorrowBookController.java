package my.cld.library.rest;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import my.cld.library.rest.dto.BorrowBookCreateRequest;
import my.cld.library.rest.dto.BorrowBookCreateResponse;
import my.cld.library.rest.dto.ReturnBookCreateRequest;
import my.cld.library.service.IBorrowBookService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class BorrowBookController {

    IBorrowBookService borrowBookService;

    @PostMapping(value = "/borrow-book", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<BorrowBookCreateResponse> borrowBook(@Valid @RequestBody final Mono<BorrowBookCreateRequest> request) {
        return borrowBookService.borrowBook(request);
    }

    @PostMapping(value = "/return-book", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Void> returnBook(@Valid @RequestBody final Mono<ReturnBookCreateRequest> request) {
        return borrowBookService.returnBorrowedBook(request);
    }
}
