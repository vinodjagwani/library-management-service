package my.cld.library.rest.dto;

import jakarta.validation.constraints.NotBlank;

public record BookCreateRequest(@NotBlank(message = "isbn can't be null or empty") String isbn,
                                @NotBlank(message = "title can't be null or empty") String title,
                                @NotBlank(message = "author can't be null or empty") String author) {
}
