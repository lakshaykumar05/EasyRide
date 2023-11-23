package rent.project.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rent.project.Model.Rent;
import rent.project.Service.RentService;

@RestController
public class RentController {
    
    @Autowired
    RentService rentService;

    @PostMapping("/rent/scooter/{scooterId}")
    public ResponseEntity<Rent> createRent(@PathVariable("scooterId") int scooterId, @RequestParam String key)
    {
        return new ResponseEntity<>(rentService.saveRent(scooterId, key), HttpStatus.CREATED);
    }

    @PostMapping("/unrent/{rentId}")
    public ResponseEntity<Rent> unActiveRent(@PathVariable("rentId") int rentId, @RequestParam String key)
    {
        return new ResponseEntity<Rent>(rentService.unActiveRent(rentId, key), HttpStatus.OK);
    }

}
