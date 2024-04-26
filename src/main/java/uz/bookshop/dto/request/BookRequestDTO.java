package uz.bookshop.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookRequestDTO {

    @JsonProperty("titile")
    private String title;

    @JsonProperty("writer")
    private String writer;

    @JsonProperty("count")
    private int count;

    @JsonProperty("price")
    private Integer price;

}
