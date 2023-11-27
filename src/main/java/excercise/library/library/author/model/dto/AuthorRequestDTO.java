package excercise.library.library.author.model.dto;

import excercise.library.library.author.model.Author;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthorRequestDTO {
  private Long id;
  @NotBlank(message = "First Name is required")
  private String firstName;

  @NotBlank(message = "Last Name")
  private String lastName;

  public Author convertToEntity() {
    return Author.builder().id(this.id).firstName(this.firstName).lastName(this.lastName).build();
  }
}
