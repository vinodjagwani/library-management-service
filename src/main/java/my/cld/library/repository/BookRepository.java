package my.cld.library.repository;

import my.cld.library.repository.entity.Book;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import reactor.core.publisher.Mono;

public interface BookRepository extends ReactiveCrudRepository<Book, String>, ReactiveSortingRepository<Book, String> {

    @Query("{_id: ?0, isAvailable: ?1}")
    Mono<Book> findByBookIdAndiSAvailable(final String bookId, final boolean isAvailable);

}
