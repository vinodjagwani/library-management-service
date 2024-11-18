package my.cld.library.repository.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@Document("borrower")
public class Borrower {

    @Id
    private String id;

    private String name;

    @Indexed(unique = true)
    private String email;
}
