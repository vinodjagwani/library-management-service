package my.cld.library.repository;

import my.cld.library.repository.entity.BorrowBook;
import org.springframework.data.querydsl.ReactiveQuerydslPredicateExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;

public interface BorrowBookRepository extends ReactiveCrudRepository<BorrowBook, String>, ReactiveSortingRepository<BorrowBook, String>, ReactiveQuerydslPredicateExecutor<BorrowBook> {

}
