package excercise.library.library.borrowingRecord.model.dto;

import java.time.LocalDate;

import excercise.library.library.book.model.Book;
import excercise.library.library.borrowingRecord.model.BorrowingRecord;
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

  private Integer days;

  private Double penalty;

  public BorrowingRecord convertToEntity() {
    Book book = Book.builder().id(this.bookId).build();
    LocalDate currenDate = LocalDate.now();
    LocalDate returnedDate = currenDate.plusDays(this.days);
    return BorrowingRecord.builder()
        .id(this.id)
        .book(book)
        .returnedAt(returnedDate)
        .penalty(this.penalty)
        .build();
  }
}
