package uz.bookshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.bookshop.model.Books;

@Repository
public interface BookRepository extends JpaRepository<Books, Long> {

}
