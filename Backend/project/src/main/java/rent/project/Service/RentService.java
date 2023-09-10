package rent.project.Service;

import java.nio.channels.AlreadyBoundException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
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

    public Rent unActiveRent(int rentId)
    {
        Rent rent = new Rent();

        try {
            Optional < Rent > optRent = rentRepository.findById(rentId);

            if(optRent.isEmpty())
                throw new NoSuchElementException("Rent not found");

            rent = optRent.get();

            if(rent.getRentStatus() == Rent.RentStatus.COMPLETED)
                throw new NoSuchElementException("This rent has already been completed");

            rent.setRentStatus(Rent.RentStatus.COMPLETED);

            Scooter scooter = adminRepository.findById(rent.getScooterId().getScooterId()).get();

            scooter.setScooterStatus(Scooter.ScooterStatus.AVAILABLE);

            LocalDateTime oldTime = rent.getTime();
            LocalDateTime rentCompletionTime = oldTime.plus(rent.getDurationInHours(), ChronoUnit.HOURS);
            LocalDateTime currentTime = LocalDateTime.now();

            if(currentTime.isAfter(rentCompletionTime))
            {
                System.out.println("You have to pay extra");
            }

            System.out.println("done all things in unactive rent");

            scooter = adminRepository.save(scooter);
            rent = rentRepository.save(rent);

        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }

        return rent;
    }
}