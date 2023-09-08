package rent.project.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rent.project.Model.Scooter;
import rent.project.Repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    UserRepository userRepository;

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
