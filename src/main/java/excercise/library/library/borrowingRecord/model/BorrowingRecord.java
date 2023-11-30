package excercise.library.library.borrowingRecord.model;

import java.sql.Timestamp;
import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import excercise.library.library.book.model.Book;
import excercise.library.library.borrowingRecord.model.dto.BorrowingRecordResponseDTO;
import excercise.library.library.customer.model.Customer;
import jakarta.persistence.Column;
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

  @Column(updatable = false, nullable = false)
  @CreationTimestamp
  private Timestamp createdAt;

  @UpdateTimestamp
  private Timestamp updatedAt;

  private LocalDate returnedAt;

  @Builder.Default
  private Double penalty = 0.0;

  @Builder.Default
  private Boolean isReturned = false;

  public BorrowingRecordResponseDTO convertToResponse() {

    return BorrowingRecordResponseDTO.builder()
        .id(this.id).bookResponseDTO(this.book.convertToResponseWithoutRelation())
        .customerResponseDTO(this.customer.cunvertToResponsePublic())
        .createdAt(this.createdAt)
        .updatedAt(this.updatedAt)
        .returnedAt(this.returnedAt)
        .penalty(this.penalty)
        .isReturned(this.isReturned)
        .build();
  }

}
