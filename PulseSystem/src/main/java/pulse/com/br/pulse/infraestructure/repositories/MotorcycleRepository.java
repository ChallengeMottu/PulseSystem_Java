package pulse.com.br.pulse.infraestructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pulse.com.br.pulse.domainmodel.entities.Motorcycle;
import pulse.com.br.pulse.domainmodel.enums.MotorcycleModel;
import pulse.com.br.pulse.domainmodel.enums.OperationStatus;

import java.util.List;

public interface MotorcycleRepository extends JpaRepository<Motorcycle, Long> {
    Motorcycle findByLicensePlate(String licensePlate);
    List<Motorcycle> findByParking_Id(Long parkingId);
    List<Motorcycle> findByOperationalStatusAndParking_Id(OperationStatus status, Long parkingId);
    long countByParking_Id(Long parkingId);
}
