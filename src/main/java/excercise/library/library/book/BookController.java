package excercise.library.library.book;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import excercise.library.library.book.model.Book;
import excercise.library.library.book.model.dto.BookRequestDTO;
import excercise.library.library.book.model.dto.BookResponseDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BookController {
  private final BookService bookService;

  @GetMapping("/books")
  public ResponseEntity<Page<BookResponseDTO>> getAll(
      @RequestParam(value = "q", required = false) Optional<String> optionalQ,
      @RequestParam(value = "field", defaultValue = "title", required = false) String fieldName,
      @RequestParam(value = "order", defaultValue = "asc", required = false) String order,
      @RequestParam(value = "page", defaultValue = "1", required = false) String pageString,
      @RequestParam(value = "size", defaultValue = "5", required = false) String sizeString) {
    int page = Integer.parseInt(pageString) - 1;
    int size = Integer.parseInt(sizeString);
    Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(order), fieldName));
    Page<Book> books = this.bookService.getAll(optionalQ, pageable);
    Page<BookResponseDTO> bookResponseDTOs = books.map(Book::convertToResponse);
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
