package uz.bookshop.repository;//package uz.bookshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.bookshop.model.Roles;
@Repository
public interface RoleRepository extends JpaRepository<Roles, Long> {

    Roles findByName(String name);
}
