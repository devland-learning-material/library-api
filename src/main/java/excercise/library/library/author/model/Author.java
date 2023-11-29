package excercise.library.library.author.model;

import java.util.ArrayList;
import java.util.List;

import excercise.library.library.author.model.dto.AuthorResponseDTO;
import excercise.library.library.book.model.Book;
import excercise.library.library.book.model.dto.BookResponseDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Author {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String firstName;
  private String lastName;
  @OneToMany(mappedBy = "author")
  private List<Book> books;

  public AuthorResponseDTO convertToResponse() {
    List<BookResponseDTO> bookResponseDTOs = new ArrayList<>();
    if (this.books != null) {
      bookResponseDTOs = this.books.stream().map(Book::convertToResponsePublic).toList();
    }
    return AuthorResponseDTO.builder().id(this.id).firstName(this.firstName).lastName(this.lastName)
        .books(bookResponseDTOs).build();
  }

  public AuthorResponseDTO convertToResponseBook() {
    return AuthorResponseDTO.builder().id(this.id).firstName(this.firstName).lastName(this.lastName).build();
  }
}
