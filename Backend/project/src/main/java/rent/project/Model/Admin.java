package rent.project.Model;

import java.util.ArrayList;
import java.util.List;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Admin {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int adminId;

    @NonNull
    private String name;

    @NonNull
    private String email;

    @NonNull
    private String password;

    // private List < Scooter > addedScooters = new ArrayList<>();

    // private List < Scooter > deletedScooters = new ArrayList<>();
}
