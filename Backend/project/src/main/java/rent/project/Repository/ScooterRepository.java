package rent.project.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import rent.project.Model.Scooter;

@Repository
public interface ScooterRepository extends CrudRepository<Scooter, Integer> {
    
}
