package rent.project.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rent.project.Model.Admin;


@Repository
public interface AdminLoginRepository extends JpaRepository<Admin, Integer> {
    List<Admin> findByEmail(String email);
}
