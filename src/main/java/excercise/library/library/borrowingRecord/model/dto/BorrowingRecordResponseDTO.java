package excercise.library.library.borrowingRecord.model.dto;

import java.sql.Timestamp;
import java.time.LocalDate;

import excercise.library.library.book.model.dto.BookResponseDTO;
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
  private LocalDate returnedAt;
  private Double penalty;
  private Boolean isReturned;
}
