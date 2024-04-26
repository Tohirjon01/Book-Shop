package uz.bookshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.bookshop.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
