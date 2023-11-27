package excercise.library.library.author;

import java.util.List;

import org.springframework.stereotype.Service;

import excercise.library.library.author.model.Author;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthorService {
  private final AuthorRepository authorRepository;

  public List<Author> getAll() {
    return this.authorRepository.findAll();
  }

  public Author getOneById(Long id) {
    return this.authorRepository.findById(id).orElseThrow(AuthorNotFoundException::new);
  }

  public Author create(Author newAuthor) {
    return this.authorRepository.save(newAuthor);
  }

  public Author updateById(Author author) {
    Author existingAuthor = this.getOneById(author.getId());
    author.setId(existingAuthor.getId());
    return this.authorRepository.save(author);
  }

  public void deleteById(Long id) {
    Author existingAuthor = this.getOneById(id);
    this.authorRepository.deleteById(existingAuthor.getId());
  }

}
