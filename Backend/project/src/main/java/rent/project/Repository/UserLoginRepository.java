package rent.project.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import rent.project.Model.User;

@Repository
public interface UserLoginRepository extends CrudRepository<User, Integer> {
    User findByEmail(String email);
}
