package rent.project.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rent.project.Model.Scooter;
import rent.project.Repository.AdminRepository;

@Service
public class AdminService {

    @Autowired
    AdminRepository adminRepository;

    public Scooter addScooter(Scooter scooter)
    {
        Scooter scooterr = null;
        try {
            scooterr = adminRepository.save(scooter);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        // System.out.println("scooter is " + scooterr);
        return scooterr;
    }

    public Scooter deleteScooter(int scooterId)
    {
        Scooter scooter = null;
        try {
            scooter = adminRepository.findById(scooterId).get();
            adminRepository.deleteById(scooterId);
        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }
        return scooter;
    }
    
}
