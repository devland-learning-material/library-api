package excercise.library.library.book;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import excercise.library.library.book.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

  Page<Book> findAll(Pageable pageable);

  Page<Book> findAllByTitleIgnoreCaseContaining(String optionalQ, Pageable pageable);

}
