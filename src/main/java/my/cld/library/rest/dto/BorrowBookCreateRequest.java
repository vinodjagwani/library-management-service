package my.cld.library.rest.dto;

import jakarta.validation.constraints.NotBlank;

public record BorrowBookCreateRequest(@NotBlank(message = "bookId can't be null or empty") String bookId,
                                      @NotBlank(message = "borrowerId can't be null or empty") String borrowerId) {
}
