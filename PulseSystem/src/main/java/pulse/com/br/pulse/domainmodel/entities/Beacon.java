package pulse.com.br.pulse.domainmodel.entities;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pulse.com.br.pulse.application.exceptions.InvalidArgumentException;
import pulse.com.br.pulse.domainmodel.enums.StatusBeacon;

import java.util.UUID;

@Entity
@Table(name = "beacons")
@NoArgsConstructor
@AllArgsConstructor
public class Beacon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "beacon_code")
    private String beaconCode;

    @Enumerated(EnumType.STRING)
    private StatusBeacon beaconStatus;

    @OneToOne
    @JoinColumn(name = "motorcycle_id")
    @JsonManagedReference
    private Motorcycle motorcycle;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBeaconCode() {
        return beaconCode;
    }

    public void setBeaconCode(String beaconCode) {
        this.beaconCode = beaconCode;
    }

    public StatusBeacon getBeaconStatus() {
        return beaconStatus;
    }

    public void setBeaconStatus(StatusBeacon beaconStatus) {
        this.beaconStatus = beaconStatus;
    }

    public Motorcycle getMotorcycle() {
        return motorcycle;
    }

    public void setMotorcycle(Motorcycle motorcycle) {
        this.motorcycle = motorcycle;
    }


    public void validated(){
        validatedBeaconCode();
    }

    private void validatedBeaconCode(){
        if (beaconCode == null || !beaconCode.matches("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$")){
            throw new InvalidArgumentException("Código UUID do beacon inválido");
        }
    }
}
