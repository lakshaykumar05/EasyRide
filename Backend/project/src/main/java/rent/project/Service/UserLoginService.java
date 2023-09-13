package rent.project.Service;

import org.springframework.stereotype.Service;

import rent.project.Exception.UserException;
import rent.project.Model.User;
import rent.project.Repository.UserLoginRepository;

@Service
public class UserLoginService {

    UserLoginRepository userLoginRepository;

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

    public void loginUser()
    {
        try {
            
        } catch (Exception e) {

        }
    }

    public void logoutUser()
    {
        try {
            
        } catch (Exception e) {

        }
    }
    
}
