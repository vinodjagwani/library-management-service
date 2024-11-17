package my.cld.library.repository;

import my.cld.library.repository.entity.Borrower;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface BorrowerRepository extends ReactiveMongoRepository<Borrower, String> {
}
