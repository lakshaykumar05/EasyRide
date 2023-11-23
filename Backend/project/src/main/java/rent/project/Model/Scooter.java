package rent.project.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
// import org.springframework.data.annotation.Id;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Scooter {

    public enum ScooterStatus {
        AVAILABLE,
        BOOKED
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int scooterId;
    private String name;
    private ScooterStatus scooterStatus;
    private int pricePerHour;
    private int penaltyPerHour;

    // @OneToOne
    private int adminId;

    public int getAdminId() {
        return adminId;
    }
    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }
    public int getScooterId() {
        return scooterId;
    }
    public void setScooterId(int scooterId) {
        this.scooterId = scooterId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public ScooterStatus getScooterStatus() {
        return scooterStatus;
    }
    public void setScooterStatus(ScooterStatus scooterStatus) {
        this.scooterStatus = scooterStatus;
    }
    public int getPricePerHour() {
        return pricePerHour;
    }
    public void setPricePerHour(int pricePerHour) {
        this.pricePerHour = pricePerHour;
    }
    public int getPenaltyPerHour() {
        return penaltyPerHour;
    }
    public void setPenaltyPerHour(int penaltyPerHour) {
        this.penaltyPerHour = penaltyPerHour;
    }
    @Override
    public String toString() {
        return "Scooter [scooterId=" + scooterId + ", name=" + name + ", scooterStatus=" + scooterStatus
                + ", pricePerHour=" + pricePerHour + ", penaltyPerHour=" + penaltyPerHour + "]";
    }
}
