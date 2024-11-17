package my.cld.library.rest.dto;

public record BookQueryResponse(String id, String isbn, String title, String author, boolean isAvailable) {
}
