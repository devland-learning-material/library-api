package excercise.library.library.book.model;

import excercise.library.library.author.model.Author;
import excercise.library.library.author.model.dto.AuthorResponseDTO;
import excercise.library.library.book.BookOutOfStockException;
import excercise.library.library.book.model.dto.BookResponseDTO;
import excercise.library.library.borrowingRecord.model.BorrowingRecord;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String title;

  private String description;

  private int quantity;

  @ManyToOne(cascade = CascadeType.PERSIST)
  @JoinColumn(name = "author_id")
  private Author author;

  @ManyToOne(cascade = CascadeType.PERSIST)
  private BorrowingRecord borrowingRecord;

  public void descreaseQuantity() {
    if (this.quantity <= 0) {
      throw new BookOutOfStockException();
    }
    int DECREASE_VALUE = 1;
    this.quantity = this.quantity - DECREASE_VALUE;
  }

  public BookResponseDTO convertToResponse() {
    AuthorResponseDTO authorResponseDTO = null;
    if (this.author != null) {
      authorResponseDTO = this.author.convertToResponseBook();
    }

    return BookResponseDTO.builder()
        .id(this.id).title(this.title)
        .description(this.description)
        .quantity(this.quantity)
        .author(authorResponseDTO)
        .build();
  }

  public BookResponseDTO convertToResponseWithoutRelation() {
    return BookResponseDTO.builder()
        .id(this.id)
        .title(this.title)
        .quantity(this.quantity)
        .description(this.description)
        .build();
  }

}
