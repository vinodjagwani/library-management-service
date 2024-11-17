package my.cld.library.repository.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@ToString
@Document(collection = "book")
public class Book {

    @Id
    private String id;

    private String isbn;

    private String title;

    private String author;

    @Field(name = "isAvailable")
    private boolean available;

}
