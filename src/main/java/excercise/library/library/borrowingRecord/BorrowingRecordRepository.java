package excercise.library.library.borrowingRecord;

import org.springframework.data.jpa.repository.JpaRepository;

import excercise.library.library.borrowingRecord.model.BorrowingRecord;

public interface BorrowingRecordRepository extends JpaRepository<BorrowingRecord, Long> {

}
