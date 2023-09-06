package rent.project.Model;

// import org.springframework.data.annotation.Id;
import jakarta.persistence.Id;

import jakarta.persistence.Entity;

@Entity
public class Scooter {
    
    @Id
    private String scooterId;
    private String name;
    private int securityDeposit;
    // private Status status;
    private int pricePerHour;
    private int penaltyPerHour;

}
