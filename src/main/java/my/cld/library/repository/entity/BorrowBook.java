package my.cld.library.repository.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Document(collection = "borrow-book")
public class BorrowBook {

    @Id
    private String id;

    private String bookId;

    private String borrowerId;

    private LocalDateTime borrowedAt;

}
