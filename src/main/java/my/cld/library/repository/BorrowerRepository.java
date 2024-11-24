package my.cld.library.repository;

import my.cld.library.repository.entity.Borrower;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.querydsl.ReactiveQuerydslPredicateExecutor;

public interface BorrowerRepository extends ReactiveMongoRepository<Borrower, String>, ReactiveQuerydslPredicateExecutor<Borrower> {
}
