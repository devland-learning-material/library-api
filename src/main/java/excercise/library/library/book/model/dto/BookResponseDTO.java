package excercise.library.library.book.model.dto;

import excercise.library.library.author.model.dto.AuthorResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookResponseDTO {
  private Long id;
  private String title;
  private String description;
  private int quantity;
  private AuthorResponseDTO author;

}
