package rent.project.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import rent.project.Model.Rent;
import rent.project.Service.RentService;

@RestController
public class RentController {
    
    @Autowired
    RentService rentService;

    @PostMapping("/rent/scooter/{scooterId}")
    public ResponseEntity<Rent> createRent(@PathVariable("scooterId") int scooterId)
    {
        return new ResponseEntity<>(rentService.saveRent(scooterId), HttpStatus.CREATED);
    }

    @PostMapping("/unrent/scooter/{rentId}")
    public ResponseEntity<Rent> unActiveRent(@PathVariable("rentId") int rentId)
    {
        return new ResponseEntity<Rent>(rentService.unActiveRent(rentId), HttpStatus.OK);
    }

}
