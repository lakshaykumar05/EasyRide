package rent.project.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import rent.project.Model.CurrentUserSession;
import rent.project.Model.User;
import rent.project.Service.UserLoginService;

@RestController
public class UserLoginController {

    @Autowired
    UserLoginService userLoginService;
    
    @PostMapping("user/signup")
    ResponseEntity<User> signUpUser(@RequestBody User user)
    {
        return new ResponseEntity<>(userLoginService.signUpUser(user), null, HttpStatus.CREATED);
    }

    @PostMapping("user/login")
    ResponseEntity<CurrentUserSession> loginUser(@RequestBody User user) // 
    {
        return new ResponseEntity<>(userLoginService.loginUser(user), null, HttpStatus.CREATED);
    }

    @PostMapping("user/logout")
    ResponseEntity<String> logoutUser(String key)
    {
        return new ResponseEntity<>(userLoginService.logoutUser(key), null, HttpStatus.CREATED);
    }
}
