package my.cld.library.rest.v1.dto;

public record BookQueryResponse(String id, String isbn, String title, String author, boolean isAvailable) {
}
