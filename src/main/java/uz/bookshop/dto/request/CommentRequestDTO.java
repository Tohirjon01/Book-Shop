package uz.bookshop.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommentRequestDTO {
    @JsonProperty("text")
    private String text;

    @JsonProperty("book_id")
    private String bookId;
}
