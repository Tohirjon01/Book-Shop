package uz.bookshop.repository;//package uz.bookshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.bookshop.model.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    Users findByUserName(String username);
}
