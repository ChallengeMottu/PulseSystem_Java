package pulse.com.br.pulse.domainmodel.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import pulse.com.br.pulse.application.exceptions.InvalidArgumentException;
import pulse.com.br.pulse.domainmodel.enums.MotorcycleModel;
import pulse.com.br.pulse.domainmodel.enums.OperationStatus;

@Entity
@Table(name = "motorcycles")
@NoArgsConstructor
@AllArgsConstructor
public class Motorcycle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String licensePlate;

    @Enumerated(EnumType.STRING)
    private MotorcycleModel model;

    private String chassisNumber;

    @Enumerated(EnumType.STRING)
    private OperationStatus operationalStatus;

    private String mechanicalCondition;

    @OneToOne(mappedBy = "motorcycle")
    private Beacon beacon;

    @Column(name = "parking_id", nullable = false)
    private Long parkingId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public MotorcycleModel getModel() {
        return model;
    }

    public void setModel(MotorcycleModel model) {
        this.model = model;
    }

    public String getChassisNumber() {
        return chassisNumber;
    }

    public void setChassisNumber(String chassisNumber) {
        this.chassisNumber = chassisNumber;
    }

    public OperationStatus getOperationalStatus() {
        return operationalStatus;
    }

    public void setOperationalStatus(OperationStatus operationalStatus) {
        this.operationalStatus = operationalStatus;
    }

    public String getMechanicalCondition() {
        return mechanicalCondition;
    }

    public void setMechanicalCondition(String mechanicalCondition) {
        this.mechanicalCondition = mechanicalCondition;
    }

    public Beacon getBeacon() {
        return beacon;
    }

    public void setBeacon(Beacon beacon) {
        this.beacon = beacon;
    }

    public Long getParkingId() {
        return parkingId;
    }

    public void setParkingId(Long parkingId) {
        this.parkingId = parkingId;
    }

    public void validate(){
        validateChassisNumber();
        validateLicensePlate();
    }

    private void validateChassisNumber(){
        if (chassisNumber == null || chassisNumber.length() != 17){
            throw new InvalidArgumentException("O número de chassi precisa ter 17 caracteres");
        }
    }

    private void validateLicensePlate(){
        if (licensePlate == null || !licensePlate.matches("^([A-Z]{3}[0-9]{4}|[A-Z]{3}[0-9][A-Z][0-9]{2})$")){
            throw new InvalidArgumentException("Placa inválida");
        }
    }
}
