package excercise.library.library.borrowingRecord;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.springframework.stereotype.Service;

import excercise.library.library.book.BookService;
import excercise.library.library.book.model.Book;
import excercise.library.library.borrowingRecord.model.BorrowingRecord;
import excercise.library.library.customer.CustomerService;
import excercise.library.library.customer.model.Customer;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BorrowingRecordService {
  private final BorrowingRecordRepository borrowingRecordRepository;
  private final CustomerService customerService;
  private final BookService bookService;

  public BorrowingRecord getOneById(BorrowingRecord borrowingRecord) {

    return this.borrowingRecordRepository
        .findByIdAndCustomerId(borrowingRecord.getId(), borrowingRecord.getCustomer().getId())
        .orElseThrow(BorrowingRecordNotFoundException::new);
  }

  public BorrowingRecord create(BorrowingRecord newBorrowingRecord) {
    Book existingBook = this.bookService.getOneById(newBorrowingRecord.getBook().getId());
    Customer existingCustomer = this.customerService.getOneById(newBorrowingRecord.getCustomer().getId());

    existingBook.descreaseQuantity();
    newBorrowingRecord.setBook(existingBook);
    newBorrowingRecord.setCustomer(existingCustomer);

    return this.borrowingRecordRepository.save(newBorrowingRecord);
  }

  public BorrowingRecord returning(BorrowingRecord borrowingRecord) {
    BorrowingRecord exisingBorrowingRecord = this.getOneById(borrowingRecord);
    this.customerService.getOneById(borrowingRecord.getCustomer().getId());

    if (exisingBorrowingRecord.getIsReturned()) {
      throw new BorrowingRecordIsAlreadyReturnedException();
    }

    if (!exisingBorrowingRecord.getBook().getId().equals(borrowingRecord.getBook().getId())) {
      throw new BorrowingRecordBookNotMatchException();
    }

    LocalDate returnedAt = exisingBorrowingRecord.getReturnedAt();
    LocalDate currentDate = LocalDate.now();

    if (!returnedAt.equals(currentDate) && !returnedAt.isAfter(currentDate)) {
      Period period = Period.between(returnedAt, currentDate);
      int daysLate = period.getDays();
      Double PENALTY_PER_DAY = 1000.0;
      Double currentPenalty = Math.abs(daysLate * PENALTY_PER_DAY);
      DecimalFormat format = new DecimalFormat("Rp #,###");

      if (borrowingRecord.getPenalty() == null) {
        throw new BorrowingRecordPenaltyException(
            "You have to pay " + format.format(currentPenalty) + ", for returning book late for " + daysLate + " days");
      } else if (!borrowingRecord.getPenalty().equals(currentPenalty)) {
        throw new BorrowingRecordPenaltyException(
            "The amount of penalty you entered is not correct, the amount of penalty you have to pay is "
                + format.format(currentPenalty));
      }

      borrowingRecord.setPenalty(currentPenalty);
    }

    borrowingRecord.setIsReturned(true);
    borrowingRecord.setReturnedAt(returnedAt);

    return this.borrowingRecordRepository.save(borrowingRecord);
  }

  public List<BorrowingRecord> getAllBtCustomer(Long customerId) {
    return this.borrowingRecordRepository.findAllByCustomerId(customerId);
  }

}
