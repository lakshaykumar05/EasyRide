package rent.project.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rent.project.Exception.UserException;
import rent.project.Model.CurrentUserSession;
import rent.project.Model.User;
import rent.project.Repository.CurrentUserSessionRepository;
import rent.project.Repository.UserRepository;

@Service
public class UserLoginService {

    @Autowired
    UserRepository userLoginRepository;

    @Autowired
    CurrentUserSessionRepository currentUserSessionRepository;

    public User signUpUser(User user)
    {
        User user2 = new User();

        try {
            user2 = userLoginRepository.findByEmail(user.getEmail());

            if(user2 != null)
                throw new UserException("User is already present with this email");

            user2 = userLoginRepository.save(user);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return user2;
    }

    public CurrentUserSession loginUser(User user)
    {
        CurrentUserSession currentUserSession2 = new CurrentUserSession();

        try {

            User user2 = new User();

            user2 = userLoginRepository.findByEmail(user.getEmail());

            if(user2 == null)
                throw new UserException("User is not present with this email");
            
            Optional < CurrentUserSession > currentUserSession = currentUserSessionRepository.findById(user2.getUserId());

            if(currentUserSession.isPresent())
                throw new UserException("User already logged in");

            if(user2.getPassword().equals(user.getPassword()))
            {
                SecureRandom secureRandom = new SecureRandom();
                byte[] keyBytes = new byte[10];
                secureRandom.nextBytes(keyBytes);

                String key = Base64.getEncoder().encodeToString(keyBytes);

                currentUserSession2.setUid(key);
                currentUserSession2.setTime(LocalDateTime.now());
                currentUserSession2.setUserId(user2.getUserId());

                currentUserSession2 = currentUserSessionRepository.save(currentUserSession2);
            }
            else
                throw new UserException("Password wrong");
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return currentUserSession2;
    }

    public String logoutUser(String key)
    {
        try {

            CurrentUserSession currentUserSession = currentUserSessionRepository.findByuid(key);

            if(currentUserSession == null)
                throw new UserException("Not logged in");

            currentUserSessionRepository.delete(currentUserSession);
            
            return "User Logged out";
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return "Error occured";
    }
    
}
