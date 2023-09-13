package rent.project.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rent.project.Model.Admin;
import rent.project.Model.CurrentAdminSession;
import rent.project.Service.AdminLoginService;

@RestController
public class AdminLoginController {

    @Autowired
    AdminLoginService adminLoginService;
    
    @PostMapping("/admin/signup")
    ResponseEntity<Admin> signUpAdmin(@RequestBody Admin admin)
    {
        return new ResponseEntity<Admin>(adminLoginService.signUpAdmin(admin), null, HttpStatus.CREATED);
    }

    @PostMapping("/admin/login")
    ResponseEntity<CurrentAdminSession> loginAdmin(@RequestBody Admin adminData)
    {
        return new ResponseEntity<CurrentAdminSession>(adminLoginService.loginAdmin(adminData.getEmail(), adminData.getPassword()), HttpStatus.ACCEPTED);
    }

    @PostMapping("/admin/logout")
    ResponseEntity<String> logoutAdmin(@RequestParam String key)
    {
        return new ResponseEntity<String>(adminLoginService.logoutAdmin(key), HttpStatus.OK);
    }
}
