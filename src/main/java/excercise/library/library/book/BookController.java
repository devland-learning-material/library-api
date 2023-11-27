package excercise.library.library.book;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import excercise.library.library.book.model.Book;
import excercise.library.library.book.model.dto.BookRequestDTO;
import excercise.library.library.book.model.dto.BookResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BookController {
  private final BookService bookService;

  @GetMapping("/books")
  public ResponseEntity<List<BookResponseDTO>> getAll() {
    List<Book> books = this.bookService.getAll();
    List<BookResponseDTO> bookResponseDTOs = books.stream().map(Book::convertToResponse).toList();
    return ResponseEntity.ok(bookResponseDTOs);
  }

  @GetMapping("/books/{id}")
  public ResponseEntity<BookResponseDTO> getOneById(@PathVariable Long id) {
    Book book = this.bookService.getOneById(id);
    return ResponseEntity.ok(book.convertToResponse());
  }

  @PostMapping("/books")
  public ResponseEntity<BookResponseDTO> create(@Valid @RequestBody BookRequestDTO bookRequestDTO) {
    Book newBook = bookRequestDTO.convertToEntity();
    Book savedBook = this.bookService.create(newBook);
    return ResponseEntity.status(HttpStatus.CREATED).body(savedBook.convertToResponse());
  }

  @PutMapping("/books/{id}")
  public ResponseEntity<BookResponseDTO> updateById(@PathVariable Long id,
      @Valid @RequestBody BookRequestDTO bookRequestDTO) {
    Book book = bookRequestDTO.convertToEntity();
    book.setId(id);
    Book updatedBook = this.bookService.updateById(book);
    return ResponseEntity.ok(updatedBook.convertToResponse());
  }

  @DeleteMapping("/books/{id}")
  public ResponseEntity<Map<String, String>> deleteById(@PathVariable Long id) {
    this.bookService.deleteById(id);
    Map<String, String> deleteMessage = new HashMap<>();
    deleteMessage.put("status", "success");
    deleteMessage.put("message", "Success delete book");
    return ResponseEntity.ok(deleteMessage);
  }
}
