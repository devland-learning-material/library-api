package excercise.library.library.author;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import excercise.library.library.author.model.Author;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthorService {
  private final AuthorRepository authorRepository;

  public Page<Author> getAll(
    Optional<String> optionalQ,
    Pageable pageable
  ) {
    if(!optionalQ.isPresent()){
      return this.authorRepository.findAll(pageable);
    }

    return this.authorRepository.findAllByFirstNameIgnoreCaseContainingOrLastNameIgnoreCaseContaining(optionalQ.get(), optionalQ.get(), pageable);
    
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
