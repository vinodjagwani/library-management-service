package my.cld.library.service.impl;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import my.cld.library.repository.BorrowerRepository;
import my.cld.library.repository.entity.Borrower;
import my.cld.library.rest.v1.dto.BorrowerCreateRequest;
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
public class BorrowerServiceImplTest {

    @Mock
    BorrowerRepository borrowerRepository;

    @InjectMocks
    BorrowerServiceImpl borrowerService;

    @Test
    void testFindBorrowerById() {
        when(borrowerRepository.findById(any(String.class))).thenReturn(Mono.just(new Borrower()));
        borrowerService.findBorrowerById("123").block();
        verify(borrowerRepository, atLeastOnce()).findById(any(String.class));
    }

    @Test
    void testCreateBorrower() {
        when(borrowerRepository.save(any(Borrower.class))).thenReturn(Mono.just(new Borrower()));
        borrowerService.createBorrower(Mono.just(new BorrowerCreateRequest("", ""))).block();
        verify(borrowerRepository, atLeastOnce()).save(any(Borrower.class));
    }

}
