package excercise.library.library.book.model.dto;

import excercise.library.library.author.model.Author;
import excercise.library.library.author.model.dto.AuthorRequestDTO;
import excercise.library.library.author.model.dto.AuthorResponseDTO;
import excercise.library.library.book.model.Book;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookRequestDTO {
  @NotBlank(message = "Title is required")
  private String title;

  @NotBlank(message = "Description is required")
  private String description;

  private AuthorRequestDTO author;

  public Book convertToEntity() {
    return Book.builder().title(this.title).description(this.description).author(this.author.convertToEntity()).build();
  }
}
