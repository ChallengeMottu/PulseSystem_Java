package pulse.com.br.pulse.infraestructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pulse.com.br.pulse.domainmodel.entities.Parking;

public interface ParkingRepository extends JpaRepository<Parking, Long> {

}
