package excercise.library.library.borrowingRecord;

import org.springframework.stereotype.Service;

import excercise.library.library.book.BookOutOfStockException;
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

  public BorrowingRecord getOneById(Long id) {
    return this.borrowingRecordRepository.findById(id).orElseThrow(BorrowingRecordNotFoundException::new);
  }

  public BorrowingRecord create(BorrowingRecord newBorrowingRecord) {
    Book existingBook = this.bookService.getOneById(newBorrowingRecord.getBook().getId());
    Customer existingCustomer = this.customerService.getOneById(newBorrowingRecord.getCustomer().getId());

    existingBook.descreaseQuantity();
    // Book updatedBook = this.bookService.updateById(existingBook);

    newBorrowingRecord.setBook(existingBook);
    newBorrowingRecord.setCustomer(existingCustomer);

    return this.borrowingRecordRepository.save(newBorrowingRecord);
  }

  public BorrowingRecord returning(BorrowingRecord borrowingRecord) {
    BorrowingRecord exisingBorrowingRecord = this.getOneById(borrowingRecord.getId());
    // if(!  .equals(exisingBorrowingRecord.getUpdatedAt())){
      
    // }
    return null;
  }

}
