package rent.project.Model;

import java.util.ArrayList;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;

    @NonNull
    private String name;

    @NonNull
    private String email;

    @NonNull
    private String password;

    @NonNull
    // @Size(min = 4, max=34)
    private String mobile;

    // private List < Booking > bookings = new ArrayList<>();
}
