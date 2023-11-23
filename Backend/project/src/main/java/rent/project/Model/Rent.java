package rent.project.Model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;


@Entity
public class Rent {

    public enum RentStatus {
        ACTIVE,
        COMPLETED
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToOne
    private Scooter scooter;

    int userId;

    private RentStatus rentStatus;
    private int durationInHours;
    private int rentPrice;
    private LocalDateTime time;

    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    
    public int getId() {
        return id;
    }
    @Override
    public String toString() {
        return "Rent [id=" + id + ", scooter=" + scooter + ", rentStatus=" + rentStatus + ", durationInHours="
                + durationInHours + ", rentPrice=" + rentPrice + ", time=" + time + "]";
    }
    public void setId(int id) {
        this.id = id;
    }
    public Scooter getscooter() {
        return scooter;
    }
    public void setscooter(Scooter scooter) {
        this.scooter = scooter;
    }
    public Rent() {
    }
    public Rent(int id, Scooter scooter, RentStatus rentStatus, int durationInHours, int rentPrice,
            LocalDateTime time) {
        this.id = id;
        this.scooter = scooter;
        this.rentStatus = rentStatus;
        this.durationInHours = durationInHours;
        this.rentPrice = rentPrice;
        this.time = time;
    }
    public RentStatus getRentStatus() {
        return rentStatus;
    }
    public void setRentStatus(RentStatus rentStatus) {
        this.rentStatus = rentStatus;
    }
    public int getDurationInHours() {
        return durationInHours;
    }
    public void setDurationInHours(int durationInHours) {
        this.durationInHours = durationInHours;
    }
    public int getRentPrice() {
        return rentPrice;
    }
    public void setRentPrice(int rentPrice) {
        this.rentPrice = rentPrice;
    }
    public LocalDateTime getTime() {
        return time;
    }
    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
