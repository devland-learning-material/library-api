package excercise.library.library.book;

import java.util.List;

import org.springframework.stereotype.Service;

import excercise.library.library.author.AuthorService;
import excercise.library.library.author.model.Author;
import excercise.library.library.book.model.Book;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookService {
  private final BookRepository bookRepository;
  private final AuthorService authorService;

  public List<Book> getAll() {
    return this.bookRepository.findAll();
  }

  public Book getOneById(Long id) {
    return this.bookRepository.findById(id).orElseThrow(BookNotFoundException::new);
  }

  public Book create(Book newBook) {
    Author existingAuthor = this.authorService.getOneById(newBook.getAuthor().getId());
    newBook.setAuthor(existingAuthor);
    existingAuthor.getBooks().add(newBook);
    return this.bookRepository.save(newBook);
  }

  public Book updateById(Book book) {
    Book existingBook = this.getOneById(book.getId());
    Author existingAuthor = this.authorService.getOneById(book.getAuthor().getId());
    book.setAuthor(existingAuthor);
    book.setId(existingBook.getId());
    return this.bookRepository.save(book);
  }

  public void deleteById(Long id) {
    Book exisBook = this.getOneById(id);
    this.bookRepository.deleteById(exisBook.getId());
  }

}
