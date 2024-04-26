package uz.bookshop.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CommentResponseDTO  {

    private int id;
    private String text;
    private Long bookId;
    private String title;

}
