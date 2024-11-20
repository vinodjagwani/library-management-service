package my.cld.library.rest.v1.dto;

import jakarta.validation.constraints.NotBlank;

public record ReturnBookCreateRequest(@NotBlank(message = "bookId can't be null or empty") String bookId,
                                      @NotBlank(message = "borrowerId can't be null or empty") String borrowerId) {
}
