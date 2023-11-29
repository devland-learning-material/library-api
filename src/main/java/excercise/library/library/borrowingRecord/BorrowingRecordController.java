package excercise.library.library.borrowingRecord;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import excercise.library.library.borrowingRecord.model.BorrowingRecord;
import excercise.library.library.borrowingRecord.model.dto.BorrowingRecordRequestDTO;
import excercise.library.library.borrowingRecord.model.dto.BorrowingRecordResponseDTO;
import excercise.library.library.customer.model.Customer;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BorrowingRecordController {
  private final BorrowingRecordService borrowingRecordService;

  @PostMapping("/customers/{customerId}/borrowing-records")
  public ResponseEntity<BorrowingRecordResponseDTO> create(
      @PathVariable Long customerId,
      @Valid @RequestBody BorrowingRecordRequestDTO borrowingRecordRequestDTO) {
    BorrowingRecord newBorrowingRecord = borrowingRecordRequestDTO.convertToEntity();
    Customer customer = Customer.builder().id(customerId).build();
    newBorrowingRecord.setCustomer(customer);
    BorrowingRecord savedBorrowingRecord = this.borrowingRecordService.create(newBorrowingRecord);
    return ResponseEntity.status(HttpStatus.CREATED).body(savedBorrowingRecord.convertToResponse());
  }

  @PutMapping("/customers/{customerId}/borrowing-records/{id}")
  public ResponseEntity<BorrowingRecordResponseDTO> returning(
      @PathVariable Long customerId,
      @PathVariable Long borrowingRecordId,
      @Valid @RequestBody BorrowingRecordRequestDTO borrowingRecordRequestDTO) {
    BorrowingRecord borrowingRecord = borrowingRecordRequestDTO.convertToEntity();
    Customer customer = Customer.builder().id(customerId).build();
    borrowingRecord.setId(borrowingRecordId);
    borrowingRecord.setCustomer(customer);
    BorrowingRecord returningRecord = this.borrowingRecordService.returning(borrowingRecord);
    return ResponseEntity.ok(returningRecord.convertToResponse());

  }
}
