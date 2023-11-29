package excercise.library.library.borrowingRecord.model.dto;

import java.sql.Timestamp;

import excercise.library.library.book.model.Book;
import excercise.library.library.book.model.dto.BookResponseDTO;
import excercise.library.library.customer.model.Customer;
import excercise.library.library.customer.model.dto.CustomerResponseDTO;
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
public class BorrowingRecordResponseDTO {
  private Long id;
  private BookResponseDTO bookResponseDTO;
  private CustomerResponseDTO customerResponseDTO;
  private Timestamp createdAt;
  private Timestamp updatedAt;
}
  