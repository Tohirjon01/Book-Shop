package uz.bookshop.model;

import jakarta.persistence.*;
import lombok.*;

import javax.management.relation.Role;
import java.io.Serializable;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "users")
public class Users implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String userName;
    private String phoneNumber;
    @ManyToOne
    private Roles roles;


}
