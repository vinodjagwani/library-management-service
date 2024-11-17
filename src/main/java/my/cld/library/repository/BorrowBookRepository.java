package my.cld.library.repository;

import my.cld.library.repository.entity.BorrowBook;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import reactor.core.publisher.Mono;

public interface BorrowBookRepository extends ReactiveCrudRepository<BorrowBook, String>, ReactiveSortingRepository<BorrowBook, String> {

    @Query("{bookId: ?0, borrowerId: ?1}")
    Mono<BorrowBook> findByBookIdAndBorrowerId(final String bookId, final String borrowerId);
}
