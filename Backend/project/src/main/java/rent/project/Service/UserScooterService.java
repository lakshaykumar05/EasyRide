package rent.project.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import rent.project.Model.Scooter;
import rent.project.Repository.ScooterRepository;

@Service
public class UserScooterService {
    
    @Autowired
    ScooterRepository userRepository;

    public List<Scooter> getScooters()
    {
        List<Scooter> scooters = new ArrayList<>();

        try {
            scooters = (List < Scooter >) userRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return scooters;
    }

    public Scooter getScooter(int scooterId)
    {
        Scooter scooter = null;

        try {   
            scooter = userRepository.findById(scooterId).get();
        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }

        return scooter;
    }

}
