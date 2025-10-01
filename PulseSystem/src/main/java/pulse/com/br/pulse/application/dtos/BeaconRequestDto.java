package pulse.com.br.pulse.application.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import pulse.com.br.pulse.domainmodel.enums.StatusBeacon;


public class BeaconRequestDto {

    @NotNull(message = "Código UUID é obrigatório")
    @Pattern(regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$",
            message = "Código UUID inválido")
    private String beaconCode; // Troquei para String para facilitar validação via regex

    @NotNull(message = "Status do beacon é obrigatório")
    private StatusBeacon beaconStatus;

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
}
