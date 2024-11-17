package my.cld.library.rest.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record BorrowerCreateRequest(@NotBlank(message = "name can't be null or empty") String name,
                                    @NotBlank(message = "email can't be null or empty") @Email(message = "email is invalid") String email) {


}
