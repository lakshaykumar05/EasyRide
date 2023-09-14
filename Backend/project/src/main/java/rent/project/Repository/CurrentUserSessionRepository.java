package rent.project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import rent.project.Model.CurrentUserSession;

@Repository
public interface CurrentUserSessionRepository extends JpaRepository<CurrentUserSession, Integer> {
    @Query("select c from CurrentUserSession c where c.aid=?1")
    public CurrentUserSession findByaid(String aid);
}
