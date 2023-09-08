package rent.project.Controller;

// import org.hibernate.mapping.List;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import rent.project.Model.Scooter;
import rent.project.Service.UserService;

@RestController
public class UserController {

    @Autowired
    UserService userService;
    
    @GetMapping("/scooters")
    public ResponseEntity<List<Scooter>> getAllScooters()
    {
        return new ResponseEntity<>(userService.getScooters(), HttpStatus.OK);
    }

    @GetMapping("/scooters/{scooterId}")
    public ResponseEntity<Scooter> getScooter(@PathVariable("scooterId") int scooterId)
    {
        return new ResponseEntity<>(userService.getScooter(scooterId), HttpStatus.OK);
    }



}
