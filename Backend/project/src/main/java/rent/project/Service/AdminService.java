package rent.project.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rent.project.Exception.AdminException;
import rent.project.Model.CurrentAdminSession;
import rent.project.Model.Scooter;
import rent.project.Repository.AdminRepository;
import rent.project.Repository.CurrentAdminSessionRepository;

@Service
public class AdminService {

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    CurrentAdminSessionRepository currentAdminSessionRepository;

    public Scooter addScooter(Scooter scooter, String key)
    {
        Scooter scooterr = null;
        try {
            CurrentAdminSession currentAdminSession = currentAdminSessionRepository.findByaid(key);

            if(currentAdminSession == null)
                throw new AdminException("Admin not authenticated");

            scooterr = adminRepository.save(scooter);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        // System.out.println("scooter is " + scooterr);
        return scooterr;
    }

    public Scooter deleteScooter(int scooterId, String key)
    {
        Scooter scooter = null;
        try {

            CurrentAdminSession currentAdminSession = currentAdminSessionRepository.findByaid(key);

            if(currentAdminSession == null)
                throw new AdminException("Admin not authenticated");

            scooter = adminRepository.findById(scooterId).get();
            adminRepository.deleteById(scooterId);
        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }
        return scooter;
    }
    
}
