package excercise.library.library.borrowingRecord.model.dto;

import excercise.library.library.book.model.Book;
import excercise.library.library.book.model.dto.BookRequestDTO;
import excercise.library.library.borrowingRecord.model.BorrowingRecord;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BorrowingRecordRequestDTO {
  private Long id;

  @Positive(message = "Book ID is required!")
  private Long bookId;

  public BorrowingRecord convertToEntity() {
    Book book = Book.builder().id(this.bookId).build();
    return BorrowingRecord.builder().id(this.id).book(book).build();
  }
}
