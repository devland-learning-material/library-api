package excercise.library.library.borrowingRecord;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import excercise.library.library.borrowingRecord.model.BorrowingRecord;

public interface BorrowingRecordRepository extends JpaRepository<BorrowingRecord, Long> {

  List<BorrowingRecord> findAllByCustomerId(Long customerId);

  Optional<BorrowingRecord> findByIdAndCustomerId(Long id, Long customerId);

}
