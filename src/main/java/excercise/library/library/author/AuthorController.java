package excercise.library.library.author;

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
  public ResponseEntity<List<AuthorResponseDTO>> getAll() {
    List<Author> authors = this.authorService.getAll();
    List<AuthorResponseDTO> authorResponseDTOs = authors.stream().map(Author::convertToResponse).toList();
    return ResponseEntity.ok(authorResponseDTOs);
  }

  @GetMapping("/authors/{id}")
  public ResponseEntity<AuthorResponseDTO> getOneById(@PathVariable Long id){
    Author author = this.authorService.getOneById(id);
    return ResponseEntity.ok(author.convertToResponse());
  }

  @PostMapping("/authors")
  public ResponseEntity<AuthorResponseDTO> create(@Valid @RequestBody AuthorRequestDTO authorRequestDTO){
    Author newAuthor = authorRequestDTO.convertToEntity();
    Author savedAuthor = this.authorService.create(newAuthor);
    return ResponseEntity.status(HttpStatus.CREATED).body(savedAuthor.convertToResponse());
  }

  @PutMapping("/authors/{id}")
  public ResponseEntity<AuthorResponseDTO> updateById(@PathVariable Long id, @Valid @RequestBody AuthorRequestDTO authorRequestDTO){
    Author author = authorRequestDTO.convertToEntity();
    author.setId(id);
    Author updatedAuthor = this.authorService.updateById(author);
    return ResponseEntity.ok(updatedAuthor.convertToResponse());
  }

  @DeleteMapping("/authors/{id}")
  public ResponseEntity<Map<String,String>> deleteById(@PathVariable Long id){
    this.authorService.deleteById(id);
    Map<String, String> deleteMessage = new HashMap<>();
    deleteMessage.put("status", "success");
    deleteMessage.put("message", "Success delete author");
    return ResponseEntity.ok(deleteMessage);
  }
}
