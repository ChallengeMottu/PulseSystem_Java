package pulse.com.br.pulse.infraestructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pulse.com.br.pulse.domainmodel.entities.Motorcycle;
import pulse.com.br.pulse.domainmodel.enums.MotorcycleModel;
import pulse.com.br.pulse.domainmodel.enums.OperationStatus;

import java.util.List;

public interface MotorcycleRepository extends JpaRepository<Motorcycle, Long> {
    Motorcycle findByLicensePlate(String licensePlate);
    List<Motorcycle> findByParkingId(Long parkingId);
    List<Motorcycle> findByOperationalStatusAndParkingId(OperationStatus status, Long parkingId);
    long countByParkingId(Long parkingId);
}
