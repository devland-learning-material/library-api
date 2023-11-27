package excercise.library.library.book;

import org.springframework.data.jpa.repository.JpaRepository;

import excercise.library.library.book.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}
