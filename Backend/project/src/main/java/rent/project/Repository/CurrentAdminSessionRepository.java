package rent.project.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import rent.project.Model.CurrentAdminSession;

@Repository
public interface CurrentAdminSessionRepository extends CrudRepository<CurrentAdminSession, Integer> {
    @Query("select c from CurrentAdminSession c where c.aid=?1")
    public CurrentAdminSession findByaid(String aid);
}
