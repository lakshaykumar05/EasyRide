package rent.project.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rent.project.Exception.AdminException;
import rent.project.Model.Admin;
import rent.project.Model.CurrentAdminSession;
import rent.project.Model.Scooter;
import rent.project.Repository.AdminRepository;
import rent.project.Repository.ScooterRepository;
import rent.project.Repository.CurrentAdminSessionRepository;

@Service
public class AdminService {

    // @Autowired
    // ScooterRepository adminRepository;

    @Autowired
    CurrentAdminSessionRepository currentAdminSessionRepository;

    @Autowired
    AdminRepository adminLoginRepository;

    @Autowired
    ScooterRepository scooterRepository;

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

            scooter.setAdminId(admin.getAdminId());

            scooterr = scooterRepository.save(scooter);
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

            Optional<Scooter> scooter2 = scooterRepository.findById(scooterId);

            if(scooter2.isEmpty())
                throw new NoSuchElementException("Scooter not found, so cant delete it");

            int loggedAdmin_admin_id = currentAdminSession.getAdminID();

            int scooter_admin_id = scooter2.get().getAdminId();

            System.out.println("loggedAdmin_admin_id " + loggedAdmin_admin_id);

            System.out.println("scooter_admin_id " + scooter_admin_id);

            if(loggedAdmin_admin_id != scooter_admin_id)
                throw new NoSuchElementException("Scooter does not match with user.");

            scooter = scooterRepository.findById(scooterId).get();
            scooterRepository.deleteById(scooterId);
        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }
        return scooter;
    }
    
}
