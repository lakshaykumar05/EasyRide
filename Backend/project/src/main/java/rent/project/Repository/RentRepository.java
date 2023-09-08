package rent.project.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import rent.project.Model.Rent;

@Repository
public interface RentRepository extends CrudRepository<Rent, Integer> {
    
}
