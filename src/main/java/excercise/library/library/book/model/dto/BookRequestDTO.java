package excercise.library.library.book.model.dto;

import excercise.library.library.author.model.dto.AuthorRequestDTO;
import excercise.library.library.book.model.Book;
import jakarta.validation.constraints.Min;
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
  private Long id;

  @NotBlank(message = "Title is required")
  private String title;

  @NotBlank(message = "Description is required")
  private String description;

  @Min(value = 1, message = "Quantity is required")
  private int quantity;

  private AuthorRequestDTO author;

  public Book convertToEntity() {
    return Book.builder().title(this.title).description(this.description).quantity(this.quantity)
        .author(this.author.convertToEntity()).build();
  }
}
