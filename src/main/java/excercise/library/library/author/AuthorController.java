package excercise.library.library.author;

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

import excercise.library.library.author.model.Author;
import excercise.library.library.author.model.dto.AuthorRequestDTO;
import excercise.library.library.author.model.dto.AuthorResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthorController {
  private final AuthorService authorService;

  @GetMapping("/authors")
  public ResponseEntity<Page<AuthorResponseDTO>> getAll(
      @RequestParam(value = "q", required = false) Optional<String> optionalQ,
      @RequestParam(value = "field", defaultValue = "firstName", required = false) String fieldName,
      @RequestParam(value = "order", defaultValue = "asc", required = false) String order,
      @RequestParam(value = "page", defaultValue = "1", required = false) String pageString,
      @RequestParam(value = "size", defaultValue = "5", required = false) String sizeString) {
    int page = Integer.parseInt(pageString) - 1;
    int size = Integer.parseInt(sizeString);
    Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(order), fieldName));
    Page<Author> authors = this.authorService.getAll(optionalQ, pageable);
    Page<AuthorResponseDTO> authorResponseDTOs = authors.map(Author::convertToResponse);
    return ResponseEntity.ok(authorResponseDTOs);
  }

  @GetMapping("/authors/{id}")
  public ResponseEntity<AuthorResponseDTO> getOneById(@PathVariable Long id) {
    Author author = this.authorService.getOneById(id);
    return ResponseEntity.ok(author.convertToResponse());
  }

  @PostMapping("/authors")
  public ResponseEntity<AuthorResponseDTO> create(@Valid @RequestBody AuthorRequestDTO authorRequestDTO) {
    Author newAuthor = authorRequestDTO.convertToEntity();
    Author savedAuthor = this.authorService.create(newAuthor);
    return ResponseEntity.status(HttpStatus.CREATED).body(savedAuthor.convertToResponse());
  }

  @PutMapping("/authors/{id}")
  public ResponseEntity<AuthorResponseDTO> updateById(@PathVariable Long id,
      @Valid @RequestBody AuthorRequestDTO authorRequestDTO) {
    Author author = authorRequestDTO.convertToEntity();
    author.setId(id);
    Author updatedAuthor = this.authorService.updateById(author);
    return ResponseEntity.ok(updatedAuthor.convertToResponse());
  }

  @DeleteMapping("/authors/{id}")
  public ResponseEntity<Map<String, String>> deleteById(@PathVariable Long id) {
    this.authorService.deleteById(id);
    Map<String, String> deleteMessage = new HashMap<>();
    deleteMessage.put("status", "success");
    deleteMessage.put("message", "Success delete author");
    return ResponseEntity.ok(deleteMessage);
  }
}
