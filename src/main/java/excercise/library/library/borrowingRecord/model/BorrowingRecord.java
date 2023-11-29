package excercise.library.library.borrowingRecord.model;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import excercise.library.library.book.model.Book;
import excercise.library.library.borrowingRecord.model.dto.BorrowingRecordResponseDTO;
import excercise.library.library.customer.model.Customer;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class BorrowingRecord {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  private Book book;

  @ManyToOne
  private Customer customer;

  @CreationTimestamp
  private Timestamp createdAt;

  @UpdateTimestamp
  private Timestamp updatedAt;

  public BorrowingRecordResponseDTO convertToResponse() {

    return BorrowingRecordResponseDTO.builder().id(this.id).bookResponseDTO(this.book.convertToResponsePublic())
        .customerResponseDTO(this.customer.cunvertToResponsePublic())
        .createdAt(this.createdAt).updatedAt(this.updatedAt).build();
  }

}
