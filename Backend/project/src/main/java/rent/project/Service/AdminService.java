package rent.project.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rent.project.Exception.AdminException;
import rent.project.Model.Admin;
import rent.project.Model.CurrentAdminSession;
import rent.project.Model.Scooter;
import rent.project.Repository.AdminLoginRepository;
import rent.project.Repository.AdminRepository;
import rent.project.Repository.CurrentAdminSessionRepository;

@Service
public class AdminService {

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    CurrentAdminSessionRepository currentAdminSessionRepository;

    @Autowired
    AdminLoginRepository adminLoginRepository;

    public Scooter addScooter(Scooter scooter, String key)
    {
        Scooter scooterr = null;
        try {
            System.out.println(key);
            // key.replace(' ', '');
            key = key.replaceAll("\\s", "");

            System.out.println(key);

            CurrentAdminSession currentAdminSession = currentAdminSessionRepository.findByaid(key);

            System.out.println(currentAdminSession);

            if(currentAdminSession == null)
                throw new AdminException("Admin not authenticated");

            Admin admin = adminLoginRepository.findById(currentAdminSession.getAdminID()).get();

            scooter.setAdminId(admin);

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
