package pulse.com.br.pulse.infraestructure.repositories;

import jdk.dynalink.linker.LinkerServices;
import org.springframework.data.jpa.repository.JpaRepository;
import pulse.com.br.pulse.domainmodel.entities.Beacon;
import pulse.com.br.pulse.domainmodel.enums.StatusBeacon;

import java.util.List;
import java.util.UUID;

public interface BeaconRepository extends JpaRepository<Beacon, Long> {
    Beacon findByBeaconCode(String beaconCode);
    List<Beacon> findByBeaconStatus(StatusBeacon status);


}
