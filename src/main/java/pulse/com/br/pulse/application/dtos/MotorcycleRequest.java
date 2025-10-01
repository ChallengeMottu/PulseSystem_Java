package pulse.com.br.pulse.application.dtos;

import jakarta.validation.constraints.*;
import pulse.com.br.pulse.domainmodel.enums.MotorcycleModel;
import pulse.com.br.pulse.domainmodel.enums.OperationStatus;

public class MotorcycleRequest {

    private Long id;

    @NotBlank(message = "Placa é obrigatória")
    @Size(min = 7, max = 8, message = "Placa deve ter entre 7 e 8 caracteres")
    private String licensePlate;

    @NotNull(message = "Modelo é obrigatório")
    private MotorcycleModel model;

    @NotBlank(message = "Número do chassi é obrigatório")
    @Size(min = 17, max = 17, message = "Número do chassi deve ter 17 caracteres")
    private String chassisNumber;

    @NotNull(message = "Status operacional é obrigatório")
    private OperationStatus operationalStatus;

    @NotNull(message = "Condição mecânica é obrigatória")
    private String mechanicalCondition;

    private Long parkingId;

    // Getters e setters


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

    public Long getParkingId() {
        return parkingId;
    }

    public void setParkingId(Long parkingId) {
        this.parkingId = parkingId;
    }
}
