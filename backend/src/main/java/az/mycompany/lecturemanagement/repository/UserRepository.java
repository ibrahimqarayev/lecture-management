package az.mycompany.lecturemanagement.repository;

import az.mycompany.lecturemanagement.entity.User;
import az.mycompany.lecturemanagement.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByIdentityNumber(String identityNumber);

    List<User> findAllByRole(Role role);

    List<User> findAllByRoleAndIdIsNotIn(Role role, List<Integer> idList);
}
