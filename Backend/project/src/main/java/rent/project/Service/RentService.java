package rent.project.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rent.project.Model.Rent;
import rent.project.Model.Scooter;
import rent.project.Repository.AdminRepository;
import rent.project.Repository.RentRepository;

@Service
public class RentService {

    @Autowired
    RentRepository rentRepository;

    @Autowired
    AdminRepository adminRepository;
    
    public Rent saveRent(int scooterId)
    {
        Rent rent = new Rent();
        Scooter scooter = null;

        try {

            Optional<Scooter> optional = adminRepository.findById(scooterId);

            if(optional.isEmpty())
                throw new NoSuchElementException("Scooter not found");

            scooter = adminRepository.findById(scooterId).get();

            if(scooter.getScooterStatus() == Scooter.ScooterStatus.BOOKED)
                throw new NoSuchElementException("Scooter is already booked");

            scooter.setScooterStatus(Scooter.ScooterStatus.BOOKED);

            rent.setScooterId(scooter);
            rent.setRentStatus(Rent.RentStatus.ACTIVE);
            rent.setDurationInHours(9);
            rent.setRentPrice(9 * scooter.getPricePerHour());
            rent.setTime(LocalDateTime.now());

            rent = rentRepository.save(rent);
            
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }

        return rent;
    }
}
