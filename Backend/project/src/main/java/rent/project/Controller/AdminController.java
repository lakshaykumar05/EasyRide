package rent.project.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import rent.project.Model.Scooter;
import rent.project.Service.AdminService;

@RestController
public class AdminController {

    @Autowired
    AdminService adminService;
    
    @PostMapping("/admin/scooter/add")
    public ResponseEntity<Scooter> addScooter(@RequestBody Scooter scooter)
    {
        Scooter scooterr = adminService.addScooter(scooter);
        System.out.println("Admin added scooter");
        return new ResponseEntity<>(scooterr, HttpStatus.CREATED);
    }


    @DeleteMapping("/admin/scooter/delete/{scooterId}")
    public ResponseEntity<Scooter> deleteScooter(@PathVariable("scooterId") int scooterId)
    {
        Scooter scooter = adminService.deleteScooter(scooterId);
        return new ResponseEntity<Scooter>(scooter, HttpStatus.OK);
    }
}
