package rent.project.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rent.project.Exception.AdminException;
import rent.project.Model.Admin;
import rent.project.Model.CurrentAdminSession;
import rent.project.Model.Scooter;
import rent.project.Repository.AdminLoginRepository;
import rent.project.Repository.CurrentAdminSessionRepository;

@Service
public class AdminLoginService {

    @Autowired
    AdminLoginRepository adminLoginRepository;

    @Autowired
    CurrentAdminSessionRepository currentAdminSessionRepository;

    public Admin signUpAdmin(Admin admin)
    {
        Admin admin2 = new Admin();

        try {

            List<Admin> isAdminAlreadyPresent = adminLoginRepository.findByEmail(admin.getEmail());

            if(!isAdminAlreadyPresent.isEmpty())
                throw new AdminException("Cannot sign up, Signed Up already");

            admin2.setEmail(admin.getEmail());
            admin2.setName(admin.getName());
            admin2.setPassword(admin.getPassword());
            List<Scooter> scooters = new ArrayList<>();
            admin2.setScooters(scooters);

            admin2 = adminLoginRepository.save(admin2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return admin2;
    }

    public CurrentAdminSession loginAdmin(String email, String password)
    {
        CurrentAdminSession currentAdminSession2 = new CurrentAdminSession();

        try {
            List < Admin > adminLst = adminLoginRepository.findByEmail(email);

            // System.out.println(email+ " "+password);

            // System.out.println(adminLst.size());

            if(adminLst.isEmpty())
                throw new AdminException("Email not found!!");

            Admin admin = adminLst.get(0);

            Optional<CurrentAdminSession> currentAdminSession = currentAdminSessionRepository.findById(admin.getAdminId());

            if(currentAdminSession.isPresent())
                throw new AdminException("Admin Already logged In");

            if(admin.getPassword().equals(password))
            {
                SecureRandom secureRandom = new SecureRandom();
                byte[] keyBytes = new byte[10];
                secureRandom.nextBytes(keyBytes);

                String key = Base64.getEncoder().encodeToString(keyBytes);

                currentAdminSession2.setAdminID(admin.getAdminId());
                currentAdminSession2.setTime(LocalDateTime.now());
                currentAdminSession2.setAid(key);

                currentAdminSession2 = currentAdminSessionRepository.save(currentAdminSession2);
            }
            else
                throw new AdminException("password wrong");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return currentAdminSession2;
    }

    public String logoutAdmin(String key)
    {
        try {
            CurrentAdminSession currentAdminSession = currentAdminSessionRepository.findByaid(key);

            if(currentAdminSession == null)
                throw new AdminException("not logged in");

            currentAdminSessionRepository.delete(currentAdminSession);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return "Admin Logged Out";
    }
}
