package excercise.library.library.author;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import excercise.library.library.author.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {

  Page<Author> findAll(Pageable pageable);

  Page<Author> findAllByFirstNameIgnoreCaseContainingOrLastNameIgnoreCaseContaining(String optionalQ1,
      String optionalQ2,
      Pageable pageable);

}
