package pulse.com.br.pulse.infraestructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pulse.com.br.pulse.domainmodel.entities.Parking;

public interface ParkingRepository extends JpaRepository<Parking, Long> {
    @Query("SELECT p.floorPlan FROM Parking p WHERE p.id = :id")
    String findFloorPlanById(@Param("id") long id);

    @Query("SELECT p.structurePlan FROM Parking p WHERE p.id = :id")
    String findStructurePlanById(@Param("id") long id);

    @Query("SELECT p.mapPlan FROM Parking p WHERE p.id = :id")
    String findMapPlanById(@Param("id") long id);
}
