package my.cld.library.repository;

import my.cld.library.repository.entity.Book;
import org.springframework.data.querydsl.ReactiveQuerydslPredicateExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;

public interface BookRepository extends ReactiveCrudRepository<Book, String>, ReactiveSortingRepository<Book, String>, ReactiveQuerydslPredicateExecutor<Book> {
}
