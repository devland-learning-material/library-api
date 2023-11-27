package excercise.library.library.author.model.dto;

import java.util.List;

import excercise.library.library.book.model.dto.BookResponseDTO;
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
public class AuthorResponseDTO {
  private Long id;
  private String firstName;
  private String lastName;
  private List<BookResponseDTO> books;
}
